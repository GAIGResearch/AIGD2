package c_cubed.coevo;

import evodef.*;
import utilities.StatSummary;

import java.util.ArrayList;

import java.util.Random;

public class CRMHC {

    // Random mutation hill climber for testing one-max
    static Random random = new Random();

    int[] bestYet;
    public int[] bestYetOp;

    int[] seed;
    int[] opSeed;
    // SolutionEvaluator evaluator
    SearchSpace searchSpace;

    private int nSamples;

    public CRMHC() {
        this(1);
    }

    public CRMHC(int nSamples) {
        this.nSamples = nSamples;
    }

    public String toString() {
        return String.format("CRMHC, r=%d", nSamples);
    }

    public void setSamplingRate(int n) {
        this.nSamples = n;
    }


    // should not be a static, just did it quick and dirty

    // this just adds a noisy test within the algorithm
    // normally set to false
    static boolean noisy = false;

    static double epsilon = 1.0;

    //public static boolean accumulateBestYetStats = false;

    // this is only checked if not resampling parent
    public static boolean resampleParent = true;


    public void setInitialSeed(int[] seed, int[] opSeed) {

        this.seed = seed;
        this.opSeed = opSeed;
    }

    Mutator mutator;
    Mutator opMutator;

    public CRMHC setMutator(Mutator mutator, Mutator opMutator) {
        this.mutator = mutator;
        this.opMutator = opMutator;
        return this;
    }

    /**
     * @param evaluator
     * @param maxEvals
     * @return: the solution coded as an array of int
     */
    public ArrayList<int[]> runTrial(GameAdapter evaluator, GameAdapter evaluatorOp, int maxEvals) {
        init(evaluator);
        StatSummary fitBest = fitness(evaluator, bestYet, bestYetOp, new StatSummary());
        StatSummary fitBestOp = fitness(evaluatorOp, bestYetOp, bestYet, new StatSummary());

        // create a mutator if it has not already been made
        if (mutator == null)
            mutator = new DefaultMutator(searchSpace);
        else {
            mutator.setSearchSpace(searchSpace);
            opMutator.setSearchSpace(searchSpace);
        }

        while (evaluator.nEvals() < maxEvals ){//&& !evaluator.optimalFound()) {
            int[] mut = mutator.randMut(bestYet);
            int[] mutOp = opMutator.randMut(bestYetOp);

            // keep track of how much we want to mutate this
            int prevEvals = evaluator.nEvals();
            StatSummary fitMut = fitness(evaluator, mut, bestYetOp, new StatSummary());
            StatSummary fitMutOp = fitness(evaluatorOp, mutOp, bestYet, new StatSummary());
//            if (accumulateBestYetStats) {
//                fitBest = fitness(evaluator, bestYet, bestYetOp, fitBest);
//            } else {
//                if (resampleParent) {
//                    fitBest = fitness(evaluator, bestYet, bestYetOp, new StatSummary());
//                }
 //           }
            // System.out.println(fitBest.mean() + " : " + fitMut.mean());
            if (fitMut.mean() >= fitBest.mean()) {
                // System.out.println("Updating best");
                bestYet = mut;
                fitBest = fitMut;
                evaluator.logger().keepBest(mut, fitMut.mean());
            }

            if (fitMutOp.mean() >= fitBestOp.mean()) {
                // System.out.println("Updating best");
                bestYetOp = mutOp;
                fitBestOp = fitMutOp;
                evaluatorOp.logger().keepBest(mutOp, fitMutOp.mean());
            }

            int evalDiff = evaluator.nEvals() - prevEvals;
            for (int i = 0; i < evalDiff; i++) {
                evaluator.logger().logBestYest(bestYet);
            }

        }
        // System.out.println("Ran for: " + evaluator.nEvals());
        // System.out.println("Sampling rate: " + nSamples);
        ArrayList<int[]> results = new ArrayList<>();
        results.add(bestYet);
        results.add(bestYetOp);
        return results;
    }

    BanditLandscapeModel model;


    public void setModel(BanditLandscapeModel nTupleSystem) {
        this.model = nTupleSystem;
    }


    public BanditLandscapeModel getModel() {
        return model;
    }

    StatSummary fitness(GameAdapter evaluator, int[] sol, int[] opSol, StatSummary ss) {
        for (int i = 0; i < nSamples; i++) {
            double fitness = evaluator.evaluate(sol, opSol);
            // System.out.println((int) fitness + "\t " + Arrays.toString(sol));
            ss.add(fitness);
        }
        if (model != null) {
            // System.out.println("Added summary");
            // model.addSummary(sol, ss);
        }
        return ss;
    }

    private void init(GameAdapter evaluator) {
        this.searchSpace = evaluator.searchSpace();
        bestYetOp = SearchSpaceUtil.randomPoint(searchSpace);
        if (seed == null) {
            bestYet = SearchSpaceUtil.randomPoint(searchSpace);

        } else {
            bestYet = SearchSpaceUtil.copyPoint(seed);
            //bestYetOp = SearchSpaceUtil.copyPoint(opSeed);
        }
    }
}


