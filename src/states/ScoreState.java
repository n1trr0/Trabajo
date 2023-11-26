package states;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import UI.Action;
import gameObjects.Constants;
import graphics.Text;
import graphics.assets;
import io.JsonParser;
import io.ScoreData;
import UI.Button;
import math.Vector2D;
/**
 * Representa el estado del juego correspondiente a la pantalla de puntajes.
 * Muestra los puntajes más altos obtenidos por los jugadores.
 * @author Raul Garcia & Alejandro Molero
 */
public class ScoreState extends State {
    /**
     * Botón para regresar al menú principal.
     */
    private Button returnButton;
    /**
     * Cola de prioridad que almacena los datos de puntajes, ordenados por puntaje.
     */
    private PriorityQueue<ScoreData> highScores;
    /**
     * Comparador para ordenar los puntajes de manera ascendente.
     */
    private Comparator<ScoreData>scoreComparator;
    /**
     * Arreglo auxiliar para almacenar y ordenar los puntajes.
     */
    private ScoreData[]auxArray;
    /**
     * Imagen de fondo de la pantalla de puntajes.
     */
    private BufferedImage backgroundImage;
    /**
     * Imagen del encabezado del puntaje.
     * Imagen del encabezado de la fecha.
     */
    private BufferedImage scoreImage, dateImage;
    /**
     * Constructor de la clase ScoreState. Inicializa los elementos necesarios para mostrar los puntajes.
     */
    public ScoreState(){
        returnButton=new Button(
                assets.volverOut,
                assets.volverIn,
                assets.volverOut.getHeight(),
                Constants.HEIGHT - assets.volverOut.getHeight()*2,
                Constants.PLAY,
                new Action(){
                    @Override
                    public void doAction() {
                        State.changeState(new MenuState());
                    }
                }
        );
        scoreComparator = new Comparator<ScoreData>() {
            @Override
            public int compare(ScoreData o1, ScoreData o2) {
                return o1.getScore()<o2.getScore()?-1: o1.getScore()> o2.getScore()?1:0;
            }
        };
        highScores=new PriorityQueue<ScoreData>(10,scoreComparator);
        try {
            ArrayList<ScoreData> dataList = JsonParser.readFile();
            for(ScoreData data: dataList){
                highScores.add(data);
            }
            while(highScores.size() > 10){
                highScores.poll();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        backgroundImage = assets.menuBackground;
        scoreImage = assets.score;
        dateImage = assets.date;
    }

    /**
     * Actualiza la lógica del estado de la pantalla de puntajes.
     */
    @Override
    public void update() {
        returnButton.update();
    }

    /**
     * Dibuja el estado actual de la pantalla de puntajes en el lienzo proporcionado.
     *
     * @param graphics El contexto gráfico en el que se realizará el dibujo.
     */
    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(backgroundImage, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
        returnButton.draw(graphics);
        auxArray=highScores.toArray(new ScoreData[highScores.size()]);
        Arrays.sort(auxArray,scoreComparator);

        Vector2D scorePos = new Vector2D((int) (Constants.WIDTH/2- scoreImage.getWidth()*1.5 + scoreImage.getWidth()/2),75+ scoreImage.getHeight());
        Vector2D datePos= new Vector2D(Constants.WIDTH/2+ dateImage.getWidth(),75+ dateImage.getHeight());

        graphics2D.drawImage(scoreImage, (int) (Constants.WIDTH/2- scoreImage.getWidth()*1.5), 75, null);
        graphics2D.drawImage(dateImage, Constants.WIDTH/2+ dateImage.getWidth()/2, 75, null);
        //Text.drawText(graphics,Constants.SCORE,scorePos,true,Color.BLUE,assets.fontMed);
        //Text.drawText(graphics,Constants.DATE,datePos,true,Color.BLUE,assets.fontMed);
        scorePos.setY(scorePos.getY()+40);
        datePos.setY(datePos.getY() + 40);
        for(int i = auxArray.length - 1; i > -1; i--) {

            ScoreData data = auxArray[i];

            Text.drawText(graphics, Integer.toString(data.getScore()), scorePos, true, Color.WHITE, assets.fontMed);
            Text.drawText(graphics, data.getDate(), datePos, true, Color.WHITE, assets.fontMed);

            scorePos.setY(scorePos.getY() + 40);
            datePos.setY(datePos.getY() + 40);

        }
    }
}