package graphics;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Se encarga de la carga de imagenes/fuentes
 */

public class loader {
    /**
     * Carga las imagenes
     * @param path Directorio de la imagen
     * @return Try->La imagen Catch->Null
     */
    public static BufferedImage imageLoader(String path){
        try {
            return ImageIO.read(loader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Carga las fuentes
     * @param path Directorio de la fuente
     * @param size Tamaño de la fuente
     * @return La fuente cargada
     */
    public static Font loadFont(String path, int size){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Clip loadSound(String path){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(loader.class.getResource(path)));
            return clip;
        }catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }
}
