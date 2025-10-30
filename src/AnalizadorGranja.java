import java.util.HashMap;
import java.util.Map;

/**
 * PS 3.36
 * Analiza la producción (kg y precio) de 5 productos de una granja
 * a lo largo de 12 meses.
 */
public class AnalizadorGranja {

    // Arreglos para almacenar los 120 datos de entrada
    private double[] tomKg, tomPrecio;
    private double[] lecKg, lecPrecio;
    private double[] aceKg, acePrecio;
    private double[] zanKg, zanPrecio;
    private double[] chiKg, chiPrecio;

    // Constantes para los nombres de los productos
    private static final String TOMATE = "Tomate";
    private static final String LECHUGA = "Lechuga";
    private static final String ACELGA = "Acelga";
    private static final String ZANAHORIA = "Zanahoria";
    private static final String CHICHARO = "Chícharo";

    /**
     * Constructor que recibe todos los datos de producción.
     */
    public AnalizadorGranja(
            double[] tomKg, double[] tomPrecio,
            double[] lecKg, double[] lecPrecio,
            double[] aceKg, double[] acePrecio,
            double[] zanKg, double[] zanPrecio,
            double[] chiKg, double[] chiPrecio) {
        
        // Aquí se podrían añadir validaciones (ej. que todos los arreglos midan 12)
        this.tomKg = tomKg; this.tomPrecio = tomPrecio;
        this.lecKg = lecKg; this.lecPrecio = lecPrecio;
        this.aceKg = aceKg; this.acePrecio = acePrecio;
        this.zanKg = zanKg; this.zanPrecio = zanPrecio;
        this.chiKg = chiKg; this.chiPrecio = chiPrecio;
    }

    // --- MÉTODOS AUXILIARES ---

    /**
     * Suma todos los valores de un arreglo.
     */
    private double sumarArreglo(double[] arreglo) {
        double total = 0;
        for (double valor : arreglo) {
            total += valor;
        }
        return total;
    }

    /**
     * Calcula el ingreso total (kg * precio) de un producto.
     */
    private double calcularIngresoTotal(double[] kilos, double[] precios) {
        double total = 0;
        for (int i = 0; i < 12; i++) {
            total += kilos[i] * precios[i];
        }
        return total;
    }
    
    // --- MÉTODOS PARA CADA INCISO ---

    /**
     * a) ¿Cuál es el producto que más kilogramos rindió al final del año?
     */
    public String getProductoMasKilos() {
        // Usamos un Map para guardar el total de KG por producto
        Map<String, Double> totalesKg = new HashMap<>();
        totalesKg.put(TOMATE, sumarArreglo(tomKg));
        totalesKg.put(LECHUGA, sumarArreglo(lecKg));
        totalesKg.put(ACELGA, sumarArreglo(aceKg));
        totalesKg.put(ZANAHORIA, sumarArreglo(zanKg));
        totalesKg.put(CHICHARO, sumarArreglo(chiKg));

        // Encontramos el máximo
        String productoGanador = "";
        double maxKilos = -1;
        for (Map.Entry<String, Double> entry : totalesKg.entrySet()) {
            if (entry.getValue() > maxKilos) {
                maxKilos = entry.getValue();
                productoGanador = entry.getKey();
            }
        }
        return productoGanador;
    }

    /**
     * b) ¿Cuál fue la producción total (kg y dinero) de cada producto?
     * Retorna un Map<String, Map<String, Double>>
     * Ej: {"Tomate": {"kg": 120.0, "dinero": 240.0}, ...}
     */
    public Map<String, Map<String, Double>> getTotalesProduccion() {
        Map<String, Map<String, Double>> resultados = new HashMap<>();
        
        resultados.put(TOMATE, Map.of("kg", sumarArreglo(tomKg), "dinero", calcularIngresoTotal(tomKg, tomPrecio)));
        resultados.put(LECHUGA, Map.of("kg", sumarArreglo(lecKg), "dinero", calcularIngresoTotal(lecKg, lecPrecio)));
        resultados.put(ACELGA, Map.of("kg", sumarArreglo(aceKg), "dinero", calcularIngresoTotal(aceKg, acePrecio)));
        resultados.put(ZANAHORIA, Map.of("kg", sumarArreglo(zanKg), "dinero", calcularIngresoTotal(zanKg, zanPrecio)));
        resultados.put(CHICHARO, Map.of("kg", sumarArreglo(chiKg), "dinero", calcularIngresoTotal(chiKg, chiPrecio)));
        
        return resultados;
    }

    /**
     * c) ¿Cuál es el producto que más dinero produjo al final del año?
     */
    public String getProductoMasDinero() {
        Map<String, Double> totalesDinero = new HashMap<>();
        totalesDinero.put(TOMATE, calcularIngresoTotal(tomKg, tomPrecio));
        totalesDinero.put(LECHUGA, calcularIngresoTotal(lecKg, lecPrecio));
        totalesDinero.put(ACELGA, calcularIngresoTotal(aceKg, acePrecio));
        totalesDinero.put(ZANAHORIA, calcularIngresoTotal(zanKg, zanPrecio));
        totalesDinero.put(CHICHARO, calcularIngresoTotal(chiKg, chiPrecio));

        // Encontramos el máximo
        String productoGanador = "";
        double maxDinero = -1;
        for (Map.Entry<String, Double> entry : totalesDinero.entrySet()) {
            if (entry.getValue() > maxDinero) {
                maxDinero = entry.getValue();
                productoGanador = entry.getKey();
            }
        }
        return productoGanador;
    }

    /**
     * d) Qué importe mensual le pagaron a esta familia de granjeros.
     */
    public double[] getImportesMensuales() {
        double[] importesMensuales = new double[12];
        
        // Bucle de 12 meses (i = 0 a 11)
        for (int i = 0; i < 12; i++) {
            double importeMes = 0;
            importeMes += tomKg[i] * tomPrecio[i];
            importeMes += lecKg[i] * lecPrecio[i];
            importeMes += aceKg[i] * acePrecio[i];
            importeMes += zanKg[i] * zanPrecio[i];
            importeMes += chiKg[i] * chiPrecio[i];
            
            importesMensuales[i] = importeMes;
        }
        return importesMensuales;
    }
}