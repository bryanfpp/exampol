/**
 * Esta clase determina si un alumno está aprobado o reprobado
 * basándose en su calificación.
 */
public class Calificacion {

    /**
     * Evalúa una calificación y devuelve un mensaje.
     * La calificación para aprobar es 8.
     *
     * @param cal La calificación del alumno (variable CAL).
     * @return "Aprobado" si cal es >= 8, "Reprobado" en caso contrario.
     */
    public String evaluar(double cal) {
        // Esta es la estructura condicional (if-else)
        if (cal >= 8) {
            return "Aprobado";
        } else {
            return "Reprobado";
        }
    }
}