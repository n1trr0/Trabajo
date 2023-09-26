package main;

import javax.swing.JFrame;
public class Window extends JFrame {

    public static final int WIDTH = 800, HEIGHT = 600;              //Window dimensions
    public Window(){                                                  //Constructor general
        setTitle("Survivor");                                       //Window title
        setSize(WIDTH,HEIGHT);                                      //Window dimensions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             //Allows to close window when pressing x
        setResizable(false);                                        //Cant resize window
        setLocationRelativeTo(null);                                //Window appears in the middle of the screen
        setVisible(true);                                           //Window can now be seeing

    }
    public static void main(String[] args) {
        new Window();
    }
}