package com.gestion.empleados.backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.empleados.backend.modelo.Empleado;


@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado , Long>{

}
