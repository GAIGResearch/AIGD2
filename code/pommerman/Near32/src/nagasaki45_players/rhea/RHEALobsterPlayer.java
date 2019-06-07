package nagasaki45_players.rhea;

import core.GameState;
import nagasaki45_players.Memory;
import core.Player;
import nagasaki45_players.optimisers.ParameterizedPlayer;
import nagasaki45_players.rhea.utils.RHEALobsterParams;
import utils.ElapsedCpuTimer;
import utils.Types;

import java.util.Random;

import static nagasaki45_players.rhea.utils.Constants.TIME_BUDGET;

public class RHEALobsterPlayer extends ParameterizedPlayer {
    private RollingHorizonLobsterPlayer player;
    private LobsterGameInterface gInterface;
    private RHEALobsterParams params;
    private Memory memory;

    public RHEALobsterPlayer(long seed, int playerID) {
        this(seed, playerID, null);
    }

    public RHEALobsterPlayer(long seed, int playerID, RHEALobsterParams params) {
        super(seed, playerID, params);
        reset(seed, playerID);
    }

    @Override
    public void reset(long seed, int playerID) {
        this.seed = seed;
        this.playerID = playerID;
        this.memory = new Memory();

        // Make sure we have parameters
        this.params = (RHEALobsterParams) getParameters();
        if (this.params == null) {
            this.params = new RHEALobsterParams();
        }

        // Set up random generator
        Random randomGenerator = new Random(seed);

        // Create interface with game
        gInterface = new LobsterGameInterface(this.params, randomGenerator, playerID - Types.TILETYPE.AGENT0.getKey());

        // Set up player
        player = new RollingHorizonLobsterPlayer(randomGenerator, this.params, gInterface);
    }

    @Override
    public Types.ACTIONS act(GameState gs) {
        gs = memory.update(gs);
        ElapsedCpuTimer elapsedTimer = null;
        if (params.budget_type == TIME_BUDGET) {
            elapsedTimer = new ElapsedCpuTimer();
            elapsedTimer.setMaxTimeMillis(params.time_budget);
        }
        setup(gs, elapsedTimer);
        return gInterface.translate(player.getAction(elapsedTimer, 5));
    }

    private void setup(GameState rootState, ElapsedCpuTimer elapsedTimer) {
        gInterface.initTick(rootState, elapsedTimer);
    }

    @Override
    public Player copy() {
        return new RHEALobsterPlayer(seed, playerID, params);
    }
}
