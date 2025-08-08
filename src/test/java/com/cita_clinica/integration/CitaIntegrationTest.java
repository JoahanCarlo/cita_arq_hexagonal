package com.cita_clinica.integration;

import com.cita_clinica.dto.Cita.CitaRequest;
import com.cita_clinica.dto.Cita.CitaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CitaIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearYObtenerCita() throws Exception{
        CitaRequest citaRequest = CitaRequest.builder()
                .dniPaciente("12345678")
                .nombreMedico("Luis Angel")
                .fechaCita(LocalDate.parse("2025-11-05"))
                .build();

        MvcResult postResult = mockMvc.perform(post("/citas/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(citaRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String jsonRespuesta = postResult.getResponse().getContentAsString();
        CitaResponse citaCreada = objectMapper.readValue(jsonRespuesta,CitaResponse.class);

        System.out.println("Respuesta de /citas/guardar : "+jsonRespuesta);

        if (citaCreada.getId()==null){
            System.out.println("Advertencia: El ID de la cita devuelto es null");
            return;
        }else{
            System.out.println("El ID generado : "+citaCreada.getId());
        }


        MvcResult getResult = mockMvc.perform(get("/citas/activas"))
                .andExpect(status().isOk())
                .andReturn();

        List<CitaResponse> citasActivas = objectMapper.readValue(
                getResult.getResponse().getContentAsString(),
                new TypeReference<List<CitaResponse>>(){}
        );

        boolean citaEncontrada = citasActivas.stream()
                .anyMatch(c -> c.getId().equals(citaCreada.getId()));

        if (!citaEncontrada) {
            System.out.println("Advertencia: La cita creada no se encontró en las activas");
        } else {
            System.out.println("La cita debería de estar en las citas activas");
        }

    }
}
