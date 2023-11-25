package gameObjects;

import graphics.Text;
import math.Vector2D;
import states.GameState;

import java.awt.*;

/**Implementa y gestiona los mensajes que salen por pantalla
 * @author Raul Garcia & Alejandro Molero
 */

public class Message {
    /**
     * Valor de la transparencia del texto
     */
    private float alpha;
    /**
     * Contenido del mensaje
     */
    private String text;
    /**
     * Posicion del mensaje
     */
    private Vector2D position;
    /**
     * Color del texto
     */
    private Color color;
    /**
     * Determina si el mensaje debe aparecer en el centro de la pantalla
     */
    private boolean center;
    /**
     * Desvanecimiento del texto
     */
    private boolean fade;
    /**
     * La fuente que se usara para el texto
     */
    private Font font;
    /**
     * Determina si el mensaje debe ser eliminado
     */
    private boolean dead;
    /**
     * Ajusta la velocidad del parametro alpha
     */
    private final float deltaAlpha = 0.01f;

    /**
     * Constructor de Message
     * @param position Posicion del texto
     * @param fade Desvenecimiento del texto
     * @param text Contenido del texto
     * @param color Color del texto
     * @param center Si el texto debe estar en el centro de la pantalla
     * @param font Fuente empleada para el texto
     */
    public Message(Vector2D position, boolean fade, String text, Color color,
                   boolean center, Font font){
        this.font = font;
        this.text = text;
        this.position = position;
        this.fade = fade;
        this.color = color;
        this.center = center;

        if(fade){
            alpha = 1;
        }
        else{
            alpha = 0;
        }
    }

    /**
     * Dibujo el texto por pantalla
     * @param graphics2D
     */
    public void draw(Graphics2D graphics2D){
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        Text.drawText(graphics2D, text, position, center, color, font);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        position.setY(position.getY()- 1);

        if(fade){
            alpha -= deltaAlpha;
        }
        else{
            alpha += deltaAlpha;
        }
        if(fade && alpha < 0){
            dead = true;
        }
        if(!fade && alpha > 1){
            fade = true;
            alpha = 1;
        }
    }

    /**
     * Comprueba si el texto debe ser eliminado
     * @return el estado de booleano dead
     */
    public boolean isDead(){return dead;}
}
