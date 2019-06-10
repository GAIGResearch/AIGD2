package pommerball_players;

import core.GameState;
import utils.Types;

import java.util.Random;
import core.Player;

public class ojs_ai extends Player {
    private Random random;

    public ojs_ai(long seed, int id) {
        super(seed, id);
        random = new Random(seed);
    }


    @Override
    public Types.ACTIONS act(GameState gs) {
        int actionIdx = random.nextInt(gs.nActions());
        return Types.ACTIONS.all().get(actionIdx);
    }

    @Override
    public Player copy() {
        return new ojs_ai(seed, playerID);
    }
}
