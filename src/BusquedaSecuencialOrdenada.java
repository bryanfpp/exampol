/**
 * PS 4.8
 * Realiza una búsqueda secuencial en un arreglo ordenado (ascendente).
 * La búsqueda se detiene si se encuentra el elemento, si se llega
 * al final, o si el elemento actual es mayor que el buscado.
 */
public class BusquedaSecuencialOrdenada {

    /**
     * Busca X en el VECTOR.
     *
     * @param vector (VECTOR) Arreglo unidimensional ordenado.
     * @param x (X) Elemento a buscar.
     * @return El índice (posición) donde se encontró X, o -1 si no se encontró.
     */
    public int buscar(int[] vector, int x) {
        
        int i = 0;
        int n = vector.length;
        
        // Bucle de búsqueda:
        // Continúa mientras:
        // 1. No nos salgamos del arreglo (i < n)
        // 2. El elemento actual sea MENOR que el que buscamos (vector[i] < x)
        // (Nota: Tu libro dice X > V[I], que es lo mismo que V[I] < X)
        while (i < n && vector[i] < x) {
            i++;
        }
        
        // Verificación final:
        // ¿Terminó el bucle porque encontramos X?
        if (i < n && vector[i] == x) {
            return i; // Encontrado en la posición 'i'
        } else {
            return -1; // No encontrado
        }
    }
}