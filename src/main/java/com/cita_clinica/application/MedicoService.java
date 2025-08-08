package com.cita_clinica.application;

import com.cita_clinica.domain.model.Especialidad;
import com.cita_clinica.domain.model.Medico;
import com.cita_clinica.dto.Medico.MedicoRequest;
import com.cita_clinica.dto.Medico.MedicoResponse;
import com.cita_clinica.mapper.MedicoMapper;
import com.cita_clinica.repository.EspecialidadRepository;
import com.cita_clinica.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;
    private final EspecialidadRepository especialidadRepository;

    public MedicoService(MedicoRepository medicoRepository, MedicoMapper medicoMapper, EspecialidadRepository especialidadRepository) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
        this.especialidadRepository = especialidadRepository;
    }


    public MedicoResponse registrarMedico(MedicoRequest medicoRequest){
        Especialidad especialidad = especialidadRepository.findById(medicoRequest.getEspecialidadId())
                .orElseThrow(()->new RuntimeException("Especialidad no encontrada"));
        Medico medico = medicoMapper.toModel(medicoRequest);
        medico.setNombreMedico(medicoRequest.getNombreMedico());
        medico.setApellidoMedico(medicoRequest.getApellidoMedico());
        medico.setCodigoMedico(medicoRequest.getCodigoMedico());
        medico.setEspecialidad(especialidad);
        return medicoMapper.toResponse(medicoRepository.save(medico));
    }

    public List<MedicoResponse> listarMedico(){
        return medicoRepository.findAll()
                .stream()
                .map(medicoMapper::toResponse)
                .toList();
    }
    public Optional<MedicoResponse> actualizarMedico(
            Long id, MedicoRequest medicoRequest
    ){
        return medicoRepository.findById(id)
                .map(medicoExistente->{
                    Especialidad especialidad = especialidadRepository.findById(medicoRequest
                            .getEspecialidadId())
                            .orElseThrow(()->new RuntimeException("Especialidad no encontrada"));
                    medicoExistente.setCodigoMedico(medicoRequest.getCodigoMedico());
                    medicoExistente.setNombreMedico(medicoRequest.getNombreMedico());
                    medicoExistente.setApellidoMedico(medicoRequest.getApellidoMedico());
                    medicoExistente.setEspecialidad(especialidad);
                    Medico medicoActualizado = medicoRepository.save(medicoExistente);
                    return medicoMapper.toResponse(medicoActualizado);
                });
    }
}
