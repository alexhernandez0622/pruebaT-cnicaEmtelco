package com.example.pruebatecnicaemtelco.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identificacion;
    private String nombreCompleto;
    private String genero;
    private String numeroTelefono;
    private String correoElectronico;
}
