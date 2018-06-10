package hplovecraftspinbattle.test;

import hplovecraftspinbattle.actuator.SourceTargetActuator;
import hplovecraftspinbattle.core.Planet;
import hplovecraftspinbattle.core.SpinGameState;
import hplovecraftspinbattle.core.Transporter;
import hplovecraftspinbattle.params.Constants;
import hplovecraftspinbattle.params.SpinBattleParams;

/**
 * This was to fix a bug in the copying
 */

public class ParamCopyError {


    public static void main(String[] args) {
        // find the launch error

        SpinBattleParams params = new SpinBattleParams();
        // params.transitSpeed = 0;


        SpinGameState gameState = new SpinGameState().setParams(params).setPlanets();

        SourceTargetActuator actuator = new SourceTargetActuator().setPlayerId(0);

        gameState.actuators[0] = actuator;

        // now launch a transit

        gameState.planets.get(0).ownedBy = Constants.playerOne;



        SpinBattleParams params2 = new SpinBattleParams();
        params2.transitSpeed = 5;
        Planet source = gameState.planets.get(0);
        Planet target = gameState.planets.get(1);

        gameState.setParams(params2);
        // source.setParams(params2);

        Transporter trans = gameState.planets.get(0).getTransporter().launch(source.position, target.position, Constants.playerOne, gameState);
        trans.setPayload(source, 10);

        for (Planet p : gameState.planets) {
            System.out.println(p.index + "\t " + p.getTransporter());
        }

    }
}
