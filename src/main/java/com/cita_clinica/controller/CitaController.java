package com.cita_clinica.controller;

import com.cita_clinica.application.CitaService;
import com.cita_clinica.dto.Cita.CitaRequest;
import com.cita_clinica.dto.Cita.CitaResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/guardar")
    @Operation(summary = "Registrar las citas reservadas por los pacientes")
    public ResponseEntity<CitaResponse> registrar(@RequestBody CitaRequest citaRequest){
        return ResponseEntity.ok(citaService.registrarCita(citaRequest));
    }

    @GetMapping("/activas")
    @Operation(summary = "Lista de citas reservadas y activas")
    public List<CitaResponse> listarCitasActivas(){
        return citaService.listaCitaActivas();
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar las citas reservadas")
    public ResponseEntity<CitaResponse> actualizarCita(
            @PathVariable Long id,
            @RequestBody CitaRequest citaRequest
    ){
        CitaResponse citaActualizada = citaService.actualizarCita(id,citaRequest);
        return ResponseEntity.ok(citaActualizada);
    }

    @PutMapping("/cancelar/{id}")
    @Operation(summary = "Cancelar las citas reservadas")
    public ResponseEntity<CitaResponse> cancelarCitas(Long id){
        CitaResponse citaCancelada = citaService.cancelarCita(id);
        return ResponseEntity.ok(citaCancelada);
    }
}
