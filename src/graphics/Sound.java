package graphics;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Implementa los sonidos en el juego
 * @author Raul Garcia & Alejandro Molero
 */
public class Sound {
    /**
     * Almacena el sonido
     */
    private Clip clip;
    /**
     * Maneja el volumen del sonido
     */
    private FloatControl volume;

    /**
     * Constructor
     * @param clip
     */
    public Sound(Clip clip){
        this.clip = clip;
        volume  = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
    }

    /**
     * Activa el sonido en modo bucle
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**
     * Activa el sonido desde el principio en modo bucle
     */
    public void loopInicio(){
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**
     * Activa el sonido desde el principio
     */
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    /**
     * Detiene el sonido
     */
    public void stop(){
        clip.stop();
    }

    /**
     * Obtiene el frame actual del sonido
     * @return Posicion del frame actual
     */
    public int getFramePos(){return clip.getFramePosition();}
    /**
     * Cambia el volumen
     */
    public void changeVolume(float value){
        volume.setValue(value);
    }
    public void mute(){
        volume.setValue(volume.getMinimum());
    }
}
