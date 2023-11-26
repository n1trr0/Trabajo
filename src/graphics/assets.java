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
    public static float maxCount = 43;
    /**
     * Imagen del jugador
     */
    public static BufferedImage player;
    /**
     * Imagen de los disparos
     */
    public static BufferedImage fireball;
    /**
     * Imagen de los disparos enemigos
     */
    public static BufferedImage fireballToxic;
    public static BufferedImage[] numbers = new BufferedImage[11];
    //enemies
    public static BufferedImage[] bigs = new BufferedImage[2];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];
    /**
     * Imagen de los enemigos de clase ruler
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
     * Imagen del boton opciones cuando el raton esta encima
     */
    public static BufferedImage opcionesIn;
    /**
     * Imagen del boton opciones por defecto
     */
    public static BufferedImage opcionesOut;
    /**
     * Imagen del boton volver cuando el raton esta encima
     */
    public static BufferedImage volverIn;
    /**
     * Imagen del boton volver por defecto
     */
    public static BufferedImage volverOut;
    /**
     * Imagen del boton score cuando el raton esta encima
     */
    public static BufferedImage scoreIn;
    /**
     * Imagen del boton score por defecto
     */
    public static BufferedImage scoreOut;
    /**
     * Imagen del score en las tablas de puntuacion
     */
    public static BufferedImage score;
    /**
     * Imagen de la fecha en las tablas de puntuacion
     */
    public static BufferedImage date;
    /**
     * Imagen de la pausa
     */
    public static BufferedImage pause;
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
    /**
     * Imagen de fondo del menu
     */
    public static BufferedImage menuBackground;
    /**
     * Imagen de fondo dentro de la partida
     */
    public static BufferedImage gameBackground;
    /**
     * Imagen del nombre del menu principal
     */
    public static BufferedImage titleImage;
    /**
     * Carga las imagenes/fuentes/sonidos/animaciones desde el directorio indicado
     */
    public static void init(){
        player = loadImage("/characters/nocturne.png");
        fireball = loadImage("/projectiles/fireball.png");
        fireballToxic = loadImage("/projectiles/fireballToxic.png");
        ruler = loadImage("/characters/enemies/ruler.png");
        vida = loadImage("/hud/mask.png");

        playIn = loadImage("/ui/playIn.png");
        playOut = loadImage("/ui/playOut.png");
        salirIn = loadImage("/ui/SalirIn.png");
        salirOut = loadImage("/ui/SalirOut.png");
        opcionesIn = loadImage("/ui/optionsIn.png");
        opcionesOut = loadImage("/ui/optionsOut.png");
        volverIn = loadImage("/ui/volverIn.png");
        volverOut = loadImage("/ui/volverOut.png");
        scoreIn = loadImage("/ui/scoreIn.png");
        scoreOut = loadImage("/ui/scoreOut.png");
        score = loadImage("/ui/score.png");
        date = loadImage("/ui/date.png");
        titleImage = loadImage("/ui/UntilDawn.png");
        pause = loadImage("/ui/pause.png");

        menuBackground = loadImage("/menuBkg/menuBackground0.png");
        gameBackground = loadImage("/bkg.png");

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

        fontWave = loadFont("/fonts/TrajanProBold.ttf",42);
        fontMed = loadFont("/fonts/TrajanProBold.ttf",20);

        loaded = true;
    }

    /**
     * Metodo que carga la imaganes
     * @param path Ruta del archivo
     * @return Devuelve la imagen
     */
    public static BufferedImage loadImage(String path){
        count++;
        return  loader.imageLoader(path);
    }

    /**
     * Metodo que carga las fuentes
     * @param path Ruta del archivo
     * @param size Escala de la fuente
     * @return Devuelve la fuente
     */
    public static Font loadFont(String path, int size){
        count++;
        return loader.loadFont(path, size);
    }

    /**
     * Metodo que carga los sonidos
     * @param path Ruta del archivo
     * @return Devuelve el sonido
     */
    public static Clip loadSound(String path){
        count++;
        return loader.loadSound(path);
    }
}
