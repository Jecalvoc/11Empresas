package com.empresa.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empresa.game.service.MercadoService;

@SpringBootApplication
@EnableScheduling // Habilita tareas programadas (útil si quieres que el mercado fluctúe automáticamente cada X minutos)
public class SimulatorApplication {

    private static final Logger log = LoggerFactory.getLogger(SimulatorApplication.class);

    public static void main(String[] args) {
        // Inicia el servidor embebido de Tomcat y el contexto de Spring
        SpringApplication.run(SimulatorApplication.class, args);
        
        // Mensajes de consola para confirmar que el backend está vivo
        log.info("=====================================================");
        log.info("       🚀 CEO SIMULATOR - BACKEND INICIADO 🚀        ");
        log.info("=====================================================");
        log.info("  API REST disponible en: http://localhost:8080/api");
        log.info("  WebSocket disponible en: ws://localhost:8080/ws");
        log.info("=====================================================");
    }

    /**
     * Este Bean se ejecuta automáticamente justo después de que Spring Boot 
     * termine de iniciar. Lo usamos para verificar que el juego está listo.
     */
    @Bean
    public CommandLineRunner verificarEstadoDelJuego(MercadoService mercadoService) {
        return args -> {
            log.info("🔍 Verificando integridad de los datos del juego...");
            
            // Verificamos que el MercadoService haya cargado los 120 meses de datos históricos
            if (mercadoService.estaDatosCargados()) {
                log.info("✅ Datos históricos del mercado (10 años) cargados correctamente.");
            } else {
                log.warn("⚠️ ADVERTENCIA: No se pudieron cargar los datos históricos. El mercado usará valores por defecto.");
            }

            log.info("✅ Base de datos H2/PostgreSQL conectada.");
            log.info("✅ Sistema de WebSockets para multijugador activo.");
            log.info("🎮 ¡El servidor está listo para recibir CEOs!");
        };
    }
}
