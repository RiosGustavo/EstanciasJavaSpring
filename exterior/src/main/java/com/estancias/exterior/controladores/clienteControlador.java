/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.controladores;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.clientes;
import com.estancias.exterior.servicios.clienteServicios;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/cliente")
public class clienteControlador {

    @Autowired
    private clienteServicios clienServ;

    @GetMapping("/registrar")
    public String registrar() {

        return "cliente_form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String nombre, String calle,
            Integer numero, String codigo_postal, String ciudad, String pais, String email, ModelMap modelo) {

        try {
            clienServ.crearCliente(nombre, calle, numero, codigo_postal, ciudad, pais, email);
            modelo.put("exito", "Cliente creado Exitosamente");

        } catch (MIExcepcion ex) {
            // Logger.getLogger(clienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "cliente_form.html";
        }
        return "Index.html";

    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List <clientes> clientes = clienServ.listarClientes();
        
        modelo.addAttribute("clientes", clientes);
        
        return "cliente_list.html";
    }

     @GetMapping("/modificar/{id_usuario}")
    public String modificar(@PathVariable String id_usuario, ModelMap modelo){
        modelo.put("cliente", clienServ.getOne(id_usuario));
        
        return "cliente_modificar.html";
    }
    
    @PostMapping("/modificar/{id_usuario}")
    public String modificar(@PathVariable String id_usuario, String nombre, String calle, Integer numero,
            String ciudad, String pais, String email, ModelMap modelo){
        try {
            clienServ.modificacrClientes(id_usuario, nombre, calle, numero, ciudad, pais, email);
            
            return "redirect:../lista";
        } catch (MIExcepcion ex) {
            modelo.put("error", ex.getMessage());
            return "cliente_modificar.html";
        }
        
    }
}
