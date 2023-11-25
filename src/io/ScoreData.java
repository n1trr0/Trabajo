package io;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Gestiona la puntacion de un jugador almacenando el tamaño y fecha
 */
public class ScoreData {
    /**
     * Fecha de la puntacion
     */
    private String date;
    /**
     * Tamaño de la puntuacion
     */
    private int score;
    /**
     * Constructor con parametros
     * @param score Puntuacion del jugador
     */
    public ScoreData(int score) {
        this.score = score;

        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(today);

    }
    /**
     * Constructor base
     */
    public ScoreData() {}

    /**
     * getter de la fecha
     * @return string con la fecha almacenada
     */
    public String getDate() {
        return date;
    }

    /**
     * setter de date a partir de un parametro
     * @param date Fecha que se va a almacenar
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * getter de la puntuacion
     * @return int del tamaño de la puntacion
     */
    public int getScore() {
        return score;
    }

    /**
     * setter de score a partir de un parametro
     * @param score Tamaño de la puntacion
     */
    public void setScore(int score) {
        this.score = score;
    }
}