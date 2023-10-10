package gameObjects;

import math.Vector2D;
import states.GameState;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class MovingObject extends GameObject{
    protected Vector2D velocity;
    protected AffineTransform affineTransform;
    protected double angle;
    protected double maxVelocity;
    protected int width;
    protected int height;
    protected GameState gameState;
    public MovingObject(Vector2D position,Vector2D velocity,double maxVelocity, BufferedImage texture, GameState gameState) {
        super(position, texture);
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        this.gameState = gameState;
        width = texture.getWidth();
        height = texture.getHeight();
        angle = 0;
    }
    protected void collidesWith(){
        ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();

        for(int i = 0; i < movingObjects.size(); i++){
            MovingObject m = movingObjects.get(i);

            if(m.equals(this)){
                continue;
            }
            double distance = m.getCenter().substract(getCenter()).getMagnitude();

            if(distance < m.width/2 + width/2 && movingObjects.contains(this)){
                objectCollision(m,this);
            }
        }
    }
    private void objectCollision(MovingObject a, MovingObject b){
        if((a instanceof Player || b instanceof Player)&&(a instanceof Laser || b instanceof Laser)){
            return;
        }
        if(!(a instanceof Enemies && b instanceof Enemies)){
            a.destroy();
            b.destroy();
        }
    }
    protected void destroy(){
        gameState.getMovingObjects().remove(this);
    }
    protected Vector2D getCenter() {
        return new Vector2D(position.getX() + width / 2, position.getY() + height / 2);
    }
}
