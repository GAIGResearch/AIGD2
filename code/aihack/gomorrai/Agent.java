package gomorrai;

import core.player.AbstractMultiPlayer;
import ggi.core.AbstractGameState;
import ggi.core.SimplePlayerInterface;
import gvglink.PlanetWarsLinkState;
import gomorrai.spinbattle.players.GVGAIWrapper;
import gvglink.SpinBattleLinkState;
import tools.ElapsedCpuTimer;

public class Agent implements SimplePlayerInterface {

    private GVGAIWrapper wrapper = null;

    void initAgent(AbstractGameState gameState, int playerId){
        ElapsedCpuTimer timer = new ElapsedCpuTimer();
        SpinBattleLinkState linkState = new SpinBattleLinkState(gameState);
        AbstractMultiPlayer agent =
                new gomorrai.discountOLMCTS.Agent(linkState.copy(), timer, playerId);
        wrapper = new GVGAIWrapper().setAgent(agent);
    }

    @Override
    public int getAction(AbstractGameState gameState, int playerId) {
        if(wrapper==null){
            initAgent(gameState,playerId);
        }

        return wrapper.getAction(gameState,playerId);
    }

    @Override
    public SimplePlayerInterface reset() {
        if(wrapper!=null){
            wrapper.reset();
        }
        return this;
    }
}
