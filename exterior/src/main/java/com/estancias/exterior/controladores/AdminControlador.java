/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.controladores;

import com.estancias.exterior.entidades.usuario;
import com.estancias.exterior.servicios.usuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
     @Autowired
    private usuarioServicio usuarioServicio;
     
      @GetMapping("/dashboard")
   public String panelAdministrativo(){
       return "panel.html";
   }

    @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {
        List<usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);

        return "usuario_list";
    }
    
     @GetMapping("/modificarRol/{id_usuario}")
    public String cambiarRol(@PathVariable String id_usuario){
        usuarioServicio.cambiarRol(id_usuario);
        
       return "redirect:/admin/usuarios";
    }
    
   
}
