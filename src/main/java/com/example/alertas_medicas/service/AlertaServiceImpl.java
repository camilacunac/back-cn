package com.example.alertas_medicas.service;

import com.example.alertas_medicas.model.Alerta;
import com.example.alertas_medicas.model.Response;
import com.example.alertas_medicas.repository.AlertaRepository;
import com.example.alertas_medicas.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaServiceImpl implements AlertaService {

    private final AlertaRepository alertaRepository;

    @Autowired
    public AlertaServiceImpl(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @Override
    public Response getAllAlertas() {
        try {
            List<Alerta> alertas = alertaRepository.findAll();
            return new Response("success", alertas, null);
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response getAlertaById(Long id) {
        try {
            Optional<Alerta> alerta = alertaRepository.findById(id);
            if (alerta.isPresent()) {
                return new Response("success", alerta.get(), null);
            } else {
                return new Response("error", null, "Alerta no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response createAlerta(Alerta alerta) {
        try {
            Alerta nuevaAlerta = alertaRepository.save(alerta);
            return new Response("success", nuevaAlerta, null);
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response updateAlerta(Long id, Alerta alertaDetails) {
        try {
            Optional<Alerta> alertaOptional = alertaRepository.findById(id);
            if (alertaOptional.isPresent()) {
                Alerta alerta = alertaOptional.get();
                alerta.setTipoAlerta(alertaDetails.getTipoAlerta());
                alerta.setGravedad(alertaDetails.getGravedad());
                alerta.setMensaje(alertaDetails.getMensaje());
                alerta.setFechaGeneracion(alertaDetails.getFechaGeneracion());
                alerta.setAtendida(alertaDetails.getAtendida());
                Alerta actualizada = alertaRepository.save(alerta);
                return new Response("success", actualizada, null);
            } else {
                return new Response("error", null, "Alerta no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response deleteAlerta(Long id) {
        try {
            if (alertaRepository.existsById(id)) {
                alertaRepository.deleteById(id);
                return new Response("success", "Alerta eliminada con éxito", null);
            } else {
                return new Response("error", null, "Alerta no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response getAlertasByPacienteId(Long pacienteId) {
        try {
            List<Alerta> alertas = alertaRepository.findByPacienteId(pacienteId);
            return new Response("success", alertas, null);
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }
}
