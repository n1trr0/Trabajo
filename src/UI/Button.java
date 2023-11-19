package UI;

import input.Mouse;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {
    private BufferedImage mouseOutImg;
    private BufferedImage mouseInImg;
    private boolean mouseIn;
    private Rectangle boundingBox;
    private Action action;
    private String text;

    public Button(BufferedImage Out, BufferedImage In, int x, int y, String text, Action action){
        this.mouseInImg = In;
        this.mouseOutImg = Out;
        this.text = text;
        boundingBox = new Rectangle(x, y, mouseInImg.getWidth(), mouseInImg.getHeight());
        this.action = action;
    }
    public void update(){
        if(boundingBox.contains(Mouse.x, Mouse.y)){
            mouseIn = true;
        }
        else{
            mouseIn = false;
        }
        if(mouseIn && Mouse.clickIzq){
            action.doAction();
        }
    }
    public void draw(Graphics graphics){
        if(mouseIn){
            graphics.drawImage(mouseInImg, boundingBox.x, boundingBox.y, null);
        }
        else{
            graphics.drawImage(mouseOutImg, boundingBox.x, boundingBox.y, null);
        }
    }
}
