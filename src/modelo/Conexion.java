/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author aariasg
 */
public class Conexion {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/TallerMetalMecanico";
    private String usuario = "root";
    private String contraseña = "";

    private Connection conexion;
    private PreparedStatement consulta;

    public boolean crearConexion() {
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean agregarNuevoCliente(Cliente cliente) {

        try {
            consulta = conexion.prepareStatement("INSERT INTO Cliente (cli_id, cli_nombre, cli_apellido, cli_direccion, cli_telefono, cli_email) VALUES (?,?,?,?,?,?)");
            consulta.setInt(1, cliente.getId());
            consulta.setString(2, cliente.getNombre());
            consulta.setString(3, cliente.getApellido());
            consulta.setString(4, cliente.getDireccion());
            consulta.setDouble(5, cliente.getTelefono());
            consulta.setString(6, cliente.getEmail());
            consulta.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }

    public boolean agregarNuevoEmpleado(Empleado empleado) {
        try {
            consulta = conexion.prepareStatement("INSERT INTO Empleado (emp_id, emp_nombre, emp_apellido, emp_cargo, emp_salario, emp_telefono, emp_direccion, emp_email, emp_foto) VALUES (?,?,?,?,?,?,?,?,?)");
            consulta.setInt(1, empleado.getId());
            consulta.setString(2, empleado.getNombre());
            consulta.setString(3, empleado.getApellido());
            consulta.setString(4, empleado.getCargo());
            consulta.setDouble(5, empleado.getSalario());
            consulta.setFloat(6, empleado.getTelefono());
            consulta.setString(7, empleado.getDireccion());
            consulta.setString(8, empleado.getEmail());
            consulta.setBlob(9, empleado.getImagen(), empleado.getLongitudFoto());
            consulta.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public ArrayList<Empleado> cargarEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        ResultSet re = null;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Empleado");
            re = consulta.executeQuery();
            while (re.next()) {
                empleados.add(new Empleado(re.getInt(1), re.getString(2), re.getString(3), re.getString(4), re.getDouble(5), re.getFloat(6), re.getString(7), re.getString(8)));
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return empleados;
    }

    public ArrayList<Empleado> cargarEmpleadosTabla(int id) {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        ResultSet re = null;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Empleado WHERE  emp_id = ?");
            consulta.setInt(1, id);
            re = consulta.executeQuery();
            while (re.next()) {
                empleados.add(new Empleado(re.getInt(1), re.getString(2), re.getString(3), re.getString(4), re.getDouble(5), re.getFloat(6), re.getString(7), re.getString(8)));
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return empleados;
    }

    public InputStream cargarFotoEmpleado(int id) {
        ResultSet re = null;
        InputStream datos = null;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Empleado WHERE  emp_id = ?");
            consulta.setInt(1, id);
            re = consulta.executeQuery();
            while (re.next()) {
                datos = re.getBinaryStream(9);
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return datos;
    }

    public ArrayList<Cliente> cargarClientes(int id) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ResultSet re = null;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Cliente WHERE  cli_id = ?");
            consulta.setInt(1, id);
            re = consulta.executeQuery();

            while (re.next()) {
                clientes.add(new Cliente(re.getInt(1), re.getString(2), re.getString(3), re.getString(4), re.getInt(5), re.getString(6)));
            }
            if (clientes.size() == 0) {
                JOptionPane.showMessageDialog(null, "Cliente NO encontrado");
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return clientes;
    }
    
     public ArrayList<Cliente> cargarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ResultSet re = null;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Cliente");
            re = consulta.executeQuery();

            while (re.next()) {
                clientes.add(new Cliente(re.getInt(1), re.getString(2), re.getString(3), re.getString(4), re.getInt(5), re.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return clientes;
    }
    

    public boolean eliminarCliente(int id) {
        try {
            consulta = conexion.prepareStatement("DELETE FROM  Cliente WHERE cli_id = ?");
            consulta.setInt(1, id);
            
            if (consulta.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado con EXITO!");
            }
            else
                JOptionPane.showMessageDialog(null, "ERROR al eliminar cliente!");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public boolean actulizarCliente(Cliente cli){
        try {
            consulta = conexion.prepareStatement("UPDATE Cliente SET cli_nombre = ?, cli_apellido = ?, cli_direccion = ?, cli_telefono = ?, cli_email = ?  WHERE cli_id = ?");
            consulta.setString(1, cli.getNombre());
            consulta.setString(2, cli.getApellido());
            consulta.setString(3, cli.getDireccion());
            consulta.setDouble(4, cli.getTelefono());
            consulta.setString(5, cli.getEmail());
            consulta.setInt(6, cli.getId());
            if (consulta.executeUpdate() > 0)
                JOptionPane.showMessageDialog(null, "Datos correctamente actualizados!");
            else
                JOptionPane.showMessageDialog(null, "Error al actualizar datos!");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
     public boolean modificarEmpleado(Empleado empleado) {
        try {
            consulta = conexion.prepareStatement("UPDATE Empleado SET emp_nombre=?, emp_apellido=?, emp_cargo=?, emp_salario=?, emp_telefono=?, emp_direccion=?, emp_email=?, emp_foto=? WHERE emp_id = ?");
            consulta.setString(1, empleado.getNombre());
            consulta.setString(2, empleado.getApellido());
            consulta.setString(3, empleado.getCargo());
            consulta.setDouble(4, empleado.getSalario());
            consulta.setFloat(5, empleado.getTelefono());
            consulta.setString(6, empleado.getDireccion());
            consulta.setString(7, empleado.getEmail());
            consulta.setBlob(8, empleado.getImagen(), empleado.getLongitudFoto());
            consulta.setInt(9, empleado.getId());
             if (consulta.executeUpdate() > 0)
                JOptionPane.showMessageDialog(null, "Datos correctamente actualizados!");
            else
                JOptionPane.showMessageDialog(null, "Error al actualizar datos!");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
     
     public boolean eliminarEmpleado(int id) {
        try {
            consulta = conexion.prepareStatement("DELETE FROM  Empleado WHERE emp_id = ?");
            consulta.setInt(1, id);
            
            if (consulta.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Empleado eliminado con EXITO!");
            }
            else
                JOptionPane.showMessageDialog(null, "ERROR al eliminar empleado!");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
     
     public boolean agregarInventario(Inventario inv){
         try {
             consulta = conexion.prepareStatement("INSERT INTO Inventario(inv_codigo, inv_nombre, inv_descripcion, inv_fechaIngreso, inv_costo, inv_cantidad) VALUES (?,?,?,?,?,?)");
             consulta.setInt(1, inv.getCodigo());
             consulta.setString(2, inv.getNombre());
             consulta.setString(3, inv.getDescripcion());
             consulta.setDate(4, inv.getFechaIngreso());
             consulta.setFloat(5, inv.getPrecio());
             consulta.setInt(6, inv.getCantidad());
             
             if (consulta.executeUpdate()>0) {
                 JOptionPane.showMessageDialog(null, "Producto guardado en inventario");
             }
         } catch (Exception e) {
             System.out.println(e);
             return false;
         }
         return true;
     }
     
    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (Exception e) {
        }
    }
    
     public ArrayList<Inventario> cargarInventario() {
        ArrayList<Inventario> inventarios = new ArrayList<Inventario>();
        ResultSet re = null;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Inventario");
            re = consulta.executeQuery();
            while (re.next()) {
                inventarios.add(new Inventario(re.getInt(1), re.getString(2), re.getString(3), re.getDate(4),  re.getFloat(5), re.getInt(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return inventarios;
    }
     
     
     public ArrayList<Inventario> cargarInventarioTabla(int id) {
        ArrayList<Inventario> inventarios = new ArrayList<Inventario>();
        ResultSet re = null;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Inventario WHERE  inv_codigo = ?");
            consulta.setInt(1, id);
            re = consulta.executeQuery();
            while (re.next()) {
                inventarios.add(new Inventario(re.getInt(1), re.getString(2), re.getString(3), re.getDate(4),  re.getFloat(5), re.getInt(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return inventarios;
    }
     
     public boolean modificarInventario(Inventario inv) {
        try {
            consulta = conexion.prepareStatement("UPDATE Inventario SET inv_nombre=?, inv_descripcion=?, inv_fechaIngreso=?, inv_costo=?, inv_cantidad=? WHERE inv_codigo = ?");
            consulta.setString(1, inv.getNombre());
            consulta.setString(2, inv.getDescripcion());
            consulta.setDate(3, inv.getFechaIngreso());
            consulta.setFloat(4, inv.getPrecio());
            consulta.setInt(5, inv.getCantidad());
            consulta.setInt(6, inv.getCodigo());

             if (consulta.executeUpdate() > 0)
                JOptionPane.showMessageDialog(null, "Datos correctamente actualizados!");
            else
                JOptionPane.showMessageDialog(null, "Error al actualizar datos!");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
     
     public boolean eliminarInventario(int id) {
        try {
            consulta = conexion.prepareStatement("DELETE FROM  Inventario WHERE inv_codigo = ?");
            consulta.setInt(1, id);
            
            if (consulta.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Elemento eliminado con EXITO!");
            }
            else
                JOptionPane.showMessageDialog(null, "ERROR al eliminar elemento!");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
     
     public boolean agregarPedido(Pedido ped) {

        try {
            consulta = conexion.prepareStatement("INSERT INTO Pedido (ped_cod, cli_id, emp_id, ped_fPedido, ped_fEntrga, ped_detalle) VALUES (?,?,?,?,?,?)");
            consulta.setInt(1, ped.getCodigo());
            consulta.setInt(2, ped.getCli());
            consulta.setInt(3, ped.getEmp());
            consulta.setDate(4, ped.getPedido());
            consulta.setDate(5, ped.getEntrega());
            consulta.setString(6, ped.getDetalle());

            if (consulta.executeUpdate() > 0)
                JOptionPane.showMessageDialog(null, "Pedido guardato con EXITO!");
            
            else
                JOptionPane.showMessageDialog(null, "ERROR al guardato pedido!");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
     
     public ArrayList<Pedido> cargarPedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        ResultSet re = null;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Pedido");
            re = consulta.executeQuery();

            while (re.next()) {
                pedidos.add(new Pedido(re.getInt(1), re.getInt(2), re.getInt(3), re.getDate(4), re.getDate(5), re.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return pedidos;
    }
}
