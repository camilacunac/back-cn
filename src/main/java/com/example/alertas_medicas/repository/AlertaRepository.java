package com.example.alertas_medicas.repository;

import com.example.alertas_medicas.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    // Método personalizado: buscar alertas por paciente
    List<Alerta> findByPacienteId(Long pacienteId);

    // Método personalizado: buscar alertas por estado (atendida o no)
    List<Alerta> findByAtendida(String atendida);
}
