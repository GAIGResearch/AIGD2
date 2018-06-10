package teamZero.spinbattle.actuator;

import teamZero.spinbattle.core.SpinGameState;

public interface Actuator {
    SpinGameState actuate(int action, SpinGameState gameState);
    Actuator copy();
}
