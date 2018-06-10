package c_cubed.spinbattle.test;

import agents.evo.EvoAgent;
import c_cubed.coevo.CoEvoAgent;
import c_cubed.coevo.CoEvoAgentFactory;
import c_cubed.spinbattle.core.SpinGameStateFactory;
import c_cubed.spinbattle.params.SpinBattleParams;
import ggi.agents.EvoAgentFactory;
import ggi.core.GameRunnerTwoPlayer;
import utilities.ElapsedTimer;

public class GeneralGameRunnerTest {
    public static void main(String[] args) {
        ElapsedTimer t = new ElapsedTimer();

        int maxTicks = 2000;
        GameRunnerTwoPlayer runner = new GameRunnerTwoPlayer().setLength(maxTicks);

        EvoAgentFactory evoAgentFactory = new EvoAgentFactory();
        CoEvoAgentFactory coEvoAgentFactory = new CoEvoAgentFactory();
        evoAgentFactory.useShiftBuffer = true;
        evoAgentFactory.mutationRate = 5;
        evoAgentFactory.totalRandomMutation = true;

        coEvoAgentFactory.useShiftBuffer = true;
        coEvoAgentFactory.mutationRate = 5;
        coEvoAgentFactory.totalRandomMutation = true;

        EvoAgent evoAgent = evoAgentFactory.getAgent().setSequenceLength(100);
        CoEvoAgent coEvoAgent = coEvoAgentFactory.getAgent().setSequenceLength(50);

        evoAgent.nEvals = 20;
        coEvoAgent.nEvals = 20;

        SpinGameStateFactory factory = new SpinGameStateFactory();
        factory.params = new SpinBattleParams();;

        runner.setGameFactory(factory);

        for (int i=0; i<100; i++) {
            factory.params.getRandom();//.setSeed(i);
            runner.setPlayersWithoutReset(evoAgent, coEvoAgent);
            runner.playGame();
            System.out.println(runner.p1Wins + "\t " + runner.p2Wins);
            System.out.println();
        }
        runner.plotGameScores();
        System.out.println(runner.scores);
    }
}
