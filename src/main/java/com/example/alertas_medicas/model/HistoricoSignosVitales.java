package com.example.alertas_medicas.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class HistoricoSignosVitales implements Serializable {
    private Long pacienteId;
    private String pacienteNombre;
    private List<SignoVitalDTO> historial;

    // ✅ Default constructor for Jackson
    public HistoricoSignosVitales() {
    }

    // ✅ Corrected JsonCreator constructor
    @JsonCreator
    public HistoricoSignosVitales(
            @JsonProperty("pacienteId") Long pacienteId,
            @JsonProperty("pacienteNombre") String pacienteNombre,
            @JsonProperty("historial") List<SignoVitalDTO> historial) {
        this.pacienteId = pacienteId;
        this.pacienteNombre = pacienteNombre;
        this.historial = historial;
    }

    // Getters
    public Long getPacienteId() {
        return pacienteId;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public List<SignoVitalDTO> getHistorial() {
        return historial;
    }

    // DTO Class for historical vital signs
    public static class SignoVitalDTO {
        private int frecuenciaCardiaca;
        private int presionArterialSistolica;
        private int presionArterialDiastolica;
        private int saturacionOxigeno;
        private double temperatura;
        private LocalDateTime fechaRegistro;

        // ✅ Default constructor for Jackson
        public SignoVitalDTO() {
        }

        @JsonCreator
        public SignoVitalDTO(
                @JsonProperty("frecuenciaCardiaca") int frecuenciaCardiaca,
                @JsonProperty("presionArterialSistolica") int presionArterialSistolica,
                @JsonProperty("presionArterialDiastolica") int presionArterialDiastolica,
                @JsonProperty("saturacionOxigeno") int saturacionOxigeno,
                @JsonProperty("temperatura") double temperatura,
                @JsonProperty("fechaRegistro") LocalDateTime fechaRegistro) {
            this.frecuenciaCardiaca = frecuenciaCardiaca;
            this.presionArterialSistolica = presionArterialSistolica;
            this.presionArterialDiastolica = presionArterialDiastolica;
            this.saturacionOxigeno = saturacionOxigeno;
            this.temperatura = temperatura;
            this.fechaRegistro = fechaRegistro;
        }

        // Getters
        public int getFrecuenciaCardiaca() {
            return frecuenciaCardiaca;
        }

        public int getPresionArterialSistolica() {
            return presionArterialSistolica;
        }

        public int getPresionArterialDiastolica() {
            return presionArterialDiastolica;
        }

        public int getSaturacionOxigeno() {
            return saturacionOxigeno;
        }

        public double getTemperatura() {
            return temperatura;
        }

        public LocalDateTime getFechaRegistro() {
            return fechaRegistro;
        }
    }
}
