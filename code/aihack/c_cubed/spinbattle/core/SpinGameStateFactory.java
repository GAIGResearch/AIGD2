package c_cubed.spinbattle.core;

import c_cubed.spinbattle.actuator.Actuator;
import c_cubed.spinbattle.actuator.SourceTargetActuator;
import c_cubed.spinbattle.params.SpinBattleParams;
import ggi.core.AbstractGameFactory;
import ggi.core.AbstractGameState;

public class SpinGameStateFactory implements AbstractGameFactory {

    public SpinBattleParams params = new SpinBattleParams();


    public Actuator[] actuators;

    public SpinGameStateFactory() {
        actuators = new Actuator[2];
    }

    @Override
    public AbstractGameState newGame() {
        // params.transitSpeed = 0;
        SpinGameState gameState = new SpinGameState().setParams(params).setPlanets();
        actuators[0] = new SourceTargetActuator().setPlayerId(0);
        actuators[1] = new SourceTargetActuator().setPlayerId(1);
        gameState.actuators = actuators;
        // actuators = new Actuator[2];
        // System.out.println("Actuators: " + actuators);
        return gameState;
    }
}
