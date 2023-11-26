package states;

import UI.Action;
import UI.Button;
import gameObjects.Constants;
import graphics.assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * Representa el estado del juego correspondiente a la pantalla de opciones.
 * Permite al usuario ajustar diferentes configuraciones del juego.
 * @author Raul Garcia & Alejandro Molero
 */
public class OptionsState extends State{
    /**
     * Lista que contiene los botones disponibles en la pantalla de opciones.
     */
    private ArrayList<Button> buttons;
    /**
     * Imagen de fondo de la pantalla de opciones.
     */
    private BufferedImage backgroundImage;

    /**
     * Constructor de la clase OptionsState. Inicializa los botones y la imagen de fondo.
     */
    public OptionsState(){
        buttons = new ArrayList<Button>();

        buttons.add(new Button(
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
        backgroundImage = assets.menuBackground;
    }

    /**
     * Actualiza la lógica del estado de la pantalla de opciones.
     */
    @Override
    public void update() {
        for(Button b:buttons){
            b.update();
        }
    }

    /**
     * Dibuja el estado actual de la pantalla de opciones en el lienzo proporcionado.
     *
     * @param graphics El contexto gráfico en el que se realizará el dibujo.
     */
    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.drawImage(backgroundImage, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);

        for(Button b:buttons){
            b.draw(graphics);
        }
        graphics2D.dispose();
    }
}

