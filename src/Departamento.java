/**
 * PS 5.3
 * Define la estructura de datos para un departamento.
 * Esta es una clase de tipo "POJO" (Plain Old Java Object) o "record".
 */
public class Departamento {

    // Atributos de cada departamento
    private int clave;
    private double extension;
    private String ubicacion;
    private double precio;
    private boolean disponible;

    // Constructor para crear un nuevo departamento
    public Departamento(int clave, double extension, String ubicacion, double precio, boolean disponible) {
        this.clave = clave;
        this.extension = extension;
        this.ubicacion = ubicacion; // "excelente", "buena", "regular", "mala"
        this.precio = precio;
        this.disponible = disponible;
    }

    // Getters: Métodos públicos para acceder a los datos privados
    public int getClave() { return clave; }
    public double getExtension() { return extension; }
    public String getUbicacion() { return ubicacion; }
    public double getPrecio() { return precio; }
    public boolean isDisponible() { return disponible; }

    // (Opcional) Sobrescribimos toString para imprimirlo fácilmente
    @Override
    public String toString() {
        return "Clave: " + clave + ", Ext: " + extension + "m², Precio: $" + precio;
    }
}