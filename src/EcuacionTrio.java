import java.util.ArrayList;
import java.util.List;

/**
 * PS 3.35
 * Imprime todos los valores de X, Y y Z (enteros positivos) que satisfacen:
 * 18*X^5 + 11*Y^5 + 8*Z^6 < 6300
 */
public class EcuacionTrio {

    /**
     * Clase auxiliar para almacenar los tríos (X, Y, Z).
     */
    public static class Trio {
        public final int x, y, z;

        public Trio(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Trio trio = (Trio) obj;
            return x == trio.x && y == trio.y && z == trio.z;
        }
        

        @Override
        public String toString() {
            return "(" + x + ", " + y + ", " + z + ")";
        }
    }

    /**
     * Calcula la expresión 18*X^5 + 11*Y^5 + 8*Z^6
     */
    private double calcularExpresion(int x, int y, int z) {
        return 18 * Math.pow(x, 5) + 11 * Math.pow(y, 5) + 8 * Math.pow(z, 6);
    }

    /**
     * Encuentra todos los tríos (X, Y, Z) que satisfacen la ecuación.
     *
     * @return Una lista de objetos 'Trio' (List<Trio>)
     */
    public List<Trio> encontrarTrios() {
        
        List<Trio> triosEncontrados = new ArrayList<>();
        int x = 1; // X debe ser entero positivo

        // Bucle Exterior (para X)
        // Se prueba con Y=1, Z=1 (los valores más pequeños)
        while (calcularExpresion(x, 1, 1) < 6300) {
            
            int y = 1; // Y debe ser entero positivo

            // Bucle Medio (para Y)
            // Se prueba con Z=1
            while (calcularExpresion(x, y, 1) < 6300) {
                
                int z = 1; // Z debe ser entero positivo

                // Bucle Interior (para Z)
                while (calcularExpresion(x, y, z) < 6300) {
                    
                    // Si la condición se cumple, guardamos el trío
                    triosEncontrados.add(new Trio(x, y, z));
                    
                    z++; // Pasamos al siguiente Z
                }
                
                y++; // Pasamos al siguiente Y
            }
            
            x++; // Pasamos al siguiente X
        }

        return triosEncontrados;
    }
}