package com.example.alertas_medicas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Permitir solo el origen del BFF
        config.addAllowedOrigin("http://localhost:8080"); // Cambia por la URL del BFF

        // Permitir métodos HTTP
        config.addAllowedMethod("*");

        // Permitir todas las cabeceras
        config.addAllowedHeader("*");

        // Habilitar credenciales
        config.setAllowCredentials(true);

        // Aplicar configuración a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
