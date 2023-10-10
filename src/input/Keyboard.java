package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private  boolean[] keys = new boolean[256];
    public static boolean UP, LEFT, RIGHT, SHOOT;
    public Keyboard(){
        UP = false;
        LEFT = false;
        RIGHT = false;
        SHOOT = false;
    }

    public void update(){
        UP = keys[KeyEvent.VK_W];                          //UP is associated to the actual up key
        RIGHT = keys[KeyEvent.VK_D];                    //RIGHT is associated to the actual right key
        LEFT = keys[KeyEvent.VK_A];                      //LEFT is associated to the actual left key
        SHOOT = keys[KeyEvent.VK_SPACE];
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
