package vethan_players;

import vethan_players.mcts.MCTSParams;
import vethan_players.mcts.MCTSPlayer;


public class IGGIPlayer extends MCTSPlayer {
    // MCTS player with some finely tuned parameters ;)
    // Rollout depth of 15 led to some uh... real pacifist vethan_players that just tied
    // Simulating a RandomPlayer as opponent also seems to have done better than doing similar with SimplePlayer
    // Could not do with RHEA or MCTS, as predicting a predictor gets recursive

    public IGGIPlayer(long seed, int id) {
        super(seed, id, new MCTSParams(), new RandomPlayer(seed, id));
        params.rollout_depth = 8;
        params.heuristic_method = params.CUSTOM_HEURISTIC;

    }
}
