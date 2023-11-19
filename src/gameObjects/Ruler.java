package gameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graphics.assets;
import math.Vector2D;
import states.GameState;

public class Ruler extends MovingObject{

    private ArrayList<Vector2D> path;

    private Vector2D currentNode;

    private int index;

    private boolean following;

    private Chronometer fireRate;

    public Ruler(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture,
               ArrayList<Vector2D> path, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        this.path = path;
        index = 0;
        following = true;
        fireRate = new Chronometer();
        fireRate.run(Constants.RULER_FIRE_RATE);
    }

    private Vector2D pathFollowing() {
        currentNode = path.get(index);

        double distanceToNode = currentNode.substract(getCenter()).getMagnitude();

        if(distanceToNode < Constants.NODE_RADIUS) {
            index ++;
            if(index >= path.size()) {
                following = false;
            }
        }

        return seekForce(currentNode);

    }

    private Vector2D seekForce(Vector2D target) {
        Vector2D desiredVelocity = target.substract(getCenter());
        desiredVelocity = desiredVelocity.normalize().scale(maxVelocity);
        return desiredVelocity.substract(velocity);
    }

    @Override
    public void update() {

        Vector2D pathFollowing;

        if(following)
            pathFollowing = pathFollowing();
        else
            pathFollowing = new Vector2D();

        pathFollowing = pathFollowing.scale(1/Constants.RULER_MASS);

        velocity = velocity.add(pathFollowing);

        velocity = velocity.limit(maxVelocity);

        position = position.add(velocity);

        if(position.getX() > Constants.WIDTH || position.getY() > Constants.HEIGHT
                || position.getX() < -width || position.getY() < -height)
            destroy();

        // shoot

        if(!fireRate.isRunning()) {

            Vector2D toPlayer = gameState.getPlayer().getCenter().substract(getCenter());

            toPlayer = toPlayer.normalize();

            double currentAngle = toPlayer.getAngle();
            currentAngle += Math.random()*Constants.RULER_ANGLE_RANGE - Constants.RULER_ANGLE_RANGE / 2;

            if(toPlayer.getX() < 0)
                currentAngle = -currentAngle + Math.PI;

            toPlayer = toPlayer.setDirection(currentAngle);

            Laser laser = new Laser(
                    getCenter().add(toPlayer.scale(width)),
                    toPlayer,
                    Constants.LASER_VELOCITY,
                    currentAngle + Math.PI/2,
                    assets.fireball,
                    gameState
            );

            gameState.getMovingObjects().add(0, laser);

            fireRate.run(Constants.RULER_FIRE_RATE);

        }

        //angle += 0.05;

        collidesWith();
        fireRate.update();
    }
    @Override
    public void destroy(){
        gameState.addScore(Constants.RULER_SCORE, position);
        super.destroy();
    }
    @Override
    public void draw(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D)graphics;

        affineTransform = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        affineTransform.rotate(angle, width/2, height/2);

        graphics2D.drawImage(texture, affineTransform, null);

    }

}
