package hplovecraftspinbattle.discountOLMCTS;

import core.game.StateObservationMulti;
import core.player.AbstractMultiPlayer;
import ontology.Types;
import tools.ElapsedCpuTimer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ssamot
 * Date: 14/11/13
 * Time: 21:45
 * This is a Java port from Tom Schaul's VGDL - https://github.com/schaul/py-vgdl
 */
public class Agent extends AbstractMultiPlayer {

    public int[] NUM_ACTIONS;
    public Types.ACTIONS[][] actions;
    public int id, oppID, no_players;

    protected SingleMCTSPlayer mctsPlayer;

    public int ROLLOUT_DEPTH = 10;
    public double DISCOUNT_FACTOR = 1.00;
    public double K = Math.sqrt(2);

    /**
     * Public constructor with state observation and time due.
     * @param so state observation of the current game.
     * @param elapsedCpuTimer Timer for the controller creation.
     */
    public Agent(StateObservationMulti so, ElapsedCpuTimer elapsedCpuTimer, int playerID, int ROLLOUT_DEPTH, double DISCOUNT_FACTOR, double K) {
        this.ROLLOUT_DEPTH = ROLLOUT_DEPTH;
        this.DISCOUNT_FACTOR = DISCOUNT_FACTOR;
        this.K = K;

        //get game information

        no_players = so.getNoPlayers();
        id = playerID;
        oppID = (id + 1) % so.getNoPlayers();

        //Get the actions for all players in a static array.

        NUM_ACTIONS = new int[no_players];
        actions = new Types.ACTIONS[no_players][];
        for (int i = 0; i < no_players; i++) {

            ArrayList<Types.ACTIONS> act = so.getAvailableActions(i);

            actions[i] = new Types.ACTIONS[act.size()];
            for (int j = 0; j < act.size(); ++j) {
                actions[i][j] = act.get(j);
            }
            NUM_ACTIONS[i] = actions[i].length;
        }
        //Create the player.
        mctsPlayer = getPlayer(so, elapsedCpuTimer, NUM_ACTIONS, actions, id, oppID, no_players);
    }

    public SingleMCTSPlayer getPlayer(StateObservationMulti so, ElapsedCpuTimer elapsedTimer, int[] NUM_ACTIONS, Types.ACTIONS[][] actions, int id, int oppID, int no_players) {
        return new SingleMCTSPlayer(new Random(), NUM_ACTIONS, actions, id, oppID, no_players, ROLLOUT_DEPTH, DISCOUNT_FACTOR, K);
    }


    /**
     * Picks an action. This function is called every game step to request an
     * action from the player.
     * @param stateObs Observation of the current state.
     * @param elapsedTimer Timer when the action returned is due.
     * @return An action for the current state
     */
    public Types.ACTIONS act(StateObservationMulti stateObs, ElapsedCpuTimer elapsedTimer) {

        //Set the state observation object as the new root of the tree.
        mctsPlayer.init(stateObs);

        //Determine the action using MCTS...
        int action = mctsPlayer.run(elapsedTimer);

        //... and return it.
        return actions[id][action];
    }

}
