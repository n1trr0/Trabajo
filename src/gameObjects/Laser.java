package gameObjects;

import math.Vector2D;
import states.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Implementa los disparos del jugador
 * @author Raul Garcia & Alejandro Molero
 */
public class Laser extends MovingObject{
    /**
     * Constructor
     * @param position Posicion de la bala
     * @param velocity Velocidad actual
     * @param maxVelocity Velocidad maxima
     * @param angle Angulo con el que se dirige
     * @param texture Imagen de la bala
     * @param gameState Estado actual de la partida
     */
    public Laser(Vector2D position, Vector2D velocity, double maxVelocity, double angle, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVelocity, texture, gameState);
        this.angle = angle;
        this.velocity = velocity.scale(maxVelocity);
    }

    /**
     * Cambia la posicion, comprueba si colisiona y lo elimina si sale de la pantalla
     */
    @Override
    public void update() {

        position = position.add(velocity);
        if(position.getX() < 0 || position.getX() > Constants.WIDTH ||
            position.getY() < 0 || position.getY() > Constants.HEIGHT){
            destroy();
        }
        collidesWith();
    }

    /**
     * Dibuja por pantalla el disparo
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D =(Graphics2D) graphics;
        affineTransform = AffineTransform.getTranslateInstance(position.getX() -width/2,position.getY());
        affineTransform.rotate(angle, width/2,0);
        graphics2D.drawImage(texture, affineTransform,null);
    }
}