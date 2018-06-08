package teamZero.spinbattle.core;

import ggi.core.AbstractGameState;
import logger.sample.DefaultLogger;
import math.Vector2d;
import teamZero.spinbattle.actuator.Actuator;
import teamZero.spinbattle.params.Constants;
import teamZero.spinbattle.params.SpinBattleParams;

import java.util.ArrayList;

public class SpinGameState implements AbstractGameState {

    // this tracks all calls to the next method
    // useful for calculating overall stats

    public static int totalTicks = 0;
    public static int totalInstances = 0;

    public SpinGameState() {
        totalInstances++;
    }

    // number of ticks made by this instance
    public int nTicks;

    // may set a limit on the game length
    // this will be used in the isTerminal() method
    public SpinBattleParams params;

    public ArrayList<Planet> planets;
    public ProximityMap proximityMap;
    public VectorField vectorField;
    public DefaultLogger logger;
    static int nPlayers = 2;
    public Actuator[] actuators = new Actuator[nPlayers];

    public SpinGameState setLogger(DefaultLogger logger) {
        this.logger = logger;
        return this;
    }

    @Override
    public AbstractGameState copy() {
        SpinGameState copy = new SpinGameState();
        // just shallow-copy the params
        copy.params = params;
        copy.nTicks = nTicks;
        // deep copy the planets
        copy.planets = new ArrayList<>();
        for (Planet p : planets) {
            copy.planets.add(p.copy());
        }
        // actuators = new Actuator[nPlayers];
        for (int i=0; i<nPlayers; i++) {
            if (actuators[i] != null) copy.actuators[i] = actuators[i].copy();
        }
        // shallow copy the proximity map (which may even be null)
        copy.proximityMap = proximityMap;
        proximityMap = new ProximityMap().setPlanets(this); //@moveplanet
        copy.vectorField = vectorField;
        vectorField = new VectorField().setParams(params).setField(this); //@moveplanet

        // do NOT copy the logger - this is only used in the "real" game by default
        return copy;
    }

    @Override
    public AbstractGameState next(int[] actions) {
        for (int i=0; i<nPlayers; i++) {
            if (actuators[i] != null)
                actuators[i].actuate(actions[i], this);
        }
        for (Planet p : planets) {
            p.update(this);
        }
        nTicks++;
        totalTicks++;
        return this;
    }

    @Override
    public int nActions() {
        // this may depend on the actuator model, which could be different for each player
        // or could be different anyway for an asymmetric game

        // for now just do something very simple ...
        // but MUST be changed in future
        return planets.size();
    }

    @Override
    public double getScore() {
        if (params.clampZeroScore) return 0;

        double score = 0;
        for (Planet p : planets) {
            score += p.getScore();
        }
        // but it the game is over, add in an early completion bonus
        Integer singleOwner = singleOwner();
        if (singleOwner != null) {
            double tot = 0;
            for (Planet p : planets) tot += p.growthRate;
            double bonus = tot * (params.maxTicks - nTicks);
            // System.out.println("Awarding bonus: " + bonus);
            score += (singleOwner == Constants.playerOne) ? bonus : -bonus;
        }
        return score;
    }

    public double getPlayerShips(int playerId) {
        double ships = 0;
        for (Planet p : planets) {
            if (p.ownedBy == playerId) ships += p.shipCount;
        }
        return ships;
    }

    @Override
    public boolean isTerminal() {
        return nTicks > params.maxTicks || singleOwner() != null;
    }
    
    public boolean gameOver() {
    	return singleOwner() != null;
    }

    // if only one player owns planets then the game is over
    public Integer singleOwner() {
        boolean playerOne = false;
        boolean playerTwo = false;

        for (Planet p : planets) {
            playerOne |= p.ownedBy == Constants.playerOne;
            playerTwo |= p.ownedBy == Constants.playerTwo;
            if (playerOne && playerTwo) return null;
        }

        // System.out.println(playerOne + " : " + playerTwo);
        if (playerOne) return Constants.playerOne;
        if (playerTwo) return Constants.playerTwo;
        return null;
    }

    public SpinGameState setParams(SpinBattleParams params) {
        this.params = params;
        // set all the planet params also
        if (planets != null) {
            for (Planet p : planets) {
                p.setParams(params);
            }
        }
        return this;
    }

    static int maxTries = 200;

    public SpinGameState setPlanets() {
        planets = new ArrayList<>();
        int i=0;
        int whichEven = params.getRandom().nextInt(2);
        // int nToAllocate = params.nPlanets - params.nNeutral;
        while (planets.size() < params.nToAllocate) {
            int owner = (planets.size() % 2 == whichEven ? Constants.playerOne : Constants.playerTwo);
            Planet planet = makePlanet(owner);
            // System.out.println("Made planet for: " + owner + " ... size: " + planets.size());
            planet.growthRate = params.maxGrowth;
            if (valid(planet)) {
                planet.setIndex(planets.size());
                planets.add(planet);
                // System.out.println("Added planet for: " + owner);
            } else {
                // System.out.println("Failed to add planet for: " + owner);
            }
            // System.out.println();
        }
        // System.out.println("To allocate: " + nToAllocate + " : " + planets.size());

        // set the neutral ones
        while (planets.size() < params.nPlanets && i++ < maxTries) {
            Planet planet = makePlanet(Constants.neutralPlayer);
            if (valid(planet)) {
                planet.setIndex(planets.size());
                planets.add(planet);
            }
        }
        // System.out.println(planets);
        if (params.useProximityMap) {
            proximityMap = new ProximityMap().setPlanets(this);
        }
        if (params.useVectorField) {
            vectorField = new VectorField().setParams(params).setField(this);
            // System.out.println("Set VF: " + vectorField);
        }

        return this;
    }

    Planet makePlanet(int owner) {
        Planet planet =new Planet().setParams(params).
                setRandomLocation(params).setOwnership(Constants.neutralPlayer);
        planet.setRandomGrowthRate();
        planet.setShipMax();
        Vector2d adjustPosition = new Vector2d(params.getRandom().nextDouble()*(params.getRandom().nextInt(3)-1)*3, params.getRandom().nextDouble()*(params.getRandom().nextInt(3)-1)*3);
        planet.setMoveDirection(adjustPosition);
        planet.setOwnership(owner);
        return planet;
    }

    boolean valid(Planet p) {
        double minX = Math.min(p.position.x, params.width - p.position.x);
        double minY = Math.min(p.position.y, params.height - p.position.y);
        // test whether planet is too close to border
        if (Math.min(minX, minY) < p.getRadius() * params.radSep) {
            // System.out.println("Failed border sep:" + minX +  " : " + minY);
            return false;
        }

        // now check proximity to each of the existing ones

        for (Planet x : planets) {
            double sep = x.position.dist(p.position);
            if (sep < params.radSep * (x.getRadius() + p.getRadius())) {
                // System.out.println("Failed planet proximity: " + (int) sep);
                return false;
            }
        }
        return true;

    }

    public void notifyLaunch(Transporter transit) {
        if (logger != null) {
            // logger.logEvent(new LaunchEvent());
            // System.out.println(transit);
        }
    }

    public void notifySelection(Planet source) {
        if (logger != null) {
            // logger.logEvent(new SelectPlanetEvent());
            // System.out.println(source);
        }
    }
    // todo - set up the planets based on the params that have been passed
}
