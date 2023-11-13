package gameObjects;

import math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * GameObject es una clase abstracta que sirve para crear los objectos del juego
 * @author Raul Garcia & Alejandro Molero
 *
 */

public abstract class GameObject {
    /**
     * Almacena la imagen del objecto
     * @param texture
     */
    protected BufferedImage texture;
    /**
     * Almacena la posicion del objecto
     * @param position
     */
    protected Vector2D position;

    /**
     * Crea un objecto recibiendo como parametros un Vector2D que almacena la posicion
     * y una BufferedImage que almacena la imagen del objecto
     * @param position Posicion del objecto
     * @param texture  Imagen del objecto
     */

    public GameObject(Vector2D position,BufferedImage texture){
        this.position= position;
        this.texture= texture;
    }
    public abstract void update();
    public abstract void draw(Graphics graphics);
    /**
     * Devuelve la posicion del objecto
     * @return Devuelve la posicion del objecto
     */
    public Vector2D getPosition() {
        return position;
    }
    /**
     * Modifica la posicion del objecto
     * @param position Nueva posicion del objecto
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }
}
