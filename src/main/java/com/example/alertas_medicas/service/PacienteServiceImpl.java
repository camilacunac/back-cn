package com.example.alertas_medicas.service;

import com.example.alertas_medicas.model.Paciente;
import com.example.alertas_medicas.model.Response;
import com.example.alertas_medicas.repository.PacienteRepository;
import com.example.alertas_medicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Response getAllPacientes() {
        try {
            List<Paciente> pacientes = pacienteRepository.findAll();
            return new Response("success", pacientes, null);
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response getPacienteById(Long id) {
        try {
            Optional<Paciente> paciente = pacienteRepository.findById(id);
            if (paciente.isPresent()) {
                return new Response("success", paciente.get(), null);
            } else {
                return new Response("error", null, "Paciente no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response createPaciente(Paciente paciente) {
        try {
            Paciente nuevoPaciente = pacienteRepository.save(paciente);
            return new Response("success", nuevoPaciente, null);
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response updatePaciente(Long id, Paciente pacienteDetails) {
        try {
            Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
            if (pacienteOptional.isPresent()) {
                Paciente paciente = pacienteOptional.get();
                paciente.setNombre(pacienteDetails.getNombre());
                paciente.setApellido(pacienteDetails.getApellido());
                paciente.setFechaNacimiento(pacienteDetails.getFechaNacimiento());
                paciente.setSexo(pacienteDetails.getSexo());
                paciente.setNumeroHistorial(pacienteDetails.getNumeroHistorial());
                paciente.setEstado(pacienteDetails.getEstado());
                paciente.setFechaIngreso(pacienteDetails.getFechaIngreso());
                Paciente actualizado = pacienteRepository.save(paciente);
                return new Response("success", actualizado, null);
            } else {
                return new Response("error", null, "Paciente no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }

    @Override
    public Response deletePaciente(Long id) {
        try {
            if (pacienteRepository.existsById(id)) {
                pacienteRepository.deleteById(id);
                return new Response("success", "Paciente eliminado con éxito", null);
            } else {
                return new Response("error", null, "Paciente no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            return new Response("error", null, e.getMessage());
        }
    }
}
