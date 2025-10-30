
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el problema de Calificación.
 *
 * === Explicación de las variables ===
 * Variable de tipo real:
 * - CAL: Expresa la calificación del alumno.
 */
class TestCalificacion {

    // Creamos una instancia de la clase que queremos probar
    private Calificacion evaluador = new Calificacion();

    @Test
    void testAprobadoConOchoExacto() {
        // Dato: CAL = 8.0
        String resultado = evaluador.evaluar(8.0);
        // Resultado esperado: "Aprobado"
        assertEquals("Aprobado", resultado, "Calificación de 8 debe ser 'Aprobado'");
    }

    @Test
    void testAprobadoConCalificacionSuperior() {
        // Dato: CAL = 9.5
        String resultado = evaluador.evaluar(9.5);
        // Resultado esperado: "Aprobado"
        assertEquals("Aprobado", resultado, "Calificación de 9.5 debe ser 'Aprobado'");
    }

    @Test
    void testReprobadoConCalificacionInferior() {
        // Dato: CAL = 7.9
        String resultado = evaluador.evaluar(7.9);
        // Resultado esperado: "Reprobado"
        assertEquals("Reprobado", resultado, "Calificación de 7.9 debe ser 'Reprobado'");
    }

    @Test
    void testReprobadoConCero() {
        // Dato: CAL = 0
        String resultado = evaluador.evaluar(0.0);
        // Resultado esperado: "Reprobado"
        assertEquals("Reprobado", resultado, "Calificación de 0 debe ser 'Reprobado'");
    }
}