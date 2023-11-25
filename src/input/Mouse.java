package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Implementa la funcionalidad del raton
 */
public class Mouse extends MouseAdapter {
    /**
     * Posicion del raton en x e y respectivamente
     */
    public static int x, y;
    /**
     * Determina si clickIzq debe ser marcado como pulsado
     */
    public static boolean clickIzq;

    /**
     * Determina si el raton ha sido arrastrado (click and move combined)
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /**
     * Determina si un boton fue presionado
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()== MouseEvent.BUTTON1){
            clickIzq = true;
        }
    }
    /**
     * Determina si un boton dejo de ser presionado
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()== MouseEvent.BUTTON1){
            clickIzq = false;
        }
    }

    /**
     * Determina si el raton ha sido movido
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}
