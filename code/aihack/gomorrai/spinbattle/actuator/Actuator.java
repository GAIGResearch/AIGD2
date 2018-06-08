package gomorrai.spinbattle.actuator;

import gomorrai.spinbattle.core.SpinGameState;

public interface Actuator {
    SpinGameState actuate(int action, SpinGameState gameState);
    Actuator copy();
}
