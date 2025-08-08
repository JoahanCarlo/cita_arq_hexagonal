package com.cita_clinica.mapper;

import com.cita_clinica.domain.model.Paciente;
import com.cita_clinica.dto.Paciente.PacienteRequest;
import com.cita_clinica.dto.Paciente.PacienteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PacienteMapper {
    @Mapping(source = "paciente",target = "nomPaciente", qualifiedByName = "nombreCompleto")
    PacienteResponse toResponse(Paciente paciente);
    Paciente toModel(PacienteRequest pacienteRequest);

    @Named("nombreCompleto")
    static String nombreCompleto(Paciente paciente){
        return paciente.getNombrePaciente() + " " + paciente.getApellidoPaciente();
    }
}
