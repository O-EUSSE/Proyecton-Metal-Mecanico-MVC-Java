/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author aariasg
 */
public class Cliente {
    private int id = 0;
    private String nombre = "";
    private String apellido = "";
    private String direccion = "";
    private double telefono = 0;
    private String email = "";

    public Cliente(int id, String nombre, String apellido, String direccion, double telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
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
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return the telefono
     */
    public double getTelefono() {
        return telefono;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return ""+this.id;
    }
    
    
}
