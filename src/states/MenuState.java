package states;

import UI.Action;
import UI.Button;
import gameObjects.Constants;
import graphics.Sound;
import graphics.assets;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MenuState extends State{
    private ArrayList<Button> buttons;
    private Sound backgroundMusic;
    private BufferedImage backgroundImage;
    public MenuState(){
        buttons = new ArrayList<Button>();

        buttons.add(new Button(assets.playOut, assets.playIn,
                Constants.WIDTH / 2 - assets.playOut.getWidth() / 2,
                Constants.HEIGHT / 2 - assets.playOut.getHeight() / 2,
                Constants.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new GameState());
                        backgroundMusic.stop();

                    }
                }));
        buttons.add(new Button(assets.salirOut, assets.salirIn,
                Constants.WIDTH / 2 - assets.salirOut.getWidth() / 2,
                Constants.HEIGHT / 2 + assets.salirOut.getHeight() ,
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
    }
    @Override
    public void update() {
        for(Button b:buttons){
            b.update();
        }
    }

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
