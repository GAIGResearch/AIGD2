package gomorrai.spinbattle.test;

import core.player.AbstractMultiPlayer;
import ggi.core.AbstractGameState;
import ggi.core.SimplePlayerInterface;
import gvglink.PlanetWarsLinkState;
import logger.sample.DefaultLogger;
import gomorrai.spinbattle.actuator.SourceTargetActuator;
import gomorrai.spinbattle.core.SpinGameState;
import gomorrai.spinbattle.log.BasicLogger;
import gomorrai.spinbattle.params.Constants;
import gomorrai.spinbattle.params.SpinBattleParams;
import gomorrai.spinbattle.players.GVGAIWrapper;
import gomorrai.spinbattle.ui.MouseSlingController;
import gomorrai.spinbattle.view.SpinBattleView;
import tools.ElapsedCpuTimer;
import utilities.JEasyFrame;

import java.util.Random;

public class HumanSlingVersusEvoAgent {

    public static void main(String[] args) throws Exception {
        // to always get the same initial game
        SpinBattleParams.random = new Random(8);
        SpinBattleParams params = new SpinBattleParams();

        params.maxTicks = 5000;
        params.gravitationalFieldConstant *= 1;
        params.transitSpeed *= 1;
        SpinGameState gameState = new SpinGameState().setParams(params).setPlanets();
        gameState.actuators[1] = new SourceTargetActuator().setPlayerId(1);

        BasicLogger basicLogger = new BasicLogger();
        gameState.setLogger(new DefaultLogger());
        SpinBattleView view = new SpinBattleView().setParams(params).setGameState(gameState);
        String title = "Spin Battle Game" ;
        JEasyFrame frame = new JEasyFrame(view, title + ": Waiting for Graphics");
        frame.setLocation(400, 100);
        MouseSlingController mouseSlingController = new MouseSlingController();
        mouseSlingController.setGameState(gameState).setPlayerId(Constants.playerOne);
        view.addMouseListener(mouseSlingController);
        waitUntilReady(view);

//        SimplePlayerInterface evoAgent = new EvoAgentFactory().getAgent().setVisual();
//        SpinBattleParams falseParams = new SpinBattleParams();
//        falseParams.transitSpeed = 0;
//        falseParams.gravitationalFieldConstant = 0;
//        evoAgent = new FalseModelAdapter().setParams(falseParams).setPlayer(evoAgent);

        SimplePlayerInterface mctsAgent = getMCTSAgent(gameState, 1);

        int[] actions = new int[2];


        for (int i=0; i<=5000 && !gameState.isTerminal(); i++) {
            actions[1] = mctsAgent.getAction(gameState.copy(), 1);
            gameState.next(actions);
            mouseSlingController.update();
            // launcher.makeTransits(gameState, Constants.playerOne);
            view.setGameState((SpinGameState) gameState.copy());
            view.repaint();
            frame.setTitle(title + " : " + i); //  + " : " + CaveView.getTitle());
            Thread.sleep(50);
        }
        System.out.println(gameState.isTerminal());
    }

    static GVGAIWrapper getMCTSAgent(AbstractGameState gameState, int playerId) {
        ElapsedCpuTimer timer = new ElapsedCpuTimer();
        PlanetWarsLinkState linkState = new PlanetWarsLinkState(gameState);
        AbstractMultiPlayer agent =
                new gomorrai.discountOLMCTS.Agent(linkState.copy(), timer, playerId);
        GVGAIWrapper wrapper = new GVGAIWrapper().setAgent(agent);
        return wrapper;

    }

    static void waitUntilReady(SpinBattleView view) throws Exception {
        int i = 0;
        while (view.nPaints == 0) {
            // System.out.println(i++ + " : " + CaveView.nPaints);
            Thread.sleep(50);
        }
    }
}
