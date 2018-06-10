package teamZero.spinbattle.test;

import teamZero.spinbattle.core.SpinGameState;
import teamZero.spinbattle.params.Constants;
import teamZero.spinbattle.params.SpinBattleParams;
import teamZero.spinbattle.players.HeuristicLauncher;
import teamZero.spinbattle.view.SpinBattleView;
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
