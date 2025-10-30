/**
 * Problema 2.6
 * Determina si un número entero A es par, impar o nulo.
 */
public class Numero {

    /**
     * Verifica el tipo de número (Par, Impar o Nulo) usando la
     * lógica de la potencia de -1.
     *
     * @param a Variable de tipo entero (A).
     * @return "Nulo", "Par" o "Impar".
     */
    public String verificarTipo(int a) {
        
        // 1. Condición para Nulo (debe ir primero, como dice la nota)
        if (a == 0) {
            return "Nulo";
        }
        
        // 2. Condición para Par/Impar usando la nota:
        // (-1)^A > 0 si A es par
        // (-1)^A < 0 si A es impar
        if (Math.pow(-1, a) > 0) {
            return "Par";
        } else {
            return "Impar";
        }
    }
}