package hplovecraftspinbattle.actuator;

import hplovecraftspinbattle.core.SpinGameState;

public interface Actuator {
    SpinGameState actuate(int action, SpinGameState gameState);
    Actuator copy();
}
