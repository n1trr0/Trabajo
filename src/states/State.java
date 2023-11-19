package states;

import java.awt.*;

public abstract class State {
    public static State currentState = null;
    public static State getCurrentState(){return currentState;};
    public static void changeState(State newState){currentState = newState;};
    public abstract void update();
    public abstract void draw(Graphics graphics);

}
