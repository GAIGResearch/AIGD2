import core.Game;
import players.*;
import utils.Types;
import players.rhea.utils.Constants;
import objects.Avatar;
import players.mcts.MCTSPlayer;
import players.mcts2.MCTSPlayer2;
import players.mcts.MCTSParams;
import players.mcts2.MCTSParams2;
import players.rhea.RHEAPlayer;
import players.rhea.utils.RHEAParams;
import utils.*;


import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        // Game parameters
        long seed = System.currentTimeMillis();
        int boardSize = Types.BOARD_SIZE;
        Types.GAME_MODE gameMode = Types.GAME_MODE.FFA;
        boolean useSeparateThreads = false;                 //true may be unstable, false is recommended.

        Game game = new Game(seed, boardSize, gameMode, "");

        // Key controllers for human player s (up to 2 so far).
        KeyController ki1 = new KeyController(true);
        KeyController ki2 = new KeyController(false);


        // Create players
        ArrayList<Player> players = new ArrayList<>();
        int playerID = Types.TILETYPE.AGENT0.getKey();

        // Define some heuristics
        MCTSParams mctsParams = new MCTSParams();
        mctsParams.stop_type = mctsParams.STOP_ITERATIONS;
        mctsParams.heuristic_method = mctsParams.CUSTOM_HEURISTIC;
        RHEAParams rheaParams = new RHEAParams();
        rheaParams.heurisic_type = Constants.CUSTOM_HEURISTIC;

        // Define ustom mcts
        MCTSParams2 custom_params = new MCTSParams2();
        custom_params.stop_type = custom_params.STOP_ITERATIONS;
        custom_params.heuristic_method = custom_params.CUSTOM_HEURISTIC;

        /* Different available players */

        //players.add(new DoNothingPlayer(playerID++));
        players.add(new MCTSPlayer2(seed, playerID++, custom_params));
        players.add(new MCTSPlayer(seed, playerID++, mctsParams));
        players.add(new MCTSPlayer(seed, playerID++, mctsParams));
        players.add(new MCTSPlayer(seed, playerID++, mctsParams));


        // Make sure we have exactly NUM_PLAYERS players
        assert players.size() == Types.NUM_PLAYERS : "There should be " + Types.NUM_PLAYERS +
                                                     " added to the game, but there are " + players.size();

        //Assign players and run the game.
        game.setPlayers(players);

        //Run a single game with the players
        Run.runGame(game, ki1, ki2, useSeparateThreads);

        /* Uncomment to run the replay of the previous game: */
//        if (game.isLogged()){
//            Game replay = Game.getLastReplayGame();
//            Run.runGame(replay, ki1, ki2, useSeparateThreads);
//            assert(replay.getGameState().equals(game.getGameState()));
//        }

        /* Run with no visuals, N repetitions TIMES number-seeds: */
//        int N = 20;
//        Run.runGames(game, new long[]{seed}, N, useSeparateThreads);

    }

}
