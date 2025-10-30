import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Clase de prueba para el PS 4.24 (Calificaciones N Alumnos).
 *
 * === Explicación de las variables ===
 * ALUM[i][j] (i=alumno, j=examen)
 */
class TestAnalizadorCalificaciones {

    private static AnalizadorCalificaciones analizador;
    
    @BeforeAll
    static void setUp() {
        // Matriz de 3 Alumnos (N=3) y 5 Exámenes
        double[][] alum = {
        //  E1   E2   E3   E4   E5
           {10.0, 8.0, 9.0, 7.0, 10.0}, // Alumno 1 (Prom: 8.8)
           { 8.0, 9.0, 8.0, 8.0, 8.0 }, // Alumno 2 (Prom: 8.2)
           { 7.0, 7.0, 9.0, 9.0, 9.0 }  // Alumno 3 (Prom: 8.2)
        };
        // Promedios Exámenes:
        // E1: 8.33
        // E2: 8.0
        // E3: 8.66  <- Mejor Promedio
        // E4: 8.0
        // E5: 9.0
        
        analizador = new AnalizadorCalificaciones(alum);
    }

    @Test
    void testIncisoA_PromediosAlumnos() {
        double[] promedios = analizador.getPromediosAlumnos();
        assertEquals(3, promedios.length);
        assertEquals(8.8, promedios[0], 0.001); // Alumno 1
        assertEquals(8.2, promedios[1], 0.001); // Alumno 2
    }

    @Test
    void testIncisoB_MejoresExamen3() {
        // En E3 (idx 2), las calificaciones son {9.0, 8.0, 9.0}
        // Max es 9.0. Alumnos 1 y 3.
        List<Integer> esperados = List.of(1, 3);
        assertEquals(esperados, analizador.getMejoresExamen3());
    }

    @Test
    void testIncisoC_MejoresExamen1y5() {
        // E1: {10, 8, 7} -> Max = 10 (Alumno 1)
        // E5: {10, 8, 9} -> Max = 10 (Alumno 1)
        // El único que cumple ambas es el Alumno 1.
        List<Integer> esperados = List.of(1);
        assertEquals(esperados, analizador.getMejoresExamen1y5());
    }

    @Test
    void testIncisoD_PeorExamenAlumno() {
        // Alumno 2 (idx 1): {8.0, 9.0, 8.0, 8.0, 8.0}
        // Sus peores notas (8.0) están en E1, E3, E4, E5.
        // El método devuelve el primero que encuentra.
        assertEquals(1, analizador.getPeorExamenAlumno(2));
        
        // Alumno 1 (idx 0): {10.0, 8.0, 9.0, 7.0, 10.0}
        // Su peor nota (7.0) está en E4.
        assertEquals(4, analizador.getPeorExamenAlumno(1));
    }

    @Test
    void testIncisoE_ExamenMejorPromedio() {
        // E1: 8.33, E2: 8.0, E3: 8.66, E4: 8.0, E5: 9.0
        // El mejor promedio es 9.0, en el Examen 5.
        assertEquals(5, analizador.getExamenMejorPromedio());
    }
}