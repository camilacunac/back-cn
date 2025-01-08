package com.example.alertas_medicas.controller;

import com.example.alertas_medicas.model.Alerta;
import com.example.alertas_medicas.model.Response;
import com.example.alertas_medicas.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    @Autowired
    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    // Obtener todas las alertas
    @GetMapping
    public ResponseEntity<Response> getAllAlertas() {
        Response response = alertaService.getAllAlertas();
        return buildResponseEntity(response);
    }

    // Obtener una alerta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Response> getAlertaById(@PathVariable Long id) {
        Response response = alertaService.getAlertaById(id);
        return buildResponseEntity(response);
    }

    // Crear una nueva alerta
    @PostMapping
    public ResponseEntity<Response> createAlerta(@RequestBody Alerta alerta) {
        Response response = alertaService.createAlerta(alerta);
        return buildResponseEntity(response);
    }

    // Actualizar una alerta existente
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateAlerta(@PathVariable Long id, @RequestBody Alerta alerta) {
        Response response = alertaService.updateAlerta(id, alerta);
        return buildResponseEntity(response);
    }

    // Eliminar una alerta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAlerta(@PathVariable Long id) {
        Response response = alertaService.deleteAlerta(id);
        return buildResponseEntity(response);
    }

    // Obtener alertas por ID de paciente
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Response> getAlertasByPacienteId(@PathVariable Long pacienteId) {
        Response response = alertaService.getAlertasByPacienteId(pacienteId);
        return buildResponseEntity(response);
    }

    // Método auxiliar para construir la respuesta HTTP
    private ResponseEntity<Response> buildResponseEntity(Response response) {
        if ("success".equals(response.getState())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
