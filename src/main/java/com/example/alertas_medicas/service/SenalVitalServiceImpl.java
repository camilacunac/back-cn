package com.example.alertas_medicas.service;

import com.example.alertas_medicas.model.Response;
import com.example.alertas_medicas.model.SenalVital;
import com.example.alertas_medicas.repository.SenalVitalRepository;
import com.example.alertas_medicas.service.SenalVitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SenalVitalServiceImpl implements SenalVitalService {

    private final SenalVitalRepository senalVitalRepository;

    @Autowired
    public SenalVitalServiceImpl(SenalVitalRepository senalVitalRepository) {
        this.senalVitalRepository = senalVitalRepository;
    }

    @Override
    public Response getAllSenales() {
        try {
            List<SenalVital> senales = senalVitalRepository.findAll();
            return new Response("success", senales, null);
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response getSenalById(Long id) {
        try {
            Optional<SenalVital> senal = senalVitalRepository.findById(id);
            if (senal.isPresent()) {
                return new Response("success", senal.get(), null);
            } else {
                return new Response("error", null, "Señal vital no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response createSenal(SenalVital senalVital) {
        try {
            SenalVital nuevaSenal = senalVitalRepository.save(senalVital);
            return new Response("success", nuevaSenal, null);
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response updateSenal(Long id, SenalVital senalVitalDetails) {
        try {
            Optional<SenalVital> senalOptional = senalVitalRepository.findById(id);
            if (senalOptional.isPresent()) {
                SenalVital senal = senalOptional.get();
                senal.setFrecuenciaCardiaca(senalVitalDetails.getFrecuenciaCardiaca());
                senal.setPresionArterialSistolica(senalVitalDetails.getPresionArterialSistolica());
                senal.setPresionArterialDiastolica(senalVitalDetails.getPresionArterialDiastolica());
                senal.setSaturacionOxigeno(senalVitalDetails.getSaturacionOxigeno());
                senal.setTemperatura(senalVitalDetails.getTemperatura());
                senal.setFechaRegistro(senalVitalDetails.getFechaRegistro());
                SenalVital actualizada = senalVitalRepository.save(senal);
                return new Response("success", actualizada, null);
            } else {
                return new Response("error", null, "Señal vital no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response deleteSenal(Long id) {
        try {
            if (senalVitalRepository.existsById(id)) {
                senalVitalRepository.deleteById(id);
                return new Response("success", "Señal vital eliminada con éxito", null);
            } else {
                return new Response("error", null, "Señal vital no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response getSenalesByPacienteId(Long pacienteId) {
        try {
            List<SenalVital> senales = senalVitalRepository.findByPacienteId(pacienteId);
            return new Response("success", senales, null);
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response getLast10SenalesByPacienteId(Long pacienteId) {
        try {
            List<SenalVital> senales = senalVitalRepository.findTop10ByPacienteIdOrderByFechaRegistroDesc(pacienteId);
            return new Response("success", senales, null);
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }
}
