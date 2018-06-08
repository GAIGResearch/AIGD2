package c_cubed.spinbattle.league;

import agents.dummy.DoNothingAgent;
import agents.dummy.RandomAgent;
import c_cubed.coevo.CoEvoAgentFactory;
import c_cubed.spinbattle.core.SpinGameStateFactory;
import ggi.agents.EvoAgentFactory;
import ggi.core.GameRunnerTwoPlayer;
import ggi.core.SimplePlayerInterface;
import ggi.league.RoundRobinLeague;

import java.util.ArrayList;

/**
 *  Should move this later to the the ggi package ...
 */

public class RoundRobinLeagueTest {

    static int maxTicks = 750;

    public static void main(String[] args) {
        // set up some players
        EvoAgentFactory f1 = new EvoAgentFactory();
        CoEvoAgentFactory f2 = new CoEvoAgentFactory();
        f1.useShiftBuffer = true;
        SimplePlayerInterface p1 = new RandomAgent();
        SimplePlayerInterface p2 = f1.getAgent();
        SimplePlayerInterface p3 = new DoNothingAgent();
        SimplePlayerInterface p4 = f2.getAgent();

        ArrayList<SimplePlayerInterface> players = new ArrayList<>();
        players.add(p1); players.add(p2); players.add(p3); players.add(p4);

        RoundRobinLeague league = new RoundRobinLeague().setPlayers(players);

        SpinGameStateFactory factory = new SpinGameStateFactory();
        factory.params.maxTicks = maxTicks;
        GameRunnerTwoPlayer gameRunner = new GameRunnerTwoPlayer();
        gameRunner.nSteps = maxTicks;

        gameRunner.setGameFactory(factory);
        league.gameRunner = gameRunner;

        int gamesPerMatch = 10;
        league.playGames(gamesPerMatch);

        System.out.println(league);
    }
}
