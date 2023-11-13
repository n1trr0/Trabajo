package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.assets;
import math.Vector2D;
import states.GameState;

public class Enemies extends MovingObject{

    private Size size;
    private Player player;

    public Enemies(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState, Size size, Player player) {
        super(position, velocity, maxVel, texture, gameState);
        this.size = size;
        this.player = player;
        this.velocity = velocity.scale(maxVel);
    }

    @Override
    public void update() {
        //position = position.add(velocity);

        double dx = player.getPosition().getX() - position.getX();
        double dy = player.getPosition().getY() - position.getY();

        double distancia = Math.sqrt(dx * dx + dy * dy);

        dx /= distancia;
        dy /= distancia;

        Vector2D aux;
        aux = new Vector2D(dx,dy);

        position = position.add(aux);

        if(position.getX() > Constants.WIDTH)
            position.setX(-width);
        if(position.getY() > Constants.HEIGHT)
            position.setY(-height);

        if(position.getX() < -width)
            position.setX(Constants.WIDTH);
        if(position.getY() < -height)
            position.setY(Constants.HEIGHT);

        //angle += Math.PI/40;

    }
    @Override
    public void destroy(){
        //gameState.divideEnemies(this);
        gameState.addScore(Constants.ENEMIES_SCORE);
        super.destroy();
    }
    @Override
    public void draw(Graphics graphics) {

        Graphics2D g2d = (Graphics2D)graphics;

        affineTransform = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        //affineTransform.rotate(angle, width/2, height/2);

        g2d.drawImage(texture, affineTransform, null);
    }

    public Size getSize(){
        return size;
    }


}