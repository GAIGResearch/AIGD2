package utils;

import objects.Avatar;
import objects.GameObject;

import java.util.*;

import static utils.Types.BREATHING_SPACE;
import static utils.Types.VERBOSE;

public class LevelGenerator {


    /**
     * Constructs the board: places vethan_players and blocks in the level,
     * guaranteeing a maximum of accessible passages.
     * @param seed Unique seed to generate this board.
     * @param size size of the board (size x size)
     * @param woodProb likelyhood of a free tile being a wooden, destructible, block
     * @param agents Agents to put in the game.
     * @return board created by this algorithm
     */
    public static int[][] makeBoard(long seed, int size,  double woodProb, GameObject[] agents){
        //Build the board
        int[][] board = make(seed, size, woodProb, agents);

        //Make a record of all agent positions
        ArrayList<Vector2d> agent_positions = new ArrayList<>();
        for (GameObject p: agents) {
            agent_positions.add(p.getPosition());
        }

        //Find out how many inaccessible passages exist in the board. If above certain threshold, repeat
        int inaccessPassages = inaccesibleTiles(board, agent_positions).size();
        while (inaccessPassages > Types.MAX_INACCESIBLE_TILES){
            if (VERBOSE) {
                System.out.println("Size of inaccessible passages: " + inaccessPassages);
            }
            board = make(seed, size,  woodProb, agents);
            inaccessPassages = inaccesibleTiles(board, agent_positions).size();
        }

        return board;
    }


    /**
     * Place pick-ups on the board's wooden boxes
     * @param board - the board to check for wood wall placements
     * @param item_probability - likelyhood of a wooden box containing a powerup
     * @param seed - seed for random generator of powerups
     */
    public static int[][] makeItems(int[][] board, double item_probability, long seed) {

        Random random = new Random(seed);                           //Items are set at random
        int[][] items = new int[board.length][board[0].length];     //Items will be here.

        //All items to place.
        Types.TILETYPE[] powerUpTypes = Types.TILETYPE.getPowerUpTypes().keySet().toArray(new Types.TILETYPE[0]);
        int[] distribution = Types.TILETYPE.getPowerUpTypes().values().stream().mapToInt(x->x).toArray();
        int total = Arrays.stream(distribution).sum();

        for(int x = 0; x < board.length; x++) {
          for(int y = 0; y < board[0].length; y++) {
            if(board[x][y] != Types.TILETYPE.WOOD.getKey()){
              continue;
            }

            if(random.nextDouble() <= item_probability) {
              int select = random.nextInt(total);
              for(int i = 0; i < distribution.length; i++) {
                if(select < distribution[i]) {
                  items[x][y] = powerUpTypes[i].getKey();
                  break;
                }
                select -= distribution[i];
              }
            }
          }
        }
        return items;
    }

    /**
     * Constructs a board: places vethan_players and blocks in the level. Doesn't check of inaccessible passages.
     * @param seed Unique seed to generate this board.
     * @param size size of the board (size x size)
     * @param woodProb likelyhood of a free tile being a wooden, destructible block.
     * @param agents Agents to put in the game.
     * @return a int[][] with the walls and player locations in the board.
     */
    private static int[][] make(long seed, int size,  double woodProb, GameObject[] agents){


        // board of all 0s
        int[][] board = new int[size][size];

        if (VERBOSE) {
            System.out.println("Initial map, all 0s: ");
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
        }

        //List of free coordinates on the board.
        ArrayList<Vector2d> available_coordinates = new ArrayList<>();
        for (int i = 0; i<size ; i++){
            for (int j = 0; j < size; j++){
                    available_coordinates.add(new Vector2d(j, i));

            }
        }


        // Locate all the vethan_players in the board. they must respect a Types.CORNER_DISTANCE to
        // their respective corners.
        board[Types.CORNER_DISTANCE][Types.CORNER_DISTANCE] = Types.TILETYPE.AGENT0.getKey();
        board[size-Types.CORNER_DISTANCE -1][Types.CORNER_DISTANCE] = Types.TILETYPE.AGENT1.getKey();
        board[size-Types.CORNER_DISTANCE -1][size-Types.CORNER_DISTANCE -1] = Types.TILETYPE.AGENT2.getKey();
        board[Types.CORNER_DISTANCE][size-Types.CORNER_DISTANCE -1] = Types.TILETYPE.AGENT3.getKey();

        // Keep a list of the agent positions
        ArrayList<Vector2d> agent_positions = new ArrayList<>();
        agent_positions.add(new Vector2d(Types.CORNER_DISTANCE, Types.CORNER_DISTANCE));
        agent_positions.add(new Vector2d(size-Types.CORNER_DISTANCE -1, Types.CORNER_DISTANCE));
        agent_positions.add(new Vector2d(size-Types.CORNER_DISTANCE -1, size-Types.CORNER_DISTANCE -1));
        agent_positions.add(new Vector2d(Types.CORNER_DISTANCE, size-Types.CORNER_DISTANCE -1));

        // and mark those as not available places to put more stuff
        for (Vector2d agent_position : agent_positions) available_coordinates.remove(agent_position);


        if (VERBOSE) {
            System.out.println("Agents added: ");
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
        }

        //Some positions around the agent must be free, so those are not valid coordinates either.
        int loc = Types.CORNER_DISTANCE;
        for (int i = 1; i <= BREATHING_SPACE; i++){
            //top left
            int avX = loc, avY = loc; // location of the avatar in this corner
            available_coordinates.remove(new Vector2d(avX, avY + i));
            available_coordinates.remove(new Vector2d(avX + i, avY));

            //bottom right
            avX = size - 1 - loc; avY = size - 1 - loc;  // location of the avatar in this corner
            available_coordinates.remove(new Vector2d(avX, avY - i));
            available_coordinates.remove(new Vector2d(avX - i, avY));

            //top right
            avX = size - 1 - loc; avY = loc;  // location of the avatar in this corner
            available_coordinates.remove(new Vector2d(avX, avY + i));
            available_coordinates.remove(new Vector2d(avX - i, avY));

            //bottom left
            avX = loc; avY = size - 1 - loc;  // location of the avatar in this corner
            available_coordinates.remove(new Vector2d(avX + i, avY));
            available_coordinates.remove(new Vector2d(avX, avY - i));
        }

        for(int x = 1; x < size; x += 2) {
            for(int y = 1; y < size; y += 2) {
                board[x][y] = Types.TILETYPE.RIGID.getKey();
                available_coordinates.remove(new Vector2d(x, y));
            }
        }
        int WOOD = Types.TILETYPE.WOOD.getKey();
        Random r = new Random(seed);
        for(int x = 0; x < size; x += 1) {
            for(int y = 0; y < size; y += 1) {
                if(available_coordinates.contains((new Vector2d(x,y))) && r.nextDouble() < woodProb) {
                    board[x][y] = WOOD;
                }
            }
        }

        int TELEPORT = Types.TILETYPE.TELEPORT.getKey();
        int counterPortal = 0;
        double selectPortal = Math.random();
        if (selectPortal<=0.5){
            board[2][3] = TELEPORT;
            board[7][8] = TELEPORT;
        }else{
            board[8][2] = TELEPORT;
            board[2][8] = TELEPORT;}

        /*
        Random p = new Random(seed);
        for(int x = 0; x < size; x += 1) {
            for(int y = 0; y < size; y += 1) {
                if(available_coordinates.contains((new Vector2d(x,y))) && counterPortal<2 && p.nextDouble() < 0.9) {
                    board[x][y] = TELEPORT;
                    firstPortal = new Vector2d(x,y);
                    counterPortal ++;
                }
            }
        }*/

        //Finally, set the positions to the agent objects.
        for (int i = 0; i < agents.length; i++) {
            agents[i].setPosition(agent_positions.get(i));
        }

        return board;
    }

    /**
     * Puts two blocks of type 'type'. A random location (x,y) is selected and
     * blocks of type 'type' is added to (x,y) and (y,x)
     * @param seed random seed of this genreator
     * @param type type of block to add
     * @param num_left number of blocks left at this point
     * @param coordinates set of possible coordinates to place a block
     * @param board Board to place a block in.
     * @return number of blocks left to put after this addition.
     */
    private static int placeBlock(long seed, int type, int num_left, ArrayList<Vector2d> coordinates, int[][] board){
        Random r = new Random(seed);
        Vector2d rnd = coordinates.get(r.nextInt(coordinates.size()));
        int x = rnd.x;
        int y = rnd.y;
        coordinates.remove(rnd);
        coordinates.remove(new Vector2d(y, x));
        board[y][x] = type;
        board[x][y] = type;
        num_left-=2;
        return num_left;
    }

    /**
     * Gets all the positions, as vectors, of tiles in the board of type 'type'
     * @param board level of the game
     * @param type type of the tile we're looking for.
     * @return list of positions where there's a tile of type 'type'
     */
    private static ArrayList<Vector2d> findIndices(int[][] board, int type){
        int size = board.length;
        ArrayList<Vector2d> indices = new ArrayList<>();
        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board[i][j] == type ){
                    indices.add(new Vector2d(i, j));
                }
            }
        }
        return indices;
    }

    /**
     * Checks if a position is within the board dimensions
     * @param position position to check
     * @param size size of the board
     * @return true if position is inside board, false otherwise.
     */
    private static boolean isOnBoard(Vector2d position, int size){
        int x = position.x;
        int y = position.y;

        return x < size && x >= 0 && y < size && y >= 0;
    }

    /**
     * Calculates a list of inaccessible tiles (due to board limits and rigid blocks) in the board.
     * @param board the board with blocks already placed.
     * @param agent_positions positions of the agents.
     * @return the list of tiles that are inaccessible
     */
    private static ArrayList<Vector2d> inaccesibleTiles(int[][] board, ArrayList<Vector2d> agent_positions)
    {
        int size = board.length;
        if (VERBOSE) {
            System.out.println("Solving inaccessible passage for board:");
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
        }

        //Get a list of positions in the board that are free.
        ArrayList<Vector2d> passage_positions = findIndices(board, Types.TILETYPE.PASSAGE.getKey());

        //Odd case. No agents mean nothing is unreachable.
        if (agent_positions.size() == 0) {
            passage_positions.clear();
            return passage_positions;
        }


        //Now, use Dijkstra to try to reach them all.

        Set<Vector2d> seen = new HashSet<>();

        Vector2d agent_position = agent_positions.get(agent_positions.size() - 1);
        agent_positions.remove(agent_positions.size() - 1);

        ArrayList<Vector2d> Q = new ArrayList<>();
        Q.add(agent_position);

        Types.DIRECTIONS[] positions_to_be_checked = Types.DIRECTIONS.values();

        while(Q.size() > 0){
            Vector2d rowcol = Q.get(Q.size() - 1);
            Q.remove(Q.size() - 1);

            for (Types.DIRECTIONS position_to_be_checked : positions_to_be_checked) {
                int rowcol_x = rowcol.x;
                int rowcol_y = rowcol.y;

                Vector2d pos = position_to_be_checked.toVec();
                int position_to_be_checked_x = pos.x;
                int position_to_be_checked_y = pos.y;

                Vector2d next_position = new Vector2d(rowcol_x + position_to_be_checked_x,
                        rowcol_y + position_to_be_checked_y);

                if (seen.contains(next_position)) {
                    continue;
                }

                if (!isOnBoard(next_position, size)) {
                    continue;
                }

                if (board[next_position.y][next_position.x] == Types.TILETYPE.RIGID.getKey()){
                    continue;
                }

                if (passage_positions.contains(next_position)) {
                    //If the position has been found, remove it from the list.
                    passage_positions.remove(next_position);

                    //All free positions have been checked
                    if (passage_positions.size() == 0) {
                        return new ArrayList<>();
                    }
                }

                seen.add(next_position);
                Q.add(next_position);
            }
        }

        //All remaining ones hae not been found by the algorithm.
        return passage_positions;
    }


    /**
     * Function to test level generation code.
     */
    public static void main(String[] args) {

        long seed = System.currentTimeMillis();
        GameObject[] agents = new GameObject[]{
                new Avatar(Types.TILETYPE.AGENT0.getKey(), Types.GAME_MODE.FFA),
                new Avatar(Types.TILETYPE.AGENT1.getKey(), Types.GAME_MODE.FFA),
                new Avatar(Types.TILETYPE.AGENT2.getKey(), Types.GAME_MODE.FFA),
                new Avatar(Types.TILETYPE.AGENT3.getKey(), Types.GAME_MODE.FFA)
        };
        int[][] board = LevelGenerator.makeBoard(seed+4, 11, .95, agents);

        if (VERBOSE) {
            System.out.println("Final map generated: ");
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
        }
    }
}