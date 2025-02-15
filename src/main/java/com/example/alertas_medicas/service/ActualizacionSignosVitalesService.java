package com.example.alertas_medicas.service;

import com.example.alertas_medicas.config.RabbitMQConfig;
import com.example.alertas_medicas.config.RabbitMQConfigReport;
import com.example.alertas_medicas.model.AlertMessage;
import com.example.alertas_medicas.model.HistoricoSignosVitales;
import com.example.alertas_medicas.model.Paciente;
import com.example.alertas_medicas.model.SenalVital;
import com.example.alertas_medicas.repository.PacienteRepository;
import com.example.alertas_medicas.repository.SenalVitalRepository;

import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ActualizacionSignosVitalesService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private SenalVitalRepository senalVitalRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplateService2;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    private final Random random = new Random();

    @Scheduled(fixedRate = 30000)
    @Transactional
    public void actualizarSignosVitales() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        for (Paciente paciente : pacientes) {

            senalVitalRepository.deleteByPacienteId(paciente.getId());

            SenalVital nuevaSenal = generarSignosVitales(paciente);

            senalVitalRepository.save(nuevaSenal);

            if (esAnormal(nuevaSenal)) {

                publicarAlerta(paciente, nuevaSenal);
            }
        }
    }

    @Scheduled(fixedRate = 60000) // 1 minuto
    public void enviarReporteSignosVitales() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        for (Paciente paciente : pacientes) {
            List<SenalVital> historial = senalVitalRepository.findByPacienteId(paciente.getId());

            HistoricoSignosVitales reporte = new HistoricoSignosVitales(
                    paciente.getId(),
                    paciente.getNombre(),
                    historial.stream().map(sv -> new HistoricoSignosVitales.SignoVitalDTO(
                            sv.getFrecuenciaCardiaca(),
                            sv.getPresionArterialSistolica(),
                            sv.getPresionArterialDiastolica(),
                            sv.getSaturacionOxigeno(),
                            sv.getTemperatura(),
                            sv.getFechaRegistro())).collect(Collectors.toList()));

            // Enviar a RabbitMQ
            // rabbitTemplateService2.convertAndSend(
            // RabbitMQConfigReport.EXCHANGE_NAME,
            // RabbitMQConfigReport.ROUTING_KEY,
            // reporte);

            System.out
                    .println("📊 Reporte de signos vitales enviado a RabbitMQ para paciente: " + paciente.getNombre());

            // Enviar a Kafka
            kafkaProducerService.enviarReporteSignosVitales(reporte);
        }
    }

    // Generar signos vitales aleatorios
    private SenalVital generarSignosVitales(Paciente paciente) {
        SenalVital senalVital = new SenalVital();
        senalVital.setPaciente(paciente);
        senalVital.setFrecuenciaCardiaca(60 + random.nextInt(80));
        senalVital.setPresionArterialSistolica(90 + random.nextInt(50));
        senalVital.setPresionArterialDiastolica(60 + random.nextInt(30));
        senalVital.setSaturacionOxigeno(90 + random.nextInt(10));
        senalVital.setTemperatura(35.0 + random.nextDouble() * 5);
        senalVital.setFechaRegistro(LocalDateTime.now());
        return senalVital;
    }

    // Verificar si los signos vitales son anormales
    private boolean esAnormal(SenalVital senalVital) {
        return senalVital.getFrecuenciaCardiaca() > 100 ||
                senalVital.getTemperatura() > 38.0 ||
                senalVital.getSaturacionOxigeno() < 92;
    }

    // Publicar alerta en RabbitMQ cuando hay signos vitales anormales
    private void publicarAlerta(Paciente paciente, SenalVital senalVital) {
        AlertMessage alerta = new AlertMessage(
                paciente.getId(),
                "Signos vitales anormales detectados",
                senalVital.getFrecuenciaCardiaca(),
                senalVital.getTemperatura(),
                senalVital.getSaturacionOxigeno(),
                LocalDateTime.now());

        // Enviar a RabbitMQ
        // rabbitTemplate.convertAndSend(
        // RabbitMQConfig.EXCHANGE_NAME,
        // RabbitMQConfig.ROUTING_KEY,
        // alerta);

        System.out.println("⚠️ Alerta publicada en RabbitMQ: " + alerta);

        // Enviar a Kafka
        kafkaProducerService.enviarAlerta(alerta);
    }
}

// package com.example.alertas_medicas.service;

// import com.example.alertas_medicas.config.RabbitMQConfig;
// import com.example.alertas_medicas.model.AlertMessage;
// import com.example.alertas_medicas.model.Alerta;
// import com.example.alertas_medicas.model.Paciente;
// import com.example.alertas_medicas.model.SenalVital;
// import com.example.alertas_medicas.repository.AlertaRepository;
// import com.example.alertas_medicas.repository.PacienteRepository;
// import com.example.alertas_medicas.repository.SenalVitalRepository;

// import jakarta.transaction.Transactional;

// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.io.Serializable;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Random;

// @Service
// public class ActualizacionSignosVitalesService {

// @Autowired
// private PacienteRepository pacienteRepository;

// @Autowired
// private SenalVitalRepository senalVitalRepository;

// @Autowired
// private AlertaRepository alertaRepository;

// @Autowired
// private RabbitTemplate rabbitTemplate;

// private final Random random = new Random();

// @Scheduled(fixedRate = 30000) // Ejecuta cada 30 segundos
// @Transactional
// public void actualizarSignosVitales() {
// List<Paciente> pacientes = pacienteRepository.findAll();

// for (Paciente paciente : pacientes) {
// senalVitalRepository.deleteByPacienteId(paciente.getId());

// SenalVital nuevaSenal = generarSignosVitales(paciente);
// senalVitalRepository.save(nuevaSenal);

// if (esAnormal(nuevaSenal)) {
// Alerta alerta = crearAlerta(paciente, nuevaSenal);
// publicarAlerta(alerta, nuevaSenal);
// }
// }
// }

// private SenalVital generarSignosVitales(Paciente paciente) {
// SenalVital senalVital = new SenalVital();
// senalVital.setPaciente(paciente);
// senalVital.setFrecuenciaCardiaca(60 + random.nextInt(80));
// senalVital.setPresionArterialSistolica(90 + random.nextInt(50));
// senalVital.setPresionArterialDiastolica(60 + random.nextInt(30));
// senalVital.setSaturacionOxigeno(90 + random.nextInt(10));
// senalVital.setTemperatura(35.0 + random.nextDouble() * 5);
// senalVital.setFechaRegistro(LocalDateTime.now());
// return senalVital;
// }

// private boolean esAnormal(SenalVital senalVital) {
// return senalVital.getFrecuenciaCardiaca() > 100 ||
// senalVital.getTemperatura() > 38.0 ||
// senalVital.getSaturacionOxigeno() < 92;
// }

// private Alerta crearAlerta(Paciente paciente, SenalVital senalVital) {
// Alerta alerta = new Alerta();
// alerta.setPaciente(paciente);

// if (senalVital.getFrecuenciaCardiaca() > 100) {
// alerta.setTipoAlerta("Frecuencia cardíaca alta");
// alerta.setMensaje("Frecuencia cardíaca fuera de rango: " +
// senalVital.getFrecuenciaCardiaca() + " bpm");
// } else if (senalVital.getTemperatura() > 38.0) {
// alerta.setTipoAlerta("Temperatura elevada");
// alerta.setMensaje("Temperatura fuera de rango: " +
// senalVital.getTemperatura() + " °C");
// } else if (senalVital.getSaturacionOxigeno() < 92) {
// alerta.setTipoAlerta("Saturación de oxígeno baja");
// alerta.setMensaje("Saturación de oxígeno fuera de rango: " +
// senalVital.getSaturacionOxigeno() + "%");
// } else {
// alerta.setTipoAlerta("Parámetros anormales");
// alerta.setMensaje("Se detectaron parámetros fuera de rango.");
// }

// alerta.setGravedad("Alta");
// alerta.setFechaGeneracion(LocalDateTime.now());
// alerta.setAtendida("N");

// return alertaRepository.save(alerta);
// }

// private void publicarAlerta(Alerta alerta, SenalVital senalVital) {
// AlertMessage mensaje = new AlertMessage(
// alerta.getPaciente().getId(),
// alerta.getMensaje(),
// senalVital.getFrecuenciaCardiaca(),
// senalVital.getTemperatura(),
// senalVital.getSaturacionOxigeno(),
// alerta.getFechaGeneracion());

// rabbitTemplate.convertAndSend(
// RabbitMQConfig.EXCHANGE_NAME,
// RabbitMQConfig.ROUTING_KEY,
// mensaje);

// System.out.println("Alerta publicada en RabbitMQ: " + mensaje);
// }
// }
