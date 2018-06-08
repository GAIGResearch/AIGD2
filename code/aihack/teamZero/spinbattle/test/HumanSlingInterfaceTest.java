package teamZero.spinbattle.test;

import logger.sample.DefaultLogger;
import teamZero.spinbattle.core.SpinGameState;
import teamZero.spinbattle.log.BasicLogger;
import teamZero.spinbattle.params.Constants;
import teamZero.spinbattle.params.SpinBattleParams;
import teamZero.spinbattle.players.HeuristicLauncher;
import teamZero.spinbattle.ui.MouseSlingController;
import teamZero.spinbattle.ui.SourceTargetMouseController;
import teamZero.spinbattle.view.SpinBattleView;
import utilities.JEasyFrame;

import java.util.Random;

public class HumanSlingInterfaceTest {

    public static void main(String[] args) throws Exception {
        // to always get the same initial game
        long seed = 10;
        seed = new Random().nextLong();
        SpinBattleParams.random = new Random(seed);
        SpinBattleParams params = new SpinBattleParams();

        params.maxTicks = 5000;
        params.gravitationalFieldConstant *= 1;
        params.transitSpeed *= 1;
        params.width = 1200;
        params.height = 700;
        params.nPlanets = 100; // 10 + new Random().nextInt(50);

        System.out.println("Selected n planets: " + params.nPlanets);

        SpinGameState gameState = new SpinGameState().setParams(params).setPlanets();
        System.out.println("nPlanets made = " + gameState.planets.size());
        // BasicLogger basicLogger = new BasicLogger();
        // gameState.setLogger(new DefaultLogger());
        SpinBattleView view = new SpinBattleView().setParams(params).setGameState(gameState);
        HeuristicLauncher launcher = new HeuristicLauncher();
        String title = "Spin Battle Game" ;
        JEasyFrame frame = new JEasyFrame(view, title + ": Waiting for Graphics");
        frame.setLocation(400, 100);
        MouseSlingController mouseSlingController = new MouseSlingController();
        mouseSlingController.setGameState(gameState).setPlayerId(Constants.playerOne);
        view.addMouseListener(mouseSlingController);
        int launchPeriod = 400; // params.releasePeriod;
        waitUntilReady(view);

        for (int i=0; i<=5000 && !gameState.isTerminal(); i++) {
            gameState.next(null);
            mouseSlingController.update();
            // launcher.makeTransits(gameState, Constants.playerOne);
            if (i % launchPeriod == 0)
                launcher.makeTransits(gameState, Constants.playerTwo);
            view.setGameState((SpinGameState) gameState.copy());
            view.repaint();
            frame.setTitle(title + " : " + i); //  + " : " + CaveView.getTitle());
            Thread.sleep(20);
        }
        System.out.println(gameState.isTerminal());
    }

    static void waitUntilReady(SpinBattleView view) throws Exception {
        int i = 0;
        while (view.nPaints == 0) {
            // System.out.println(i++ + " : " + CaveView.nPaints);
            Thread.sleep(50);
        }
    }
}
