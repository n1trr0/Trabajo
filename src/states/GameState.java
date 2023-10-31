package states;

import gameObjects.*;
import graphics.assets;
import math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameState {
    private Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
    private int enemies;
    public  GameState(){
        player = new Player(new Vector2D(600,600),new Vector2D(), 5, assets.player, this);
        movingObjects.add(player);
        enemies = 1;
        startWave();
    }

    //esta sin acabar pq ocurre el bug de que desaparecen balas

    public void divideEnemies(Enemies enemies){
        Size size = enemies.getSize();

        BufferedImage[] textures = size.textures;

        Size newSize = null;

        switch (size){
            case BIG :
                newSize = Size.MED;
                break;
            case MED:
                newSize = Size.SMALL;
                break;
            case SMALL:
                newSize = Size.TINY;
                break;
            default:
                return;
        }

        for(int i = 0; i < size.quantity; i++){
            movingObjects.add(new Enemies(
                    enemies.getPosition(),
                    new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
                    Constants.ENEMIES_VELOCITY*Math.random() + 1,
                    textures[(int)(Math.random()*assets.bigs.length)],
                    this,
                    newSize,
                    player
            ));
        }
    }

    private void startWave(){
        double x, y;

        for(int i = 0; i < enemies; i++){

            x = i % 2 == 0 ? Math.random()*Constants.WIDTH : 0;
            y = i % 2 == 0 ? 0 : Math.random()*Constants.HEIGHT;

            BufferedImage texture = assets.bigs[(int)(Math.random()*assets.bigs.length)];

            movingObjects.add(new Enemies(
                    new Vector2D(x, y),
                    new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
                    Constants.ENEMIES_VELOCITY*Math.random() + 1,
                    texture,
                    this,
                    Size.BIG,
                    player
            ));

        }
        enemies ++;
    }
    public void update(){
        for(int i = 0; i < movingObjects.size(); i++)
            movingObjects.get(i).update();

        for(int i = 0; i < movingObjects.size(); i++)
            if(movingObjects.get(i) instanceof Enemies)
                return;

        startWave();
    }

    public void draw(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        for(int i = 0; i < movingObjects.size(); i++){
            movingObjects.get(i).draw(graphics);
        }
    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
}