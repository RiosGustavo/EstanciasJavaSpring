/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.servicios;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.casas;
import com.estancias.exterior.entidades.comentarios;
import com.estancias.exterior.repositorios.casasRepositorio;
import com.estancias.exterior.repositorios.comentariosRepositorio;
import java.util.ArrayList;
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
public class comentarioServicio {

    @Autowired
    private comentariosRepositorio repoComen;

    @Autowired
    private casasRepositorio repoCasa;

    @Transactional
    public void crearComentario(String id_casa, String comentario) throws MIExcepcion {
        
        validar(id_casa,  comentario);
        Optional<casas> respuestaCasa = repoCasa.findById(id_casa);
        
        casas casa= new casas();

        if (respuestaCasa.isPresent()) {
            
            casa = respuestaCasa.get();
            
        }

        comentarios comen = new comentarios();

        comen.setComentario(comentario);

        comen.setCasa(casa);

        repoComen.save(comen);

    }

    public List<comentarios> listarComentarios() {

        List<comentarios> comentarios = new ArrayList();

        comentarios = repoComen.findAll();

        return comentarios;

    }
    
     public comentarios getOne(String id_comentario){
       return repoComen.getOne(id_comentario);
              
    }
    
    private void validar(String id_casa, String comentario) throws MIExcepcion{
       
        if(id_casa == null){
            throw new MIExcepcion("el id de la casa  no puede ser nulo"); //
        }
        if(comentario.isEmpty() || comentario == null){
            throw new MIExcepcion("el Comentario no puede ser nulo o estar vacio");
        }
       
    }

}
