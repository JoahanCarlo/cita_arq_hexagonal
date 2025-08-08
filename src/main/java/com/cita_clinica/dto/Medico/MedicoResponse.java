package com.cita_clinica.dto.Medico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta a la información de un medico")
public class MedicoResponse {
    private Long id;
    private String nombreMedico;
    private String apellidoMedico;
    private String codigoMedico;
    private String nombreEspecialidad;
}
