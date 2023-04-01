/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.servicios;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.casas;
import com.estancias.exterior.entidades.familias;
import com.estancias.exterior.repositorios.casasRepositorio;
import com.estancias.exterior.repositorios.familiasRepositorio;
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
public class familiaServicio {

    @Autowired
    private familiasRepositorio repoFamili;

    @Autowired
    private casasRepositorio repoCasa;

    @Transactional
    public void crearFamilia( String nombre, int edad_minima,
            int edad_maxima, int num_hijos, String email, String id_casa) throws MIExcepcion {

        validar( nombre, edad_minima, edad_maxima, num_hijos, email, id_casa);

        casas casa = repoCasa.findById(id_casa).get();

        familias famili = new familias();

        
        famili.setNombre(nombre);
        famili.setEdad_minima(edad_minima);
        famili.setEdad_maxima(edad_maxima);
        famili.setNum_hijos(num_hijos);
        famili.setEmail(email);
        famili.setCasas(casa);
        
        famili.setFechaAlta(new Date());

        repoFamili.save(famili);

    }

    public List<familias> listarFamilias() {

        List<familias> familia = new ArrayList();

        familia = repoFamili.findAll();

        return familia;

    }

    @Transactional
    public void modificarFamilia(String id_usuario, String nombre, int num_hijos, String email, String id_casa) throws MIExcepcion {

        validar( nombre, num_hijos, num_hijos, num_hijos, email, id_casa);

        Optional<familias> respuesta = repoFamili.findById(id_usuario);
        Optional<casas> respuestaCasa = repoCasa.findById(id_casa);

        casas casa = new casas();

        if (respuestaCasa.isPresent()) {

            casa = respuestaCasa.get();

        }

        if (respuesta.isPresent()) {
            familias familia = respuesta.get();

            familia.setNombre(nombre);
            familia.setNum_hijos(num_hijos);
            familia.setEmail(email);
            familia.setCasas(casa);

            repoFamili.save(familia);

        }

    }
    
    public familias getOne(String isbn){
       return repoFamili.getOne(isbn);
    }

    private void validar( String nombre, int edad_minima,
            int edad_maxima, int num_hijos, String email, String id_casa) throws MIExcepcion {

      

        if (id_casa == null) {
            throw new MIExcepcion("El id de la casa  no puede ser nulo");
        }

        if (edad_minima == 0 || edad_minima < 0) {
            throw new MIExcepcion("El nombre no puede ser nulo");

        }

        if (edad_maxima == 0 || edad_maxima < 0) {
            throw new MIExcepcion("El nombre no puede ser nulo");

        }

        if (num_hijos == 0 || num_hijos < 0) {
            throw new MIExcepcion("El nombre no puede ser nulo");

        }

        if (email == null || email.isEmpty()) {
            throw new MIExcepcion("email puede ser nulo");

        }

    }

}
