import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el Problema 4.19 (Temperaturas 3D).
 *
 * === Explicación de las variables ===
 * TEMP[e][m][a]
 * - e: Estado (Índice 0-31)
 * - m: Mes (Índice 0-11)
 * - a: Año (Índice 0-49, donde 0 es 1950)
 */
class TestAnalizadorTemperaturas {

    private AnalizadorTemperaturas analizador;
    private double[][][] datosDePrueba;

    // Este método se ejecuta ANTES de CADA @Test
    // Rellena el arreglo de datos con "minas" para probar los incisos.
    @BeforeEach
    void setUp() {
        // Inicializamos el arreglo [32 estados][12 meses][50 años]
        datosDePrueba = new double[32][12][50];
        
        // --- Datos para Inciso (a) ---
        // (Estado con mayor prom. últimos 10 años, 1990-1999, índices 40-49)
        // Hacemos que el Estado 5 (índice 4) tenga un promedio altísimo.
        // Ponemos 100° en un mes del año 1995 (índice 45).
        datosDePrueba[4][0][45] = 100.0;
        // Ponemos 50° en otro estado (índice 10) para asegurarnos.
        datosDePrueba[10][0][45] = 50.0;

        // --- Datos para Inciso (b) ---
        // (Estado con menor prom. anual último año, 1999, índice 49)
        // Hacemos que el Estado 20 (índice 19) tenga un promedio bajísimo.
        // Llenamos todos sus meses con -50°
        for (int m = 0; m < 12; m++) {
            datosDePrueba[19][m][49] = -50.0;
        }
        // Ponemos un valor bajo (-10) en otro estado para comparar
        datosDePrueba[5][0][49] = -10.0;

        // --- Datos para Inciso (c) ---
        // (Mes más caluroso, Estado 29 (índice 28), Año 1953 (índice 3))
        // Hacemos que el Mes 6 (índice 5) sea el más caliente.
        datosDePrueba[28][0][3] = 30.0; // Enero
        datosDePrueba[28][5][3] = 45.0; // Junio (El ganador)
        datosDePrueba[28][11][3] = 25.0; // Diciembre

        // --- Datos para Inciso (d) ---
        // (Mes y Estado más frío en 1975 (índice 25))
        // Hacemos que Estado 16 (índice 15), Mes 9 (índice 8) sea el ganador.
        datosDePrueba[0][0][25] = 10.0;
        datosDePrueba[15][8][25] = -100.0; // El ganador
        datosDePrueba[30][11][25] = -50.0;

        // Inicializamos el analizador con estos datos
        analizador = new AnalizadorTemperaturas(datosDePrueba);
    }

    @Test
    void testIncisoA() {
        // (a) Estado con mayor prom. últimos 10 años (1990-1999)
        // Esperamos el Estado 5 (basado en setUp)
        assertEquals(5, analizador.incisoA());
    }

    @Test
    void testIncisoB() {
        // (b) Estado con menor prom. anual último año (1999)
        // Esperamos el Estado 20 (basado en setUp)
        assertEquals(20, analizador.incisoB());
    }
    
    @Test
    void testIncisoC() {
        // (c) Mes más caluroso, E=29, A=1953
        // Esperamos el Mes 6 (Junio) (basado en setUp)
        assertEquals(6, analizador.incisoC());
    }
    
    @Test
    void testIncisoD() {
        // (d) Mes y Estado más frío en 1975
        // Esperamos E=16, M=9 (basado en setUp)
        AnalizadorTemperaturas.ResultadoMesEstado resultado = analizador.incisoD();
        
        // Comprobamos con un objeto 'esperado'
        AnalizadorTemperaturas.ResultadoMesEstado esperado = 
            new AnalizadorTemperaturas.ResultadoMesEstado(16, 9);
            
        assertEquals(esperado, resultado, "El resultado E=16, M=9 no coincide");
    }
}