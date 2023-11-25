package states;

import UI.Action;
import UI.Button;
import gameObjects.Constants;
import graphics.Text;
import graphics.assets;
import math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Menu del Game Over
 * @author Raul Garcia & Alejandro Molero
 */
public class GameOverState extends State{
    /**
     * ArrayList con todos los botones
     */
    private ArrayList<UI.Button> buttons;
    /**
     * Imagen de fondo del menu
     */
    private BufferedImage backgroundImage;
    /**
     * Puntuacion del jugador
     */
    int score;
    /**
     * Constructor por defecto que añade un boton para volver y la puntuacion obtenida
     * @param lastScore Puntuacion del jugador tras la partida
     */
    public GameOverState(int lastScore){
        buttons = new ArrayList<UI.Button>();

        buttons.add(new UI.Button(
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
        ));
        score = lastScore;
        backgroundImage = assets.menuBackground;
    }

    /**
     * Actualiza todos los botones del menu
     */
    @Override
    public void update() {
        for(UI.Button b:buttons){
            b.update();
        }
    }
    /**
     * Dibuja los objetos en cuestion
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.drawImage(backgroundImage, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);

        int anchoTexto = graphics.getFontMetrics(assets.fontWave).stringWidth("Puntuacion: " + Integer.toString(score));
        Text.drawText(graphics,"Puntuacion: " + Integer.toString(score), new Vector2D((Constants.WIDTH - anchoTexto)/2, (Constants.HEIGHT/3)*2), false, Color.WHITE, assets.fontWave);
        for(Button b:buttons){
            b.draw(graphics);
        }
        graphics2D.dispose();
    }
}
