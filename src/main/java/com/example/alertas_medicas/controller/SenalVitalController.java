package com.example.alertas_medicas.controller;

import com.example.alertas_medicas.model.Response;
import com.example.alertas_medicas.model.SenalVital;
import com.example.alertas_medicas.service.SenalVitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/senales-vitales")
public class SenalVitalController {

    private final SenalVitalService senalVitalService;

    @Autowired
    public SenalVitalController(SenalVitalService senalVitalService) {
        this.senalVitalService = senalVitalService;
    }

    // Obtener todas las señales vitales
    @GetMapping
    public ResponseEntity<Response> getAllSenales() {
        Response response = senalVitalService.getAllSenales();
        return buildResponseEntity(response);
    }

    // Obtener una señal vital por ID
    @GetMapping("/{id}")
    public ResponseEntity<Response> getSenalById(@PathVariable Long id) {
        Response response = senalVitalService.getSenalById(id);
        return buildResponseEntity(response);
    }

    // Crear una nueva señal vital
    @PostMapping
    public ResponseEntity<Response> createSenal(@RequestBody SenalVital senalVital) {
        Response response = senalVitalService.createSenal(senalVital);
        return buildResponseEntity(response);
    }

    // Actualizar una señal vital existente
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateSenal(@PathVariable Long id, @RequestBody SenalVital senalVital) {
        Response response = senalVitalService.updateSenal(id, senalVital);
        return buildResponseEntity(response);
    }

    // Eliminar una señal vital por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteSenal(@PathVariable Long id) {
        Response response = senalVitalService.deleteSenal(id);
        return buildResponseEntity(response);
    }

    // Obtener señales vitales por ID de paciente
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Response> getSenalesByPacienteId(@PathVariable Long pacienteId) {
        Response response = senalVitalService.getSenalesByPacienteId(pacienteId);
        return buildResponseEntity(response);
    }

    // Obtener las últimas 10 señales vitales de un paciente
    @GetMapping("/paciente/{pacienteId}/ultimas")
    public ResponseEntity<Response> getLast10SenalesByPacienteId(@PathVariable Long pacienteId) {
        Response response = senalVitalService.getLast10SenalesByPacienteId(pacienteId);
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
