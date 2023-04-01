/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.controladores;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.casas;
import com.estancias.exterior.entidades.comentarios;
import com.estancias.exterior.servicios.casaServicios;
import com.estancias.exterior.servicios.comentarioServicio;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/comentario")
public class comentarioControlador {

    @Autowired
    comentarioServicio comenServ;

    @Autowired
    casaServicios casaServ;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        List<casas> casas = casaServ.listarCasas();

        modelo.addAttribute("casas", casas);

        return "comentario_form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String id_casa,
            String comentario, ModelMap modelo) {

        try {
            comenServ.crearComentario(id_casa, comentario);
            modelo.put("exito", "El comentario fue exitosamente registrado");
        } catch (MIExcepcion ex) {
            List<casas> casas = casaServ.listarCasas();

            modelo.addAttribute("casas", casas);
            modelo.put("error", ex.getMessage());
            return "comentario_form.html";  // volvemos a cargar el formulario.

        }
        return "Index.html";

    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<comentarios> comentarios = comenServ.listarComentarios();
        modelo.addAttribute("comentarios", comentarios);

        return "comentario_list.html";
    }
    

}
