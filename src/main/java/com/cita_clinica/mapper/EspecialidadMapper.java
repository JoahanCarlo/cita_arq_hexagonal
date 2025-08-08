package com.cita_clinica.mapper;

import com.cita_clinica.domain.model.Especialidad;
import com.cita_clinica.dto.Especialidad.EspecialidadRequest;
import com.cita_clinica.dto.Especialidad.EspecialidadResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EspecialidadMapper {
    EspecialidadResponse toResponse(Especialidad especialidad);
    Especialidad toModel(EspecialidadRequest especialidadRequest);
}
