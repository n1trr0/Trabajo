package states;

import UI.Action;
import UI.Button;
import gameObjects.Constants;
import graphics.assets;

import java.awt.*;
import java.util.ArrayList;

public class MenuState extends State{
    private ArrayList<Button> buttons;
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
        for(Button b:buttons){
            b.draw(graphics);
        }
    }
}
