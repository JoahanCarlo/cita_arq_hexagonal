package com.cita_clinica.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreMedico;
    private String apellidoMedico;
    private String codigoMedico;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidad_id",nullable = false)
    @NotNull(message = "La especialidad es obligatoria")
    private Especialidad especialidad;
}
