package states;

import java.awt.*;

/**
 * Clase abstracta que representa un estado en el juego.
 * Un estado define la lógica y la representación visual de una parte específica del juego.
 * @author Raul Garcia & Alejandro Molero
 */
public abstract class State {

    /**
     * Estado actual del juego.
     */
    public static State currentState = null;

    /**
     * Obtiene el estado actual del juego.
     *
     * @return El estado actual del juego.
     */
    public static State getCurrentState() {
        return currentState;
    }

    ;

    /**
     * Cambia el estado del juego al estado proporcionado.
     *
     * @param newState El nuevo estado del juego.
     */
    public static void changeState(State newState) {
        currentState = newState;
    }

    ;

    /**
     * Actualiza la lógica del estado.
     */
    public abstract void update();

    /**
     * Dibuja el estado en el lienzo proporcionado.
     *
     * @param graphics El contexto gráfico en el que se realizará el dibujo.
     */
    public abstract void draw(Graphics graphics);

}