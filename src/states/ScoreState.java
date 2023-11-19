package states;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import UI.Action;
import gameObjects.Constants;
import graphics.Text;
import graphics.assets;
import io.JsonParser;
import io.ScoreData;
import UI.Button;
import math.Vector2D;


public class ScoreState extends State {

    private Button returnButton;
    private PriorityQueue<ScoreData> highScores;
    private Comparator<ScoreData>scoreComparator;
    private ScoreData[]auxArray;
    private BufferedImage backgroundImage;
    public ScoreState(){
        returnButton=new Button(
                assets.volverOut,
                assets.volverIn,
                assets.volverOut.getHeight(),
                Constants.HEIGHT - assets.volverOut.getHeight()*2,
                Constants.PLAY,
                new Action(){
                    @Override
                    public void doAction() {
                        State.changeState(new MenuState());
                    }
                }
        );
        scoreComparator = new Comparator<ScoreData>() {
            @Override
            public int compare(ScoreData o1, ScoreData o2) {
                return o1.getScore()<o2.getScore()?-1: o1.getScore()> o2.getScore()?1:0;
            }
        };
        highScores=new PriorityQueue<ScoreData>(10,scoreComparator);
        try {
            ArrayList<ScoreData> dataList = JsonParser.readFile();
            for(ScoreData data: dataList){
                highScores.add(data);
            }
            while(highScores.size() > 10){
                highScores.poll();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        backgroundImage = assets.menuBackground;
    }
    @Override
    public void update() {
        returnButton.update();
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(backgroundImage, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
        returnButton.draw(graphics);
        auxArray=highScores.toArray(new ScoreData[highScores.size()]);
        Arrays.sort(auxArray,scoreComparator);
        Vector2D scorePos = new Vector2D(Constants.WIDTH/2-200,100);
        Vector2D datePos= new Vector2D(Constants.WIDTH/2+200,100);
        Text.drawText(graphics,Constants.SCORE,scorePos,true,Color.BLUE,assets.fontMed);
        Text.drawText(graphics,Constants.DATE,datePos,true,Color.BLUE,assets.fontMed);
        scorePos.setY(scorePos.getY()+40);
        datePos.setY(datePos.getY() + 40);
        for(int i = auxArray.length - 1; i > -1; i--) {

            ScoreData d = auxArray[i];

            Text.drawText(graphics, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, assets.fontMed);
            Text.drawText(graphics, d.getDate(), datePos, true, Color.WHITE, assets.fontMed);

            scorePos.setY(scorePos.getY() + 40);
            datePos.setY(datePos.getY() + 40);

        }
    }
}
