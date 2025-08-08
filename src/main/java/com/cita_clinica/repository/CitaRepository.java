package com.cita_clinica.repository;

import com.cita_clinica.domain.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita,Long> {
    List<Cita> findByPaciente_DniPaciente(String dniPaciente);
    List<Cita> findByEstadoCitaTrue();
}
