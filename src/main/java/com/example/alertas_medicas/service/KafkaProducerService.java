package com.example.alertas_medicas.service;

import com.example.alertas_medicas.model.AlertMessage;
import com.example.alertas_medicas.model.HistoricoSignosVitales;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.alertas}")
    private String topicAlertas;

    @Value("${spring.kafka.topic.seniales_vitales}")
    private String topicSenialesVitales;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarAlerta(AlertMessage alerta) {
        kafkaTemplate.send(new ProducerRecord<>(topicAlertas, alerta));
        System.out.println("📢 Alerta enviada a Kafka (Topic: " + topicAlertas + "): " + alerta);
    }

    public void enviarReporteSignosVitales(HistoricoSignosVitales reporte) {
        kafkaTemplate.send(new ProducerRecord<>(topicSenialesVitales, reporte));
        System.out.println(
                "📊 Reporte de signos vitales enviado a Kafka (Topic: " + topicSenialesVitales + "): " + reporte);
    }
}
