/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author aariasg
 */
public class Gestor {

    Conexion conex = new Conexion();

    public boolean agregarNuevoCliente(Cliente cliente) {
        conex.crearConexion();
        boolean respuesta = conex.agregarNuevoCliente(cliente);
        conex.cerrarConexion();
        return respuesta;
    }

    public boolean probarConexion() {
        if (conex.crearConexion()) {
            System.out.println("Conectado");
            return true;
        } else {
            System.out.println("No conectado");
        }
        return false;
    }

    public boolean agregarNuevoEmpleado(Empleado empleado) {
        conex.crearConexion();
        boolean respuesta = conex.agregarNuevoEmpleado(empleado);
        conex.cerrarConexion();
        return respuesta;
    }

    public ArrayList<Empleado> cargarEmpleados() {
        conex.crearConexion();
        ArrayList<Empleado> empleados = conex.cargarEmpleados();
        conex.cerrarConexion();
        return empleados;
    }

    public ArrayList<Empleado> cargarEmpleadosTabla(int id) {
        conex.crearConexion();
        ArrayList<Empleado> empleados = conex.cargarEmpleadosTabla(id);
        conex.cerrarConexion();
        return empleados;
    }

    public InputStream cargarFotoEmpleado(int id) {
        conex.crearConexion();
        InputStream datos = conex.cargarFotoEmpleado(id);
        conex.cerrarConexion();
        return datos;
    }

    public ArrayList<Cliente> cargarClientes(int id) {
        conex.crearConexion();
        ArrayList<Cliente> clientes = conex.cargarClientes(id);
        conex.cerrarConexion();
        return clientes;
    }
    
     public ArrayList<Cliente> cargarClientes() {
        conex.crearConexion();
        ArrayList<Cliente> clientes = conex.cargarClientes();
        conex.cerrarConexion();
        return clientes;
    }


    public boolean elieminarCliente(int id) {
        conex.crearConexion();
        boolean resultado = conex.eliminarCliente(id);
        conex.cerrarConexion();
        return resultado;
    }

    public boolean actualizarCliente(Cliente cli) {
        conex.crearConexion();
        boolean resultado = conex.actulizarCliente(cli);
        conex.cerrarConexion();
        return resultado;
    }

    public boolean actulizarEmpleado(Empleado emple) {
        conex.crearConexion();
        boolean resultado = conex.modificarEmpleado(emple);
        conex.cerrarConexion();
        return resultado;
    }

    public boolean elieminarEmpleado(int id) {
        conex.crearConexion();
        boolean resultado = conex.eliminarEmpleado(id);
        conex.cerrarConexion();
        return resultado;
    }

    public boolean agregarInventario(Inventario inv) {
        conex.crearConexion();
        boolean respuesta = conex.agregarInventario(inv);
        conex.cerrarConexion();
        return respuesta;
    }

    public ArrayList<Inventario> cargarInventario() {
        conex.crearConexion();
        ArrayList<Inventario> inventarios = conex.cargarInventario();
        conex.cerrarConexion();
        return inventarios;
    }

    public ArrayList<Inventario> cargarInventarioTabla(int cod) {
        conex.crearConexion();
        ArrayList<Inventario> inventarios = conex.cargarInventarioTabla(cod);
        conex.cerrarConexion();
        return inventarios;
    }

    public boolean actulizarInventario(Inventario inv) {
        conex.crearConexion();
        boolean resultado = conex.modificarInventario(inv);
        conex.cerrarConexion();
        return resultado;
    }

    public boolean elieminarInvetario(int cod) {
        conex.crearConexion();
        boolean resultado = conex.eliminarInventario(cod);
        conex.cerrarConexion();
        return resultado;
    }

    public boolean agregarNuevoPedido(Pedido ped) {
        conex.crearConexion();
        boolean respuesta = conex.agregarPedido(ped);
        conex.cerrarConexion();
        return respuesta;
    }
    
    public ArrayList<Pedido> cargarPedidos() {
        conex.crearConexion();
        ArrayList<Pedido> pedidos = conex.cargarPedidos();
        conex.cerrarConexion();
        return pedidos;
    }
}
