package c_cubed.coevo;

import evodef.DefaultMutator;

public class CoEvoAgentFactory {

    public double mutationRate = 2;
    public boolean totalRandomMutation = false;
    public boolean useShiftBuffer = true;

    public CoEvoAgent getAgent() {
        //
        // todo Add in the code t make this
        int nResamples = 1;

        DefaultMutator mutator = new DefaultMutator(null);
        // setting to true may give best performance
        mutator.pointProb = mutationRate;
        mutator.totalRandomChaosMutation = totalRandomMutation;

        DefaultMutator opMutator = new DefaultMutator(null);
        // setting to true may give best performance
        opMutator.pointProb = 50;
        opMutator.totalRandomChaosMutation = totalRandomMutation;

        CRMHC searchAlg = new CRMHC();
        searchAlg.setSamplingRate(nResamples);
        searchAlg.setMutator(mutator, opMutator);

        CRMHC evoAlg = searchAlg;

        // evoAlg = new SlidingMeanEDA();

        int nEvals = 40;
        int seqLength = 50;
        CoEvoAgent evoAgent = new CoEvoAgent().setEvoAlg(evoAlg, nEvals).setSequenceLength(seqLength);
        evoAgent.setUseShiftBuffer(useShiftBuffer);
        // evoAgent.setVisual();

        return evoAgent;
    }
}
