package c_cubed.spinbattle.actuator;

import c_cubed.spinbattle.core.SpinGameState;

public interface Actuator {
    SpinGameState actuate(int action, SpinGameState gameState);
    Actuator copy();
}
