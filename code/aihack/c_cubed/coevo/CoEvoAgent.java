package c_cubed.coevo;

import evodef.RegularSearchSpace;
import evodef.SearchSpaceUtil;
import ggi.core.AbstractGameState;
import ggi.core.SimplePlayerInterface;
import plot.NullPlayoutPlotter;
import plot.PlayoutPlotter;
import plot.PlayoutPlotterInterface;

import java.awt.*;
import java.util.ArrayList;

/**
 *  This is a simple evolutionary planning agent
 *  that combines a game state, and evolutionary algorithm and an action sequencer -
 *  taken together they provide a way to harness an evolutionary algorithm
 *  to play a game.
 *
 *  This functionality is already implemented in the gvglink package,
 *  but this is a simpler approach that enables
 */

public class CoEvoAgent implements SimplePlayerInterface {

    ActionSequencer actionSequencer;
    ActionSequencer actionSequencerOp;
    public CRMHC evoAlg;
    public GameAdapter gameAdapter;
    public GameAdapter gameAdapterOp;
    RegularSearchSpace searchSpace;

    // static int nActions = AsteroidsGameState.nActions;
    public int sequenceLength = 20;
    public boolean useShiftBuffer = true;
    public int[] solution;
    public int[] opPlan;
    public int nEvals;

    PlayoutPlotterInterface playoutPlotter = new NullPlayoutPlotter();

    public SimplePlayerInterface reset() {
        solution = null;
        opPlan = null;
        actionSequencer = new ActionSequencer();
        actionSequencerOp = new ActionSequencer();

        // todo: find the BUG!!!
        System.out.println("Called reset!!!!");
        return this;
    }

    public CoEvoAgent setEvoAlg(CRMHC evoAlg, int nEvals) {
        this.evoAlg = evoAlg;
        this.nEvals = nEvals;
        // set up the search space and other helpers at the same time
        actionSequencer = new ActionSequencer();
        actionSequencerOp = new ActionSequencer();
        return this;
    }

    public CoEvoAgent setUseShiftBuffer(boolean useShiftBuffer) {
        this.useShiftBuffer = useShiftBuffer;
        return this;
    }

    Dimension dimension = new Dimension(800, 600);

    public CoEvoAgent setDimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public CoEvoAgent setVisual() {
        playoutPlotter = new PlayoutPlotter().setDimension(dimension);
        playoutPlotter.startPlot(sequenceLength);
        return this;
    }

    public CoEvoAgent setSequenceLength(int sequenceLength) {
        this.sequenceLength = sequenceLength;
        return this;
    }

    public int[] getActions(AbstractGameState gameState, int playerId) {
        searchSpace = new RegularSearchSpace(sequenceLength, gameState.nActions());

        gameAdapter = new GameAdapter().setEvaluator(actionSequencer).setSearchSpace(searchSpace);
        gameAdapterOp = new GameAdapter().setEvaluator(actionSequencerOp).setSearchSpace(searchSpace);

        actionSequencer.setGameState(gameState.copy()).setPlayerId(playerId);
        actionSequencerOp.setGameState(gameState.copy()).setPlayerId(1 -playerId);

        if (solution != null) {
            solution = SearchSpaceUtil.shiftLeftAndRandomAppend(solution, searchSpace);
            opPlan = SearchSpaceUtil.shiftLeftAndRandomAppend(opPlan, searchSpace);
            evoAlg.setInitialSeed(solution, opPlan);
        }
        gameAdapter.reset();
        ArrayList<int[]> results = evoAlg.runTrial(gameAdapter, gameAdapterOp, nEvals);
        solution = results.get(0);
        opPlan =  results.get(1);

        playoutPlotter.plotPlayout();
        // System.out.println(Arrays.toString(solution) + "\t " + game.evaluate(solution));
        int[] tmp = solution;
        // already return the first element, so now set it to 1 ...
        if (!useShiftBuffer) solution = null;
        return tmp;
    }

    public String toString() {
        return "EA: " + evoAlg.getClass().getSimpleName() + " : " + nEvals + " : " + sequenceLength;
    }

    public int getAction(AbstractGameState gameState, int playerId) {
        return getActions(gameState, playerId)[0];
    }

}

