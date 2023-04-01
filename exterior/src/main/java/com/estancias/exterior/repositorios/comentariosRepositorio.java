/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.repositorios;

import com.estancias.exterior.entidades.comentarios;
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
public interface comentariosRepositorio extends JpaRepository<comentarios, String>{
    
     @Query("SELECT co FROM comentarios co WHERE co.id_comentario = :id_comentario")
    public comentarios buscarPorId(@Param("id_comentario") String id_comentario);
//    select * from comentarios where id_casa like 1;
//     @Query("SELECT co FROM comentarios co WHERE co.id_casa = :id_casa")
//    public comentarios buscarPorCasa(@Param("id_casa") String id_casa);
    
}
