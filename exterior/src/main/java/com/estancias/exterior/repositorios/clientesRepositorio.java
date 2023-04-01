/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.repositorios;

import com.estancias.exterior.entidades.clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface clientesRepositorio extends JpaRepository<clientes, String> {

    @Query("SELECT cl FROM clientes cl WHERE cl.id_usuario = :id_usuario")
    public clientes buscarPorId(@Param("id_usuario") String id_usuario);
    
    @Query("SELECT cl FROM clientes cl WHERE cl.nombre = :nombre")
    public clientes buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT cl FROM clientes cl WHERE cl.ciudad = :ciudad")
    public clientes buscarPorCiudad(@Param("ciudad") String ciudad);

    @Query("SELECT cl FROM clientes cl WHERE cl.pais = :pais")
    public clientes buscarPorPais(@Param("pais") String pais);

    @Query("SELECT cl FROM clientes cl WHERE cl.calle = :calle")
    public clientes buscarPorDireccion(@Param("calle") String calle);
    
    

}
