package pommerball_players.mcts2;

import core.GameState;
import core.Player;
import pommerball_players.optimisers.ParameterizedPlayer;
import utils.ElapsedCpuTimer;
import utils.Types;

import java.util.ArrayList;
import java.util.Random;

public class MCTSPlayer2 extends ParameterizedPlayer {

    /**
     * Random generator.
     */
    private Random m_rnd;

    /**
     * All actions available.
     */
    public Types.ACTIONS[] actions;

    /**
     * Params for this MCTS
     */
    public MCTSParams2 params;

    public MCTSPlayer2(long seed, int id) {
        this(seed, id, null);
    }

    public MCTSPlayer2(long seed, int id, MCTSParams2 params) {
        super(seed, id, params);
        reset(seed, id);

        ArrayList<Types.ACTIONS> actionsList = Types.ACTIONS.all();
        actions = new Types.ACTIONS[actionsList.size()];
        int i = 0;
        for (Types.ACTIONS act : actionsList) {
            actions[i++] = act;
        }
    }

    @Override
    public void reset(long seed, int playerID) {
        this.seed = seed;
        this.playerID = playerID;
        m_rnd = new Random(seed);

        this.params = (MCTSParams2) getParameters();
        if (this.params == null) {
            this.params = new MCTSParams2();
        }
    }

    @Override
    public Types.ACTIONS act(GameState gs) {

        ElapsedCpuTimer ect = new ElapsedCpuTimer();
        ect.setMaxTimeMillis(params.num_time);

        // Number of actions available
        int num_actions = actions.length;

        // Root of the tree
        SingleTreeNode m_root = new SingleTreeNode(params, m_rnd, num_actions, actions);
        m_root.setRootGameState(gs);

        //Determine the action using MCTS...
        m_root.mctsSearch(ect);

        //Determine the best action to take and return it.
        int action = m_root.mostVisitedAction();

        //... and return it.
        return actions[action];
    }

    @Override
    public Player copy() {
        return new MCTSPlayer2(seed, playerID, params);
    }
}