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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CitaServiceTest {
    @InjectMocks
    private CitaService citaService;

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private CitaMapper citaMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listaCitaActivas() {
    }

    @Test
    void actualizarCita() {
        Long citaId = 1L;
        CitaRequest citaRequest = CitaRequest.builder()
                .dniPaciente("12345678")
                .nombreMedico("Alex Trelles")
                .fechaCita(LocalDate.parse("2026-01-02"))
                .build();
        Cita citaExistente = new Cita();
        Paciente paciente = new Paciente();
        paciente.setDniPaciente("12345678");

        Medico medico = new Medico();
        medico.setNombreMedico("Alex Trelles");

        Cita citaActualizada = new Cita();
        citaActualizada.setId(citaId);
        citaActualizada.setPaciente(paciente);
        citaActualizada.setMedico(medico);
        citaActualizada.setFechaCita(citaRequest.getFechaCita());

        CitaResponse citaResponse = CitaResponse.builder()
                .id(citaId)
                .dniPaciente("12345678")
                .nombreMedico("Alex Trelles")
                .fechaCita(LocalDate.parse("2026-01-02"))
                .estadoCita(true)
                .build();

        when(citaRepository.findById(citaId)).thenReturn(Optional.of(citaExistente));
        when(pacienteRepository.findByDniPaciente("12345678")).thenReturn(Optional.of(paciente));
        when(medicoRepository.findByNombreMedico("Alex Trelles")).thenReturn(Optional.of(medico));
        when(citaRepository.save(any(Cita.class))).thenReturn(citaActualizada);
        when(citaMapper.toResponse(any(Cita.class))).thenReturn(citaResponse);

        CitaResponse citaActual = citaService.actualizarCita(citaId,citaRequest);

        assertNotNull(citaActual);
        assertEquals("12345678",citaActual.getDniPaciente());
        assertEquals("Alex Trelles",citaActual.getNombreMedico());
        assertEquals(LocalDate.parse("2026-01-02"),citaActual.getFechaCita());

        verify(citaRepository).findById(citaId);
        verify(pacienteRepository).findByDniPaciente("12345678");
        verify(medicoRepository).findByNombreMedico("Alex Trelles");
        verify(citaRepository).save(any(Cita.class));
        verify(citaMapper).toResponse(any(Cita.class));

    }
}