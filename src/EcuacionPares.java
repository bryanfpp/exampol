import java.util.ArrayList;
import java.util.List;

/**
 * PS 3.34
 * Imprime todos los pares de m y n (enteros positivos) que cumplan
 * con la condición: m^4 + 7*n^2 < 540
 */
public class EcuacionPares {

    /**
     * Clase auxiliar para almacenar los pares (m, n).
     * La usamos para que las pruebas sean más robustas.
     */
    public static class Par {
        public final int m;
        public final int n;

        public Par(int m, int n) {
            this.m = m;
            this.n = n;
        }
        
        // Sobrescribimos equals para que las pruebas (AssertEquals) funcionen
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Par par = (Par) obj;
            return m == par.m && n == par.n;
        }
        
        // Sobrescribimos toString para una fácil depuración (opcional)
        @Override
        public String toString() {
            return "(" + m + ", " + n + ")";
        }
    }

    /**
     * Calcula la expresión m^4 + 7*n^2
     */
    private double calcularExpresion(int m, int n) {
        // Usamos Math.pow para las potencias
        return Math.pow(m, 4) + 7 * Math.pow(n, 2);
    }

    /**
     * Encuentra todos los pares (m, n) que satisfacen la ecuación.
     *
     * @return Una lista de objetos 'Par' (List<Par>)
     */
    public List<Par> encontrarPares() {
        
        List<Par> paresEncontrados = new ArrayList<>();
        int m = 1; // m debe ser entero positivo

        // Bucle Exterior (para m)
        // La condición se prueba con n=1 (el valor más pequeño de n)
        // Si m^4 + 7*(1)^2 ya es >= 540, no tiene caso probar más 'm'.
        while (calcularExpresion(m, 1) < 540) {
            
            int n = 1; // n debe ser entero positivo

            // Bucle Interior (para n)
            // Se prueba cada 'n' para la 'm' actual
            while (calcularExpresion(m, n) < 540) {
                
                // Si la condición se cumple, guardamos el par
                paresEncontrados.add(new Par(m, n));
                
                // Pasamos al siguiente n
                n++;
            }
            
            // Pasamos al siguiente m
            m++;
        }

        return paresEncontrados;
    }
}