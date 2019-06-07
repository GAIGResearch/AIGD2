package nagasaki45_players;

import core.GameState;
import core.Player;
import utils.Types;

import java.util.Random;

public class HumanPlayer extends Player {
    private Random random;
    private KeyController keyboard;

    public HumanPlayer(KeyController ki, int id) {
        super(0, id);
        keyboard = ki;
    }

    public KeyController getKeyAdapter() {return keyboard;}

    @Override
    public Types.ACTIONS act(GameState gs)
    {
        return keyboard.getNextAction();
    }

    @Override
    public Player copy() {
        return new HumanPlayer(getKeyAdapter().copy(), playerID);
    }
}
