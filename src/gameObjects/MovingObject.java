package gameObjects;

import math.Vector2D;
import states.GameState;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * MovingObject extiende a GameObject y se usa para todos los objectos que se muevan
 * @author Raul Garcia & Alejandro Molero
 */
public abstract class MovingObject extends GameObject{
    /**
     * Velocidad del objecto
     * @param velocity
     */
    protected Vector2D velocity;
    /**
     * Modifica la imagen (rotar)
     * @param affineTransform
     */
    protected AffineTransform affineTransform;
    /**
     * Angulo de la imagen
     * @param angle
     */
    protected double angle;
    /**
     * Velocidad maxima del objecto
     * @param maxVelocity
     */
    protected double maxVelocity;
    /**
     * Ancho de la imagen
     * @param width
     */
    protected int width;
    /**
     * Alto de la imagen
     * @param height
     */
    protected int height;
    /**
     * Estado del juego
     * @param gameState
     */
    protected GameState gameState;
    /**
     * Indica si el objeto fue destruido
     * @param dead
     */
    protected boolean dead;

    /**
     * Crea un objecto a partir del super de GameObject y de los nuevos parametros
     * @param position Posicion del objecto
     * @param velocity Velocidad del objecto
     * @param maxVelocity Velocidad maxima del objecto
     * @param texture Imagen del objecto
     * @param gameState Estado actual del juego
     */
    public MovingObject(Vector2D position,Vector2D velocity,double maxVelocity, BufferedImage texture, GameState gameState) {
        super(position, texture);
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        this.gameState = gameState;
        width = texture.getWidth();
        height = texture.getHeight();
        angle = 0;
    }

    /**
     * Comprueba las colisiones entre los diferentes objectos
     */
    protected void collidesWith(){
        ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();

        for(int i = 0; i < movingObjects.size(); i++){
            MovingObject m = movingObjects.get(i);

            if(m.equals(this)){
                continue;
            }
            double distance = m.getCenter().substract(getCenter()).getMagnitude();

            if(distance < m.width/2 + width/2 && movingObjects.contains(this) && !m.dead && !dead){
                objectCollision(m,this);
            }
        }
    }

    /**
     * Se activa cuando dos objectos colisionan y determina si deben ser eleminados
     * @param a Primer objecto
     * @param b Segundo objecto
     */
    private void objectCollision(MovingObject a, MovingObject b){
        Player player = null;

        if(a instanceof Player)
            player = (Player)a;
        else if(b instanceof Player)
            player = (Player)b;

        if(player != null && player.isSpawing())
            return;

        if((a instanceof Laser) && (b instanceof Laser)){
            return;
        }
        if((a instanceof Enemies || b instanceof Enemies)&&(a instanceof Ruler || b instanceof Ruler)){
            return;
        }
        if(player == null){
            a.destroy();
            b.destroy();
        }
        if(player != null){
            if(a instanceof Laser || b instanceof Laser){
                return;
            }
            a.destroy();
            b.destroy();
        }
    }

    /**
     * Marca el objeto como destruido
     */
    protected void destroy(){
        dead = true;
    }

    /**
     * Devuelve si el objeto esta marcado para destruirse
     * @return
     */
    public boolean isDead(){
        return dead;
    }
    /**
     * Calcula el centro de la imagen
     * @return Devuelve el centro de la imagen
     */
    protected Vector2D getCenter() {
        return new Vector2D(position.getX() + width / 2, position.getY() + height / 2);
    }
}
