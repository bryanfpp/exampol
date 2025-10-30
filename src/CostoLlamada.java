/**
 * PS 2.18
 * Calcula el costo final de una llamada telefónica basado en la
 * CLAVE de la zona y el número de minutos (NUMIN).
 */
public class CostoLlamada {

    /**
     * Calcula el costo total de la llamada.
     *
     * @param clave La clave de la zona geográfica.
     * @param numin El número total de minutos de la llamada.
     * @return El costo total (COSTO).
     */
    public double calcularCosto(int clave, int numin) {
        
        // Validación de datos de entrada
        if (numin < 0) {
            throw new IllegalArgumentException("El número de minutos no puede ser negativo.");
        }

        // Usamos if-else-if en lugar de switch por la CLAVE 25 duplicada
        if (clave == 12) { // A. Norte
            return calcular(numin, 2.0, 1.5);
        } else if (clave == 15) { // A. Central
            return calcular(numin, 2.2, 1.8);
        } else if (clave == 18) { // A. Sur
            return calcular(numin, 4.5, 3.5);
        } else if (clave == 19) { // Europa
            return calcular(numin, 3.5, 2.7);
        } else if (clave == 25) { // Asia y África (ambos usan CLAVE 25)
            return calcular(numin, 6.0, 4.6);
        } else if (clave == 29) { // Oceanía
            return calcular(numin, 5.0, 3.9);
        } else {
            // Si la clave no es ninguna de las anteriores
            throw new IllegalArgumentException("Clave no válida: " + clave);
        }
    }

    /**
     * Método auxiliar privado para calcular el costo
     * basado en los precios por tramo.
     */
    private double calcular(int numin, double precioBase, double precioExtra) {
        if (numin <= 3) {
            // Costo si son 3 minutos o menos
            return numin * precioBase;
        } else {
            // Costo si son más de 3 minutos
            double costoBase = 3 * precioBase;
            double costoAdicional = (numin - 3) * precioExtra;
            return costoBase + costoAdicional;
        }
    }
}