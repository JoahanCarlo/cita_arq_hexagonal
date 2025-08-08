package com.cita_clinica.controller;

import com.cita_clinica.application.PacienteService;
import com.cita_clinica.dto.Paciente.PacienteRequest;
import com.cita_clinica.dto.Paciente.PacienteResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/guardar")
    @Operation(summary = "Registrar a los pacientes que van a reservar consulta")
    public ResponseEntity<PacienteResponse> registrar(@RequestBody PacienteRequest pacienteRequest){
        return ResponseEntity.ok(pacienteService.registrar(pacienteRequest));
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista de pacientes que van a reservar consulta")
    public ResponseEntity<List<PacienteResponse>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar pacientes que han reservado cita medica")
    public ResponseEntity<PacienteResponse> actualizarPaciente(
            @PathVariable Long id,
            @RequestBody PacienteRequest pacienteRequest
    ){
        return ResponseEntity.ok(pacienteService.actualizarPaciente(id,pacienteRequest));
    }
}
