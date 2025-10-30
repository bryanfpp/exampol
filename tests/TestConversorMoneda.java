import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el PS 1.3 (Conversor de Moneda).
 *
 * === Explicación de las variables ===
 * Variable de tipo real:
 * - CAN: Cantidad en dólares.
 */
class TestConversorMoneda {

    private ConversorMoneda conversor = new ConversorMoneda();

    @Test
    void testConversionNormal() {
        // Dato: CAN = 100.0
        double pesos = conversor.convertir(100.0);
        // Resultado esperado: 100 * 11.96 = 1196.0
        assertEquals(1196.0, pesos, "100 dólares deben ser 1196 pesos");
    }

    @Test
    void testConversionUno() {
        // Dato: CAN = 1.0
        double pesos = conversor.convertir(1.0);
        // Resultado esperado: 11.96
        assertEquals(11.96, pesos, "1 dólar debe ser 11.96 pesos");
    }

    @Test
    void testConversionCero() {
        // Dato: CAN = 0.0
        double pesos = conversor.convertir(0.0);
        // Resultado esperado: 0.0
        assertEquals(0.0, pesos, "0 dólares deben ser 0 pesos");
    }

    @Test
    void testManejoDeNegativos() {
        // Prueba de error
        // Verifica que el programa lance una excepción si la entrada es negativa.
        assertThrows(IllegalArgumentException.class, () -> {
            conversor.convertir(-50.0);
        }, "No se deben permitir cantidades negativas");
    }
}