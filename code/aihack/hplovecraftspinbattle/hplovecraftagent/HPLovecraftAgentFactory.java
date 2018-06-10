package hplovecraftspinbattle.hplovecraftagent;

import evodef.DefaultMutator;
import evodef.EvoAlg;
import ga.SimpleRMHC;

public class HPLovecraftAgentFactory {
    public double mutationRate = 2;
    public boolean totalRandomMutation = false;
    public boolean useShiftBuffer = true;

    public HPLovecraftAgent getAgent() {
        //
        // todo Add in the code t make this
        int nResamples = 1;

        DefaultMutator mutator = new DefaultMutator(null);
        // setting to true may give best performance
        mutator.pointProb = mutationRate;
        mutator.totalRandomChaosMutation = totalRandomMutation;

        SimpleRMHC simpleRMHC = new SimpleRMHC();
        simpleRMHC.setSamplingRate(nResamples);
        simpleRMHC.setMutator(mutator);

        EvoAlg evoAlg = simpleRMHC;

        // evoAlg = new SlidingMeanEDA();

        int nEvals = 20;
        int seqLength = 800;
        HPLovecraftAgent evoAgent = new HPLovecraftAgent().setEvoAlg(evoAlg, nEvals).setSequenceLength(seqLength);
        evoAgent.setUseShiftBuffer(useShiftBuffer);
        // evoAgent.setVisual();

        return evoAgent;
    }
}
