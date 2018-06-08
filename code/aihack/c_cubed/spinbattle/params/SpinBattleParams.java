package c_cubed.spinbattle.params;

import c_cubed.spinbattle.core.Collider;
import math.Vector2d;

import java.awt.*;
import java.util.Random;

public class SpinBattleParams {


    public static void main(String[] args) {
        // run a copy test
        SpinBattleParams params = new SpinBattleParams();
        SpinBattleParams alt = params.copy();

        // test should output true, false
        alt.width = 100;
        System.out.println(params.inBounds(new Vector2d(150, 150)));
        System.out.println(alt.inBounds(new Vector2d(150, 150)));
    }


    // of the arena
    public int width=800/*700*/, height=600/*400*/;

    public int maxTicks = 500;
    public int nPlanets = 20/*20*/;

    // public int nNeutral = nPlanets - 4;
    // the number to allocate to each player
    public int nToAllocate = 4;

    public double minGrowth =  0.04/*0.05*/;
    public double maxGrowth =  0.10/*0.15*/;


    public double spinRatio = 0.6/*0.4*/;

    public int minInitialShips = 5;

    public int maxInitialShips = 15 /*20*/;

    public double transitSpeed = 2/*3*/;


    // this is for a heuristic AI opponent
    public int releasePeriod = 400;

    public boolean clampZeroScore = false;

    public boolean useProximityMap = true;

    public boolean useVectorField = true;

    public double transportTax = 0.01 /*zero before*/;
    public double radSep = 1.2/*1.5*/;

    // upper limit for overall number of ships (NOTE: this works nicely IF the nToAllocate is at least 4. This is because
    // the threshold is calculated by adding the ships players are having and dividing it by 3. therefore if they're )
    public boolean upperLimit = true;

    //these are useful if the upperLimit is set to true:
    //this transfers the extra ships (if there are more than the upper limit) to one random neutral planet
    public boolean makeExtraShipsNeutral = false;
    //this transfers the extra ships (if there are more than the upper limit) to a random planet
    public boolean makeExtraShipsRandom =false;


    public SpinBattleParams copy() {
        SpinBattleParams p = new SpinBattleParams();
        p.width = width;
        p.height = height;
        p.maxTicks = maxTicks;
        p.nPlanets = nPlanets;
        p.nToAllocate = nToAllocate;
        p.minGrowth = minGrowth;
        p.maxGrowth = maxGrowth;
        p.spinRatio = spinRatio;
        p.minInitialShips = minInitialShips;
        p.maxInitialShips = maxInitialShips;
        p.transitSpeed = transitSpeed;
        p.releasePeriod = releasePeriod;
        p.useProximityMap = useProximityMap;
        p.useVectorField = useVectorField;
        p.gravitationalFieldConstant = gravitationalFieldConstant;
        p.gravitationalForceConstant = gravitationalForceConstant;
        p.clampZeroScore = clampZeroScore;
        p.transportTax = transportTax;
        p.radSep = radSep;

        p.upperLimit = upperLimit;
        p.makeExtraShipsNeutral = makeExtraShipsNeutral;
        p.makeExtraShipsRandom = makeExtraShipsRandom;
        return p;
    }

    // these could be collapsed in to a single parameter
    // for functional purposes
    // but adjusting both of them gives a clumsy way to control the display
    // of the field (which should really be done in the CaveView class
    // todo: collapse in to a single constant, and add a separate cosmetic param to control draw length of vectors
    public double gravitationalFieldConstant = 1.2;
    public double gravitationalForceConstant = 0.001;

    public static Random random = new Random();

    static Collider collider = new Collider();

    public Random getRandom() {
        return random;
    }

    public Collider getCollider() {
        return collider;
    }

    public boolean inBounds(Vector2d s) {
        return s.x >= 0 && s.x <= width && s.y >= 0 && s.y <= height;
    }

    public static Color[] playerColors = {
            Color.getHSBColor(0.17f, 1, 1),
            Color.getHSBColor(0.50f, 1, 1),
    };

}
