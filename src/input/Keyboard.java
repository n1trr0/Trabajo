package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Maneja las entradas por teclado
 * @author Raul Garcia & Alejandro Molero
 *
 */
public class Keyboard implements KeyListener {
    /**
     * Almacena las teclas
     * @param keys
     */
    private  boolean[] keys = new boolean[256];
    /**
     * Cada boolean se asignara a una tecla
     * @param UP tecla W
     * @param LEFT tecla A
     * @param RIGHT tecla S
     * @param SHOOT tecla SPACE
     * @param PAUSE tecla ESCAPE
     */
    public static boolean UP, LEFT, RIGHT, SHOOT, PAUSE;

    /**
     * Inicializa todos los parametros en falso
     */
    public Keyboard(){
        UP = false;
        LEFT = false;
        RIGHT = false;
        SHOOT = false;
        PAUSE = false;
    }

    /**
     * Asigna cada parametro a una tecla
     */
    public void update(){
        UP = keys[KeyEvent.VK_W];
        RIGHT = keys[KeyEvent.VK_D];
        LEFT = keys[KeyEvent.VK_A];
        SHOOT = keys[KeyEvent.VK_SPACE];
        PAUSE = keys[KeyEvent.VK_ESCAPE];
    }
    public void setPAUSE(boolean option){
        PAUSE = option;
    }

    /**
     * Se debe implementar pero no se usa
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Cuando se pulsa una tecla esta pasa a ser true
     * @param e es un KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (!keys[e.getKeyCode()]) {
                keys[e.getKeyCode()] = true;
            } else {
                keys[e.getKeyCode()] = false;
            }
        }
        else{
            keys[e.getKeyCode()] = true;
        }

    }

    /**
     * Cuando se deja de pulsar una tecla esta pasa a ser falsa
     * @param e es un KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() != KeyEvent.VK_ESCAPE){
            keys[e.getKeyCode()] = false;
        }

    }

}