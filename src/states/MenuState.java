package states;

import UI.Action;
import UI.Button;
import gameObjects.Constants;
import graphics.Sound;
import graphics.assets;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * Representa el estado del juego correspondiente al menú principal.
 * Permite al usuario acceder a diferentes opciones, como jugar, ajustar opciones, ver puntajes o salir del juego.
 * @author Raul Garcia & Alejandro Molero
 */
public class MenuState extends State{
    /**
     * Lista que contiene los botones disponibles en el menú.
     */
    private ArrayList<Button> buttons;
    /**
     * Objeto para gestionar el sonido de fondo del menú.
     */
    private Sound backgroundMusic;
    /**
     * Imagen de fondo del menú.
     */
    private BufferedImage backgroundImage;
    /**
     * Imagen del título del juego.
     */
    private BufferedImage titleImage;
    /**
     * Constructor de la clase MenuState. Inicializa los botones del menú, la música de fondo y las imágenes.
     */
    public MenuState(){
        buttons = new ArrayList<Button>();

        buttons.add(new Button(assets.playOut, assets.playIn,
                (Constants.WIDTH - assets.playOut.getWidth()) / 2,
                Constants.HEIGHT / 2,
                Constants.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        backgroundMusic.stop();
                        State.changeState(new GameState());
                    }
                }));
        buttons.add(new Button(assets.opcionesOut, assets.opcionesIn,
                (Constants.WIDTH - assets.opcionesOut.getWidth()) / 2,
                Constants.HEIGHT / 2 + 100,
                Constants.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new OptionsState());
                    }
                }));
        buttons.add(new Button(assets.scoreOut, assets.scoreIn,
                (Constants.WIDTH - assets.scoreOut.getWidth()) / 2,
                Constants.HEIGHT / 2 + 200,
                Constants.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new ScoreState());
                    }
                }));
        buttons.add(new Button(assets.salirOut, assets.salirIn,
                (Constants.WIDTH - assets.salirOut.getWidth()) / 2,
                Constants.HEIGHT / 2 + 300 ,
                Constants.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        System.exit(0);
                    }
                }));
        backgroundMusic = new Sound(assets.menuMusic);
        backgroundMusic.loop();
        backgroundImage = assets.menuBackground;
        titleImage = assets.titleImage;
    }

    /**
     * Actualiza la lógica del estado del menú.
     */
    @Override
    public void update() {
        for(Button b:buttons){
            b.update();
        }
    }
    /**
     * Dibuja el estado actual del menú en el lienzo proporcionado.
     *
     * @param graphics El contexto gráfico en el que se realizará el dibujo.
     */
    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.drawImage(backgroundImage, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
        graphics2D.drawImage(titleImage, (Constants.WIDTH - titleImage.getWidth()) / 2, 0, null);


        for(Button b:buttons){
            b.draw(graphics);
        }
        graphics2D.dispose();
    }
}