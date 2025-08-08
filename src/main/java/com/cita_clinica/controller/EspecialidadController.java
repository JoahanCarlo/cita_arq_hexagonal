package com.cita_clinica.controller;

import com.cita_clinica.application.EspecialidadService;
import com.cita_clinica.dto.Especialidad.EspecialidadRequest;
import com.cita_clinica.dto.Especialidad.EspecialidadResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {
    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @PostMapping("/guardar")
    @Operation(summary = "Registrar las especialidades que se van a atender")
    public ResponseEntity<EspecialidadResponse> registrar(@RequestBody EspecialidadRequest
                                                          especialidadRequest){
        return ResponseEntity.ok(especialidadService.registrar(especialidadRequest));
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista de las especialidades")
    public ResponseEntity<List<EspecialidadResponse>> listarEspecialidad(){
        return ResponseEntity.ok(especialidadService.listarEspecialidad());
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar especialidades")
    public ResponseEntity<EspecialidadResponse> actualizarEspecialidad(
            @PathVariable Long id,
            @RequestBody EspecialidadRequest especialidadRequest
    ){
        return ResponseEntity.ok(especialidadService.actualizarEspecialidad(id,especialidadRequest));
    }
}
