/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.repositorios;

import com.estancias.exterior.entidades.casas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface casasRepositorio extends JpaRepository<casas, String> {

    @Query("SELECT c FROM casas c WHERE c.id_casa = :id_casa")
    public casas buscarPorId(@Param("id_casa") String id_casa);

//    @Query("SELECT c FROM casas c WHERE c.ciudad = :ciudad")
//    public casas buscarPorCiudad(@Param("ciudad") String ciudad);
//
//    @Query("SELECT c FROM casas c WHERE c.pais = :pais")
//    public casas buscarPorPais(@Param("pais") String pais);
//
//    @Query("SELECT c FROM casas c WHERE c.calle = :calle")
//    public casas buscarPorDireccion(@Param("calle") String calle);
//
//    @Query("SELECT c FROM casas c WHERE c.precio_habitacion = :precio_habitacion")
//    public casas buscarPorPrecio(@Param("precio_habitacion") Double precio_habitacion);
//
//    @Query("SELECT c FROM casas c WHERE c.tipo_vivienda = :tipo_vivienda")
//    public casas buscarPorTipoVivienda(@Param("tipo_vivienda") String tipo_vivienda);

}
