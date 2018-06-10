package teamZero.spinbattle;

import core.player.AbstractMultiPlayer;
import ggi.core.AbstractGameState;
import ggi.core.SimplePlayerInterface;
import gvglink.PlanetWarsLinkState;
import ontology.Types;
import tools.ElapsedCpuTimer;
import teamZero.spinbattle.controllers.multiPlayer.treeReusageDiscountOLMCTS.Agent;

public class GVGAIWrapper implements SimplePlayerInterface {

    AbstractMultiPlayer agent;

    public GVGAIWrapper setAgent(AbstractMultiPlayer agent) {
        this.agent = agent;
        return this;
    }

    // thinking time in milli seconds
    int thinkingTime = 5;

    @Override
    public int getAction(AbstractGameState gameState, int playerId) {
        PlanetWarsLinkState linkState = new PlanetWarsLinkState(gameState);

        ElapsedCpuTimer timer = new ElapsedCpuTimer();
        timer.setMaxTimeMillis(thinkingTime);
        Agent a = (Agent) this.agent;
        a.id = playerId;
        a.oppID = (a.id + 1) % 2;

        Types.ACTIONS actions = a.act(linkState, timer);
        return actions.ordinal();
    }

    @Override
    public SimplePlayerInterface reset() {
        return this;
    }
}
