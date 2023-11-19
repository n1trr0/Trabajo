package gameObjects;

/**Implementa un cronometro que se usa para calcular los tiempos de espera de determinadas acciones
 * @author Raul Garcia & Alejandro Molero
 */
public class Chronometer {
    /**
     * @param delta Almacena el tiempo que lleva el cronometro activo
     * @param lastTime Guarda el tiempo del sistema en milisegundos
     */
    private long delta, lastTime;
    /**
     * @param time Tiempo deseado para que dure el cronometro
     */
    private long time;
    /**
     * @param running Comprueba si el cronometro esta en marcha
     */
    private boolean running;
    /**
     * Inicializa el cronometro pero no lo pone en marcha
     */
    public Chronometer() {
        delta = 0;
        lastTime = System.currentTimeMillis();
        running = false;
    }

    /**
     * Pone el cronometro en marcha
     * @param time tiempo que deseas que este activo el cronometro
     */
    public void run(long time) {
        running = true;
        this.time = time;
    }

    /**
     * Actualiza el cronometro en funcion de si esta en marcha y de si el delta supera el tiempo deseado
     */
    public void update() {
        if (running) {
            delta += System.currentTimeMillis() - lastTime;
        }
        if (delta >= time) {
            running = false;
            delta = 0;
        }
        lastTime = System.currentTimeMillis();
    }

    /**
     * Comprueba si el cronometro esta en marcha
     * @return devuelve un booleano true si esta en marcha
     */
    public boolean isRunning() {
        return running;
    }
}
