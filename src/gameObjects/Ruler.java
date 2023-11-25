package gameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.assets;
import math.Vector2D;
import states.GameState;

/**Implementa un nuevo enemigo que tiene el comportamiento de una torreta
 * @author Raul Garcia & Alejandro Molero
 */
public class Ruler extends MovingObject {
    /**
     * Se encarga de controlar la velocidad de disparo del enemigo
     */
    private final Chronometer fireRate;

    /**
     * Constructor
     * @param position Posicion
     * @param velocity Velocidad
     * @param maxVel Velocidad maxima
     * @param texture Imagen del objeto
     * @param gameState Estado del juego
     */
    public Ruler(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        fireRate = new Chronometer();
        fireRate.run(Constants.RULER_FIRE_RATE);
    }

    /**
     * Comprueba si le toca disparar si es asi calcula el tiro y dispara
     * Ademas comprueba las colisiones
     */
    @Override
    public void update() {
        if (!fireRate.isRunning()) {

            Vector2D toPlayer = gameState.getPlayer().getCenter().substract(getCenter());

            toPlayer = toPlayer.normalize();

            double currentAngle = toPlayer.getAngle();
            currentAngle += Math.random() * Constants.RULER_ANGLE_RANGE - Constants.RULER_ANGLE_RANGE / 2;

            if (toPlayer.getX() < 0)
                currentAngle = -currentAngle + Math.PI;

            toPlayer = toPlayer.setDirection(currentAngle);

            LaserEnemy laser = new LaserEnemy(
                    getCenter().add(toPlayer.scale(width)),
                    toPlayer,
                    Constants.LASER_VELOCITY,
                    currentAngle + Math.PI / 2,
                    assets.fireballToxic,
                    gameState
            );
            gameState.getMovingObjects().add(0, laser);
            fireRate.run(Constants.RULER_FIRE_RATE);
        }

        collidesWith();
        fireRate.update();
    }

    /**
     * Destruye el objeto y le otorga puntos al jugador
     */
    @Override
    public void destroy() {
        gameState.addScore(Constants.RULER_SCORE, position);
        super.destroy();
    }

    /**
     * Dibuja el objeto por pantalla
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;

        affineTransform = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        graphics2D.drawImage(texture, affineTransform, null);

    }

}
