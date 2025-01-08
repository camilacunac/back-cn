package com.example.alertas_medicas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Long id;

    @NotNull(message = "El ID del paciente es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    @JsonBackReference
    private Paciente paciente;

    @NotBlank(message = "El tipo de alerta es obligatorio")
    @Size(max = 100, message = "El tipo de alerta no puede exceder 100 caracteres")
    private String tipoAlerta;

    @NotBlank(message = "La gravedad es obligatoria")
    @Pattern(regexp = "Baja|Media|Alta", message = "La gravedad debe ser 'Baja', 'Media' o 'Alta'")
    private String gravedad;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max = 500, message = "El mensaje no puede exceder 500 caracteres")
    private String mensaje;

    @NotNull(message = "La fecha de generación es obligatoria")
    private LocalDateTime fechaGeneracion;

    @NotNull(message = "El estado atendida es obligatorio")
    @Pattern(regexp = "S|N", message = "El campo atendida debe ser 'S' o 'N'")
    private String atendida;

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

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getAtendida() {
        return atendida;
    }

    public void setAtendida(String atendida) {
        this.atendida = atendida;
    }
}
