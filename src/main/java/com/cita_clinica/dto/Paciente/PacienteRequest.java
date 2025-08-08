package com.cita_clinica.dto.Paciente;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PacienteRequest {
    private String nombrePaciente;
    private String apellidoPaciente;
    private String dniPaciente;
    private String direccionPaciente;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String fecnacPaciente;
}
