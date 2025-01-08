package com.example.alertas_medicas.repository;

import com.example.alertas_medicas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Método personalizado: buscar por número de historial
    Paciente findByNumeroHistorial(String numeroHistorial);
}
