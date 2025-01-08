package com.example.alertas_medicas.service;

import com.example.alertas_medicas.model.Paciente;
import com.example.alertas_medicas.model.Response;

import java.util.List;

public interface PacienteService {

    // Obtener todos los pacientes
    Response getAllPacientes();

    // Obtener paciente por ID
    Response getPacienteById(Long id);

    // Crear nuevo paciente
    Response createPaciente(Paciente paciente);

    // Actualizar paciente existente
    Response updatePaciente(Long id, Paciente paciente);

    // Eliminar paciente por ID
    Response deletePaciente(Long id);
}
