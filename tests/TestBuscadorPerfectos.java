import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List; // Necesario para usar List

/**
 * Clase de prueba para el Problema 3.19 (Números Perfectos).
 *
 * === Explicación de las variables ===
 * Variable de tipo entero:
 * - N: Límite superior de la búsqueda.
 * - I: Variable de control del bucle exterior.
 * - J: Variable de control del bucle interior.
 * - SUMA: Acumula la suma de los divisores.
 */
class TestBuscadorPerfectos {

    private BuscadorPerfectos buscador = new BuscadorPerfectos();

    @Test
    void testHastaDiez() {
        // El único número perfecto menor que 10 es el 6
        List<Integer> resultado = buscador.encontrarPerfectos(10);
        
        // Creamos la lista de resultados esperados
        List<Integer> esperado = List.of(6);
        
        assertEquals(esperado, resultado, "N=10 debería encontrar solo [6]");
    }

    @Test
    void testHastaCien() {
        // Los números perfectos menores que 100 son 6 y 28
        List<Integer> resultado = buscador.encontrarPerfectos(100);
        List<Integer> esperado = List.of(6, 28);
        
        assertEquals(esperado, resultado, "N=100 debería encontrar [6, 28]");
    }
    
    @Test
    void testHastaMil() {
        // Los números perfectos menores que 1000 son 6, 28 y 496
        List<Integer> resultado = buscador.encontrarPerfectos(1000);
        List<Integer> esperado = List.of(6, 28, 496);
        
        assertEquals(esperado, resultado, "N=1000 debería encontrar [6, 28, 496]");
    }
    
    @Test
    void testLimiteInferior() {
        // No hay números perfectos menores que 6
        List<Integer> resultado = buscador.encontrarPerfectos(5);
        List<Integer> esperado = List.of(); // Lista vacía
        
        assertEquals(esperado, resultado, "N=5 debería encontrar una lista vacía []");
    }
}