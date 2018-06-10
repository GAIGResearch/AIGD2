package teamZero.spinbattle.core;

import levelgen.MarioReader;
import math.Vector2d;
import teamZero.spinbattle.params.Constants;
import teamZero.spinbattle.params.SpinBattleParams;
import teamZero.spinbattle.view.ParticleEffect;


public class Planet {
    public Vector2d position;
    public double rotation;
    public double rotationRate;
    public int index;

    public double growthRate;

    public double shipCount;
    public double shipMax;

    private Vector2d moveDirection;

    public int ownedBy;
    SpinBattleParams params;
    Transporter transit;

    public Planet copy() {
        Planet planet = new Planet();
        // shallow copy position on the assumption that it will not change
        planet.position = position;
        planet.rotation = rotation;
        planet.rotationRate = rotationRate;
        planet.growthRate = growthRate;
        planet.shipCount = shipCount;
        planet.shipMax = shipMax;
        planet.ownedBy = ownedBy;
        planet.params = params;
        planet.index = index;
        planet.moveDirection = moveDirection;
        if (transit !=  null)
            planet.transit = transit.copy();
        return planet;
    }

    Planet processIncoming(double incomingShips, int playerId, SpinGameState gameState) {
        if (ownedBy != playerId) {
            // this is an invasion
            // decrement the ships, then set to id of incoming player and invert sign if it has gone negative
            shipCount -= incomingShips;
            if (shipCount <= 0) {
                ownedBy = playerId;
                shipCount = Math.abs(shipCount);
                // and should make a transporter
                // transit = getTransporter();
                if (shipCount >= shipMax) {
                    shipCount = shipMax;
                }
            }
        } else {
            // must be owned by this player already, so add to the tally
            shipCount += incomingShips;
            if (shipCount >= shipMax) {
                shipCount = shipMax;
            }
        }
        return this;
    }

    // todo: Probably here the trajectory update and termination needs to be done
    public Planet update(SpinGameState gameState) {
        if (ownedBy != Constants.neutralPlayer) {
            shipCount += growthRate;
            if (shipCount >= shipMax) {
                shipCount = shipMax;
            }
        }
        if (transit != null && transit.inTransit()) {
            transit.next(gameState.vectorField);
            // check to see whether it has arrived and if so return the target
            Planet destination = params.getCollider().getPlanetInRange(gameState, transit);

            if (destination != null) {
                // process the inbound
                destination.processIncoming(transit.payload, transit.ownedBy, gameState);
                transit.terminateJourney();
                if (gameState.logger != null) {
                    ParticleEffect effect = new ParticleEffect().setPosition(destination.position);
                    effect.setNParticle(20);
                    // System.out.println(effect);
                    //gameState.logger.logEffect(effect);
                }
                // transit
                // System.out.println("Terminated Journey: " + transit.inTransit());
            }
        }
        rotation += rotationRate;
        movePlanet(params); //@moveplanets
        return this;
    }

    public Planet movePlanet(SpinBattleParams p) {
        Vector2d adjustPosition = new Vector2d(moveDirection.x, moveDirection.y);
        adjustPosition.x /= 5000;
        adjustPosition.y /= 5000;
    	position = position.add(adjustPosition);
    	if (position.x >= p.width) {
    		position.x = 1;
    	}
    	if (position.x <= 0) {
    		position.x = p.width - 1;
    	}
    	if (position.y >= p.height) {
    		position.y = 1;
    	}
    	if (position.y <= 0) {
    		position.y = p.height - 1;
    	}
    	return this;
    }

    public Planet setMoveDirection(Vector2d moveDirection) {
        this.moveDirection = moveDirection;
        return this;
    }
    
    public Planet setParams(SpinBattleParams params) {
        this.params = params;
        if (transit != null) {
            transit.setParams(params);
        }
        return this;
    }

    public Planet setIndex(int index) {
        this.index = index;
        return this;
    }

    Planet setRandomLocation(SpinBattleParams p) {
        position = new Vector2d(p.getRandom().nextDouble() * p.width, p.getRandom().nextDouble() * p.height);
        return this;
    }

    Planet setOwnership(int ownedBy) {
        this.ownedBy = ownedBy;
        // also set initial ships
        shipCount = params.minInitialShips +
                params.getRandom().nextInt(params.maxInitialShips - params.minInitialShips);
        return this;
    }

    public Planet setRandomGrowthRate() {
        growthRate = params.getRandom().nextDouble() * (params.maxGrowth - params.minGrowth) + params.minGrowth;
        // also set a random rotation rate
        rotationRate = params.spinRatio * (params.getRandom().nextDouble() + 1);
        if (params.getRandom().nextDouble() < 0.5) rotationRate = -rotationRate;
        rotationRate *= Math.PI * 2.0 / 100;
        return this;
    }

    public Planet setShipMax() {
        this.shipMax = this.growthRate * params.shipMaxConst;
        return this;
    }

    public int getRadius() {
        return (int) (Constants.growthRateToRadius * growthRate);
    }

    public String toString() {
        return position + " : " + ownedBy + " : " + getRadius();
    }

    public boolean transitReady() {
        return getTransporter() != null && !getTransporter().inTransit();
    }


    public Transporter getTransporter() {
        // neutral planets cannot release transporters
        if (ownedBy == Constants.neutralPlayer) return null;
        // if transit is null then make a new one
        if (transit == null)
            transit = new Transporter().setParent(index).setParams(params);
        return transit;
    }

    public int getScore() {
        if (ownedBy == Constants.neutralPlayer) return 0;
        int score = 0;
        if (ownedBy == Constants.playerOne) score = (int) shipCount;
        if (ownedBy == Constants.playerTwo) score = (int) -shipCount;
        return score;
    }

    public double mass() {
        return getRadius() * getRadius();
    }
}
