package c_cubed.coevo;


import ggi.core.AbstractGameState;
import ggi.core.SimplePlayerInterface;
import plot.NullPlayoutPlotter;
import plot.PlayoutPlotterInterface;

public class ActionSequencer {

    public static void main(String[] args) {

//        // fix a random seed to check repeatability
//        Random random = new Random(3);
//        GameState.random = random;
//
//        GameState gameState = new GameState().defaultState();
//        int[] seq = new int[]{0, 1, 2, 3, 4, 0, 1, 2, 2, 3, 4, 0, 1, 2, 1, 2, 3};
//        System.out.println("Initial fitness: " + gameState.getScore());
//        ActionSequencer actionSequencer = new ActionSequencer().setGameState(gameState).setPlayerId(0);
//        double fitness = actionSequencer.fitness(seq);
//        System.out.println("Final fitness: " + fitness);
    }

    AbstractGameState initialState, terminalState;
    int playerId;

    // double
    PlayoutPlotterInterface playoutPlotter;

    public ActionSequencer() {
        // don't plot the playouts by default
        playoutPlotter = new NullPlayoutPlotter();
    }

    public ActionSequencer setGameState(AbstractGameState gameState) {
        // actually makes a copy of it to avoid modifying the original
        this.initialState = gameState.copy();
        return this;
    }

    public ActionSequencer setPlayerId(int playerId) {
        this.playerId = playerId;
        return this;
    }


    public ActionSequencer setOpponent(SimplePlayerInterface opponent) {
        return this;
    }

    public ActionSequencer actVersusAgent(int[] seq, int playerId, int[] opSeq) {
        // careful, this may not be copiing the game state ...
        terminalState = initialState.copy();
        playoutPlotter.startPlayout(terminalState.getScore());

        int[] actions = new int[2];
        for (int i = 0; i < seq.length; i++) {
            actions[playerId] = seq[i];
            actions[1 - playerId] = opSeq[i];
            terminalState.next(actions);
            playoutPlotter.addScore(terminalState.getScore());
        }
        // System.out.println("Terminal score: " + terminalState.getScore());
        playoutPlotter.plotPlayout();
        return this;
    }

    public double fitness(int[] solution, int[] opSolution) {
        double rawScore = actVersusAgent(
                solution, playerId, opSolution).terminalState.getScore();
        return playerId == 0 ? rawScore : -rawScore;
    }

}
