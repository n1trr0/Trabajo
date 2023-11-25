package graphics;

import math.Vector2D;

import java.awt.*;

/**
 * Implementa los textos
 * @author Raul Garcia & Alejandro Molero
 */

public class Text {
    /**
     * Dibuja el texto segun los parametros indicados
     * @param graphics Objeto de la clase Grapichs
     * @param text Contenido del texto
     * @param pos Posicion del texto
     * @param center Determina si debe estar centrado
     * @param color Color del texto
     * @param font Fuente del texto
     */
    public static void drawText(Graphics graphics, String text, Vector2D pos, boolean center, Color color, Font font){
        graphics.setColor(color);
        graphics.setFont(font);
        Vector2D position = new Vector2D(pos.getX(), pos.getY());

        if(center){
            FontMetrics fontMetrics = graphics.getFontMetrics();
            position.setX(position.getX()- fontMetrics.stringWidth(text) / 2);
            position.setY(position.getY()- fontMetrics.getHeight() / 2);
        }

        graphics.drawString(text, (int) position.getX(), (int) position.getY());
    }
}
