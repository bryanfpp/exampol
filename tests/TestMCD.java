import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el PS 3.33 (MCD).
 *
 * === Explicación de las variables ===
 * - A: (Entero) Primer número.
 * - B: (Entero) Segundo número.
 */
class TestMCD {

    private MCD mcd = new MCD();

    @Test
    void testNumerosGrandes() {
        // Datos: A = 54, B = 24
        // 54 % 24 = 6
        // 24 % 6 = 0
        // Resultado: 6
        assertEquals(6, mcd.calcular(54, 24));
    }

    @Test
    void testNumerosPrimosEntreSi() {
        // Datos: A = 17, B = 5
        // El MCD debe ser 1
        assertEquals(1, mcd.calcular(17, 5));
    }

    @Test
    void testUnNumeroEsMultiploDelOtro() {
        // Datos: A = 48, B = 12
        // El MCD debe ser 12
        assertEquals(12, mcd.calcular(48, 12));
    }

    @Test
    void testArgumentosInvertidos() {
        // Datos: A = 12, B = 48
        // El resultado debe ser el mismo (12)
        assertEquals(12, mcd.calcular(12, 48));
    }

    @Test
    void testConCero() {
        // Datos: A = 9, B = 0
        // El MCD de (N, 0) es N
        assertEquals(9, mcd.calcular(9, 0));
    }

    @Test
    void testAmbosCero() {
        // Datos: A = 0, B = 0
        // El MCD de (0, 0) es 0
        assertEquals(0, mcd.calcular(0, 0));
    }

    @Test
    void testNumerosNegativos() {
        // Debe lanzar un error según nuestra validación
        assertThrows(IllegalArgumentException.class, () -> {
            mcd.calcular(-10, 5);
        });
    }
}