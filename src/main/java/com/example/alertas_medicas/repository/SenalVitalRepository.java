package com.example.alertas_medicas.repository;

import com.example.alertas_medicas.model.SenalVital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenalVitalRepository extends JpaRepository<SenalVital, Long> {

    // Método personalizado: buscar señales vitales por paciente
    List<SenalVital> findByPacienteId(Long pacienteId);

    void deleteByPacienteId(Long pacienteId);

    // Método personalizado: buscar señales vitales recientes (últimos registros)
    List<SenalVital> findTop10ByPacienteIdOrderByFechaRegistroDesc(Long pacienteId);
}
