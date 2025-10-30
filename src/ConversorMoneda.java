/**
 * PS 1.3
 * Dado como dato una cantidad expresada en dólares,
 * convierte esa cantidad a pesos.
 * Tipo de cambio: 1 USD = 11.96 MXN
 */
public class ConversorMoneda {

    // Constante para el tipo de cambio
    private static final double TIPO_CAMBIO = 11.96;

    /**
     * Convierte una cantidad de dólares a pesos.
     *
     * @param can Cantidad en dólares (variable CAN).
     * @return La cantidad equivalente en pesos.
     */
    public double convertir(double can) {
        if (can < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        return can * TIPO_CAMBIO;
    }
}