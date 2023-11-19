package gameObjects;

import graphics.Text;
import math.Vector2D;
import states.GameState;

import java.awt.*;

public class Message {
    /**
     * Valor de la transparencia del texto
     * @param alpha
     */
    private float alpha;
    private String text;
    private Vector2D position;
    private Color color;
    private boolean center;
    private boolean fade;
    private Font font;
    private boolean dead;
    /**
     * Ajusta la velocidad del parametro alpha
     * @param deltaAlpha
     */
    private final float deltaAlpha = 0.01f;

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
    public boolean isDead(){return dead;}
}
