package com.cita_clinica.application;

import com.cita_clinica.domain.model.Especialidad;
import com.cita_clinica.dto.Especialidad.EspecialidadRequest;
import com.cita_clinica.dto.Especialidad.EspecialidadResponse;
import com.cita_clinica.mapper.EspecialidadMapper;
import com.cita_clinica.repository.EspecialidadRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadService {
    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadMapper especialidadMapper;

    public EspecialidadService(EspecialidadRepository especialidadRepository, EspecialidadMapper especialidadMapper) {
        this.especialidadRepository = especialidadRepository;
        this.especialidadMapper = especialidadMapper;
    }

    public EspecialidadResponse registrar(EspecialidadRequest especialidadRequest){
        Especialidad especialidad = especialidadMapper.toModel(especialidadRequest);
        Especialidad especialidadGuardado = especialidadRepository.save(especialidad);
        return especialidadMapper.toResponse(especialidadGuardado);
    }

    public List<EspecialidadResponse> listarEspecialidad(){
        return especialidadRepository.findAll()
                .stream()
                .map(especialidadMapper::toResponse)
                .toList();
    }

    public EspecialidadResponse actualizarEspecialidad(Long id,EspecialidadRequest especialidadRequest){
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No existe el ID: "+id+" de la especialidad."));
        especialidad.setNombreEspecialidad(especialidadRequest.getNombreEspecialidad());
        Especialidad especialidadActualizado = especialidadRepository.save(especialidad);
        return especialidadMapper.toResponse(especialidadActualizado);
    }
}
