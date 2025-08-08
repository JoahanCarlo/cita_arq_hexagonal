package com.cita_clinica.controller;

import com.cita_clinica.application.MedicoService;
import com.cita_clinica.dto.Medico.MedicoRequest;
import com.cita_clinica.dto.Medico.MedicoResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping("/guardar")
    @Operation(summary = "Registrar a los medicos que van a atender a los pacientes")
    public ResponseEntity<MedicoResponse> registrar(@RequestBody MedicoRequest medicoRequest){
        return ResponseEntity.ok(medicoService.registrarMedico(medicoRequest));
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar a los medicos que van a atender a los pacientes")
    public ResponseEntity<List<MedicoResponse>> listarMedico(){
        return ResponseEntity.ok(medicoService.listarMedico());
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Listar a los medicos que van a atender a los pacientes")
    public ResponseEntity<MedicoResponse> actualizarMedico(
            @PathVariable Long id,
            @RequestBody MedicoRequest medicoRequest
    ){
        return medicoService.actualizarMedico(id,medicoRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
