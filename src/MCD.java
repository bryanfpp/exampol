/**
 * PS 3.33
 * Calcula el Máximo Común Divisor (MCD) de dos números naturales
 * A y B, usando el Algoritmo de Euclides.
 */
public class MCD {

    /**
     * Calcula el MCD de a y b.
     *
     * @param a (A) Primer número entero (debe ser >= 0).
     * @param b (B) Segundo número entero (debe ser >= 0).
     * @return El MCD de a y b.
     */
    public int calcular(int a, int b) {
        
        // El problema especifica "números naturales".
        // Asumiremos que son >= 0 y lanzaremos un error si son negativos.
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Los números deben ser >= 0.");
        }

        // Esta es la implementación del Algoritmo de Euclides
        while (b != 0) {
            int temp = b; // Variable temporal para guardar B
            b = a % b;    // B se convierte en el residuo de A / B
            a = temp;     // A toma el valor anterior de B
        }
        
        // Cuando B llega a 0, A contiene el MCD
        return a;
    }
}