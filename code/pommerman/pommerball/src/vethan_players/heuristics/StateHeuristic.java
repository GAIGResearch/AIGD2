package vethan_players.heuristics;

import core.GameState;

public abstract class StateHeuristic {
    public abstract double evaluateState(GameState gs);
}
