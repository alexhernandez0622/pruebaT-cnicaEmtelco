package com.example.pruebatecnicaemtelco.Repository;

import com.example.pruebatecnicaemtelco.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>
{
    Cliente findByIdentificacion(String identificacion);
}
