package gameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.assets;
import input.Keyboard;
import math.Vector2D;
import states.GameState;

/**
 * Implementa al jugador
 * @author Raul Garcia & Alejandro Molero
 */

public class Player extends MovingObject {

    /**
     * Hacia donde se dirige el jugador
     * @param heading
     */
    private Vector2D heading;
    /**
     * Aceleraccion del jugador
     * @param acceleration
     */
    private Vector2D acceleration;
    /**
     * Cadencia de disparo del jugador
     * @param fireRate
     */
    private Chronometer fireRate;

    /**
     * Crea un jugador a partir del super de MovingObject y de heading, acceleration & fireRate
     * @param position Posicion del objecto
     * @param velocity Velocidad del objecto
     * @param maxVelocity Velocidad maxima del objecto
     * @param texture Imagen del objecto
     * @param gameState Estado del juego
     */

    public Player(Vector2D position, Vector2D velocity, double maxVelocity, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVelocity, texture, gameState);
        heading = new Vector2D(0, 1);
        acceleration = new Vector2D();
        fireRate = new Chronometer();
    }

    /**
     * Actualiza el jugador comprobando si se ha movido o a disparado
     */
    @Override
    public void update() {
        if (Keyboard.SHOOT && !fireRate.isRunning()) {
            gameState.getMovingObjects().add(0, new Laser(
                    getCenter().add(heading.scale(width / 3)),
                    heading,
                    Constants.LASER_VELOCITY,
                    angle,
                    assets.fireball,
                    gameState
            ));
            fireRate.run(Constants.FIRE_RATE);
        }

        if (Keyboard.RIGHT)
            angle += Math.PI / 40;
        if (Keyboard.LEFT)
            angle -= Math.PI / 40;

        if (Keyboard.UP) {
            acceleration = heading.scale(Constants.acc);
        } else {
            if (velocity.getMagnitude() != 0) {
                acceleration = (velocity.scale(-1).normalize()).scale(0.1);
            }
        }
        velocity = velocity.add(acceleration);
        velocity = velocity.limit(maxVelocity);
        heading = heading.setDirection(angle - Math.PI / 2);
        position = position.add(velocity);

        //pasar de un lado a otro pantalla
        /*if(position.getX() > 1920){
            position.setX(0);
        }
        if(position.getY() > 1080){
            position.setY(0);
        }
        if(position.getX() < 0){
            position.setX(1920);
        }
        if(position.getY() < 0){
            position.setY(1080);
        }*/

        fireRate.update();
        collidesWith();
    }

    /**
     * Dibuja los graficos por pantalla
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;

        affineTransform = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        affineTransform.rotate(angle, width / 2, height / 2);

        graphics2D.drawImage(texture, affineTransform, null);


    }
}
