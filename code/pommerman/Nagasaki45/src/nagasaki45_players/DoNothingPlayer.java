package nagasaki45_players;

import core.GameState;
import core.Player;
import utils.Types;

public class DoNothingPlayer extends Player {
    public DoNothingPlayer(int pId) {
        super(0, pId);
    }

    @Override
    public Types.ACTIONS act(GameState gs) {
        return Types.ACTIONS.ACTION_STOP;
    }

    @Override
    public Player copy() {
        return new DoNothingPlayer(playerID);
    }
}
