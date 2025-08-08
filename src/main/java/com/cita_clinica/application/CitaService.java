package com.cita_clinica.application;

import com.cita_clinica.domain.model.Cita;
import com.cita_clinica.domain.model.Medico;
import com.cita_clinica.domain.model.Paciente;
import com.cita_clinica.dto.Cita.CitaRequest;
import com.cita_clinica.dto.Cita.CitaResponse;
import com.cita_clinica.mapper.CitaMapper;
import com.cita_clinica.repository.CitaRepository;
import com.cita_clinica.repository.MedicoRepository;
import com.cita_clinica.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {
    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public CitaService(CitaRepository citaRepository, CitaMapper citaMapper, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.citaRepository = citaRepository;
        this.citaMapper = citaMapper;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public CitaResponse registrarCita(CitaRequest citaRequest){
        Paciente paciente = pacienteRepository.findByDniPaciente(citaRequest.getDniPaciente())
                .orElseThrow(()->new RuntimeException("No existe el DNI del paciente"));
        Medico medico = medicoRepository.findByNombreMedico(citaRequest.getNombreMedico())
                .orElseThrow(()->new RuntimeException("No existe el nombre del medico"));
        Cita cita = new Cita();
        cita.setFechaCita(citaRequest.getFechaCita());
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setEstadoCita(true);
        Cita citaGuardada = citaRepository.save(cita);
        return citaMapper.toResponse(citaGuardada);
    }

    public List<CitaResponse> listaCitaActivas(){
        return citaRepository.findByEstadoCitaTrue().stream()
                .map(citaMapper::toResponse)
                .toList();
    }

    public CitaResponse actualizarCita(Long id, CitaRequest citaRequest){
        Cita citaExistente = citaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Cita con el N°"+id+" no existe."));
        Paciente paciente = pacienteRepository.findByDniPaciente(citaRequest.getDniPaciente())
                .orElseThrow(()->new RuntimeException("Paciente con dni no encontrado"));
        Medico medico = medicoRepository.findByNombreMedico(citaRequest.getNombreMedico())
                .orElseThrow(()->new RuntimeException("No existe el nombre del médico"));
        citaExistente.setFechaCita(citaRequest.getFechaCita());
        citaExistente.setPaciente(paciente);
        citaExistente.setMedico(medico);
        Cita citaActualizada = citaRepository.save(citaExistente);
        return citaMapper.toResponse(citaActualizada);
    }

    public CitaResponse cancelarCita(Long id){
        Cita cita = citaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No existe el Número :"+id+" de la cita."));
        cita.setEstadoCita(false);
        Cita citaCancelada = citaRepository.save(cita);
        return citaMapper.toResponse(citaCancelada);
    }
}
