/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Date;

/**
 *
 * @author aariasg
 */
public class Inventario {
    
    private int codigo;
    private String nombre;
    private String descripcion;
    private Date fechaIngreso;
    private float precio;
    private int cantidad;

    public Inventario(int codigo, String nombre, String descripcion, Date fechaIngreso, float precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the fechaIngreso
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }
    
}
