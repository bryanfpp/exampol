import java.util.ArrayList;
import java.util.List;

/**
 * Problema 3.19
 * Un número es perfecto si “la suma de sus divisores excepto el mismo
 * es igual al propio número”.
 * Esta clase calcula los números perfectos menores o iguales que N.
 */
public class BuscadorPerfectos {

    /**
     * Encuentra todos los números perfectos en el rango [1, n].
     *
     * @param n Variable de tipo entero (N), el límite superior.
     * @return Una lista de enteros (List<Integer>) que contiene
     * los números perfectos encontrados.
     */
    public List<Integer> encontrarPerfectos(int n) {
        
        // 1. Creamos una lista para guardar los resultados
        List<Integer> perfectosEncontrados = new ArrayList<>();
        
        // 2. Bucle Exterior: Revisa cada número 'i' desde 1 hasta N
        for (int i = 1; i <= n; i++) {
            
            // 3. Bucle Interior: Calcula la suma de los divisores de 'i'
            int sumaDivisores = 0;
            
            // El bucle va de 1 hasta i-1 (divisores "excepto el mismo")
            for (int j = 1; j < i; j++) {
                
                // Si 'j' es un divisor de 'i'
                if (i % j == 0) {
                    sumaDivisores = sumaDivisores + j;
                }
            }
            
            // 4. Verificación: Comprueba si el número es perfecto
            if (sumaDivisores == i) {
                perfectosEncontrados.add(i);
            }
        }
        
        // 5. Devolvemos la lista de resultados
        return perfectosEncontrados;
    }
}