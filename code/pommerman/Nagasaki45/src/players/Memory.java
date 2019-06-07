package players;

import core.GameState;
import utils.Types;

import java.util.HashMap;
import java.util.Map;

public class Memory {

    private static Map<Types.TILETYPE, Integer> defaultMemoryDuration = new HashMap<Types.TILETYPE, Integer>() {{
        put(Types.TILETYPE.PASSAGE, 400);
        put(Types.TILETYPE.RIGID, 400);
        put(Types.TILETYPE.WOOD, 100);
        put(Types.TILETYPE.BOMB, Types.BOMB_LIFE);
        put(Types.TILETYPE.FLAMES, Types.FLAME_LIFE);
//        put(Types.TILETYPE.FOG, 0);
        put(Types.TILETYPE.EXTRABOMB, 20);
        put(Types.TILETYPE.INCRRANGE, 20);
        put(Types.TILETYPE.KICK, 20);
        put(Types.TILETYPE.AGENTDUMMY, 20);
//        put(Types.TILETYPE.AGENT0, 2);
//        put(Types.TILETYPE.AGENT1, 2);
//        put(Types.TILETYPE.AGENT2, 2);
//        put(Types.TILETYPE.AGENT3, 2);
    }};

    private Map<Types.TILETYPE, Integer> memoryDuration;
    private Types.TILETYPE[][] memory;
    private int[][] ticksToRemember;

    public Memory()
    {
        this(defaultMemoryDuration);
    }

    public Memory(Map<Types.TILETYPE, Integer> memoryDuration)
    {
        memory = new Types.TILETYPE[Types.BOARD_SIZE][Types.BOARD_SIZE];
        ticksToRemember = new int[Types.BOARD_SIZE][Types.BOARD_SIZE];
        this.memoryDuration = memoryDuration;
        for (int i = 0; i < Types.BOARD_SIZE; i++)
        {
            for (int j = 0; j < Types.BOARD_SIZE; j++)
            {
                this.ticksToRemember[i][j] = 0;
                this.memory[i][j] = Types.TILETYPE.FOG;
            }
        }
    }

    public GameState update(GameState gameState) {
        Types.TILETYPE[][] board = gameState.getBoard();
        for (int i = 0; i < Types.BOARD_SIZE; i++)
        {
            for (int j = 0; j < Types.BOARD_SIZE; j++)
            {
                if (board[i][j] == Types.TILETYPE.FOG)
                {
                    if (ticksToRemember[i][j] > 0)
                    {
                        gameState.addObject(j, i, memory[i][j]);
                    }
                }
                else
                {
                    memory[i][j] = board[i][j];
                    ticksToRemember[i][j] = memoryDuration.getOrDefault(board[i][j], 0);
                }
            }
        }
        decrementTicksToRemember();
//        System.out.println(gameState);
        return gameState;
    }

    private void decrementTicksToRemember()
    {
        for (int i = 0; i < Types.BOARD_SIZE; i++)
        {
            for (int j = 0; j < Types.BOARD_SIZE; j++)
            {
                this.ticksToRemember[i][j] -= 1;
            }
        }
    }
}
