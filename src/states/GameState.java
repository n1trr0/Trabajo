package states;

import UI.Action;
import UI.Button;
import gameObjects.*;
import graphics.Sound;
import graphics.Text;
import graphics.assets;
import input.Keyboard;
import io.JsonParser;
import io.ScoreData;
import math.Vector2D;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Representa el estado del juego en el que el jugador controla un personaje para combatir enemigos y superar oleadas.
 * Contiene la lógica de juego, la gestión de objetos, la generación de enemigos y la manipulación de la interfaz de usuario.
 * @author Raul Garcia & Alejandro Molero
 */
public class GameState extends State{
    /**
     * La posición inicial del jugador en la pantalla del juego.
     */
    public static final Vector2D PLAYER_START_POSITION = new Vector2D(Constants.WIDTH/2 - assets.player.getWidth()/2,
            Constants.HEIGHT/2 - assets.player.getHeight()/2);

    /**
     * El objeto jugador que representa al personaje principal en el juego.
     */
    private Player player;
    /**
     * Una lista para almacenar todos los objetos móviles en el juego.
     */
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
    /**
     * Una lista para almacenar mensajes mostrados en el juego.
     */
    private ArrayList<Message> messages = new ArrayList<Message>();
    /**
     * El número de enemigos en el juego.
     */
    private int enemies;
    /**
     * La puntuación actual del jugador.
     */
    private int score = 0;
    /**
     * La cantidad de vidas que tiene el jugador.
     */
    private int lives = 3;
    /**
     * La oleada actual de enemigos en el juego.
     */
    private int waves = 1;
    /**
     * Un temporizador para generar objetos tipo "ruler" (suponiendo que "ruler" son objetos del juego).
     */
    private Chronometer rulerSpawner;
    /**
     * Un temporizador para generar oleadas de enemigos.
     */
    private Chronometer waveSpawner;
    /**
     * Una boleano que indica si el juego ha terminado o no.
     */
    private boolean gameOver;
    /**
     * La imagen de fondo del juego.
     */
    private BufferedImage backgroundImage;
    /**
     * Un botón para regresar a un menú o pantalla anterior.
     */
    private Button returnButton;
    /**
     * Un temporizador para controlar efectos de parpadeo.
     */
    private Chronometer flickertime;
    /**
     * El objeto de sonido para la música del juego.
     */
    private Sound gameMusic;
    /**
     * Una boleano que indica el estado de visibilidad de un objeto
     */
    private boolean visible;
    /**
     * Constructor de la clase GameState. Inicializa el estado del juego, crea al jugador, configura la música y prepara los cronómetros.
     */
    public GameState() {
        player = new Player(PLAYER_START_POSITION, new Vector2D(), 5, assets.player, this);
        gameOver = false;
        movingObjects.add(player);
        enemies = 1;
        backgroundImage = assets.gameBackground;
        gameMusic = new Sound(assets.gameMusic);
        gameMusic.loopInicio();

        rulerSpawner = new Chronometer();
        waveSpawner = new Chronometer();
        flickertime = new Chronometer();
        rulerSpawner.run(Constants.RULER_SPAWN_RATE);
        waveSpawner.run(Constants.WAVE_SPAWN_RATE);

        returnButton=new Button(
                assets.volverOut,
                assets.volverIn,
                assets.volverOut.getHeight(),
                Constants.HEIGHT - assets.volverOut.getHeight()*2,
                Constants.PLAY,
                new Action(){
                    @Override
                    public void doAction() {
                        gameMusic.stop();
                        State.changeState(new MenuState());
                    }
                }
        );

        startWave();
    }
    /**
     * Reduce la cantidad de vidas del jugador y devuelve true si aún le quedan vidas, o false si ha perdido todas sus vidas.
     *
     * @return true si el jugador aún tiene vidas, false si ha perdido todas sus vidas.
     */
    public boolean loseLive(){
        lives--;
        return lives > 0;
    }

    /**
     * Añade puntaje al jugador y muestra un mensaje en la posición especificada.
     *
     * @param value     El valor del puntaje que se añadirá.
     * @param position  La posición en la que se mostrará el mensaje.
     */

    public void addScore(int value, Vector2D position){
        score += value;
        messages.add(new Message(position, true, "+"+ value, Color.WHITE, false, assets.fontMed));
    }

    /*public void divideEnemies(Enemies enemies) {
        Size size = enemies.getSize();

        BufferedImage[] textures = size.textures;

        Size newSize = null;

        switch(size){
            case BIG:
                newSize =  Size.MED;
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
                    textures[(int)(Math.random()*textures.length)],
                    this,
                    newSize,
                    player
            ));
        }
    }*/
    /**
     * Genera un nuevo objeto "Ruler" en una posición aleatoria en el borde de la pantalla.
     * La posición del nuevo objeto depende de un valor aleatorio que determina si se ubicará en el borde horizontal o vertical.
     */
    private void spawnRuler(){

        int rand = (int) (Math.random()*2);

        double x = rand == 0 ? (Math.random()*Constants.WIDTH): 0;
        double y = rand == 0 ? 0 : (Math.random()*Constants.HEIGHT);



        movingObjects.add(new Ruler(
                new Vector2D(x, y),
                new Vector2D(),
                Constants.RULER_MAX_VELOCITY,
                assets.ruler,
                this
        ));

    }
    /**
     * Inicia una nueva oleada de enemigos.
     * Agrega un mensaje indicando el inicio de la oleada y genera enemigos con posiciones aleatorias en el borde de la pantalla.
     */
    private void startWave() {
        messages.add(new Message(new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2),
                true,"WAVE "+ waves, Color.white, true, assets.fontWave));

        waves++;
        double x, y;

        for (int i = 0; i < enemies; i++) {

            x = i % 2 == 0 ? Math.random() * Constants.WIDTH : 0;
            y = i % 2 == 0 ? 0 : Math.random() * Constants.HEIGHT;

            BufferedImage texture = assets.bigs[(int) (Math.random() * assets.bigs.length)];

            movingObjects.add(new Enemies(
                    new Vector2D(x, y),
                    new Vector2D(0, 1).setDirection(Math.random() * Math.PI * 2),
                    Constants.ENEMIES_VELOCITY * Math.random() + 1,
                    texture,
                    this,
                    Size.BIG,
                    player
            ));

        }
        enemies++;
    }

    /**
     * Actualiza el estado del juego, incluyendo la lógica de juego, la generación de objetos y la gestión de eventos.
     */
    public void update() {
        if(!Keyboard.PAUSE){
            visible=true;
            for (int i = 0; i < movingObjects.size(); i++) {
                MovingObject mo = movingObjects.get(i);
                mo.update();
                if(mo.isDead()){
                    movingObjects.remove(i);
                    i--;
                }
            }
            if(gameOver) {
                try {
                    ArrayList<ScoreData> dataList = JsonParser.readFile();
                    dataList.add(new ScoreData(score));
                    JsonParser.writeFile(dataList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                State.changeState(new GameOverState(score));
            }
            if(!rulerSpawner.isRunning()) {
                rulerSpawner.run(Constants.RULER_SPAWN_RATE);
                spawnRuler();
            }
            if(!waveSpawner.isRunning()) {
                waveSpawner.run(Constants.WAVE_SPAWN_RATE);
                startWave();
            }

            rulerSpawner.update();
            waveSpawner.update();

        }
        else{
            if(!flickertime.isRunning()){
                flickertime.run(700);
                visible=!visible;
            }
            returnButton.update();
        }
        flickertime.update();
    }

    /**
     * Dibuja el estado actual del juego en el lienzo proporcionado.
     *
     * @param graphics El contexto gráfico en el que se realizará el dibujo.
     */
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(backgroundImage, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
        for(int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(graphics);
        }
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).draw(graphics2D);
            if(messages.get(i).isDead()){
                messages.remove(i);
            }
        }
        drawScore(graphics);
        drawLives(graphics);

        if(Keyboard.PAUSE){
            returnButton.draw(graphics);
            if(visible){
                graphics.drawImage(assets.pause, (Constants.WIDTH- assets.pause.getWidth())/2,(Constants.HEIGHT- assets.pause.getHeight())/2,null);
            }
        }

        graphics2D.dispose();
    }

    /**
     * Dibuja la representación visual del score del jugador en la pantalla.
     *
     * @param g El contexto gráfico en el que se realizará el dibujo.
     */
    private void drawScore(Graphics g) {

        Vector2D pos = new Vector2D(85, 75);

        String scoreToString = Integer.toString(score);

        for(int i = 0; i < scoreToString.length(); i++) {

            g.drawImage(assets.numbers[Integer.parseInt(scoreToString.substring(i, i + 1))],
                    (int)pos.getX(), (int)pos.getY(), null);
            pos.setX(pos.getX() + 20);

        }
    }
    /**
     * Dibuja la representación visual de las vidas del jugador en la pantalla.
     *
     * @param graphics El contexto gráfico en el que se realizará el dibujo.
     */
    private void drawLives(Graphics graphics){
        if(lives < 1){
            return;
        }

        Vector2D livePosition = new Vector2D(25, 25);

        graphics.drawImage(assets.vida, (int)livePosition.getX(), (int)livePosition.getY(), null);

        graphics.drawImage(assets.numbers[10], (int)livePosition.getX() + 40,
                (int)livePosition.getY() + 5, null);

        String livesToString = Integer.toString(lives);

        Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());

        for(int i = 0; i < livesToString.length(); i ++)
        {
            int number = Integer.parseInt(livesToString.substring(i, i+1));

            if(number <= 0)
                break;
            graphics.drawImage(assets.numbers[number],
                    (int)pos.getX() + 60, (int)pos.getY() + 5, null);
            pos.setX(pos.getX() + 20);
        }

    }

    /**
     * Establece el estado de juego como "game over".
     */
    public void gameOver() {
        gameOver = true;
    }
    /**
     * Obtiene la lista de objetos móviles en el juego.
     *
     * @return La lista de objetos móviles en el juego.
     */
    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
    /**
     * Obtiene la lista de mensajes en el juego.
     *
     * @return La lista de mensajes en el juego.
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }
    /**
     * Obtiene el objeto jugador del juego.
     *
     * @return El objeto jugador del juego.
     */
    public Player getPlayer() {
        return player;
    }
}
