package main;

import gameObjects.Constants;
import graphics.Text;
import input.Keyboard;
import input.Mouse;
import math.Vector2D;
import states.GameState;
import graphics.assets;
import graphics.loader;
import states.LoadingState;
import states.MenuState;
import states.State;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Representa la ventana en la que corre el programa
 * @author Raul Garcia & Alejandro Molero
 */

public class Window extends JFrame implements Runnable {

    /**
     * El componente de lienzo donde se dibujará el juego.
     */
    private Canvas canvas;

    /**
     * Hilo de ejecución del juego.
     */
    private Thread thread;

    /**
     * Indica si el juego está en ejecución.
     */
    private boolean running = false;

    /**
     * Estrategia de búfer para gestionar el doble búfer.
     */
    private BufferStrategy bufferStrategy;

    /**
     * Objeto para realizar operaciones gráficas en el lienzo.
     */
    private Graphics graphics;

    /**
     * Número de fotogramas por segundo objetivo del juego.
     */
    private final int FPS = 60; //Frames per second

    /**
     * Tiempo objetivo por fotograma en nanosegundos.
     */
    private double targetTime = 1000000000 / FPS;                     //Nanoseconds

    /**
     * Diferencia de tiempo entre fotogramas.
     */
    private double delta = 0;

    /**
     * Fotogramas por segundo promedio.
     */
    private int averageFPS = FPS;

    /**
     * Objeto para gestionar la entrada del teclado.
     */
    private Keyboard keyboard;

    /**
     * Objeto para gestionar la entrada del mouse.
     */
    private Mouse mouse;

    /**
     * Constructor de la clase window
     */
    public Window() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        Constants.WIDTH=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        Constants.HEIGHT=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        setSize(Constants.WIDTH,Constants.HEIGHT);
        setTitle("Until dawn");                                     //Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             //Allows to close window when pressing x
        setResizable(false);                                        //Cant resize window
        setLocationRelativeTo(null);                                //Window appears in the middle of the screen



        canvas = new Canvas();
        keyboard = new Keyboard();
        mouse = new Mouse();

        canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setFocusable(true);                                  //Allows keyboard inputs
        add(canvas);
        canvas.addKeyListener(keyboard);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Window().start();
    }

    /**
     * Actualiza la lógica del juego y el estado del teclado.
     */
    private void update() {
        keyboard.update();
        State.getCurrentState().update();
    }


    /**
     * Dibuja el contenido del juego en el lienzo.
     */
    private void draw() {
        bufferStrategy = canvas.getBufferStrategy();                //It will be null since bufferStrategy has not been created

        if (bufferStrategy == null) {
            canvas.createBufferStrategy(3);              //3 stands for number of canvas that are used
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();
        //------------------------------------
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        State.getCurrentState().draw(graphics);

        //graphics.setColor(Color.white);
        //graphics.drawString(""+averageFPS, 0, 30);
        //------------------------------------
        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * Inicializa el programa cargando los assets y abriendo el estado de carga
     */
    private void init() {
        Thread loadingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                assets.init();
            }
        });
        State.changeState(new LoadingState(loadingThread));
    }


    /**
     * Método principal que ejecuta el bucle del juego.
     */
    @Override
    public void run() {                                              //For class "window" to be runnable must implement void run()
        long now = 0;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;

        init();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / targetTime;
            time += (now - lastTime);
            lastTime = now;

            if (delta >= 1) {
                update();
                draw();
                delta--;
                frames++;
            }
            if (time >= 1000000000) {
                averageFPS = frames;
                frames = 0;
                time = 0;
            }
        }

        stop();
    }

    /**
     * Inicia el hilo del juego.
     */
    private void start() {                                           //Start thread
        thread = new Thread(this);                             //"this" refers to a class to implements runnable in this case "window"
        thread.start();
        running = true;
    }

    /**
     * Detiene el hilo del juego.
     */
    private void stop() {                                            //Stop thread
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}