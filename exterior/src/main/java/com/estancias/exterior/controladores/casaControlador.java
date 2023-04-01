/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.controladores;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.casas;
import com.estancias.exterior.servicios.casaServicios;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/casa")  //localhost:8080/casa
public class casaControlador {

    @Autowired
    private casaServicios casaServ;

    @GetMapping("/registrar") //localhost:8080/casa/registrar
    public String registrar() {

        return "casa_form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String calle, Integer numero,
            String codigo_postal, String ciudad, String pais, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha_desde,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha_hasta,
            Integer tiempo_minimo, Integer tiempo_maximo, Integer precio_habitacion, String tipo_vivienda, MultipartFile archivo, ModelMap modelo) {

        try {
            casaServ.crearCasa(archivo, calle, numero, codigo_postal, ciudad, pais, fecha_desde, fecha_hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda);

            modelo.put("exito", "casa guardada exitosamente");
        } catch (MIExcepcion ex) {
            //Logger.getLogger(casaControlador.class.getName()).log(Level.SEVERE, null, ex);

            modelo.put("error", ex.getMessage());
            return "casa_form.html";
        }

        return "Index.html";

    }
    
     @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List <casas> casas = casaServ.listarCasas();
        
        modelo.addAttribute("casas", casas);
        
        return "casa_list.html";
    }
    
     @GetMapping("/modificar/{id_casa}")
    public String modificar(@PathVariable String id_casa, ModelMap modelo){
        modelo.put("casa", casaServ.getOne(id_casa));
        
        return "casa_modificar.html";
    }
    
    @PostMapping("/modificar/{id_casa}")
    public String modificar(MultipartFile archivo, @PathVariable String id_casa, String calle, Integer numero, 
            String ciudad, String pais, 
            Date fecha_desde, Date fecha_hasta, Integer precio_habitacion, String tipo_vivienda, ModelMap modelo){
        try {
           casaServ.modificarCasas(archivo, id_casa, calle, numero, ciudad, pais, fecha_desde, fecha_hasta, precio_habitacion, tipo_vivienda);
            
            return "redirect:../lista";
        } catch (MIExcepcion ex) {
            modelo.put("error", ex.getMessage());
            return "casa_modificar.html";
        }
        
    }
    
}
