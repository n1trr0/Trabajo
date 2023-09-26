package main;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame implements Runnable{
    public static final int WIDTH = 800, HEIGHT = 600;              //Window dimensions
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    public Window(){                                                //General constructor
        setTitle("Survivor");                                       //Window title
        setSize(WIDTH,HEIGHT);                                      //Window dimensions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             //Allows to close window when pressing x
        setResizable(false);                                        //Cant resize window
        setLocationRelativeTo(null);                                //Window appears in the middle of the screen
        setVisible(true);                                           //Window can now be seeing

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(true);                                  //Allows keyboard inputs
        add(canvas);
    }
    public static void main(String[] args) {
        new Window().start();
    }

    private void update(){

    }

    private void draw(){
        bufferStrategy = canvas.getBufferStrategy();                //It will be null since bufferStrategy has not been created

        if(bufferStrategy == null){
            canvas.createBufferStrategy(3);              //3 stands for number of canvas that are used
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();
        //------------------------------------
        graphics.clearRect(0,0,WIDTH,HEIGHT);
        graphics.drawRect(0,0,100,100);
        //------------------------------------
        graphics.dispose();
        bufferStrategy.show();
    }

    @Override
    public void run(){                                              //For class "window" to be runnable must implement void run()
        while(running){
            update();
            draw();
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