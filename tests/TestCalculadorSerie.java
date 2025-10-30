import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el Problema 3.3 (Cálculo de Serie).
 *
 * === Explicación de las variables ===
 * Variable de tipo entero:
 * - N: Número de términos de la serie.
 * Variables de tipo real:
 * - SERIE: Almacena el resultado de la suma.
 * - I: Variable de control del bucle.
 */
class TestCalculadorSerie {

    private CalculadorSerie calculadora = new CalculadorSerie();

    @Test
    void testConUnTermino() {
        // Dato: N = 1
        // Serie = 1
        double resultado = calculadora.calcular(1);
        assertEquals(1.0, resultado, "N = 1 debería ser 1.0");
    }

    @Test
    void testConDosTerminos() {
        // Dato: N = 2
        // Serie = 1 + 1/2 = 1.5
        double resultado = calculadora.calcular(2);
        assertEquals(1.5, resultado, "N = 2 debería ser 1.5");
    }
    
    @Test
    void testConTresTerminos() {
        // Dato: N = 3
        // Serie = 1 + 1/2 + 1/3 = 1.5 + 0.333... = 1.833...
        double resultado = calculadora.calcular(3);
        // Usamos 'assertEquals' con una 'delta' (margen de error) 
        // para comparar números 'double' que no son exactos.
        assertEquals(1.83333, resultado, 0.00001, "N = 3 debería ser aprox 1.83333");
    }
    
    @Test
    void testConCeroTerminos() {
        // Dato: N = 0
        // El bucle 'for' no se ejecutará, por lo que la serie debe ser 0.
        double resultado = calculadora.calcular(0);
        assertEquals(0.0, resultado, "N = 0 debería ser 0.0");
    }
}