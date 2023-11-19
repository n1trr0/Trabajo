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
    public static int WIDTH = 1920;
    /**
     * Altura de la pantalla
     * @param HEIGHT
     */
    public static int HEIGHT = 1080;
    /**
     * Aceleracion del jugador
     * @param acc
     */
    public static final double acc = 0.08;
    /**
     * Cadencia de disparo
     * @param FIRE_RATE
     */
    public static final int FIRE_RATE = 200;
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
    /**
     * Masa de los enemigos de clase ruler
     */
    public static final int RULER_MASS = 60;
    /**
     * Velocidad maxima de los enemigos de clase ruler
     */
    public static final int RULER_MAX_VELOCITY = 3;
    /**
     * Tiempo entre disparos de la clase ruler
     */
    public static final int RULER_FIRE_RATE = 1000;
    public static double RULER_ANGLE_RANGE = Math.PI / 2;
    /**
     * Tiempo entre el siguiente spawn de la clase ruler (5 segundos)
     */
    public static long RULER_SPAWN_RATE = 5000;
    /**
     * Puntacion obtenida al matar un enemigo de clase ruler
     */
    public static final int RULER_SCORE = 40;
    /**
     * Puntacion obtenida al matar un enemigo base
     */
    public static final int ENEMIES_SCORE = 20;
    /**
     * Escala en la que se reparte oro con respecto al score (1:4)
     */
    public static final double GOLD = 0.25;
    /**
     * Tiempo de spawn del jugador cuando pierde una vida
     */
    public static final long SPAWNING_TIME=3000;
    /**
     * Tiempo durante el que parpadea el jugador al morir
     */
    public static final long FLICKER_TIME=200;
    /**
     * String que aparece en los botones (en este caso el string viene implementado en la imagen)
     */
    public static final String PLAY = null;
    /**
     * Ancho de la barra de carga
     */
    public static final int LOADING_BAR_WIDTH = 500;
    /**
     * Alto de la barra de carga
     */
    public static final int LOADING_BAR_HEIGHT = 50;
}
