package hplovecraftspinbattle.hplovecraftagent;

import ggi.core.AbstractGameFactory;
import ggi.core.AbstractGameState;
import hplovecraftspinbattle.core.SpinGameState;
import hplovecraftspinbattle.params.SpinBattleParams;

public class SpinGameFactory implements AbstractGameFactory {
    @Override
    public AbstractGameState newGame() {
        return new SpinGameState().setParams(params).setPlanets();
    }

    SpinBattleParams params = new SpinBattleParams();

    public SpinGameFactory setParams(SpinBattleParams params) {
        this.params = params;
        return  this;
    }
}
