package math;

/**
 * Implementa una clase se comporta como un vector 2D para calcular las posiciones de los objetos
 * @author Raul Garcia & Alejandro Molero
 */
public class Vector2D {
    /**
     * Posicion en el eje x e y
     */
    private double x,y;

    /**
     * Inicializa la posicion en el parametro indicado
     * @param x
     * @param y
     */
    public  Vector2D(double x,double y){
        this.x= x;
        this.y= y;
    }

    /**
     * Constructor por defecto inicializa la posicion en 0,0
     */
    public Vector2D(){
        x=0;
        y=0;
    }
    /**
     * Suma un valor a cada coordenada
     * @param vector Vector2D que se suma al actual
     * @return Vector2D con el valor actual + el valor del parametro vector
     */
    public Vector2D add(Vector2D vector){
        return new Vector2D(x + vector.getX(),y + vector.getY());
    }
    /**
     * Resta un valor a cada coordenada
     * @param vector Vector2D que se resta al actual
     * @return Vector2D con el valor actual - el valor del parametro vector
     */
    public Vector2D substract(Vector2D vector){
        return new Vector2D(x - vector.getX(),y - vector.getY());
    }
    /**
     * Escala el vector multiplicandolo por un valor
     * @param value valor por el que se multiplica el vector
     * @return Vector2D escalado
     */
    public Vector2D scale(double value){
        return new Vector2D(x*value,y*value);
    }

    /**
     * Si la magnitud es mayor que el parametro otorgado lo normaliza y luego lo escala con el valor de value,
     * pero si no es mayor siemplemente devuelve el valor
     * @param value
     * @return
     */
    public Vector2D limit(double value){
        if(getMagnitude() > value){
            return this.normalize().scale(value);
        }
        return this;
    }

    /**
     * Normaliza el vector diviendo sus coordenadas entre la magnitud
     * @return
     */
    public Vector2D normalize(){
        double magnitude = getMagnitude();
        return new Vector2D(x/magnitude, y/magnitude);
    }

    /**
     * Calcula la magnitud del vector
     * @return El valor de la magnitud
     */
    public double getMagnitude(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D setDirection(double angle){
        double magnitude = getMagnitude();
        return new Vector2D(Math.cos(angle)*magnitude,Math.sin(angle)*magnitude);
    }
    public double getAngle(){
        return Math.asin(y/getMagnitude());
    }
    /**
     * getter de y
     * @return valor de y
     */
    public double getY() {
        return y;
    }
    /**
     * setter de y
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * getter de x
     * @return valor de x
     */
    public double getX() {
        return x;
    }
    /**
     * setter de x
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }
}
