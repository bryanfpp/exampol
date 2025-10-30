/**
 * Problema 3.3
 * Dado un número N, calcula el resultado de la serie:
 * 1 + 1/2 + 1/3 + ... + 1/N
 */
public class CalculadorSerie {

    /**
     * Calcula la suma de la serie.
     *
     * @param n Variable de tipo entero (N) que representa el número de términos.
     * @return El resultado de la serie (SERIE), como un 'double'.
     */
    public double calcular(int n) {
        
        // Variable para almacenar el resultado, debe ser 'double' para decimales
        double serie = 0.0;
        
        // Variable 'i' para el bucle (contador)
        // El bucle va desde 1 hasta N
        for (int i = 1; i <= n; i++) {
            // Sumamos el término 1.0 / i a la serie
            // Usamos 1.0 para forzar una división con decimales
            serie = serie + (1.0 / i);
        }
        
        // Devolvemos el resultado final
        return serie;
    }
}