/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.servicios;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.Imagen;
import com.estancias.exterior.entidades.casas;
import com.estancias.exterior.repositorios.casasRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author User
 */
@Service
public class casaServicios {

    @Autowired
    private casasRepositorio repoCasa;
    
     @Autowired
    private imagenServicio imagenServicio;

    @Transactional
    public void crearCasa(MultipartFile archivo, String calle, Integer numero, String codigo_postal, String ciudad,
            String pais, Date fecha_desde, Date fecha_hasta, Integer tiempo_minimo, Integer tiempo_maximo,
            Integer precio_habitacion, String tipo_vivienda) throws MIExcepcion {

        validar( ciudad, pais, fecha_desde, fecha_hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda);
        
        casas ca = new casas();

       
        ca.setCalle(calle);
        ca.setNumero(numero);
        ca.setCodigo_postal(codigo_postal);
        ca.setCiudad(ciudad);
        ca.setPais(pais);
        ca.setFecha_desde(fecha_desde);
        ca.setFecha_hasta(fecha_hasta);
        ca.setTiempo_minimo(tiempo_minimo);
        ca.setTiempo_maximo(tiempo_maximo);
        ca.setPrecio_habitacion(precio_habitacion);
        ca.setTipo_vivienda(tipo_vivienda);
        
        Imagen imagen = imagenServicio.guardar(archivo);
        
        ca.setImagen(imagen);

        repoCasa.save(ca);

    }
    
    public List<casas> listarCasas(){
        
        List<casas> casas = new ArrayList();
        
        casas = repoCasa.findAll();
        
        
        return casas;
        
    }
     @Transactional
    public void modificarCasas(MultipartFile archivo,String id_casa, String calle, Integer numero, 
            String ciudad, String pais, 
            Date fecha_desde, Date fecha_hasta, Integer precio_habitacion, String tipo_vivienda) throws MIExcepcion{
        
         validar( ciudad, pais, fecha_desde, fecha_hasta, numero, numero, precio_habitacion, tipo_vivienda);
       
         Optional<casas> respuesta = repoCasa.findById(id_casa);
        
        if (respuesta.isPresent()) {
            
            casas casa = respuesta.get();
            
            casa.setCalle(calle);
            casa.setCiudad(ciudad);
            casa.setNumero(numero);
            casa.setPais(pais);
           
            casa.setFecha_desde(fecha_desde);
            casa.setFecha_hasta(fecha_hasta);
            casa.setPrecio_habitacion(precio_habitacion);
            casa.setTipo_vivienda(tipo_vivienda);
            
            String idImagen = null;
            
            if (casa.getImagen() != null) {

                idImagen = casa.getImagen().getId();

            }
            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);

            casa.setImagen(imagen);
            
            
            repoCasa.save(casa);
        }
        
    }

    public casas getOne(String id_casa){
        return repoCasa.getOne(id_casa);
    }
    
    private void validar( String ciudad,
            String pais, Date fecha_desde, Date fecha_hasta, Integer tiempo_minimo, Integer tiempo_maximo,
            Integer precio_habitacion, String tipo_vivienda) throws MIExcepcion{
        
        
        
        if (precio_habitacion == 0 || precio_habitacion < 0) {
            throw new MIExcepcion("Ingrese precio habitacion");
        } else {
            
        }
        
        if (tipo_vivienda == null || tipo_vivienda.isEmpty()) {
            throw new MIExcepcion("El tipo de vivienda no puede ser nulo");
            
        }
        
        if (ciudad == null || ciudad.isEmpty()) {
            throw new MIExcepcion("Ciudad no puede ser nulo");
            
        }
        
        if (pais == null || pais.isEmpty()) {
            throw new MIExcepcion("Pais no puede ser nulo");
            
        }
        
        if (fecha_desde == null ) {
            throw new MIExcepcion("fecha puede ser nulo");
            
        }
        
        if (fecha_hasta == null ) {
            throw new MIExcepcion("fecha puede ser nulo");
            
        }
        
        if (tiempo_minimo == 0 || tiempo_minimo < 0 ) {
            throw new MIExcepcion("Pais no puede ser nulo");
            
        }
        
        if (tiempo_maximo == 0  || tiempo_minimo < 0) {
            throw new MIExcepcion("Pais no puede ser nulo");
            
        }
        
    }
}
