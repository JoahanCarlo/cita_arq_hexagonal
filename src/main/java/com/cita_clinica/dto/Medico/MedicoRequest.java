package com.cita_clinica.dto.Medico;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entrada de datos del médico")
public class MedicoRequest {
    private String nombreMedico;
    private String apellidoMedico;
    private String codigoMedico;
    @NotNull(message = "La especialidad es obligatorias")
    private Long especialidadId;
}
