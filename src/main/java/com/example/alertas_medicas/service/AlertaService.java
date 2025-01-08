package com.example.alertas_medicas.service;

import com.example.alertas_medicas.model.Alerta;
import com.example.alertas_medicas.model.Response;

import java.util.List;

public interface AlertaService {

    // Obtener todas las alertas
    Response getAllAlertas();

    // Obtener alerta por ID
    Response getAlertaById(Long id);

    // Crear nueva alerta
    Response createAlerta(Alerta alerta);

    // Actualizar alerta existente
    Response updateAlerta(Long id, Alerta alerta);

    // Eliminar alerta por ID
    Response deleteAlerta(Long id);

    // Obtener alertas por ID de paciente
    Response getAlertasByPacienteId(Long pacienteId);
}
