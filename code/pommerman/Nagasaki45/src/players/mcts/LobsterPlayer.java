package players.mcts;

import core.GameState;
import players.Memory;
import players.Player;
import players.mcts.MCTSPlayer;
import utils.ElapsedCpuTimer;
import utils.Types;

import java.util.ArrayList;
import java.util.Random;

public class LobsterPlayer extends MCTSPlayer {

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
    public LobsterParams params;

    /**
     * Agent memory for FoW version
     */
    private boolean enableMemory;
    public Memory memory;

    public LobsterPlayer(long seed, int id) {
        this(seed, id, null);
    }

    public LobsterPlayer(long seed, int id, LobsterParams params)
    {
        this(seed, id, params, false);
    }

    /**
     * Constructors that receive parameters
     * @param seed seed for the algorithm to use in the random generator
     * @param id ID of this player in the game.
     * @param params Parameters for MCTS.
     */
    public LobsterPlayer(long seed, int id, LobsterParams params, boolean enableMemory) {

        super(seed, id, params);
        reset(seed, id);

        this.enableMemory = enableMemory;
        this.memory = new Memory();
        ArrayList<Types.ACTIONS> actionsList = Types.ACTIONS.all();
        actions = new Types.ACTIONS[actionsList.size()];
        int i = 0;
        for (Types.ACTIONS act : actionsList) {
            actions[i++] = act;
        }
    }

    /**
     * Resets this player with seed and ID
     * @param seed seed for the algorithm to use in the random generator
     * @param playerID ID of this player in the game.
     */
    @Override
    public void reset(long seed, int playerID) {
        this.seed = seed;
        this.playerID = playerID;
        m_rnd = new Random(seed);

        this.params = (LobsterParams) getParameters();
        if (this.params == null) {
            this.params = new LobsterParams();
        }
    }

    /**
     * Action called every game tick. It must return an action to play in the real game.
     * @param gs - current game state.
     * @return the action to apply in the game.
     */
    @Override
    public Types.ACTIONS act(GameState gs) {

        //This allows us to use a time-bounded budget for MCTS
        ElapsedCpuTimer ect = new ElapsedCpuTimer();
        ect.setMaxTimeMillis(params.num_time);

        // Number of actions available
        int num_actions = java.lang.Math.min(actions.length, 10);

        // Update the memory
        if (this.enableMemory)
        {
            gs = this.memory.update(gs);
        }

        // Root of the tree
        SingleLobsterTreeNode m_root = new SingleLobsterTreeNode(params, m_rnd, num_actions, actions);
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
        return new LobsterPlayer(seed, playerID, (LobsterParams)params);
    }
}