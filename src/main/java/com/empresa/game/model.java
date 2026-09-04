// backend/src/main/java/com/empresa/game/service/RRHHService.java
@Service
public class RRHHService {

    // Factores de Seguridad Social (Ejemplo Latam)
    private static final double SALUD_EMPRESA = 0.085;
    private static final double PENSION_EMPRESA = 0.12;
    private static final double ARL_RIESGO_BASICO = 0.005;
    private static final double CAJA_COMPENSACION = 0.04;

    // Factores de Prestaciones Sociales (Mensuales)
    private static final double PRIMA_MENSUAL = 1.0 / 12.0;
    private static final double CESANTIAS_MENSUAL = 1.0 / 12.0;
    private static final double INTERESES_CESANTIAS = 0.01;
    private static final double VACACIONES_MENSUAL = 1.0 / 24.0;

    public double calcularCostoTotalEmpleado(double salarioBase) {
        double seguridadSocial = salarioBase * (SALUD_EMPRESA + PENSION_EMPRESA + ARL_RIESGO_BASICO + CAJA_COMPENSACION);
        double prestaciones = salarioBase * (PRIMA_MENSUAL + CESANTIAS_MENSUAL + INTERESES_CESANTIAS + VACACIONES_MENSUAL);
        
        return salarioBase + seguridadSocial + prestaciones;
    }
}
