package com.cita_clinica.mapper;

import com.cita_clinica.domain.model.Medico;
import com.cita_clinica.dto.Medico.MedicoRequest;
import com.cita_clinica.dto.Medico.MedicoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    //@Mapping(target = "nombreEspecialidad", expression = "java(medico.getEspecialidad() != null ? medico.getEspecialidad().getNombreEspecialidad() : null)")
    MedicoResponse toResponse(Medico medico);
    Medico toModel(MedicoRequest medicoRequest);
}
