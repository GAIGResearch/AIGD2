package teamZero.spinbattle.ui;

import planetwar.GameState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {
    static int nControllers = 0;

    int action = GameState.doNothing;

    public KeyController() {
        System.out.println("Made a new key controller: " + ++nControllers);
    }

    public int action(GameState gameState) {
        // this is defined to comply with the standard interface
        int temp = action;
        action = GameState.doNothing;
        return temp;
    }

    // need to be a bit careful with the logic for this
    // we need to record the action

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // set the default - or not - if this is set
        // then it every key press just has one action (i.e. no auto-repeat)
        // action = AsteroidsGameState.doNothing;
        if (key == KeyEvent.VK_LEFT) {
            action = GameState.decFocus;
        }
        if (key == KeyEvent.VK_RIGHT) {
            action = GameState.incFocus;
        }
        if (key == KeyEvent.VK_DOWN) {
            action = GameState.decBuffer;
        }
        if (key == KeyEvent.VK_UP) {
            action = GameState.incBuffer;
        }
        if (key == KeyEvent.VK_SPACE) {
        }
    }

    public void keyReleased(KeyEvent e) {
        // active actions are only made on KeyDown
        // so set the action to do nothing
        // action = AsteroidsGameState.doNothing;
    }
}
