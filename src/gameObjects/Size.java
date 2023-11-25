package gameObjects;

import java.awt.image.BufferedImage;

import graphics.assets;

/**
 * Almacena las diferentes formas que tendra la clase enemies
 * @author Raul Garcia & Alejandro Molero
 */

public enum Size {
    /**
     * Cada una de las formas que hay almacenadas por tamaño
     */
    BIG(2, assets.meds), MED(2, assets.smalls), SMALL(2, assets.tinies), TINY(0, null);
    /**
     * Numero total de formas que hay
     */
    public int quantity;
    /**
     * Imagenes de las formas
     */
    public BufferedImage[] textures;
    /**
     * Constructor
     * @param quantity numero total de formas
     * @param textures imagenes de las formas
     */
    private Size(int quantity, BufferedImage[] textures){
        this.quantity = quantity;
        this.textures = textures;
    }

}