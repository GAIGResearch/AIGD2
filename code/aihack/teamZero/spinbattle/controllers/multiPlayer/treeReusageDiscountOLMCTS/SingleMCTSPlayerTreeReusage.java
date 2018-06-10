package teamZero.spinbattle.controllers.multiPlayer.treeReusageDiscountOLMCTS;

import core.game.StateObservationMulti;
import ontology.Types;
import tools.ElapsedCpuTimer;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Diego
 * Date: 07/11/13
 * Time: 17:13
 */
public class SingleMCTSPlayerTreeReusage
{


    /**
     * Root of the tree.
     */
    public SingleTreeNode m_root;

    int[] NUM_ACTIONS;
    Types.ACTIONS[][] actions;

    /**
     * Random generator.
     */
    public Random m_rnd;
    public int id, oppID, no_players;
    private int elapsed_ticks_since_tree_refresh;


    public SingleMCTSPlayerTreeReusage(Random a_rnd, int[] NUM_ACTIONS, Types.ACTIONS[][] actions, int id, int oppID, int no_players)
    {
        m_rnd = a_rnd;
        this.NUM_ACTIONS = NUM_ACTIONS;
        this.actions = actions;
        this.id = id;
        this.oppID = oppID;
        this.no_players = no_players;
        this.elapsed_ticks_since_tree_refresh = 0;
        this.m_root = null;
    }

    /**
     * Inits the tree with the new observation state in the root.
     * @param a_gameState current state of the game.
     */
    public void init(StateObservationMulti a_gameState)
    {
        //Set the game observation to a newly root node.
        //System.out.println("learning_style = " + learning_style);
        this.elapsed_ticks_since_tree_refresh++;

        if (m_root == null || elapsed_ticks_since_tree_refresh % 1000 == 0) {
            m_root = new SingleTreeNode(m_rnd, NUM_ACTIONS, actions, id, oppID, no_players);
            this.elapsed_ticks_since_tree_refresh = 0;
        }
        m_root.rootState = a_gameState;
    }

    /**
     * Runs MCTS to decide the action to take. It does not reset the tree.
     * @param elapsedTimer Timer when the action returned is due.
     * @return the action to execute in the game.
     */
    public int run(ElapsedCpuTimer elapsedTimer)
    {
        //Do the search within the available time.
        m_root.mctsSearch(elapsedTimer);

        //Determine the best action to take and return it.
        int action = m_root.mostVisitedAction();
        //int action = m_root.bestAction();
        return action;
    }

    public void updateRoot(int action) {
        SingleTreeNode newRoot = findChildNodeFromAction(action);
        m_root = newRoot;
        m_root.m_depth = 0;
    }

    private SingleTreeNode findChildNodeFromAction(int action) {
        return m_root.children[action];
    }

}


