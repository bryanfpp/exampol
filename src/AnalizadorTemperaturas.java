import java.util.List;
import java.util.ArrayList;

/**
 * Problema 4.19
 * Analiza un arreglo tridimensional de temperaturas [32][12][50]
 * para responder a varias consultas.
 */
public class AnalizadorTemperaturas {

    private double[][][] temp;

    /**
     * Clase auxiliar para devolver el resultado del inciso (d).
     */
    public static class ResultadoMesEstado {
        public final int estado;
        public final int mes;

        public ResultadoMesEstado(int estado, int mes) {
            this.estado = estado;
            this.mes = mes;
        }

        // Sobrescribimos equals para que las pruebas (AssertEquals) funcionen
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ResultadoMesEstado that = (ResultadoMesEstado) obj;
            return estado == that.estado && mes == that.mes;
        }
    }

    /**
     * Constructor que recibe el arreglo de datos.
     * @param datos TEMP[32][12][50]
     */
    public AnalizadorTemperaturas(double[][][] datos) {
        // Aquí podríamos validar que las dimensiones sean correctas
        if (datos.length != 32 || datos[0].length != 12 || datos[0][0].length != 50) {
            throw new IllegalArgumentException("Dimensiones del arreglo incorrectas.");
        }
        this.temp = datos;
    }

    /**
     * Mapea un año real (ej. 1950) a su índice en el arreglo (ej. 0).
     */
    private int getIndiceAno(int anoReal) {
        return anoReal - 1950;
    }

    // --- MÉTODOS PARA CADA INCISO ---

    /**
     * a) El estado que tuvo en promedio la mayor temperatura
     * durante los últimos diez años (1990-1999).
     */
    public int incisoA() {
        int estadoGanador = -1;
        double mayorPromedioGeneral = Double.NEGATIVE_INFINITY; // -Infinito

        int anoInicio = getIndiceAno(1990); // Índice 40
        int anoFin = getIndiceAno(1999);     // Índice 49

        // Bucle 1: Estados (0-31)
        for (int e = 0; e < 32; e++) {
            double sumaDecadaEstado = 0;
            
            // Bucle 2: Años (40-49)
            for (int a = anoInicio; a <= anoFin; a++) {
                // Bucle 3: Meses (0-11)
                for (int m = 0; m < 12; m++) {
                    sumaDecadaEstado += temp[e][m][a];
                }
            }
            
            // Calculamos el promedio de ese estado para esa década
            double promedioDecada = sumaDecadaEstado / (10 * 12); // 120 lecturas
            
            if (promedioDecada > mayorPromedioGeneral) {
                mayorPromedioGeneral = promedioDecada;
                estadoGanador = e;
            }
        }
        
        return estadoGanador + 1; // Devolvemos el estado real (1-32)
    }

    /**
     * b) El estado con menor promedio anual de temperatura
     * en el último año (1999).
     */
    public int incisoB() {
        int estadoGanador = -1;
        double menorPromedioAnual = Double.POSITIVE_INFINITY; // +Infinito
        int ano = getIndiceAno(1999); // Índice 49

        // Bucle 1: Estados (0-31)
        for (int e = 0; e < 32; e++) {
            double sumaAnual = 0;
            
            // Bucle 2: Meses (0-11)
            for (int m = 0; m < 12; m++) {
                sumaAnual += temp[e][m][ano];
            }
            
            double promedioAnual = sumaAnual / 12;
            
            if (promedioAnual < menorPromedioAnual) {
                menorPromedioAnual = promedioAnual;
                estadoGanador = e;
            }
        }
        return estadoGanador + 1; // Devolvemos el estado real (1-32)
    }

    /**
     * c) ¿En qué mes se registró el mayor promedio de temperaturas
     * en el estado 29, en el año 1953?
     */
    public int incisoC() {
        int mesGanador = -1;
        double mayorTemp = Double.NEGATIVE_INFINITY;
        
        int estado = 28; // Índice para el estado 29
        int ano = getIndiceAno(1953); // Índice 3

        // Bucle: Meses (0-11)
        for (int m = 0; m < 12; m++) {
            if (temp[estado][m][ano] > mayorTemp) {
                mayorTemp = temp[estado][m][ano];
                mesGanador = m;
            }
        }
        return mesGanador + 1; // Devolvemos el mes real (1-12)
    }

    /**
     * d) ¿En qué mes y en qué estado se registró el menor promedio
     * de temperaturas en 1975?
     */
    public ResultadoMesEstado incisoD() {
        int estadoGanador = -1;
        int mesGanador = -1;
        double menorTemp = Double.POSITIVE_INFINITY;
        
        int ano = getIndiceAno(1975); // Índice 25

        // Bucle 1: Estados (0-31)
        for (int e = 0; e < 32; e++) {
            // Bucle 2: Meses (0-11)
            for (int m = 0; m < 12; m++) {
                if (temp[e][m][ano] < menorTemp) {
                    menorTemp = temp[e][m][ano];
                    estadoGanador = e;
                    mesGanador = m;
                }
            }
        }
        
        // Devolvemos el estado y mes reales (1-32, 1-12)
        return new ResultadoMesEstado(estadoGanador + 1, mesGanador + 1);
    }
}