package states;

import gameObjects.*;
import graphics.Text;
import graphics.assets;
import math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameState extends State{
    private Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
    private ArrayList<Message> messages = new ArrayList<Message>();
    private int enemies;
    private int score = 0;
    private int lives = 3;
    private int gold = 0;
    private int waves = 1;
    private Chronometer rulerSpawner;
    public GameState() {
        player = new Player(new Vector2D(600, 600), new Vector2D(), 5, assets.player, this);
        movingObjects.add(player);
        enemies = 1;
        startWave();

        rulerSpawner = new Chronometer();
        rulerSpawner.run(Constants.RULER_SPAWN_RATE);
    }
    public boolean loseLive(){
        lives--;
        return lives > 0;
    }

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

    private void spawnRuler(){

        int rand = (int) (Math.random()*2);

        double x = rand == 0 ? (Math.random()*Constants.WIDTH): 0;
        double y = rand == 0 ? 0 : (Math.random()*Constants.HEIGHT);

        ArrayList<Vector2D> path = new ArrayList<Vector2D>();

        double posX, posY;

        posX = Math.random()*Constants.WIDTH/2;
        posY = Math.random()*Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
        posY = Math.random()*Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random()*Constants.WIDTH/2;
        posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
        posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));

        movingObjects.add(new Ruler(
                new Vector2D(x, y),
                new Vector2D(),
                Constants.RULER_MAX_VELOCITY,
                assets.ruler,
                path,
                this
        ));

    }

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

    public void update() {
        for (int i = 0; i < movingObjects.size(); i++) {
            MovingObject mo = movingObjects.get(i);
            mo.update();
            if(mo.isDead()){
                movingObjects.remove(i);
                i--;
            }
        }
        if(!rulerSpawner.isRunning()) {
            rulerSpawner.run(Constants.RULER_SPAWN_RATE);
            spawnRuler();
        }
        rulerSpawner.update();
        for (int i = 0; i < movingObjects.size(); i++)
            if (movingObjects.get(i) instanceof Enemies)
                return;

        startWave();
    }

    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        for (int i = 0; i < movingObjects.size(); i++) {
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
        drawGold(graphics);
    }

    private void drawScore(Graphics g) {

        Vector2D pos = new Vector2D(Constants.WIDTH* 0.95, 25);

        String scoreToString = Integer.toString(score);

        for(int i = 0; i < scoreToString.length(); i++) {

            g.drawImage(assets.numbers[Integer.parseInt(scoreToString.substring(i, i + 1))],
                    (int)pos.getX(), (int)pos.getY(), null);
            pos.setX(pos.getX() + 20);

        }
    }
    private void drawGold(Graphics g) {

        Vector2D pos = new Vector2D(85, 75);

        gold = (int) (score*Constants.GOLD);
        String goldToString = Integer.toString(gold);

        for(int i = 0; i < goldToString.length(); i++) {

            g.drawImage(assets.numbers[Integer.parseInt(goldToString.substring(i, i + 1))],
                    (int)pos.getX(), (int)pos.getY(), null);
            pos.setX(pos.getX() + 20);

        }
    }
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
    public void gameOver(){

    }
    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
    public ArrayList<Message> getMessages() {
        return messages;
    }
    public Player getPlayer() {
        return player;
    }
}