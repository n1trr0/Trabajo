package gameObjects;

/**
 * Contiene todas los parametros constantes del juego
 * @author Raul Garcia & Alejandro Molero
 */

public class Constants {
    /**
     * Anchura de la pantalla
     * @param WIDTH
     */
    public static final int WIDTH = 1920;
    /**
     * Altura de la pantalla
     * @param HEIGHT
     */
    public static final int HEIGHT = 1080;
    /**
     * Aceleracion del jugador
     * @param acc
     */
    public static final double acc = 0.08;
    /**
     * Cadencia de disparo
     * @param FIRE_RATE
     */
    public static final int FIRE_RATE = 100;
    /**
     * Velocidad de los disparos
     * @param LASER_VELOCITY
     */
    public static final int LASER_VELOCITY = 10;
    /**
     * Velocidad de los enemigos
     * @param ENEMIES_VELOCITY
     */
    public static final double ENEMIES_VELOCITY = 1.5;
    public static final int NODE_RADIUS = 160;
    public static final int RULER_MASS = 60;
    public static final int RULER_MAX_VELOCITY = 3;
    public static final int RULER_FIRE_RATE = 1000;
    public static double RULER_ANGLE_RANGE = Math.PI / 2;

    public static final int RULER_SCORE = 40;
    public static final int ENEMIES_SCORE = 20;
    public static final double GOLD = 0.25;

}
