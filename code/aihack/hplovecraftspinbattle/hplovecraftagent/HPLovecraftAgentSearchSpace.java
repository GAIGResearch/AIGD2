package hplovecraftspinbattle.hplovecraftagent;

import agents.dummy.RandomAgent;
import evodef.*;
import ga.SimpleRMHC;
import ggi.core.GameRunnerTwoPlayer;
import ggi.core.SimplePlayerInterface;
import ntuple.params.BooleanParam;
import ntuple.params.DoubleParam;
import ntuple.params.IntegerParam;
import ntuple.params.Param;
import hplovecraftspinbattle.params.SpinBattleParams;

public class HPLovecraftAgentSearchSpace implements AnnotatedFitnessSpace {
    public static void main(String[] args){
        HPLovecraftAgentSearchSpace searchSpace = new HPLovecraftAgentSearchSpace();
        int[] point = SearchSpaceUtil.randomPoint(searchSpace);

        System.out.println(searchSpace.report(point));

        System.out.println();
        System.out.println("Size: " + SearchSpaceUtil.size(searchSpace));
    }

    public Param[] getParams() {
        return new Param[]{
                new DoubleParam().setArray(pointMutationRate).setName("Point Mutation Rate"),
                new BooleanParam().setArray(flipAtLeastOneBit).setName("Flip at least one bit?"),
                new BooleanParam().setArray(useShiftBuffer).setName("Use shift Buffer?"),
                new IntegerParam().setArray(nResamples).setName("nResamples"),
                new IntegerParam().setArray(seqLength).setName("sequence length"),
        };
    }

    double[] pointMutationRate = {1.0, 1.0};
    boolean[] flipAtLeastOneBit = {true, true};
    boolean[] useShiftBuffer = {true, true};
    int[] nResamples = {1, 1};
    int[] seqLength = {600, 800, 1000};

    int[] nValues = new int[]{pointMutationRate.length, flipAtLeastOneBit.length,
            useShiftBuffer.length, nResamples.length, seqLength.length};
    int nDims = nValues.length;

    static int pointMutationRateIndex = 0;
    static int flipAtLeastOneBitIndex = 1;
    static int useShiftBufferIndex = 2;
    static int nResamplesIndex = 3;
    static int seqLengthIndex = 4;

    int nGames = 1;
    int maxSteps = 2000;

    public static int tickBudget = 2000;

    public EvolutionLogger logger;

    public HPLovecraftAgentSearchSpace(){
        this.logger = new EvolutionLogger();
    }

    public String report(int[] solution) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("pointMutationRate:     %.2f\n", pointMutationRate[solution[pointMutationRateIndex]]));
        sb.append(String.format("flipAtLeastOneBit:     %s\n", flipAtLeastOneBit[solution[flipAtLeastOneBitIndex]]));
        sb.append(String.format("useShiftBuffer:        %s\n", useShiftBuffer[solution[useShiftBufferIndex]]));
        sb.append(String.format("nResamples:            %d\n", nResamples[solution[nResamplesIndex]]));
        sb.append(String.format("seqLength:             %d\n", seqLength[solution[seqLengthIndex]]));
        sb.append(String.format("nEvals:                %d\n", getNEvals(solution)));
        return sb.toString();
    }

    public int getNEvals(int[] solution){
        return tickBudget / seqLength[solution[seqLengthIndex]];
    }

    @Override
    public int nDims() {
        return nDims;
    }

    @Override
    public int nValues(int i) {
        return nValues[i];
    }

    // int innerEvals = 2000;

    // int nEvals = 0;
    @Override
    public void reset() {

        // nEvals = 0;
        logger.reset();
    }


    @Override
    public double evaluate(int[] x) {

        // search space will need to be set before use
        DefaultMutator mutator = new DefaultMutator(null);
        mutator.pointProb = pointMutationRate[x[pointMutationRateIndex]];
        mutator.flipAtLeastOneValue = flipAtLeastOneBit[x[flipAtLeastOneBitIndex]];
        mutator.totalRandomChaosMutation = false;

        SimpleRMHC simpleRMHC = new SimpleRMHC();
        simpleRMHC.setSamplingRate(nResamples[x[nResamplesIndex]]);
        simpleRMHC.setMutator(mutator);

        HPLovecraftAgent HPLovecraftAgent = new HPLovecraftAgent().setEvoAlg(simpleRMHC, getNEvals(x));
        HPLovecraftAgent.setUseShiftBuffer(useShiftBuffer[x[useShiftBufferIndex]]);
        HPLovecraftAgent.setSequenceLength(seqLength[x[seqLengthIndex]]);

        // create a problem to evaluate this one on ...
        // this should really be set externally, but just doing it this way for now

        GameRunnerTwoPlayer gameRunner = new GameRunnerTwoPlayer();

        SpinBattleParams params = new SpinBattleParams();
        params.gravitationalFieldConstant *= 1;
        params.transitSpeed *= 1;

        SpinGameFactory gameFactory = new SpinGameFactory().setParams(params);
        ggi.tests.SpeedTest test = new ggi.tests.SpeedTest().setGameFactory(gameFactory);
        test.setPlayers(new SimplePlayerInterface[] {HPLovecraftAgent, new RandomAgent()});

        test.playGames(nGames, maxSteps);

        // todo now run a game and return the result
        // use the evaluation code from yesterdays lab to evaluate the agent we already made
        double value = test.gameScores.mean();
        logger.log(value, x, false);
        return value;
    }

    @Override
    public boolean optimalFound() {
        return false;
    }

    @Override
    public SearchSpace searchSpace() {
        return this;
    }

    @Override
    public int nEvals() {
        return logger.nEvals();
    }

    @Override
    public EvolutionLogger logger() {
        return logger;
    }

    @Override
    public Double optimalIfKnown() {
        return null;
    }
}
