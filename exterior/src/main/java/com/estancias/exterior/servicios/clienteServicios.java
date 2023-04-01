/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.servicios;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.clientes;
import com.estancias.exterior.enumeraciones.Rol;
import com.estancias.exterior.repositorios.clientesRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class clienteServicios {

    @Autowired
    private clientesRepositorio repoCliente;

    @Transactional
    public void crearCliente(String nombre,
            String calle, int numero, String codigo_postal,
            String ciudad, String pais, String email) throws MIExcepcion {

        validar(nombre, calle, codigo_postal, ciudad, pais, email);

        clientes cli = new clientes();

        cli.setNombre(nombre);
        cli.setCalle(calle);
        cli.setNumero(numero);
        cli.setCodigo_postal(codigo_postal);
        cli.setCiudad(ciudad);
        cli.setPais(pais);
        cli.setEmail(email);
        cli.setRol(Rol.USER);

        repoCliente.save(cli);

    }

    public List<clientes> listarClientes() {

        List<clientes> cliente = new ArrayList();

        cliente = repoCliente.findAll();

        return cliente;

    }

    @Transactional
    public void modificacrClientes(String id_cliente, String nombre, String calle, int numero,
            String ciudad, String pais, String email) throws MIExcepcion {

        validar(nombre, calle, ciudad, ciudad, pais, email);
        Optional<clientes> respuesta = repoCliente.findById(id_cliente);

        if (respuesta.isPresent()) {

            clientes cliente = respuesta.get();

            cliente.setNombre(nombre);
            cliente.setCalle(calle);
            cliente.setNumero(numero);
            cliente.setCiudad(ciudad);
            cliente.setPais(pais);
            cliente.setEmail(email);

            repoCliente.save(cliente);
        }
    }

    public clientes getOne(String id_usuario){
        return repoCliente.getOne(id_usuario);
    }
    
    @org.springframework.transaction.annotation.Transactional
    public void cambiarRol(String id_usuario) {
        Optional<clientes> respuesta = repoCliente.findById(id_usuario);

        if (respuesta.isPresent()) {

           clientes cliente = respuesta.get();

            if (cliente.getRol().equals(Rol.USER)) {

                cliente.setRol(Rol.ADMIN);

            } else if (cliente.getRol().equals(Rol.ADMIN)) {

                cliente.setRol(Rol.USER);

            }

        }
    }
    
    
    private void validar(String nombre,
            String calle, String codigo_postal,
            String ciudad, String pais, String email) throws MIExcepcion {

        if (nombre == null || nombre.isEmpty()) {
            throw new MIExcepcion("El nombre no puede ser nulo");

        }

        if (ciudad == null || ciudad.isEmpty()) {
            throw new MIExcepcion("Ciudad no puede ser nulo");

        }

        if (pais == null || pais.isEmpty()) {
            throw new MIExcepcion("Pais no puede ser nulo");

        }

        if (calle == null || calle.isEmpty()) {
            throw new MIExcepcion("Calle puede ser nulo");

        }

        if (codigo_postal == null || codigo_postal.isEmpty()) {
            throw new MIExcepcion("codigo_postal puede ser nulo");

        }

        if (email == null || email.isEmpty()) {
            throw new MIExcepcion("email puede ser nulo");

        }

    }

}
