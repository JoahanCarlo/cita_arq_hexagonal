package com.cita_clinica.dto.Especialidad;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecialidadResponse {
    private Long id;
    private String nombreEspecialidad;
}
