/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author User
 */
@Entity
public class familias extends usuario{

     
    private int edad_minima;
    private int edad_maxima;
    private int num_hijos;
  
    @OneToOne
    private casas casas;
    

    public familias() {
    }

//    public familias(String id, String nombre, int edad_minima, int edad_maxima, int num_hijos, String email, casas casas) {
//        this.id = id;
//        this.nombre = nombre;
//        this.edad_minima = edad_minima;
//        this.edad_maxima = edad_maxima;
//        this.num_hijos = num_hijos;
//        this.email = email;
//        this.casas = casas;
//    }
    
    


    public int getEdad_minima() {
        return edad_minima;
    }

    public void setEdad_minima(int edad_minima) {
        this.edad_minima = edad_minima;
    }

    public int getEdad_maxima() {
        return edad_maxima;
    }

    public void setEdad_maxima(int edad_maxima) {
        this.edad_maxima = edad_maxima;
    }

    public int getNum_hijos() {
        return num_hijos;
    }

    public void setNum_hijos(int num_hijos) {
        this.num_hijos = num_hijos;
    }

   

    public casas getCasas() {
        return casas;
    }

    public void setCasas(casas casas) {
        this.casas = casas;
    }

    

    

   
}
