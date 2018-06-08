package teamZero.spinbattle.controllers.multiPlayer.treeReusageDiscountOLMCTS;

import core.game.StateObservationMulti;
import core.player.AbstractMultiPlayer;
import ontology.Types;
import tools.ElapsedCpuTimer;
import teamZero.spinbattle.GVGAIWrapper;

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
    private boolean initialized = false;

    protected SingleMCTSPlayerTreeReusage mctsPlayer;

    /**
     * Public constructor with state observation and time due.
     * @param so state observation of the current game.
     * @param elapsedTimer Timer for the controller creation.
     */
    public Agent()
    {
    }

    public void init(StateObservationMulti so, ElapsedCpuTimer elapsedTimer) {
        //get game information

        this.no_players = 2;

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
        mctsPlayer = getPlayer(so, elapsedTimer, NUM_ACTIONS, actions, id, oppID, no_players);
        this.initialized = true;

    }

    public Agent withPlayerId(int playerID) {
        this.id = playerID;
        return this;
    }

    public SingleMCTSPlayerTreeReusage getPlayer(StateObservationMulti so, ElapsedCpuTimer elapsedTimer, int[] NUM_ACTIONS, Types.ACTIONS[][] actions, int id, int oppID, int no_players) {
        return new SingleMCTSPlayerTreeReusage(new Random(), NUM_ACTIONS, actions, id, oppID, no_players);
    }


    /**
     * Picks an action. This function is called every game step to request an
     * action from the player.
     * @param stateObs Observation of the current state.
     * @param elapsedTimer Timer when the action returned is due.
     * @return An action for the current state
     */
    public Types.ACTIONS act(StateObservationMulti stateObs, ElapsedCpuTimer elapsedTimer) {

        if (!this.initialized) {
           this.init(stateObs, elapsedTimer); 
        }

        //Set the state observation object as the new root of the tree.
        mctsPlayer.init(stateObs);

        //Determine the action using MCTS...
        int action = mctsPlayer.run(elapsedTimer);

        mctsPlayer.updateRoot(action);

        //... and return it.
        return actions[id][action];
    }

}
