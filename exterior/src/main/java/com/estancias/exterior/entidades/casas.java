/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estancias.exterior.entidades;

import static java.time.temporal.WeekFields.ISO;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


/**
 *
 * @author User
 */
@Entity
public class casas {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id_casa;

    private String calle;
    private Integer numero;
    private String codigo_postal;
    private String ciudad;
    private String pais;
    
   @Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
     private Date fecha_desde;

    @Temporal(TemporalType.DATE)
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha_hasta;

    private Integer tiempo_minimo;
    private Integer tiempo_maximo;
    private Integer precio_habitacion;
    private String tipo_vivienda;
    
    @OneToOne
    private Imagen imagen;
    
    

    public casas() {
    }

    public casas(String id_casa, String calle, Integer numero, String codigo_postal, String ciudad, String pais, Date fecha_desde, Date fecha_hasta, Integer tiempo_minimo, Integer tiempo_maximo, Integer precio_habitacion, String tipo_vivienda, Imagen imagen) {
        this.id_casa = id_casa;
        this.calle = calle;
        this.numero = numero;
        this.codigo_postal = codigo_postal;
        this.ciudad = ciudad;
        this.pais = pais;
        this.fecha_desde = fecha_desde;
        this.fecha_hasta = fecha_hasta;
        this.tiempo_minimo = tiempo_minimo;
        this.tiempo_maximo = tiempo_maximo;
        this.precio_habitacion = precio_habitacion;
        this.tipo_vivienda = tipo_vivienda;
        this.imagen = imagen;
    }

    public String getId_casa() {
        return id_casa;
    }

    public void setId_casa(String id_casa) {
        this.id_casa = id_casa;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFecha_desde() {
        return fecha_desde;
    }

    public void setFecha_desde(Date fecha_desde) {
        this.fecha_desde = fecha_desde;
    }

    public Date getFecha_hasta() {
        return fecha_hasta;
    }

    public void setFecha_hasta(Date fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }

    public Integer getTiempo_minimo() {
        return tiempo_minimo;
    }

    public void setTiempo_minimo(Integer tiempo_minimo) {
        this.tiempo_minimo = tiempo_minimo;
    }

    public Integer getTiempo_maximo() {
        return tiempo_maximo;
    }

    public void setTiempo_maximo(Integer tiempo_maximo) {
        this.tiempo_maximo = tiempo_maximo;
    }

    public Integer getPrecio_habitacion() {
        return precio_habitacion;
    }

    public void setPrecio_habitacion(Integer precio_habitacion) {
        this.precio_habitacion = precio_habitacion;
    }

    public String getTipo_vivienda() {
        return tipo_vivienda;
    }

    public void setTipo_vivienda(String tipo_vivienda) {
        this.tipo_vivienda = tipo_vivienda;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }


    
}
