package com.cita_clinica.mapper;

import com.cita_clinica.domain.model.Paciente;
import com.cita_clinica.dto.Paciente.PacienteRequest;
import com.cita_clinica.dto.Paciente.PacienteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteMapperTest {
    private PacienteMapper pacienteMapper;

    @BeforeEach
    void setUp(){
        pacienteMapper = new PacienteMapperImpl();
    }

    @Test
    void toResponse() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setDniPaciente("12345678");
        paciente.setNombrePaciente("Marco Andre");
        paciente.setApellidoPaciente("Nuñez Soto");
        paciente.setFecnacPaciente(LocalDate.parse("1996-12-12"));

        PacienteResponse pacienteResponse = pacienteMapper.toResponse(paciente);
        assertNotNull(pacienteResponse);
        assertEquals(1L,pacienteResponse.getId());
        assertEquals("12345678",pacienteResponse.getDniPaciente());
        assertEquals("Marco Andre Nuñez Soto",pacienteResponse.getNomPaciente());
        assertEquals(LocalDate.parse("1996-12-12"),pacienteResponse.getFecnacPaciente());
    }

    @Test
    void toModel() {
        PacienteRequest pacienteRequest = new PacienteRequest();
        pacienteRequest.setDniPaciente("12345678");
        pacienteRequest.setNombrePaciente("Luis Angel");
        pacienteRequest.setApellidoPaciente("Pinasco Torres");
        pacienteRequest.setDireccionPaciente("Av. Luzuriaga");
        pacienteRequest.setFecnacPaciente("2000-02-02");

        Paciente paciente = pacienteMapper.toModel(pacienteRequest);
        assertNotNull(paciente);
        assertEquals("12345678",paciente.getDniPaciente());
        assertEquals("Luis Angel",paciente.getNombrePaciente());
        assertEquals("Pinasco Torres",paciente.getApellidoPaciente());
        assertEquals("Av. Luzuriaga",paciente.getDireccionPaciente());
        assertEquals(LocalDate.parse("2000-02-02"),paciente.getFecnacPaciente());
    }
}