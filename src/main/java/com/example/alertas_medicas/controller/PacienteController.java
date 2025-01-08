package com.example.alertas_medicas.controller;

import com.example.alertas_medicas.model.Paciente;
import com.example.alertas_medicas.model.Response;
import com.example.alertas_medicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<Response> getAllPacientes() {
        Response response = pacienteService.getAllPacientes();
        return buildResponseEntity(response);
    }

    // Obtener un paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Response> getPacienteById(@PathVariable Long id) {
        Response response = pacienteService.getPacienteById(id);
        return buildResponseEntity(response);
    }

    // Crear un nuevo paciente
    @PostMapping
    public ResponseEntity<Response> createPaciente(@RequestBody Paciente paciente) {
        Response response = pacienteService.createPaciente(paciente);
        return buildResponseEntity(response);
    }

    // Actualizar un paciente existente
    @PutMapping("/{id}")
    public ResponseEntity<Response> updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        Response response = pacienteService.updatePaciente(id, paciente);
        return buildResponseEntity(response);
    }

    // Eliminar un paciente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePaciente(@PathVariable Long id) {
        Response response = pacienteService.deletePaciente(id);
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
