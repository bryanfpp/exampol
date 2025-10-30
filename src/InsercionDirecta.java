/**
 * Ejemplo 4.11
 * Ordena un arreglo de enteros utilizando el método de inserción directa.
 * Este método muta (modifica) el arreglo original.
 */
public class InsercionDirecta {

    /**
     * Ordena el arreglo 'uni' (modificándolo) usando inserción directa.
     *
     * @param uni El arreglo de enteros a ordenar (ej. {30, 92, 25, ...}).
     */
    public void ordenar(int[] uni) {
        
        // Empezamos desde el segundo elemento (índice 1),
        // asumiendo que el primero (índice 0) ya es una sub-lista ordenada.
        for (int i = 1; i < uni.length; i++) {
            
            // 1. Guardamos el valor actual que queremos insertar
            int auxiliar = uni[i];
            
            // 2. Iniciamos 'j' en la posición anterior a 'i'
            int j = i - 1;
            
            // 3. Bucle de desplazamiento:
            // Mientras 'j' no se salga del arreglo (j >= 0) Y
            // el elemento en 'j' sea MAYOR que el valor auxiliar...
            while (j >= 0 && uni[j] > auxiliar) {
                
                // ...desplazamos el elemento 'j' una posición a la derecha
                uni[j + 1] = uni[j];
                
                // ...y retrocedemos 'j' para seguir comparando hacia la izquierda
                j--;
            }
            
            // 4. Insertamos el valor auxiliar en su posición correcta
            // (que es 'j + 1', el "hueco" que dejaron los desplazamientos)
            uni[j + 1] = auxiliar;
        }
    }
}