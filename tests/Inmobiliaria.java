import java.util.ArrayList;
import java.util.List;

/**
 * PS 5.3
 * Gestiona una lista de departamentos y realiza las operaciones
 * de filtrado y cálculo solicitadas.
 */
public class Inmobiliaria {

    // Lista que almacena todos los departamentos
    private List<Departamento> inventario;

    // Constructor
    public Inmobiliaria(List<Departamento> departamentos) {
        this.inventario = departamentos;
    }

    /**
     * a) Listar datos de departamentos disponibles con precio <= R.
     */
    public List<Departamento> incisoA(double r_precioMax) {
        List<Departamento> resultado = new ArrayList<>();
        
        // Recorremos todo el inventario
        for (Departamento depto : inventario) {
            if (depto.isDisponible() && depto.getPrecio() <= r_precioMax) {
                resultado.add(depto);
            }
        }
        return resultado;
    }

    /**
     * b) Listar datos de departamentos disponibles con extensión >= E
     * y ubicación "excelente".
     */
    public List<Departamento> incisoB(double e_extensionMin) {
        List<Departamento> resultado = new ArrayList<>();
        
        for (Departamento depto : inventario) {
            if (depto.isDisponible() &&
                depto.getExtension() >= e_extensionMin &&
                depto.getUbicacion().equals("excelente")) {
                
                resultado.add(depto);
            }
        }
        return resultado;
    }

    /**
     * c) Listar el monto de la renta de todos los departamentos alquilados.
     * (En el problema no queda claro si es la suma total o una lista,
     * calcularemos la SUMA TOTAL).
     */
    public double incisoC_SumaTotalAlquilados() {
        double montoTotal = 0;
        
        for (Departamento depto : inventario) {
            // Si NO está disponible, está alquilado
            if (!depto.isDisponible()) {
                montoTotal += depto.getPrecio();
            }
        }
        return montoTotal;
    }
}