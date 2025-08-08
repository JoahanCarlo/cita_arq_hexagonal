package com.cita_clinica.repository;

import com.cita_clinica.domain.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
    Optional<Medico> findByNombreMedico(String nombreMedico);
}
