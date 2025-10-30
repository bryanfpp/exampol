import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el Problema 2.6 (Par, Impar o Nulo).
 *
 * === Explicación de las variables ===
 * Variable de tipo entero:
 * - A: Número a evaluar.
 */
class TestNumero {

    // Creamos una instancia de la clase que queremos probar
    private Numero numero = new Numero();

    @Test
    void testNulo() {
        // Dato: A = 0
        String resultado = numero.verificarTipo(0);
        // Resultado esperado: "Nulo"
        assertEquals("Nulo", resultado, "A = 0 debe ser 'Nulo'");
    }

    @Test
    void testParPositivo() {
        // Dato: A = 4
        String resultado = numero.verificarTipo(4);
        // Resultado esperado: "Par" (Math.pow(-1, 4) = 1)
        assertEquals("Par", resultado, "A = 4 debe ser 'Par'");
    }

    @Test
    void testImparPositivo() {
        // Dato: A = 7
        String resultado = numero.verificarTipo(7);
        // Resultado esperado: "Impar" (Math.pow(-1, 7) = -1)
        assertEquals("Impar", resultado, "A = 7 debe ser 'Impar'");
    }
    
    @Test
    void testParNegativo() {
        // Dato: A = -2
        String resultado = numero.verificarTipo(-2);
        // Resultado esperado: "Par" (Math.pow(-1, -2) = 1)
        assertEquals("Par", resultado, "A = -2 debe ser 'Par'");
    }
    
    @Test
    void testImparNegativo() {
        // Dato: A = -3
        String resultado = numero.verificarTipo(-3);
        // Resultado esperado: "Impar" (Math.pow(-1, -3) = -1)
        assertEquals("Impar", resultado, "A = -3 debe ser 'Impar'");
    }
}