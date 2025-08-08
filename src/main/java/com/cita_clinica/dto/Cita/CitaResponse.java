package com.cita_clinica.dto.Cita;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Long id;
    private LocalDate fechaCita;
    private String dniPaciente;
    private String nomPaciente;
    private String nombreMedico;
    private String nombreEspecialidad;
    private LocalDateTime fechaCreacion;
    @JsonIgnore
    private Boolean estadoCita;

    @JsonProperty("estadoCita")
    public Integer getEstadoCitaJson(){
        return (estadoCita != null && estadoCita) ? 1 : 0;
    }
}
