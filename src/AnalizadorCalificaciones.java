import java.util.ArrayList;
import java.util.List;

/**
 * PS 4.24
 * Analiza un arreglo bidimensional ALUM[N][5] de calificaciones.
 */
public class AnalizadorCalificaciones {

    // El arreglo de calificaciones [alumno][examen]
    private double[][] calificaciones;
    private int numAlumnos;
    private int numExamenes = 5;

    /**
     * Constructor que recibe la matriz de calificaciones.
     * @param alum Arreglo ALUM[N][5]
     */
    public AnalizadorCalificaciones(double[][] alum) {
        if (alum == null || alum.length == 0 || alum[0].length != 5) {
            throw new IllegalArgumentException("El arreglo debe ser [N][5] y no ser nulo.");
        }
        this.calificaciones = alum;
        this.numAlumnos = alum.length;
    }
    
    // --- MÉTODOS PARA CADA INCISO ---

    /**
     * a) El promedio de calificaciones de cada uno de los N alumnos.
     */
    public double[] getPromediosAlumnos() {
        double[] promedios = new double[numAlumnos];
        
        // Bucle 1: Alumnos (Filas)
        for (int i = 0; i < numAlumnos; i++) {
            double suma = 0;
            // Bucle 2: Exámenes (Columnas)
            for (int j = 0; j < numExamenes; j++) {
                suma += calificaciones[i][j];
            }
            promedios[i] = suma / numExamenes;
        }
        return promedios;
    }

    /**
     * b) El/los alumnos que obtuvieron la mejor calificación en el tercer examen.
     * (Examen 3 es el índice 2)
     */
    public List<Integer> getMejoresExamen3() {
        List<Integer> mejoresAlumnos = new ArrayList<>();
        double calificacionMaxima = -1.0;
        int examenIdx = 2; // Examen 3

        // Bucle 1: Encontrar la calificación máxima
        for (int i = 0; i < numAlumnos; i++) {
            if (calificaciones[i][examenIdx] > calificacionMaxima) {
                calificacionMaxima = calificaciones[i][examenIdx];
            }
        }

        // Bucle 2: Encontrar a todos los que tienen esa máxima
        for (int i = 0; i < numAlumnos; i++) {
            if (calificaciones[i][examenIdx] == calificacionMaxima) {
                mejoresAlumnos.add(i + 1); // Devolvemos el número de alumno (1 a N)
            }
        }
        return mejoresAlumnos;
    }

    /**
     * c) El/los alumnos que obtuvieron la mayor calificación
     * en el primero (idx 0) y en el quinto examen (idx 4).
     */
    public List<Integer> getMejoresExamen1y5() {
        List<Integer> mejoresAlumnos = new ArrayList<>();
        double maxEx1 = -1.0;
        double maxEx5 = -1.0;
        
        // Bucle 1: Encontrar las calificaciones máximas de E1 y E5
        for (int i = 0; i < numAlumnos; i++) {
            if (calificaciones[i][0] > maxEx1) maxEx1 = calificaciones[i][0];
            if (calificaciones[i][4] > maxEx5) maxEx5 = calificaciones[i][4];
        }

        // Bucle 2: Encontrar alumnos que cumplan AMBAS condiciones
        for (int i = 0; i < numAlumnos; i++) {
            if (calificaciones[i][0] == maxEx1 && calificaciones[i][4] == maxEx5) {
                mejoresAlumnos.add(i + 1); // Devolvemos el número de alumno (1 a N)
            }
        }
        return mejoresAlumnos;
    }

    /**
     * d) Dado un número de alumno (1 a N), informar en qué examen (1 a 5)
     * logró la menor calificación.
     */
    public int getPeorExamenAlumno(int numAlumno) {
        int alumnoIdx = numAlumno - 1; // Convertir a índice (0 a N-1)
        double calificacionMinima = 11.0; // Un valor > 10
        int examenGanador = -1;

        // Bucle: Exámenes (Columnas)
        for (int j = 0; j < numExamenes; j++) {
            if (calificaciones[alumnoIdx][j] < calificacionMinima) {
                calificacionMinima = calificaciones[alumnoIdx][j];
                examenGanador = j;
            }
        }
        return examenGanador + 1; // Devolvemos el número de examen (1 a 5)
    }

    /**
     * e) ¿En qué examen (1 a 5) fue más alto el promedio de los N alumnos?
     */
    public int getExamenMejorPromedio() {
        double mejorPromedio = -1.0;
        int examenGanador = -1;

        // Bucle 1: Exámenes (Columnas)
        for (int j = 0; j < numExamenes; j++) {
            double sumaExamen = 0;
            // Bucle 2: Alumnos (Filas)
            for (int i = 0; i < numAlumnos; i++) {
                sumaExamen += calificaciones[i][j];
            }
            double promedioExamen = sumaExamen / numAlumnos;

            if (promedioExamen > mejorPromedio) {
                mejorPromedio = promedioExamen;
                examenGanador = j;
            }
        }
        return examenGanador + 1; // Devolvemos el número de examen (1 a 5)
    }
}