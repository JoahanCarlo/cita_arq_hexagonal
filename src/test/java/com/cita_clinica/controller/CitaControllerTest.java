package com.cita_clinica.controller;

import com.cita_clinica.application.CitaService;
import com.cita_clinica.dto.Cita.CitaRequest;
import com.cita_clinica.dto.Cita.CitaResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CitaControllerTest {
    @Mock
    private CitaService citaService;

    @InjectMocks
    private CitaController citaController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarCitasActivas() {
        CitaResponse citaResponse = CitaResponse.builder()
                .id(1L)
                .fechaCita(LocalDate.parse("2025-11-25"))
                .dniPaciente("12345678")
                .nomPaciente("Andres Lucia")
                .nombreMedico("Andres Miguel")
                .nombreEspecialidad("Pediatría")
                .estadoCita(true)
                .build();
        when(citaService.listaCitaActivas()).thenReturn(Arrays.asList(citaResponse));
        List<CitaResponse> response = citaController.listarCitasActivas();
        assertEquals(1,response.size());
        assertEquals("Andres Lucia",response.get(0).getNomPaciente());
        verify(citaService,times(1)).listaCitaActivas();
    }

    @Test
    void actualizarCita() {
        Long citaId = 1L;
        CitaRequest citaRequest = CitaRequest.builder()
                .dniPaciente("12345678")
                .nombreMedico("Andres Miguel")
                .fechaCita(LocalDate.parse("2025-12-12"))
                .build();
        CitaResponse citaResponse1 = CitaResponse.builder()
                .id(citaId)
                .dniPaciente("12345678")
                .nomPaciente("Andres Lucia")
                .nombreMedico("Andres Miguel")
                .nombreEspecialidad("Peditría")
                .estadoCita(true)
                .build();
        when(citaService.actualizarCita(eq(citaId),any(CitaRequest.class))).thenReturn(citaResponse1);

        ResponseEntity<CitaResponse> response = citaController.actualizarCita(citaId,citaRequest);
        CitaResponse actual = response.getBody();

        assertNotNull(actual);
        assertEquals(citaId,actual.getId());
        assertEquals("12345678",actual.getDniPaciente());
        assertEquals("Andres Miguel",actual.getNombreMedico());
    }
}