package c_cubed.coevo;

import evodef.EvolutionLogger;
import evodef.SearchSpace;

public class GameAdapter {

    ActionSequencer pluginEvaluator;
    SearchSpace searchSpace;
    EvolutionLogger logger = new EvolutionLogger();

    public GameAdapter setEvaluator(ActionSequencer pluginEvaluator) {
        this.pluginEvaluator = pluginEvaluator;
        return this;
    }

    public GameAdapter setSearchSpace(SearchSpace searchSpace) {
        this.searchSpace = searchSpace;
        return this;
    }

    public void reset() {
        logger.reset();
    }

    public double evaluate(int[] solution, int[] opSolution) {
        // System.out.println("Evaluating: " + Arrays.toString(solution));
        double fitness = pluginEvaluator.fitness(solution, opSolution);
        // System.out.println(fitness);
        logger.log(fitness, solution, false);
        return fitness;
    }

    public boolean optimalFound() {
        return false;
    }

    public SearchSpace searchSpace() {
        return searchSpace;
    }

    public int nEvals() {
        return logger.nEvals();
    }

    public EvolutionLogger logger() {
        return logger;
    }

    public Double optimalIfKnown() {
        return null;
    }
}
