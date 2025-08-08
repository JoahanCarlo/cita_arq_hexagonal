package com.cita_clinica.application;

import com.cita_clinica.domain.model.Paciente;
import com.cita_clinica.dto.Paciente.PacienteRequest;
import com.cita_clinica.dto.Paciente.PacienteResponse;
import com.cita_clinica.mapper.PacienteMapper;
import com.cita_clinica.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    public PacienteResponse registrar(PacienteRequest pacienteRequest){
        Paciente paciente = pacienteMapper.toModel(pacienteRequest);
        Paciente pacienteGuardado = pacienteRepository.save(paciente);
        return pacienteMapper.toResponse(pacienteGuardado);
    }

    public List<PacienteResponse> listarPacientes(){
        return pacienteRepository.findAll()
                .stream()
                .map(pacienteMapper::toResponse)
                .toList();
    }

    public PacienteResponse actualizarPaciente(Long id, PacienteRequest pacienteRequest){
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No existe el ID : "+id+" paciente."));
        paciente.setNombrePaciente(pacienteRequest.getNombrePaciente());
        paciente.setApellidoPaciente(pacienteRequest.getApellidoPaciente());
        paciente.setDniPaciente(pacienteRequest.getDniPaciente());
        paciente.setDireccionPaciente(pacienteRequest.getDireccionPaciente());
        paciente.setFecnacPaciente(LocalDate.parse(pacienteRequest.getFecnacPaciente()));
        Paciente pacienteActualizado = pacienteRepository.save(paciente);
        return pacienteMapper.toResponse(pacienteActualizado);
    }

}
