package com.example.alertas_medicas.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL) // Incluir solo campos no nulos
public class AlertMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long pacienteId;
    private String mensaje;
    private Integer frecuenciaCardiaca;
    private Double temperatura;
    private Integer saturacionOxigeno;
    private LocalDateTime timestamp;

    public AlertMessage() {
    }

    public AlertMessage(Long pacienteId, String mensaje, Integer frecuenciaCardiaca, Double temperatura,
            Integer saturacionOxigeno, LocalDateTime timestamp) {
        this.pacienteId = pacienteId;
        this.mensaje = mensaje;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.temperatura = temperatura;
        this.saturacionOxigeno = saturacionOxigeno;
        this.timestamp = timestamp;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(Integer saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AlertMessage{" +
                "pacienteId=" + pacienteId +
                ", mensaje='" + mensaje + '\'' +
                ", frecuenciaCardiaca=" + frecuenciaCardiaca +
                ", temperatura=" + temperatura +
                ", saturacionOxigeno=" + saturacionOxigeno +
                ", timestamp=" + timestamp +
                '}';
    }
}
