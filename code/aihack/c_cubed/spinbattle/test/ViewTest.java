package c_cubed.spinbattle.test;

import c_cubed.spinbattle.core.SpinGameState;
import c_cubed.spinbattle.params.Constants;
import c_cubed.spinbattle.params.SpinBattleParams;
import c_cubed.spinbattle.players.HeuristicLauncher;
import c_cubed.spinbattle.view.SpinBattleView;
import utilities.JEasyFrame;

public class ViewTest {
    public static void main(String[] args) throws Exception {
        SpinBattleParams params = new SpinBattleParams();
        SpinGameState gameState = new SpinGameState().setParams(params).setPlanets();
        SpinBattleView view = new SpinBattleView().setParams(params).setGameState(gameState);
        HeuristicLauncher launcher = new HeuristicLauncher();
        new JEasyFrame(view, "Spin Battle Game");
        for (int i=0; i<500; i++) {
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
