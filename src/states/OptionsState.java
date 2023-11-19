package states;

import UI.Action;
import UI.Button;
import gameObjects.Constants;
import graphics.assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class OptionsState extends State{
    private ArrayList<Button> buttons;
    private BufferedImage backgroundImage;
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
