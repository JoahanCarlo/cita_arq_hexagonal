package com.cita_clinica.dto.Paciente;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponse {
    private Long id;
    private String nomPaciente;
    private String dniPaciente;
    private String direccionPaciente;
    private LocalDate fecnacPaciente;
}
