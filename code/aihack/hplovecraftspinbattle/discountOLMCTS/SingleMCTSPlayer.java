package hplovecraftspinbattle.discountOLMCTS;

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
public class SingleMCTSPlayer
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

    public int ROLLOUT_DEPTH;
    public double DISCOUNT_FACTOR;
    public double K;

    public SingleMCTSPlayer(Random a_rnd, int[] NUM_ACTIONS, Types.ACTIONS[][] actions, int id, int oppID, int n_players,
                            int ROLLOUT_DEPTH, double DISCOUNT_FACTOR, double K) {
        m_rnd = a_rnd;
        this.NUM_ACTIONS = NUM_ACTIONS;
        this.actions = actions;
        this.id = id;
        this.oppID = oppID;
        this.no_players = n_players;
        this.ROLLOUT_DEPTH = ROLLOUT_DEPTH;
        this.DISCOUNT_FACTOR = DISCOUNT_FACTOR;
        this.K = K;
    }

    /**
     * Inits the tree with the new observation state in the root.
     * @param a_gameState current state of the game.
     */
    public void init(StateObservationMulti a_gameState)
    {
        //Set the game observation to a newly root node.
        //System.out.println("learning_style = " + learning_style);
        m_root = new SingleTreeNode(m_rnd, ROLLOUT_DEPTH, DISCOUNT_FACTOR, K, NUM_ACTIONS, actions, id, oppID, no_players);
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

}


