import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el Ejemplo 4.11 (Inserción Directa).
 *
 * === Explicación de las variables ===
 * Variable de tipo arreglo de enteros:
 * - UNI: El arreglo a ordenar.
 * Variables de tipo entero (control):
 * - i: Índice del bucle exterior (elemento a insertar).
 * - j: Índice del bucle interior (comparación y desplazamiento).
 * - aux: Almacena temporalmente el valor a insertar (uni[i]).
 */
class TestInsercionDirecta {

    // Creamos una instancia de la clase que queremos probar
    private InsercionDirecta ordenador = new InsercionDirecta();

    @Test
    void testCasoDelEjemplo() {
        // Dato: UNI
        int[] uni = {30, 92, 25, 31, 59, 42, 27};
        
        // Resultado esperado
        int[] esperado = {25, 27, 30, 31, 42, 59, 92};
        
        // Ejecución (el método modifica el arreglo 'uni')
        ordenador.ordenar(uni);
        
        // Verificación: assertArrayEquals compara que dos arreglos sean idénticos
        assertArrayEquals(esperado, uni, "El arreglo del ejemplo no se ordenó correctamente");
    }

    @Test
    void testArregloYaOrdenado() {
        int[] uni = {10, 20, 30, 40};
        int[] esperado = {10, 20, 30, 40};
        
        ordenador.ordenar(uni);
        assertArrayEquals(esperado, uni, "Un arreglo ya ordenado debe permanecer igual");
    }

    @Test
    void testArregloOrdenInverso() {
        int[] uni = {5, 4, 3, 2, 1};
        int[] esperado = {1, 2, 3, 4, 5};
        
        ordenador.ordenar(uni);
        assertArrayEquals(esperado, uni, "El arreglo en orden inverso no se ordenó");
    }

    @Test
    void testArregloVacio() {
        int[] uni = {};
        int[] esperado = {};
        
        ordenador.ordenar(uni);
        assertArrayEquals(esperado, uni, "Un arreglo vacío debe permanecer vacío");
    }
}