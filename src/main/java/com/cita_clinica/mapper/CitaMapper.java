package com.cita_clinica.mapper;

import com.cita_clinica.domain.model.Cita;
import com.cita_clinica.domain.model.Paciente;
import com.cita_clinica.dto.Cita.CitaRequest;
import com.cita_clinica.dto.Cita.CitaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CitaMapper {
    //@Mapping(source = "paciente.dniPaciente",target = "dniPaciente")
    //@Mapping(source = "paciente",target = "nomPaciente",qualifiedByName = "nombreCompleto")
    //@Mapping(source = "medico.nombreMedico",target = "nombreMedico")
    //@Mapping(source = "medico.especialidad.nombreEspecialidad",target = "nombreEspecialidad")
    CitaResponse toResponse(Cita cita);
    Cita toModel(CitaRequest citaRequest);

    @Named("nombreCompleto")
    static String nombreCompleto(Paciente paciente){
        return paciente.getNombrePaciente() + " " + paciente.getApellidoPaciente();
    }
}
