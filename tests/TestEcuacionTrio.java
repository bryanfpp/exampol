import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Clase de prueba para el PS 3.35 (18X^5 + 11Y^5 + 8Z^6 < 6300).
 *
 * === Explicación de las variables ===
 * - X, Y, Z: (Enteros positivos) Variables de los bucles.
 */
class TestEcuacionTrio {

    // La lista de resultados se calcula una sola vez
    private static List<EcuacionTrio.Trio> resultados;
    
    @BeforeAll // Se ejecuta una vez antes de todas las pruebas
    static void calcularResultados() {
        EcuacionTrio ecuacion = new EcuacionTrio();
        resultados = ecuacion.encontrarTrios();
    }

    @Test
    void testCantidadTotalDeTrios() {
        // X=1 -> 15 tríos
        // X=2 -> 6 tríos
        // X=3 -> 1 trío (3,1,1)
        // Total = 15 + 6 + 1 = 22 tríos
        assertEquals(22, resultados.size(), "Deberían encontrarse 22 tríos en total");
    }

    @Test
    void testTrioMinimo() {
        // El primer trío debe ser (1, 1, 1)
        // 18 + 11 + 8 = 37 < 6300
        EcuacionTrio.Trio primerTrio = new EcuacionTrio.Trio(1, 1, 1);
        assertTrue(resultados.contains(primerTrio), "Debe contener el trío (1, 1, 1)");
    }
    
    @Test
    void testLimiteDe_Z() {
        // Para X=1, Y=1: 18 + 11 + 8Z^6 < 6300 -> 8Z^6 < 6271 -> Z^6 < 783.8
        // Z máximo es 3 (3^6 = 729).
        
        // El trío (1, 1, 3) debe estar
        EcuacionTrio.Trio trioValido = new EcuacionTrio.Trio(1, 1, 3);
        assertTrue(resultados.contains(trioValido), "Debe contener el trío (1, 1, 3)");
        
        // El trío (1, 1, 4) NO debe estar
        EcuacionTrio.Trio trioInvalido = new EcuacionTrio.Trio(1, 1, 4);
        assertFalse(resultados.contains(trioInvalido), "NO debe contener el trío (1, 1, 4)");
    }

    @Test
    void testLimiteDe_Y() {
        // Para X=1, Z=1: 18 + 11Y^5 + 8 < 6300 -> 11Y^5 < 6274 -> Y^5 < 570.3
        // Y máximo es 3 (3^5 = 243).
        
        // El trío (1, 3, 1) debe estar
        EcuacionTrio.Trio trioValido = new EcuacionTrio.Trio(1, 3, 1);
        assertTrue(resultados.contains(trioValido), "Debe contener el trío (1, 3, 1)");
        
        // El trío (1, 4, 1) NO debe estar
        EcuacionTrio.Trio trioInvalido = new EcuacionTrio.Trio(1, 4, 1);
        assertFalse(resultados.contains(trioInvalido), "NO debe contener el trío (1, 4, 1)");
    }
    
    @Test
    void testLimiteDe_X() {
        // Para Y=1, Z=1: 18X^5 + 11 + 8 < 6300 -> 18X^5 < 6281 -> X^5 < 348.9
        // X máximo es 3 (3^5 = 243).
        
        // El trío (3, 1, 1) debe estar
        EcuacionTrio.Trio trioValido = new EcuacionTrio.Trio(3, 1, 1);
        assertTrue(resultados.contains(trioValido), "Debe contener el trío (3, 1, 1)");
        
        // El trío (4, 1, 1) NO debe estar
        EcuacionTrio.Trio trioInvalido = new EcuacionTrio.Trio(4, 1, 1);
        assertFalse(resultados.contains(trioInvalido), "NO debe contener el trío (4, 1, 1)");
    }
}