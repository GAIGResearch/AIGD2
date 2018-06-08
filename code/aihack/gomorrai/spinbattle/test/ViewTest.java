package gomorrai.spinbattle.test;

import gomorrai.spinbattle.core.SpinGameState;
import gomorrai.spinbattle.params.Constants;
import gomorrai.spinbattle.params.SpinBattleParams;
import gomorrai.spinbattle.players.HeuristicLauncher;
import gomorrai.spinbattle.view.SpinBattleView;
import utilities.JEasyFrame;

public class ViewTest {
    public static void main(String[] args) throws Exception {
        SpinBattleParams params = new SpinBattleParams();
        SpinGameState gameState = new SpinGameState().setParams(params).setPlanets();
        SpinBattleView view = new SpinBattleView().setParams(params).setGameState(gameState);
        HeuristicLauncher launcher = new HeuristicLauncher();
        new JEasyFrame(view, "Spin Battle Game");
        for (int i=0; i<1000; i++) {
            gameState.next(null);
            gameState = (SpinGameState) gameState.copy();
            launcher.makeTransits(gameState, Constants.playerOne);
            launcher.makeTransits(gameState, Constants.playerTwo);
            view.setGameState((SpinGameState) gameState.copy());
            view.repaint();
            Thread.sleep(20);
        }
    }
}
