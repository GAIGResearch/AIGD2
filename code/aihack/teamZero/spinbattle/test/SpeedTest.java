package teamZero.spinbattle.test;

import teamZero.spinbattle.core.SpinGameState;
import teamZero.spinbattle.params.Constants;
import teamZero.spinbattle.params.SpinBattleParams;
import teamZero.spinbattle.players.HeuristicLauncher;
import utilities.ElapsedTimer;
import utilities.StatSummary;

public class SpeedTest {

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

        constructionTime.add(t.elapsed());

        t = new ElapsedTimer();
        for (int i = 0; i < nSteps && !gameState.isTerminal(); i++) {
            gameState.next(null);
            if (copyTest)
                gameState = (SpinGameState) gameState.copy();
             launcher.makeTransits(gameState, Constants.playerOne);
             launcher.makeTransits(gameState, Constants.playerTwo);

        }
        runningTime.add(t.elapsed());
        return gameState;
    }
}
