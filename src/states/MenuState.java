package states;

import UI.Action;
import UI.Button;
import gameObjects.Constants;
import graphics.Sound;
import graphics.assets;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MenuState extends State{
    private ArrayList<Button> buttons;
    private Sound backgroundMusic;
    private BufferedImage backgroundImage;
    private BufferedImage titleImage;
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
        graphics2D.drawImage(titleImage, (Constants.WIDTH - titleImage.getWidth()) / 2, 0, null);


        for(Button b:buttons){
            b.draw(graphics);
        }
        graphics2D.dispose();
    }
}
