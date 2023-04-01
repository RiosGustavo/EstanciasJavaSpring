/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.servicios;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.casas;
import com.estancias.exterior.entidades.clientes;
import com.estancias.exterior.entidades.estancias;
import com.estancias.exterior.repositorios.casasRepositorio;
import com.estancias.exterior.repositorios.clientesRepositorio;
import com.estancias.exterior.repositorios.estanciasRepositorio;
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
public class estanciaServicio {

    @Autowired
    private estanciasRepositorio repoEstan;

    @Autowired
    private casasRepositorio repoCasa;

    @Autowired
    private clientesRepositorio repoClien;

    @Transactional
    public void crearEstancia( String id_usuario,
            String id_casa, String nombre_huesped, Date fecha_desde, Date fecha_hasta) throws MIExcepcion {
        
        validar( id_usuario, id_casa, nombre_huesped, fecha_desde, fecha_hasta);
        
        Optional<clientes> respuestaCliente = repoClien.findById(id_usuario);
         Optional<casas> respuestaCasa = repoCasa.findById(id_casa);
                
        clientes cliente = new clientes();
        casas casa = new casas();
        
        if (respuestaCliente.isPresent()) {
            cliente = respuestaCliente.get();
            
        }
        
        if (respuestaCasa.isPresent()) {
            casa = respuestaCasa.get();
            
        }

        estancias estan = new estancias();

        
        estan.setNombre_huesped(nombre_huesped);
        estan.setFecha_desde(fecha_desde);
        estan.setFecha_hasta(fecha_hasta);

        estan.setCasa(casa);
        estan.setCliente(cliente);

        repoEstan.save(estan);

    }

    public List<estancias> listarEstancias() {

        List<estancias> estancia = new ArrayList();

        estancia = repoEstan.findAll();

        return estancia;

    }
@Transactional
    public void modificarEstancia(String id_estancia, String id_usuario,
            String id_casa, String nombre_huesped,Date fecha_desde, Date fecha_hasta) throws MIExcepcion {
        
        validar( id_usuario, id_casa, nombre_huesped, fecha_desde, fecha_hasta);

        Optional<estancias> respuesta = repoEstan.findById(id_estancia);
        Optional<clientes> respuestaCliente = repoClien.findById(id_usuario);
        Optional<casas> respuestaCasa = repoCasa.findById(id_casa);

        clientes cliente = new clientes();
        casas casa = new casas();

        if (respuestaCliente.isPresent()) {

            cliente = respuestaCliente.get();

        }

        if (respuestaCasa.isPresent()) {

            casa = respuestaCasa.get();

        }

        if (respuesta.isPresent()) {
            estancias estancia = respuesta.get();

            estancia.setNombre_huesped(nombre_huesped);
            estancia.setCliente(cliente);
            estancia.setCasa(casa);

            repoEstan.save(estancia);

        }

    }

      public estancias getOne(String id_estancia){
       return repoEstan.getOne(id_estancia);
    }
    
    private void validar( String id_usuario,
            String id_casa, String nombre_huesped, Date fecha_desde, Date fecha_hasta) throws MIExcepcion {

      

        if (id_casa == null) {
            throw new MIExcepcion("El id de casa no puede ser nulo");
        }

        if (id_usuario == null) {
            throw new MIExcepcion("El id de cliente no puede ser nulo");
        }

        if (nombre_huesped == null || nombre_huesped.isEmpty()) {
            throw new MIExcepcion("El nombre_huesped no puede ser nulo");

        }

        if (fecha_desde == null) {
            throw new MIExcepcion("fecha puede ser nulo");

        }

        if (fecha_hasta == null) {
            throw new MIExcepcion("fecha puede ser nulo");

        }

    }

}
