/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.controladores;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.casas;
import com.estancias.exterior.entidades.clientes;
import com.estancias.exterior.entidades.estancias;
import com.estancias.exterior.servicios.casaServicios;
import com.estancias.exterior.servicios.clienteServicios;
import com.estancias.exterior.servicios.estanciaServicio;
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
@RequestMapping("/estancia")
public class estanciaControlador {

    @Autowired
    private estanciaServicio estanServ;

    @Autowired
    private clienteServicios clienteServ;

    @Autowired
    private casaServicios casaServ;

    @GetMapping("/registrar") //localhost:8080/estancia/registrar
    public String registrar(ModelMap modelo) {

        List<clientes> clientes = clienteServ.listarClientes();
        List<casas> casas = casaServ.listarCasas();

        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("casas", casas);

        return "estancia_form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String id_usuario,
            String id_casa, String nombre_huesped,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha_desde,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha_hasta, ModelMap modelo) {

        try {
            estanServ.crearEstancia(id_usuario, id_casa, nombre_huesped, fecha_desde, fecha_hasta);

            modelo.put("exito", "Estancia registrada exitosamente");
        } catch (MIExcepcion ex) {

            List<clientes> clientes = clienteServ.listarClientes();
            List<casas> casas = casaServ.listarCasas();
            
             modelo.addAttribute("clientes", clientes);
            modelo.addAttribute("casas", casas);
            
            modelo.put("error", ex.getMessage());
            return "estancia_form.html";
        }

        return "Index.html";
    }
    
     @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<estancias> estancias  = estanServ.listarEstancias();
        modelo.addAttribute("estancias", estancias);

        return "estancia_list.html";
    }
    
     @GetMapping("/modificar/{id_estancia}")
    public String modificar(@PathVariable String id_estancia, ModelMap modelo) {
      
        modelo.put("libro", estanServ.getOne(id_estancia));
        
        List<casas> casas = casaServ.listarCasas();
        List<clientes> clientes = clienteServ.listarClientes();
        
        modelo.addAttribute("casas", casas);
        modelo.addAttribute("clientes", clientes);
        
        return "estancia_modificar.html";
    }
    
     @PostMapping("/modificar/{id_estancia}")
    public String modificar(@PathVariable String id_estancia, String id_usuario,
            String id_casa, String nombre_huesped, Date fecha_desde, Date fecha_hasta, ModelMap modelo, MultipartFile archivo) {
        try {
            List<casas> casas = casaServ.listarCasas();
            List<clientes> clientes = clienteServ.listarClientes();
            
            modelo.addAttribute("casas", casas);
            modelo.addAttribute("clientes", clientes);
            
            estanServ.modificarEstancia(id_estancia, id_usuario, id_casa, nombre_huesped, fecha_desde, fecha_hasta);

           
            
                        
            return "redirect:../lista";

        } catch (MIExcepcion ex) {
            
             List<casas> casas = casaServ.listarCasas();
            List<clientes> clientes = clienteServ.listarClientes();
            
            modelo.addAttribute("casas", casas);
            modelo.addAttribute("clientes", clientes);
            
            
            modelo.put("error", ex.getMessage());
            
            
            
            return "estancia_modificar.html";
        }

    }
    
}
