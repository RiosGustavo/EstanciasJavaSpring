/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.controladores;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.casas;
import com.estancias.exterior.entidades.familias;
import com.estancias.exterior.servicios.casaServicios;
import com.estancias.exterior.servicios.familiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/familia")
public class familiaControlador {
    
    @Autowired
    private familiaServicio famiServ;
    
    @Autowired
    private casaServicios casaServ;
    
    @GetMapping("/registrar") //localhost:8080/casa/registrar
    public String registrar(ModelMap modelo) {
        List<casas> casas =casaServ.listarCasas();
        
        modelo.addAttribute("casas", casas);

        return "familia_form.html";

    }
     @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String nombre, int edad_minima,
            int edad_maxima, int num_hijos, String email, String id_casa, ModelMap modelo) {
        
        try {
            famiServ.crearFamilia(nombre, edad_minima, edad_maxima, num_hijos, email, id_casa);
            
            modelo.put("exito", "familia guardada exitosamente");
        } catch (MIExcepcion ex) {
            List<casas>  casas =casaServ.listarCasas();
            
            modelo.addAttribute("casas", casas);
            modelo.put("error", ex.getMessage());
            
            return "familia_form.html";
        }
        return "Index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<familias> familias = famiServ.listarFamilias();
        modelo.addAttribute("familias", familias);

        return "familia_list.html";
    }
    
     @GetMapping("/modificar/{id_usuario}")
    public String modificar(@PathVariable String id_usuario, ModelMap modelo) {
      
        modelo.put("familias", famiServ.getOne(id_usuario));
        
        List<casas> casas = casaServ.listarCasas();
        
        
        modelo.addAttribute("casas", casas);
        
        
        return "familia_modificar.html";
    }

    @PostMapping("/modificar/{id_usuario}")
    public String modificar(@PathVariable String id_usuario, String nombre, 
            int num_hijos, String email, String id_casa, ModelMap modelo, MultipartFile archivo) {
        try {
            List<casas> casas = casaServ.listarCasas();
           
            
            modelo.addAttribute("casas", casas);
            
            
            famiServ.modificarFamilia(id_usuario, nombre, num_hijos, email, id_casa);
           

                     
                        
            return "redirect:../lista";

        } catch (MIExcepcion ex) {
             List<casas> casas =casaServ.listarCasas();
            
            
           
            
            modelo.addAttribute("casas", casas);
            
             modelo.put("error", ex.getMessage());
            
            
            return "familia_modificar.html";
        }

    }
    
    
}
