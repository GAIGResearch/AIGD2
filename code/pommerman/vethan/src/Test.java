import core.Game;
import core.Player;
import nagasaki45_players.mcts.LobsterParams;
import nagasaki45_players.mcts.MCTSParams;
import nagasaki45_players.rhea.RHEALobsterPlayer;
import nagasaki45_players.rhea.utils.Constants;
import nagasaki45_players.rhea.utils.RHEALobsterParams;
import nagasaki45_players.rhea.utils.RHEAParams;
import pommerball_players.mcts2.MCTSParams2;
import pommerball_players.mcts2.MCTSPlayer2;
import vethan_players.*;
import utils.Types;


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


        // Create vethan_players
        ArrayList<Player> players = new ArrayList<>();
        int playerID = Types.TILETYPE.AGENT0.getKey();



        // Define ustom mcts
        MCTSParams2 custom_params = new MCTSParams2();
        custom_params.stop_type = custom_params.STOP_ITERATIONS;
        custom_params.heuristic_method = custom_params.CUSTOM_HEURISTIC;

        // Define some heuristics
        nagasaki45_players.mcts.MCTSParams mctsParams = new MCTSParams();
        mctsParams.stop_type = mctsParams.STOP_ITERATIONS;
        mctsParams.heuristic_method = mctsParams.CUSTOM_HEURISTIC;

        nagasaki45_players.rhea.utils.RHEAParams rheaParams = new RHEAParams();
        rheaParams.heurisic_type = Constants.CUSTOM_HEURISTIC;

        LobsterParams lobsterParams = new LobsterParams();
        lobsterParams.stop_type = lobsterParams.STOP_ITERATIONS;
        lobsterParams.heuristic_method = lobsterParams.LOBSTER_HEURISTIC;

        // Define our heuristics
        near32_players.mcts.MCTSParams OurMCTSParams = new near32_players.mcts.MCTSParams();
        OurMCTSParams.K = 10;//Math.sqrt(2);
        OurMCTSParams.rollout_depth = 6;
        OurMCTSParams.stop_type = OurMCTSParams.STOP_ITERATIONS;
        OurMCTSParams.heuristic_method = OurMCTSParams.OUR_HEURISTIC;

        /* Different available nagasaki45_players */

        //NEAR-32
        players.add(new near32_players.mcts.MCTSPlayer(seed, playerID++, OurMCTSParams));

        //Pommerball
        players.add(new MCTSPlayer2(seed, playerID++, custom_params));

        //vethan
        players.add(new IGGIPlayer(seed, playerID++));

        //NAGASAKI
        RHEALobsterParams rheaLobsterParams = new RHEALobsterParams();
        players.add(new RHEALobsterPlayer(seed, playerID++, rheaLobsterParams));




        // Make sure we have exactly NUM_PLAYERS vethan_players
        assert players.size() == Types.NUM_PLAYERS : "There should be " + Types.NUM_PLAYERS +
                                                     " added to the game, but there are " + players.size();

        //Assign vethan_players and run the game.
        game.setPlayers(players);

        //Run a single game with the vethan_players
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
