import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

/**
 * Clase de prueba para el PS 3.36 (Granja).
 */
class TestAnalizadorGranja {

    private static AnalizadorGranja analizador;

    // Datos de prueba (10 arreglos de 12 meses)
    // Se inicializan UNA VEZ para todas las pruebas
    @BeforeAll
    static void setUp() {
        // Datos simples: la producción y el precio son constantes todo el año
        // {kg_mes, precio_mes} -> Total KG, Total Dinero
        // Tomate: {10, $2} -> 120 kg, $240
        // Lechuga: {5, $1} -> 60 kg, $60
        // Acelga: {20, $3} -> 240 kg, $720
        // Zanahoria: {15, $1} -> 180 kg, $180
        // Chícharo: {2, $10} -> 24 kg, $240
        
        analizador = new AnalizadorGranja(
            new double[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, // tomKg
            new double[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, // tomPrecio
            new double[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // lecKg
            new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // lecPrecio
            new double[]{20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20}, // aceKg
            new double[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, // acePrecio
            new double[]{15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15}, // zanKg
            new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // zanPrecio
            new double[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, // chiKg
            new double[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10} // chiPrecio
        );
    }

    @Test
    void testIncisoA_ProductoMasKilos() {
        // Acelga (240 kg) debe ganar
        assertEquals("Acelga", analizador.getProductoMasKilos());
    }

    @Test
    void testIncisoB_TotalesProduccion() {
        Map<String, Map<String, Double>> totales = analizador.getTotalesProduccion();
        
        // Verificamos Tomate
        assertEquals(120.0, totales.get("Tomate").get("kg"));
        assertEquals(240.0, totales.get("Tomate").get("dinero"));
        
        // Verificamos Acelga
        assertEquals(240.0, totales.get("Acelga").get("kg"));
        assertEquals(720.0, totales.get("Acelga").get("dinero"));
    }

    @Test
    void testIncisoC_ProductoMasDinero() {
        // Acelga ($720) debe ganar
        assertEquals("Acelga", analizador.getProductoMasDinero());
    }

    @Test
    void testIncisoD_ImportesMensuales() {
        double[] mensuales = analizador.getImportesMensuales();
        
        // Verificamos que sean 12 meses
        assertEquals(12, mensuales.length);
        
        // Calculamos el importe de un mes
        // (10*2) + (5*1) + (20*3) + (15*1) + (2*10) = 20 + 5 + 60 + 15 + 20 = 120
        
        // Todos los meses deben ser 120
        assertEquals(120.0, mensuales[0]); // Mes 1
        assertEquals(120.0, mensuales[5]); // Mes 6
        assertEquals(120.0, mensuales[11]); // Mes 12
    }
}