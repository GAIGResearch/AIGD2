package hplovecraftspinbattle.test;

import agents.dummy.RandomAgent;
import agents.evo.EvoAgent;
import core.player.AbstractMultiPlayer;
import ggi.agents.EvoAgentFactory;
import ggi.core.SimplePlayerInterface;
import gvglink.SpinBattleLinkState;
import planetwar.GVGAIWrapper;
import hplovecraftspinbattle.core.SpinGameState;
import hplovecraftspinbattle.params.Constants;
import hplovecraftspinbattle.params.SpinBattleParams;
import hplovecraftspinbattle.players.HeuristicLauncher;
import tools.ElapsedCpuTimer;
import utilities.ElapsedTimer;
import utilities.StatSummary;
import hplovecraftspinbattle.discountOLMCTS.Agent;

public class SpeedTest {

    public static int ROLLOUT_DEPTH = 10;
    public static double DISCOUNT_FACTOR = 1.00;
    public static double K = Math.sqrt(2);

    static StatSummary constructionTime = new StatSummary("Construction Time");
    static StatSummary runningTime = new StatSummary("Running Time");

    static boolean copyTest = false;

    public static void main(String[] args) {

        int nSteps = 5000;
        int nGames = 1000;

        ElapsedTimer timer = new ElapsedTimer();

        StatSummary ss = new StatSummary("Game Scores");
        int nWins = 0;
        int nRand = 0;
        StatSummary np = new StatSummary("N Planets");

        for (int i=0; i<nGames; i++) {
            SpinGameState gameState = playGame(nSteps);
            np.add(gameState.planets.size());
            ss.add(gameState.getScore());
            if (gameState.getScore() > 0) nWins++;
            if (Math.random() < 0.5) nRand++;
            // System.out.println(i + "\t " + gameState.getScore());
        }
        long elapsed = timer.elapsed();
        System.out.println(np);
        System.out.println(ss);
        System.out.println(constructionTime);
        System.out.println(runningTime);
        System.out.println(timer);
        System.out.println();
        System.out.println("nWins for Player One: " + nWins);
        System.out.println("nHead (forcomparison) " + nRand);
        System.out.println("Total game ticks: " + SpinGameState.totalTicks);
        System.out.println("Total game states made: " + SpinGameState.totalInstances);

        System.out.format("%.0fk ticks / s\n ", SpinGameState.totalTicks * 1.0 / elapsed);

    }

    public static SpinGameState playGame(int nSteps) {
        ElapsedTimer t = new ElapsedTimer();
        SpinBattleParams params = new SpinBattleParams();
        params.maxTicks = nSteps;
        SpinGameState gameState = new SpinGameState().setParams(params).setPlanets();
        HeuristicLauncher launcher = new HeuristicLauncher();

        //SimplePlayerInterface mctsPlayer = getMCTSAgent(gameState, Constants.playerTwo);
        SimplePlayerInterface evoAgent = new EvoAgentFactory().getAgent();
        ((EvoAgent) evoAgent).setSequenceLength(800);

        SimplePlayerInterface randomAgent = new RandomAgent();

        int[] actions = new int[2];

        constructionTime.add(t.elapsed());

        t = new ElapsedTimer();
        for (int i = 0; i < nSteps && !gameState.isTerminal(); i++) {
            actions[Constants.playerOne]
                    = randomAgent.getAction(gameState.copy(), Constants.playerOne);
            actions[Constants.playerTwo]
                    = evoAgent.getAction(gameState.copy(), Constants.playerTwo);
            gameState.next(null);
            if (copyTest)
                gameState = (SpinGameState) gameState.copy();
             launcher.makeTransits(gameState, Constants.playerOne);
             launcher.makeTransits(gameState, Constants.playerTwo);

        }
        runningTime.add(t.elapsed());
        return gameState;
    }


    static GVGAIWrapper getMCTSAgent(SpinGameState gameState, int playerId) {
        ElapsedCpuTimer timer = new ElapsedCpuTimer();
        SpinBattleLinkState linkState = new SpinBattleLinkState(gameState);
        AbstractMultiPlayer agent =
                new Agent(linkState.copy(), timer, playerId,
                        ROLLOUT_DEPTH, DISCOUNT_FACTOR, K);

        GVGAIWrapper wrapper = new GVGAIWrapper().setAgent(agent);
        return wrapper;

    }
}
