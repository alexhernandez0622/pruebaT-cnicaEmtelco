package com.example.pruebatecnicaemtelco.Controller;

import com.example.pruebatecnicaemtelco.Entity.Cliente;
import com.example.pruebatecnicaemtelco.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController
{
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        // Validar que la identificación no esté registrada previamente
        Cliente clienteExistente = clienteService.obtenerClientePorIdentificacion(cliente.getIdentificacion());
        if (clienteExistente != null) {
            return new ResponseEntity<>("La identificación ya está registrada", HttpStatus.BAD_REQUEST);
        }

        // Validar otros campos según requerimientos
        // (como la longitud del nombre, formato de correo, etc.)

        // Llamar al servicio para crear el cliente
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/{identificacion}")
    public ResponseEntity<?> obtenerClientePorIdentificacion(@PathVariable String identificacion) {
        Cliente cliente = clienteService.obtenerClientePorIdentificacion(identificacion);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }
    // Método para actualizar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizar) {
        Cliente clienteExistente = clienteService.obtenerClientePorIdentificacion(String.valueOf(id));
        if (clienteExistente == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        // Actualizar los campos del cliente
        clienteExistente.setIdentificacion(clienteActualizar.getIdentificacion());
        clienteExistente.setNombreCompleto(clienteActualizar.getNombreCompleto());
        clienteExistente.setGenero(clienteActualizar.getGenero());
        clienteExistente.setNumeroTelefono(clienteActualizar.getNumeroTelefono());
        clienteExistente.setCorreoElectronico(clienteActualizar.getCorreoElectronico());

        // Guardar el cliente actualizado
        Cliente clienteActualizado = clienteService.actualizarCliente(clienteExistente);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    // Método para eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        Cliente clienteExistente = clienteService.obtenerClientePorIdentificacion(String.valueOf(id));
        if (clienteExistente == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        // Eliminar el cliente
        clienteService.eliminarCliente(id);
        return new ResponseEntity<>("Cliente eliminado correctamente", HttpStatus.OK);
    }

}
