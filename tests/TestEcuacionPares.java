import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Clase de prueba para el PS 3.34 (m^4 + 7n^2 < 540).
 *
 * === Explicación de las variables ===
 * - m: (Entero positivo) Variable del bucle exterior.
 * - n: (Entero positivo) Variable del bucle interior.
 */
class TestEcuacionPares {

    // La lista de resultados se calcula una sola vez
    private static List<EcuacionPares.Par> resultados;
    
    @BeforeAll // Se ejecuta una vez antes de todas las pruebas
    static void calcularResultados() {
        EcuacionPares ecuacion = new EcuacionPares();
        resultados = ecuacion.encontrarPares();
    }

    @Test
    void testCantidadTotalDePares() {
        // m=1 -> n=1..8 (8 pares)
        // m=2 -> n=1..8 (8 pares)
        // m=3 -> n=1..8 (8 pares)
        // m=4 -> n=1..6 (6 pares)
        // Total = 8 + 8 + 8 + 6 = 30 pares
        assertEquals(30, resultados.size(), "Deberían encontrarse 30 pares en total");
    }

    @Test
    void testParesDelLimiteInterior() {
        // El primer par debe ser (1, 1)
        EcuacionPares.Par primerPar = new EcuacionPares.Par(1, 1);
        assertTrue(resultados.contains(primerPar), "Debe contener el par (1, 1)");
    }
    
    @Test
    void testParesDelLimiteExterior() {
        // (m=4): 256 + 7*n^2 < 540 -> 7n^2 < 284 -> n^2 < 40.5
        // n máximo es 6 (6*6=36).
        
        // El par (4, 6) debe estar
        EcuacionPares.Par parValido = new EcuacionPares.Par(4, 6);
        assertTrue(resultados.contains(parValido), "Debe contener el par (4, 6)");
        
        // El par (4, 7) NO debe estar
        EcuacionPares.Par parInvalido = new EcuacionPares.Par(4, 7);
        assertFalse(resultados.contains(parInvalido), "NO debe contener el par (4, 7)");
    }
    
    @Test
    void testParLimiteDe_M() {
        // (m=5): 5^4 = 625. 625 + 7*(1)^2 > 540.
        // El bucle de 'm' no debería ejecutarse para m=5.
        
        // El par (5, 1) NO debe estar
        EcuacionPares.Par parInvalido = new EcuacionPares.Par(5, 1);
        assertFalse(resultados.contains(parInvalido), "NO debe contener el par (5, 1)");
    }
}