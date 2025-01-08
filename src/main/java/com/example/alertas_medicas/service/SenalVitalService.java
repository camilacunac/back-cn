package com.example.alertas_medicas.service;

import com.example.alertas_medicas.model.Response;
import com.example.alertas_medicas.model.SenalVital;

import java.util.List;

public interface SenalVitalService {

    // Obtener todas las señales vitales
    Response getAllSenales();

    // Obtener señal vital por ID
    Response getSenalById(Long id);

    // Crear nueva señal vital
    Response createSenal(SenalVital senalVital);

    // Actualizar señal vital existente
    Response updateSenal(Long id, SenalVital senalVital);

    // Eliminar señal vital por ID
    Response deleteSenal(Long id);

    // Obtener señales vitales por ID de paciente
    Response getSenalesByPacienteId(Long pacienteId);

    // Obtener las últimas 10 señales vitales de un paciente
    Response getLast10SenalesByPacienteId(Long pacienteId);
}
