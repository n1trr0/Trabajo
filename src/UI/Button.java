package UI;

import input.Mouse;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * Clase que representa un botón interactivo en la interfaz de usuario.
 * @author Raul Garcia & Alejandro Molero
 */
public class Button {

    /**
     * Imagen del botón cuando el mouse no está sobre él.
     */
    private BufferedImage mouseOutImg;

    /**
     * Imagen del botón cuando el mouse está sobre él.
     */
    private BufferedImage mouseInImg;

    /**
     * Indica si el mouse está sobre el botón.
     */
    private boolean mouseIn;

    /**
     * Área rectangular del botón en la pantalla.
     */
    private Rectangle boundingBox;

    /**
     * Acción a realizar cuando se hace clic en el botón.
     */
    private Action action;

    /**
     * Texto asociado al botón.
     */
    private String text;

    /**
     * Constructor de la clase Button.
     *
     * @param Out    Imagen del botón cuando el mouse no está sobre él.
     * @param In     Imagen del botón cuando el mouse está sobre él.
     * @param x      Coordenada x del botón en la pantalla.
     * @param y      Coordenada y del botón en la pantalla.
     * @param text   Texto asociado al botón.
     * @param action Acción a realizar cuando se hace clic en el botón.
     */
    public Button(BufferedImage Out, BufferedImage In, int x, int y, String text, Action action){
        this.mouseInImg = In;
        this.mouseOutImg = Out;
        this.text = text;
        boundingBox = new Rectangle(x, y, mouseInImg.getWidth(), mouseInImg.getHeight());
        this.action = action;
    }

    /**
     * Actualiza el estado del botón.
     */
    public void update(){
        if(boundingBox.contains(Mouse.x, Mouse.y)){
            mouseIn = true;
        }
        else{
            mouseIn = false;
        }
        if(mouseIn && Mouse.clickIzq){
            action.doAction();
        }
    }

    /**
     * Dibuja el botón en el contexto gráfico proporcionado.
     *
     * @param graphics El contexto gráfico en el que se realizará el dibujo.
     */
    public void draw(Graphics graphics){
        if(mouseIn){
            graphics.drawImage(mouseInImg, boundingBox.x, boundingBox.y, null);
        }
        else{
            graphics.drawImage(mouseOutImg, boundingBox.x, boundingBox.y, null);
        }
    }
}
