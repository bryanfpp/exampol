import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el PS 4.8 (Búsqueda Secuencial Ordenada).
 *
 * === Explicación de las variables ===
 * - VECTOR: Arreglo ordenado.
 * - X: Elemento a buscar.
 * - N: Tamaño del arreglo.
 * - I: Índice de control.
 */
class TestBusquedaSecuencialOrdenada {

    private BusquedaSecuencialOrdenada buscador = new BusquedaSecuencialOrdenada();
    // Arreglo de prueba ordenado
    private int[] vector = {10, 20, 30, 40, 50};

    @Test
    void testEncontradoEnMedio() {
        // X = 30
        assertEquals(2, buscador.buscar(vector, 30), "Debe encontrar el 30 en el índice 2");
    }

    @Test
    void testNoEncontrado_Intermedio() {
        // X = 35.
        // El bucle se detendrá en i=3 (donde está el 40),
        // porque la condición (vector[i] < x) será falsa (40 < 35 es falso).
        // La verificación final fallará.
        assertEquals(-1, buscador.buscar(vector, 35), "No debe encontrar el 35");
    }

    @Test
    void testNoEncontrado_MayorQueTodos() {
        // X = 60
        // El bucle se detendrá en i=5 (fuera del arreglo).
        assertEquals(-1, buscador.buscar(vector, 60), "No debe encontrar el 60");
    }
    
    @Test
    void testNoEncontrado_MenorQueTodos() {
        // X = 5
        // El bucle se detendrá en i=0 (donde está el 10).
        assertEquals(-1, buscador.buscar(vector, 5), "No debe encontrar el 5");
    }

    @Test
    void testEncontradoAlInicio() {
        // X = 10
        assertEquals(0, buscador.buscar(vector, 10), "Debe encontrar el 10 en el índice 0");
    }
    
    @Test
    void testEncontradoAlFinal() {
        // X = 50
        assertEquals(4, buscador.buscar(vector, 50), "Debe encontrar el 50 en el índice 4");
    }
}