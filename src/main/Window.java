package main;
import gameObjects.Constants;
import input.Keyboard;
import states.GameState;
import graphics.assets;
import graphics.loader;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame implements Runnable{
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private final int  FPS = 60;                                    //Frames per second
    private double targetTime = 1000000000/FPS;                     //Nanoseconds
    private double delta = 0;
    private int averageFPS = FPS;
    private GameState gameState;
    private Keyboard keyboard;

    public Window(){                                                //General constructor
        setTitle("Until dawn");                                     //Window title
        setSize(Constants.WIDTH,Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             //Allows to close window when pressing x
        setResizable(false);                                        //Cant resize window
        setLocationRelativeTo(null);                                //Window appears in the middle of the screen
        setVisible(true);                                           //Window can now be seeing

        canvas = new Canvas();
        keyboard = new Keyboard();

        canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setFocusable(true);                                  //Allows keyboard inputs
        add(canvas);
        canvas.addKeyListener(keyboard);
    }
    public static void main(String[] args) {
        new Window().start();
    }

    private void update(){
        keyboard.update();
        gameState.update();
    }

    private void draw(){
        bufferStrategy = canvas.getBufferStrategy();                //It will be null since bufferStrategy has not been created

        if(bufferStrategy == null){
            canvas.createBufferStrategy(3);              //3 stands for number of canvas that are used
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();
        //------------------------------------
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,Constants.WIDTH,Constants.HEIGHT);
        gameState.draw(graphics);
        graphics.setColor(Color.white);
        graphics.drawString(""+averageFPS,0,10);
        //------------------------------------
        graphics.dispose();
        bufferStrategy.show();
    }

    private void init(){

        assets.init();
        gameState = new GameState();
    } 

    @Override
    public void run(){                                              //For class "window" to be runnable must implement void run()
        long now = 0;
        long lastTime = System.nanoTime();
        int frames=0;
        long time=0;

        init();

        while(running){
            now = System.nanoTime();
            delta += (now-lastTime)/targetTime;
            time += (now-lastTime);
            lastTime = now;

            if(delta >=1){
                update();
                draw();
                delta--;
                frames++;
            }
            if(time >= 1000000000){
                averageFPS = frames;
                frames=0;
                time=0;
            }
        }

        stop();
    }
    private void start(){                                           //Start thread
        thread = new Thread(this);                             //"this" refers to a class to implements runnable in this case "window"
        thread.start();
        running = true;
    }
    private void stop(){                                            //Stop thread
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}