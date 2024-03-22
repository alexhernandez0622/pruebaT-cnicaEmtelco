package com.example.pruebatecnicaemtelco.Service;

import com.example.pruebatecnicaemtelco.Entity.Cliente;
import com.example.pruebatecnicaemtelco.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService
{
    @Autowired
    private ClienteRepository clienteRepository;
    private final String API_URL = "https://gorest.co.in/public/v2/users";

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorIdentificacion(String identificacion) {
        return clienteRepository.findByIdentificacion(identificacion);
    }
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
