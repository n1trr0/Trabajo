package graphics;

import java.awt.image.BufferedImage;

/**
 * Carga las imagenes para las texturas de los objetos
 * @author Raul Garcia & Alejandro Molero
 */

public class assets {
    /**
     * Imagen del jugador
     * @param player Imagen del jugador
     */
    public static BufferedImage player;
    /**
     * Imagen de los disparos
     * @param fireball Imagen de los disparos
     */
    public static BufferedImage fireball;
    public static BufferedImage[] numbers = new BufferedImage[11];
    //enemies
    public static BufferedImage[] bigs = new BufferedImage[2];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];
    /**
     * Imagen de los enemigos de clase ruler
     * @param ruler Imagen de los enemigos de clase ruler
     */
    public static BufferedImage ruler;
    public static BufferedImage vida;

    /**
     * Carga las imagenes desde el directorio indicado
     */
    public static void init(){
        player = loader.imageLoader("/characters/nocturne.png");
        fireball = loader.imageLoader("/projectiles/fireball.png");
        ruler = loader.imageLoader("/characters/enemies/ruler.png");
        vida = loader.imageLoader("/hud/mask.png");

        for(int i = 0; i < bigs.length; i++)
            bigs[i] = loader.imageLoader("/characters/enemies/big"+(i+1)+".png");

        for(int i = 0; i < meds.length; i++)
            meds[i] = loader.imageLoader("/characters/enemies/med"+(i+1)+".png");

        for(int i = 0; i < smalls.length; i++)
            smalls[i] = loader.imageLoader("/characters/enemies/small"+(i+1)+".png");

        for(int i = 0; i < tinies.length; i++)
            tinies[i] = loader.imageLoader("/characters/enemies/tiny"+(i+1)+".png");
        for(int i = 0; i < numbers.length; i++)
            numbers[i] = loader.imageLoader("/hud/"+i+".png");
    }
}
