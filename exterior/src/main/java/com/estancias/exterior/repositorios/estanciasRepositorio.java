/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.repositorios;

import com.estancias.exterior.entidades.estancias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 **@Repository
public interface clientesRepositorio extends JpaRepository<clientes, String> {
 * @author User
 */
@Repository
public interface estanciasRepositorio extends JpaRepository<estancias, String>{
    
    @Query("SELECT e FROM estancias e WHERE e.nombre_huesped = :nombre_huesped")
    public estancias buscarPorNombreHuesped(@Param("nombre_huesped") String nombre_huesped);
    
    
    
}
