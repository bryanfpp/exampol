import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el PS 2.18 (Costo de Llamada).
 *
 * === Explicación de las variables ===
 * - CLAVE: (Entero) Clave de la zona.
 * - NUMIN: (Entero) Número de minutos.
 */
class TestCostoLlamada {

    private CostoLlamada calculadora = new CostoLlamada();

    // --- Pruebas para Tramos <= 3 minutos ---
    
    @Test
    void testClave12_TramoBase() {
        // 3 minutos * 2.0 = 6.0
        assertEquals(6.0, calculadora.calcularCosto(12, 3));
    }

    @Test
    void testClave15_TramoBase() {
        // 2 minutos * 2.2 = 4.4
        assertEquals(4.4, calculadora.calcularCosto(15, 2));
    }
    
    @Test
    void testClave25_TramoBase() {
        // 3 minutos * 6.0 = 18.0
        assertEquals(18.0, calculadora.calcularCosto(25, 3));
    }

    // --- Pruebas para Tramos > 3 minutos ---
    
    @Test
    void testClave18_TramoExtra() {
        // 4 minutos = (3 * 4.5) + (1 * 3.5) = 13.5 + 3.5 = 17.0
        assertEquals(17.0, calculadora.calcularCosto(18, 4));
    }
    
    @Test
    void testClave19_TramoExtra() {
        // 5 minutos = (3 * 3.5) + (2 * 2.7) = 10.5 + 5.4 = 15.9
        assertEquals(15.9, calculadora.calcularCosto(19, 5));
    }

    @Test
    void testClave29_TramoExtra() {
        // 10 minutos = (3 * 5.0) + (7 * 3.9) = 15.0 + 27.3 = 42.3
        assertEquals(42.3, calculadora.calcularCosto(29, 10));
    }

    // --- Pruebas de Errores ---

    @Test
    void testClaveInvalida() {
        // Prueba que el sistema falle si la clave no existe
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcularCosto(99, 5);
        }, "Debería lanzar un error por clave inválida");
    }

    @Test
    void testMinutosNegativos() {
        // Prueba que el sistema falle si los minutos son negativos
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcularCosto(12, -1);
        }, "Debería lanzar un error por minutos negativos");
    }
}