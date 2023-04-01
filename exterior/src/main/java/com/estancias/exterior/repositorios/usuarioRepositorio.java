/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.repositorios;

import com.estancias.exterior.entidades.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface usuarioRepositorio extends JpaRepository<usuario, String> {
    
     @Query("SELECT u FROM usuario u WHERE u.email = :email")
    public usuario buscarPorEmail(@Param("email")String email);
    
    
    @Query("SELECT u FROM usuario u WHERE u.nombre = :nombre")
    public usuario buscarPorNombre(@Param("nombre")String nombre);
}
