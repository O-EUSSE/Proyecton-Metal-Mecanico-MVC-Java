/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.FileInputStream;

/**
 *
 * @author aariasg
 */
public class Empleado {
    
    private int id;
    private String nombre;
    private String apellido;
    private String cargo;
    private double salario;
    private float telefono;
    private String direccion;
    private String email;
    private FileInputStream  imagen;
    private int longitudFoto;

    public Empleado(int id, String nombre, String apellido, String cargo, double salario, float telefono, String direccion, String email, FileInputStream imagen, int longitudFoto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.imagen = imagen;
        this.longitudFoto = longitudFoto;
    }
    
    public Empleado(int id, String nombre, String apellido, String cargo, double salario, float telefono, String direccion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }
    

   

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @return the telefono
     */
    public float getTelefono() {
        return telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the imagen
     */
    public FileInputStream getImagen() {
        return imagen;
    }

    public int getLongitudFoto() {
        return longitudFoto;
    }
    
    @Override
    public String toString() {
        return ""+this.id;
    }
    
}
