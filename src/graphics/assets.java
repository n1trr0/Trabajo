package graphics;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Carga las imagenes para las texturas de los objetos
 *
 * @author Raul Garcia & Alejandro Molero
 */

public class assets {
    /**
     * Indica si todos los recursos fueron cargados
     */
    public static boolean loaded = false;
    /**
     * Porcentaje por el que va la carga de objetos
     */
    public static float count = 0;
    /**
     * Numero maximo de carga de objetos
     */
    public static float maxCount = 30;
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
    /**
     * Imagen de las vidas del jugador
     */
    public static BufferedImage vida;
    /**
     * Imagen del boton jugar cuando el raton esta encima
     */
    public static BufferedImage playIn;
    /**
     * Imagen del boton jugar por defecto
     */
    public static BufferedImage playOut;
    /**
     * Imagen del boton salir cuando el raton esta encima
     */
    public static BufferedImage salirIn;
    /**
     * Imagen del boton salir por defecto
     */
    public static BufferedImage salirOut;
    /**
     * Fuente de que aparece al principio de cada oleada
     */
    public static Font fontWave;
    /**
     * Fuente estandar del juego
     */
    public static Font fontMed;
    /**
     * Musica de fondo del menu
     */
    public static Clip menuMusic;
    public static BufferedImage menuBackground;
    /**
     * Carga las imagenes/fuentes/sonidos/animaciones desde el directorio indicado
     */
    public static void init(){
        player = loadImage("/characters/nocturne.png");
        fireball = loadImage("/projectiles/fireball.png");
        ruler = loadImage("/characters/enemies/ruler.png");
        vida = loadImage("/hud/mask.png");
        playIn = loadImage("/ui/playIn.png");
        playOut = loadImage("/ui/playOut.png");
        salirIn = loadImage("/ui/SalirIn.png");
        salirOut = loadImage("/ui/SalirOut.png");

        menuBackground = loadImage("/menuBkg/menuBackground0.png");

        for(int i = 0; i < bigs.length; i++)
            bigs[i] = loadImage("/characters/enemies/big"+(i+1)+".png");

        for(int i = 0; i < meds.length; i++)
            meds[i] = loadImage("/characters/enemies/med"+(i+1)+".png");

        for(int i = 0; i < smalls.length; i++)
            smalls[i] = loadImage("/characters/enemies/small"+(i+1)+".png");

        for(int i = 0; i < tinies.length; i++)
            tinies[i] = loadImage("/characters/enemies/tiny"+(i+1)+".png");
        for(int i = 0; i < numbers.length; i++)
            numbers[i] = loadImage("/hud/"+i+".png");

        menuMusic = loadSound("/sound/menuMusic.wav");

        fontWave = loadFont("/fonts/future.ttf",42);
        fontMed = loadFont("/fonts/future.ttf",20);

        loaded = true;
    }

    /**
     * Metodo que carga la imaganes
     * @param path Ruta del archivo
     * @return
     */
    public static BufferedImage loadImage(String path){
        count++;
        return  loader.imageLoader(path);
    }

    /**
     * Metodo que carga las fuentes
     * @param path Ruta del archivo
     * @param size Escala de la fuente
     * @return
     */
    public static Font loadFont(String path, int size){
        count++;
        return loader.loadFont(path, size);
    }

    /**
     * Metodo que carga los sonidos
     * @param path Ruta del archivo
     * @return
     */
    public static Clip loadSound(String path){
        count++;
        return loader.loadSound(path);
    }
}
