package graphics;

import java.awt.image.BufferedImage;

public class assets {
    //player
    public static BufferedImage player;
    //projectiles
    public static BufferedImage fireball;
    //enemies
    public static BufferedImage[] bigs = new BufferedImage[2];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];
    public static void init(){
        player = loader.imageLoader("/characters/nocturne.png");
        fireball = loader.imageLoader("/projectiles/fireball.png");

        for(int i = 0; i < bigs.length; i++)
            bigs[i] = loader.imageLoader("/characters/enemies/big"+(i+1)+".png");

        for(int i = 0; i < meds.length; i++)
            meds[i] = loader.imageLoader("/characters/enemies/med"+(i+1)+".png");

        for(int i = 0; i < smalls.length; i++)
            smalls[i] = loader.imageLoader("/characters/enemies/small"+(i+1)+".png");

        for(int i = 0; i < tinies.length; i++)
            tinies[i] = loader.imageLoader("/characters/enemies/tiny"+(i+1)+".png");
    }
}
