/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.servicios;

import com.estancias.exterior.Excepciones.MIExcepcion;
import com.estancias.exterior.entidades.Imagen;
import com.estancias.exterior.entidades.usuario;
import com.estancias.exterior.enumeraciones.Rol;
import com.estancias.exterior.repositorios.usuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContext;


/**
 *
 * @author User
 */
@Service
public class usuarioServicio implements UserDetailsService {

    
    
    @Autowired
    private usuarioRepositorio usuarioRepositorio;
    
     @Autowired
    private imagenServicio imagenServicio;

    @Transactional
    public void crearUsuario(MultipartFile archivo, String nombre, String email, String password, String password2 ) throws MIExcepcion {

        validar(nombre, email, password, password2);

        usuario usu = new usuario();

        usu.setNombre(nombre);
        usu.setEmail(email);
        usu.setPassword(password);
        usu.setFechaAlta(new Date());

        /// con estos comandos encritamos el password atreaves del metodo hecho en securityweb clase
        usu.setPassword(new BCryptPasswordEncoder().encode(password));

        /// aca le eindicamos al programa que por defecto cada usuario qeu se registre se ha del tipo
        // usduario y no administrador
        usu.setRol(Rol.USER);

        Imagen imagen = imagenServicio.guardar(archivo);

        usu.setImagen(imagen);

        usuarioRepositorio.save(usu);

    }
    
    @Transactional(readOnly=true)
    public List<usuario> listarUsuarios() {

        List<usuario> usuarios = new ArrayList();

        usuarios = usuarioRepositorio.findAll();

        return usuarios;

    }

    @Transactional
    public void modificarUsuario(MultipartFile archivo, String id_usuario, String nombre, String email, String password, String password2) throws MIExcepcion {

        validar(nombre, email, password, password2);

        Optional<usuario> respuesta = usuarioRepositorio.findById(id_usuario);

        if (respuesta.isPresent()) {
            
            usuario usu = respuesta.get();

            usu.setNombre(nombre);
            usu.setEmail(email);
            /// con estos comandos encritamos el password atreaves del metodo hecho en securityweb clase
            usu.setPassword(new BCryptPasswordEncoder().encode(password));

            /// aca le eindicamos al programa que por defecto cada usuario qeu se registre se ha del tipo
            // usduario y no administrador
            usu.setRol(Rol.USER);

            String idImagen = null;

            if (usu.getImagen() != null) {

                idImagen = usu.getImagen().getId();

            }

            Imagen img = imagenServicio.actualizar(archivo, idImagen);

            usu.setImagen(img);

            usuarioRepositorio.save(usu);

        }
    }

    public usuario getOne(String id_usuario) {
        return usuarioRepositorio.getOne(id_usuario);
    }

   

    @org.springframework.transaction.annotation.Transactional
    public void cambiarRol(String id_usuario) {
        Optional<usuario> respuesta = usuarioRepositorio.findById(id_usuario);

        if (respuesta.isPresent()) {

            usuario usuario = respuesta.get();

            if (usuario.getRol().equals(Rol.USER)) {

                usuario.setRol(Rol.ADMIN);

            } else if (usuario.getRol().equals(Rol.ADMIN)) {

                usuario.setRol(Rol.USER);

            }

        }
    }

    private void validar(String nombre, String email, String password,String password2) throws MIExcepcion {

        if (nombre == null || nombre.isEmpty()) {
            throw new MIExcepcion("nombre puede ser nulo");

        }

        if (email == null || email.isEmpty()) {
            throw new MIExcepcion("email puede ser nulo");

        }

        if (password == null || password.isEmpty()) {
            throw new MIExcepcion("password puede ser nulo");

        }
        
         if (!password.equals(password2)) {
            throw new MIExcepcion("Las contraseñas ingresadas deben ser iguales");
        }

        

    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getNombre(), usuario.getPassword(), permisos);
        } else {
            return null;
        }

    }

    
}
