package teamZero.spinbattle.test;

import agents.dummy.DoNothingAgent;
import agents.dummy.RandomAgent;
import agents.evo.EvoAgent;
import ggi.agents.EvoAgentFactory;
import ggi.core.GameRunnerTwoPlayer;
import ggi.core.SimplePlayerInterface;
import teamZero.spinbattle.core.FalseModelAdapter;
import teamZero.spinbattle.core.SpinGameStateFactory;
import teamZero.spinbattle.params.SpinBattleParams;
import utilities.ElapsedTimer;

public class GeneralGameRunnerTest {
    public static void main(String[] args) {
        ElapsedTimer t = new ElapsedTimer();

        int maxTicks = 2000;
        GameRunnerTwoPlayer runner = new GameRunnerTwoPlayer().setLength(maxTicks);
        SimplePlayerInterface randomAgent = new RandomAgent();
        // SimplePlayerInterface p2 = new RandomAgent();

        SimplePlayerInterface doNothingAgent = new DoNothingAgent();
        EvoAgentFactory evoAgentFactory = new EvoAgentFactory();
        evoAgentFactory.useShiftBuffer = true;
        evoAgentFactory.mutationRate = 5;
        evoAgentFactory.totalRandomMutation = true;
        // evoAgentFactory.totalRandomMutation = true
        EvoAgent evoAgent = evoAgentFactory.getAgent();
        // evoAgent.setVisual();
        evoAgent.setSequenceLength(100);
        runner.setPlayers(evoAgent, doNothingAgent);
        SpinGameStateFactory factory = new SpinGameStateFactory();
        factory.params.maxTicks = maxTicks;
        // factory.params.transitSpeed = 0;

        runner.setGameFactory(factory);

        // runner.ga
        // todo: work out why this fails
        // runner.playGames(20);

        // todo: while this one works
        for (int i=0; i<10; i++) {
            factory.params.getRandom().setSeed(i);
//            evoAgent2.setSequenceLength(50);
            EvoAgent evoAgent3 = evoAgentFactory.getAgent().setSequenceLength(100);
            evoAgent3.nEvals = 10;
            // evoAgent3.setVisual();

            // evoAgentFactory.totalRandomMutation = false;
            EvoAgent evoAgent1 = evoAgentFactory.getAgent().setSequenceLength(100);
            // evoAgent1.setVisual();
            // evoAgent1.useShiftBuffer = false;
            evoAgent1.nEvals = 10;

            SpinBattleParams params = new SpinBattleParams();
            // params.gravitationalFieldConstant *= 0;
            params.transitSpeed = 0.00001;

            params.clampZeroScore = false;
            FalseModelAdapter falsePlayer = new FalseModelAdapter().setPlayer(evoAgent3).setParams(params);

            // runner.delay = 100;
            runner.setPlayersWithoutReset(randomAgent, falsePlayer);
            runner.playGame();
            System.out.println(runner.p1Wins + "\t " + runner.p2Wins);
            System.out.println();
        }
        runner.plotGameScores();
        System.out.println(runner.scores);
    }
}
