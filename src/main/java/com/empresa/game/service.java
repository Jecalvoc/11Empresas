// backend/src/main/java/com/empresa/game/service/MercadoService.java
@Service
public class MercadoService {

    private List<Double> historicalVolatility; // % de cambio mes a mes de los últimos 10 años

    @PostConstruct
    public void cargarDatosHistoricos() {
        // Cargar desde historical_market.json (120 meses de cierres reales)
        // Calcular la variación porcentual mensual: (MesActual - MesAnterior) / MesAnterior
        this.historicalVolatility = cargarYCalcularVolatilidad("data/historical_market.json");
    }

    public double predecirVentas(double ventasEstimadas, String sector) {
        // Tomamos una variación histórica aleatoria de los últimos 10 años
        // y le aplicamos un sesgo según el sector de la empresa
        double volatilidadHistorica = historicalVolatility.get(new Random().nextInt(120));
        double factorSector = obtenerFactorSector(sector); // ej. Tecnología es más volátil
        
        double fluctuacion = volatilidadHistorica * factorSector;
        return ventasEstimadas * (1 + fluctuacion);
    }
    
    private double obtenerFactorSector(String sector) {
        return switch (sector) {
            case "TECNOLOGIA" -> 1.5;
            case "ALIMENTOS" -> 0.6;
            case "CONSTRUCCION" -> 1.2;
            default -> 1.0;
        };
    }
}
