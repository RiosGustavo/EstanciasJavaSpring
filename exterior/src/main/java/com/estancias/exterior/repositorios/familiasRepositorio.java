/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.repositorios;

import com.estancias.exterior.entidades.familias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *@Repository
public interface clientesRepositorio extends JpaRepository<clientes, String> {
 * @author User
 */
@Repository
public interface familiasRepositorio extends JpaRepository<familias, String> {
    
     @Query("SELECT f FROM familias f WHERE f.id_usuario = :id_usuario")
    public familias buscarPorId(@Param("id_usuario") String id_usuario);
    
     @Query("SELECT f FROM familias f WHERE f.nombre = :nombre")
    public familias buscarPorNombre(@Param("nombre") String nombre);
    
}
