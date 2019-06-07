package objects;

import utils.Types;
import utils.Utils;
import utils.Vector2d;

import java.awt.*;
import java.util.ArrayList;

import static utils.Types.*;

public class Bomb extends GameObject {

    private int blastStrength;
    private Vector2d velocity;
    private int playerIdx;
    private boolean remote;
    int explosionType=2;

    public  boolean isRemote() {
        return remote;
    }
    public Bomb(int blastStrength, int life, int pIdx, boolean remote) {
        super(Types.TILETYPE.BOMB);
        this.remote = remote;
        this.life = remote ? BOMB_LIFE+1 : life ;
        this.blastStrength = blastStrength;
        this.playerIdx = pIdx;
        velocity = new Vector2d();

        double x = Math.random();
        if (x<=0.5){explosionType=0;}
        else if (0.5<x & x<=0.75){explosionType=1;}
        else if (0.75<x){explosionType=2;}
    }

    public Bomb() {
        super(Types.TILETYPE.BOMB);
        blastStrength = DEFAULT_BOMB_BLAST;
    }


    public boolean canTrigger() {
        return remote && life - BOMB_LIFE > 2;
    }


    public void triggerRemotely() {
        if(canTrigger())
            life = 0;
    }

    @Override
    public Image getImage() { return remote ? TILETYPE.REMOTEBOMBGO.getImage() : Types.TILETYPE.BOMB.getImage();}


    @Override
    public void tick() {
        if(!remote) {
            life--;
        } else if(remote && life > 0)
        {
            life++;
        }
        desiredCoordinate = position.add(velocity);
    }

    @Override
    public GameObject copy() {
        Bomb copy = new Bomb(blastStrength, life, playerIdx, remote);
        copy.desiredCoordinate = desiredCoordinate.copy();
        copy.position = position.copy();
        copy.velocity = velocity.copy();
        copy.id = hashCode();
        return copy;
    }

    public ArrayList<GameObject> explode(boolean forceExplode, Types.TILETYPE[][] board, Types.TILETYPE[][] powerups) {
        ArrayList<GameObject> flames = new ArrayList<>();

        if (life == 0 || forceExplode) {
            if (VERBOSE)
                System.out.println("KABOOM at "+position.toString());

            // First add the flame at the current position
            tryToAddFlame(position.x, position.y, board, powerups, flames);
            boolean advanceP = true;
            boolean advanceM = true;
            boolean advanceO = true;
            boolean advanceQ = true;
           // int explosionType = 2;
            switch (explosionType) {
                case 0:
                for (int i = 1; i < blastStrength; i++) {
                    if (advanceP) {
                        int x1 = position.x + i;
                        advanceP = tryToAddFlame(x1, position.y, board, powerups, flames);
                    }
                    if (advanceM) {
                        int x2 = position.x - i;
                        advanceM = tryToAddFlame(x2, position.y, board, powerups, flames);
                    }
                }
                advanceM = true;
                advanceP = true;
                for (int i = 1; i < blastStrength; i++) {
                    if (advanceP) {
                        int y1 = position.y + i;
                        advanceP = tryToAddFlame(position.x, y1, board, powerups, flames);
                    }
                    if (advanceM) {
                        int y2 = position.y - i;
                        advanceM = tryToAddFlame(position.x, y2, board, powerups, flames);
                    }
                }
                break;
                case 1:
                    for (int i = 1; i < blastStrength; i++) {
                        if (advanceP) {
                            int x1 = position.x + i;
                            int x2 = position.y + i ;
                            advanceP = tryToAddFlame(x1, x2, board, powerups, flames);
                        }
                        if (advanceM) {
                            int x1 = position.x - i ;
                            int x2 = position.y + i ;
                            advanceM = tryToAddFlame(x1, x2, board, powerups, flames);
                        }
                    }
                    advanceM = true;
                    advanceP = true;
                    for (int i = 1; i < blastStrength; i++) {
                        if (advanceP) {
                            int x1 = position.x + i;
                            int x2 = position.y - i ;
                            advanceP = tryToAddFlame(x1, x2, board, powerups, flames);
                        }
                        if (advanceM) {
                            int x1 = position.x - i ;
                            int x2 = position.y - i ;
                            advanceM = tryToAddFlame(x1, x2, board, powerups, flames);
                        }
                    }
                    break;
                case 2:

                    for (int i = 1; i < blastStrength; i++) {
                        if (advanceP) {
                            int x1 = position.x + i - 1;
                            int x2 = position.y + i -1;
                            advanceP = tryToAddFlame(x1, x2, board, powerups, flames);
                        }
                        if (advanceM) {
                            int x1 = position.x - i + 1;
                            int x2 = position.y + i -1 ;
                            advanceM = tryToAddFlame(x1, x2, board, powerups, flames);
                        }
                        if (advanceO) {
                            int x1 = position.x + i;
                            advanceO = tryToAddFlame(x1, position.y, board, powerups, flames);
                        }
                        if (advanceQ) {
                            int x2 = position.x - i;
                            advanceQ = tryToAddFlame(x2, position.y, board, powerups, flames);
                        }
                    }
                    advanceM = true;
                    advanceP = true;
                    advanceO = true;
                    advanceQ = true;

                    for (int i = 1; i < blastStrength; i++) {
                        if (advanceP) {
                            int x1 = position.x + i-1;
                            int x2 = position.y - i +1;
                            advanceP = tryToAddFlame(x1, x2, board, powerups, flames);
                        }
                        if (advanceM) {
                            int x1 = position.x - i +1;
                            int x2 = position.y - i +1;
                            advanceM = tryToAddFlame(x1, x2, board, powerups, flames);
                        }
                        if (advanceO) {
                            int y1 = position.y + i;
                            advanceO = tryToAddFlame(position.x, y1, board, powerups, flames);
                        }
                        if (advanceQ) {
                            int y2 = position.y - i;
                            advanceQ = tryToAddFlame(position.x, y2, board, powerups, flames);
                        }
                    }
                    break;

            }
            return flames;
        }
        return null;
    }

    private boolean tryToAddFlame(int x, int y, Types.TILETYPE[][] board, Types.TILETYPE[][] powerups,
                                  ArrayList<GameObject> flames) {
        if (x < 0 || y < 0 || x >= board.length || y >= board.length) {
            return false;
        }
        ArrayList<Types.TILETYPE> flameCollisions = new ArrayList<>();
        flameCollisions.add(Types.TILETYPE.RIGID);

        Types.TILETYPE type = board[y][x];
        Flame f = new Flame();
        f.playerIdx = playerIdx;
        boolean success = Utils.setDesiredCoordinate(f, new Vector2d(x, y), board, flameCollisions);
        if (success) {
            f.setPosition(f.getDesiredCoordinate());
            flames.add(f);

            // Power-ups are killed by bombs, so this is commented out now.
//            if (Types.TILETYPE.getPowerUpTypes().contains(board[y][x]))
//                // Powerups temporarily removed from the board, put back into the powerups array to be revealed
//                // when this flame dies
//                powerups[y][x] = board[y][x];

            board[y][x] = f.getType();
            return type != Types.TILETYPE.WOOD;  // Flames should stop at first wooden block
        }
        else
            return false;
    }

    // Getters, setters

    public Vector2d getVelocity() { return velocity; }
    public void setVelocity(Vector2d vel) {
        this.velocity = vel;
    }

    public int getBlastStrength() {
        return blastStrength;
    }
    public void setBlastStrength(int blastStrength) {
        this.blastStrength = blastStrength;
    }

    public int getPlayerIdx() { return playerIdx; }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }
}
