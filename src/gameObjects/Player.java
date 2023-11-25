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
     */
    private Vector2D heading;
    /**
     * Aceleraccion del jugador
     */
    private Vector2D acceleration;
    /**
     * Cadencia de disparo del jugador
     */
    private Chronometer fireRate;
    /**
     * Determina si esta spawneando y si debe ser visble (para la animacion de parpadeo cuando spawneas)
     */
    private boolean spawing,visible;
    /**
     * Determina el tiempo del spawn y el de la animacion (cambiar entre visible/no visble)
     */
    private Chronometer spawntime,flickertime;

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
        spawntime=new Chronometer();
        flickertime=new Chronometer();
    }

    /**
     * Actualiza el jugador comprobando si se ha movido o a disparado, o si esta spawneando
     */
    @Override
    public void update() {
        if(!spawntime.isRunning()){
            spawing=false;
            visible=true;
        }

        //Animacion de cuando spawneas
        if(spawing){
            if(!flickertime.isRunning()){
                flickertime.run(Constants.FLICKER_TIME);
                visible=!visible;
            }
        }

        //Dispara si presionas la tecla correspondiente, el disparo no esta en tiempo de espera y no esta spawneando
        if (Keyboard.SHOOT && !fireRate.isRunning() && !spawing) {
            gameState.getMovingObjects().add(0, new Laser(
                    getCenter().add(heading.scale(width/3)),
                    heading,
                    Constants.LASER_VELOCITY,
                    angle,
                    assets.fireball,
                    gameState
            ));
            fireRate.run(Constants.FIRE_RATE);
        }

        //Comprueba y cambia si es necesario la posicion de jugador en funcion de las teclas presionadas
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

        //Comprueba que no se salga de la pantalla
        if(position.getX() > Constants.WIDTH)
            position.setX(-width);
        if(position.getY() > Constants.HEIGHT)
            position.setY(-height);
        if(position.getX() < -width)
            position.setX(Constants.WIDTH);
        if(position.getY() < -height)
            position.setY(Constants.HEIGHT);

        //Actualiza los cronometros y mira las colisiones
        fireRate.update();
        spawntime.update();
        flickertime.update();
        collidesWith();
    }

    /**
     * Si te quedan vidas respawneas, si no llama a gameOver
     */
    @Override
    public void destroy(){
        spawing=true;
        spawntime.run(Constants.SPAWNING_TIME);
        if(!gameState.loseLive()){
            gameState.gameOver();
            super.destroy();
        }
        resetValues();
    }

    /**
     * Devuelve in boolean que determina si el jugador esta respawneando
     * @return
     */
    public boolean isSpawing(){return spawing;}

    /**
     * Resetea los valores de direccion del jugador para cuando este respawnea
     */
    private void resetValues(){
        angle=0;
        velocity=new Vector2D();
        position=new Vector2D(Constants.WIDTH/2-assets.player.getWidth()/2,
                Constants.HEIGHT/2-assets.player.getHeight()/2);
    }
    /**
     * Dibuja los graficos por pantalla
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics) {
        if(!visible){
            return;
        }

        Graphics2D graphics2D = (Graphics2D) graphics;

        affineTransform = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        affineTransform.rotate(angle, width / 2, height / 2);

        graphics2D.drawImage(texture, affineTransform, null);
    }
}
