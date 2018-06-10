package teamZero.spinbattle.players;

import ggi.core.AbstractGameState;
import ggi.core.SimplePlayerInterface;

public class RamenPaellaCatAI implements SimplePlayerInterface {

	@Override
	public int getAction(AbstractGameState gameState, int playerId) {
		return 0;
	}

	@Override
	public SimplePlayerInterface reset() {
		return this;
	}

}
