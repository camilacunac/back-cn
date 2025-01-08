package com.example.alertas_medicas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "senales_vitales")
public class SenalVital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_senal")
    private Long id;

    @NotNull(message = "El ID del paciente es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    @JsonBackReference
    private Paciente paciente;

    @Min(value = 0, message = "La frecuencia cardíaca debe ser un valor positivo")
    private Integer frecuenciaCardiaca;

    @Min(value = 0, message = "La presión arterial sistólica debe ser un valor positivo")
    private Integer presionArterialSistolica;

    @Min(value = 0, message = "La presión arterial diastólica debe ser un valor positivo")
    private Integer presionArterialDiastolica;

    @Min(value = 0, message = "La saturación de oxígeno debe ser un valor positivo")
    private Integer saturacionOxigeno;

    @DecimalMin(value = "0.0", message = "La temperatura debe ser un valor positivo")
    private Double temperatura;

    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDateTime fechaRegistro;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Integer getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public Integer getPresionArterialSistolica() {
        return presionArterialSistolica;
    }

    public void setPresionArterialSistolica(Integer presionArterialSistolica) {
        this.presionArterialSistolica = presionArterialSistolica;
    }

    public Integer getPresionArterialDiastolica() {
        return presionArterialDiastolica;
    }

    public void setPresionArterialDiastolica(Integer presionArterialDiastolica) {
        this.presionArterialDiastolica = presionArterialDiastolica;
    }

    public Integer getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(Integer saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
