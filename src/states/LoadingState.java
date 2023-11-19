package states;

import gameObjects.Constants;
import graphics.Text;
import graphics.assets;
import graphics.loader;
import math.Vector2D;

import java.awt.*;

public class LoadingState extends State{
    private Thread loadingThread;
    public LoadingState(Thread loadingThread){
        this.loadingThread = loadingThread;
        this.loadingThread.start();
    }
    @Override
    public void update() {
        if(assets.loaded){
            State.changeState(new MenuState());
            try {
                loadingThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        GradientPaint gradientPaint = new GradientPaint(
                Constants.WIDTH / 2 - Constants.LOADING_BAR_WIDTH / 2,
                Constants.HEIGHT / 2 - Constants.LOADING_BAR_HEIGHT / 2,
                Color.WHITE,
                Constants.WIDTH / 2 + Constants.LOADING_BAR_WIDTH / 2,
                Constants.HEIGHT / 2 + Constants.LOADING_BAR_HEIGHT / 2,
                Color.BLUE
        );
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setPaint(gradientPaint);

        float percentage = (assets.count / assets.maxCount);

        graphics2D.fillRect(Constants.WIDTH / 2 - Constants.LOADING_BAR_WIDTH / 2,
                Constants.HEIGHT / 2 - Constants.LOADING_BAR_HEIGHT / 2,
                (int)(Constants.LOADING_BAR_WIDTH * percentage),
                Constants.LOADING_BAR_HEIGHT);

        graphics2D.drawRect(Constants.WIDTH / 2 - Constants.LOADING_BAR_WIDTH / 2,
                Constants.HEIGHT / 2 - Constants.LOADING_BAR_HEIGHT / 2,
                Constants.LOADING_BAR_WIDTH,
                Constants.LOADING_BAR_HEIGHT);
    }
}
