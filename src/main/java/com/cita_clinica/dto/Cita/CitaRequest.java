package com.cita_clinica.dto.Cita;

import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaRequest {
    private String dniPaciente;
    @Future(message = "La fecha de la cita debe ser en el futuro")
    private LocalDate fechaCita;
    private String nombreMedico;
}
