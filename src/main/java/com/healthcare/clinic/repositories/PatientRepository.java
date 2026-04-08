package com.healthcare.clinic.repositories;

import com.healthcare.clinic.entities.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByNationalID(String nationalID);

    @EntityGraph(attributePaths = {"appointments"})
    List<Patient> findAll();
}
