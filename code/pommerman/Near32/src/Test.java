import core.Game;
import core.Player;
import nagasaki45_players.mcts.LobsterParams;
import nagasaki45_players.rhea.RHEALobsterPlayer;
import nagasaki45_players.rhea.utils.Constants;
import nagasaki45_players.rhea.utils.RHEALobsterParams;
import nagasaki45_players.rhea.utils.RHEAParams;
import near32_players.*;
import pommerball_players.mcts2.MCTSParams2;
import pommerball_players.mcts2.MCTSPlayer2;
import utils.Types;
import near32_players.mcts.MCTSPlayer;
import near32_players.mcts.MCTSParams;
import near32_players.rhea.RHEAPlayer;
import vethan_players.IGGIPlayer;


import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        // Game parameters
        long seed = System.currentTimeMillis();
        int boardSize = Types.BOARD_SIZE;
        Types.GAME_MODE gameMode = Types.GAME_MODE.FFA_TELEPORT;
        boolean useSeparateThreads = false;                 //true may be unstable, false is recommended.

        Game game = new Game(seed, boardSize, gameMode, "");

        // Key controllers for human player s (up to 2 so far).
        KeyController ki1 = new KeyController(true);
        KeyController ki2 = new KeyController(false);


        // Create near32_players
        ArrayList<Player> players = new ArrayList<>();
        int playerID = Types.TILETYPE.AGENT0.getKey();


        // Define ustom mcts
        MCTSParams2 custom_params = new MCTSParams2();
        custom_params.stop_type = custom_params.STOP_ITERATIONS;
        custom_params.heuristic_method = custom_params.CUSTOM_HEURISTIC;

        // Define some heuristics
        nagasaki45_players.mcts.MCTSParams mctsParams = new nagasaki45_players.mcts.MCTSParams();
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

        //NAGASAKI
        RHEALobsterParams rheaLobsterParams = new RHEALobsterParams();
        players.add(new RHEALobsterPlayer(seed, playerID++, rheaLobsterParams));

        //vethan
        players.add(new IGGIPlayer(seed, playerID++));


        // Make sure we have exactly NUM_PLAYERS near32_players
        assert players.size() == Types.NUM_PLAYERS : "There should be " + Types.NUM_PLAYERS +
                                                     " added to the game, but there are " + players.size();

        //Assign near32_players and run the game.
        game.setPlayers(players);

        //Run a single game with the near32_players
//        Run.runGame(game, ki1, ki2, useSeparateThreads);

        /* Uncomment to run the replay of the previous game: */
//        if (game.isLogged()){
//            Game replay = Game.getLastReplayGame();
//            Run.runGame(replay, ki1, ki2, useSeparateThreads);
//            assert(replay.getGameState().equals(game.getGameState()));
//        }

        long seeds[] = new long[] {69009,9911,21029,26344,10340,78124,60088,8507,36480,7259,
                81979,76383,16283,21511,52421,84601,18958,65056,70089,43151};

        /* Run with no visuals, N repetitions TIMES number-seeds: */
        int N = 5;
        Run.runGames(game, seeds, N, useSeparateThreads);

    }

}
