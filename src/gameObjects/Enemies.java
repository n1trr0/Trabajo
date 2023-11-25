package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.assets;
import math.Vector2D;
import states.GameState;

/**
 * Implementa los enemigos basicos del juego
 * @author Raul Garcia & Alejandro Molero
 */

public class Enemies extends MovingObject{
    /**Contiene los diferentes tamaños del enemigo
     * @param size
     */
    private Size size;
    /**Se usa para calcular la trayectoria a seguir a partir de la posicion del jugador
     * @param player
     */
    private Player player;

    /**
     * Constructor unico para crear un enemigo
     * @param position Posicion
     * @param velocity Velocidad
     * @param maxVel Velocidad maxima
     * @param texture Imagen del enemigo
     * @param gameState Estado actual del juego
     * @param size Diferentes tamaños que adopta el enemigo
     * @param player El jugador actual
     */
    public Enemies(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState, Size size, Player player) {
        super(position, velocity, maxVel, texture, gameState);
        this.size = size;
        this.player = player;
        this.velocity = velocity.scale(maxVel);
    }

    /**
     * Calcula la trayectoria hacia el jugador y comprueba que no se salga de la pantalla
     */
    @Override
    public void update() {

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

    }

    /**
     * Destruye el enemigo para dividirlo en uno mas pequeño, te otorga la puntacion correspondiente
     */
    @Override
    public void destroy(){
        //gameState.divideEnemies(this);
        gameState.addScore(Constants.ENEMIES_SCORE, position);
        super.destroy();
    }

    /**
     * Dibuja al enemigo en la posicion actualizada
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics) {

        Graphics2D g2d = (Graphics2D)graphics;

        affineTransform = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        g2d.drawImage(texture, affineTransform, null);
    }
    /**
     * getter del parametro Size size
     * @return
     */
    public Size getSize(){
        return size;
    }
}