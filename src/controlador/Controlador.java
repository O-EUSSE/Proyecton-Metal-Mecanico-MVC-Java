/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import modelo.Cliente;
import modelo.Empleado;

import modelo.Gestor;
import modelo.Inventario;
import modelo.Pedido;
import vista.Menu;
import vista.Principal;
import vista.VInfo;
import vista.VistaBuscarCliente;
import vista.VistaBuscarInventario;
import vista.VistaCliente;
import vista.VistaEmpleado;
import vista.VistaInventario;
import vista.VistaPedido;

/**
 *
 * @author aariasg
 */
public class Controlador {

    Gestor gestor = new Gestor();
    Principal principal;
    Menu menu;
    VistaCliente Vcliente;
    VistaEmpleado Vempleado;
    VistaBuscarCliente VcomCliente;
    VistaInventario Vinventario;
    VistaBuscarInventario VbusInventario;
    VistaPedido Vpedido;
    VInfo Vinfomarcion;
    FileInputStream ima;
    int longBytes;

    public Controlador() {
        menu = new Menu();
        principal = new Principal();
        Vcliente = new VistaCliente();
        Vempleado = new VistaEmpleado();
        VcomCliente = new VistaBuscarCliente();
        Vinventario = new VistaInventario();
        VbusInventario = new VistaBuscarInventario();
        Vpedido = new VistaPedido();
        Vinfomarcion = new VInfo();
        principal.jDesktopPane1.add(VcomCliente);
        principal.jDesktopPane1.add(menu);
        principal.jDesktopPane1.add(Vcliente);
        principal.jDesktopPane1.add(Vempleado);
        principal.jDesktopPane1.add(Vinventario);
        principal.jDesktopPane1.add(VbusInventario);
        principal.jDesktopPane1.add(Vpedido);
        principal.jDesktopPane1.add(Vinfomarcion);
    }

    public void inicio() {
        menu.btnCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lanzarCliente();
            }
        });

        menu.btnEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lanzarEmpleado();
            }
        });

        menu.btnInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lanzarInventario();
            }
        });
        
        menu.btnPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lanzarPedido();
            }
        });
        
        menu.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.hide();
                principal.hide();
            }
        });
        
        menu.btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lanazaInfo();
            }
        });

        principal.setVisible(true);
        menu.setVisible(true);
    }
    
    public void lanazaInfo(){
        
        Vinfomarcion.btnSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Vinfomarcion.hide();
            }
        });
        
        Vinfomarcion.setVisible(true);
    
    }

    public void lanzarCliente() {
        Vcliente.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCliente();
            }
        });

        Vcliente.btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lanzarBuscarCliente();
            }
        });
        
        Vcliente.btnClienteAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vcliente.hide();
            }
        });
        
        Vcliente.setVisible(true);
    }

    public void guardarCliente() {
        int id = Integer.parseInt(Vcliente.txtId.getText());
        String nombre = Vcliente.txtNombre.getText();
        String apellido = Vcliente.txtApellido.getText();
        String direccion = Vcliente.txtDireccion.getText();
        double telefono = Double.parseDouble(Vcliente.txtTelefono.getText());
        String email = Vcliente.txtEmail.getText();

        Cliente cliente = new Cliente(id, nombre, apellido, direccion, telefono, email);
        if (gestor.agregarNuevoCliente(cliente)) {
            JOptionPane.showMessageDialog(null, "Cliente guardado con exito!");
        }
    }

    public void lanzarEmpleado() {
        cargarContactos();

        Vempleado.lblImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cargarImagen(e);
            }
        });

        Vempleado.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEmpleado();
                cargarContactos();
            }
        });

        Vempleado.tblEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    cargarContato(e);
                } catch (IOException ex) {

                }
            }
        });;

        Vempleado.btnMoficicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actulizarEmpleado();

            }
        });

        Vempleado.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEmpleado();
            }
        });
        
        Vempleado.btnEmpleadoAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vempleado.hide();
            }
        });
        
        Vempleado.setVisible(true);
    }

    public void cargarImagen(java.awt.event.MouseEvent evt) {
        JFileChooser se = new JFileChooser();
        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int esatdo = se.showOpenDialog(null);
        if (esatdo == JFileChooser.APPROVE_OPTION) {
            try {
                ima = new FileInputStream(se.getSelectedFile());
                this.longBytes = (int) se.getSelectedFile().length();

                Image icono = ImageIO.read(se.getSelectedFile()).getScaledInstance(Vempleado.lblImagen.getWidth(), Vempleado.lblImagen.getHeight(), Image.SCALE_DEFAULT);
                Vempleado.lblImagen.setIcon(new ImageIcon(icono));
                Vempleado.lblImagen.updateUI();
            } catch (Exception e) {
            }
        }
    }

    public void guardarEmpleado() {
        Empleado emple = new Empleado(Integer.parseInt(Vempleado.txtId.getText()), Vempleado.txtNombre.getText(), Vempleado.txtApellido.getText(), Vempleado.txtCargo.getText(), Double.parseDouble(Vempleado.txtSalario.getText()), Float.parseFloat(Vempleado.txtTelefono.getText()), Vempleado.txtDireccion.getText(), Vempleado.txtEmail.getText(), ima, longBytes);
        if (gestor.agregarNuevoEmpleado(emple)) {
            JOptionPane.showMessageDialog(null, "Empleado Guardado Exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "ERROR Al guardar el empleado");
        }
    }

    public void cargarContato(java.awt.event.MouseEvent evt) throws IOException {

        int row = Vempleado.tblEmpleado.getSelectedRow();
        int id = (Vempleado.tblEmpleado.getModel().getValueAt(row, 0).hashCode());

        ArrayList<Empleado> empleados = gestor.cargarEmpleadosTabla(id);
        for (Empleado empleado : empleados) {
            Vempleado.txtId.setText("" + empleado.getId());
            Vempleado.txtNombre.setText("" + empleado.getNombre());
            Vempleado.txtApellido.setText("" + empleado.getApellido());
            Vempleado.txtCargo.setText("" + empleado.getCargo());
            Vempleado.txtSalario.setText("" + empleado.getSalario());
            Vempleado.txtTelefono.setText("" + empleado.getTelefono());
            Vempleado.txtDireccion.setText("" + empleado.getDireccion());
            Vempleado.txtEmail.setText("" + empleado.getEmail());
        }

        ByteArrayOutputStream ouput = new ByteArrayOutputStream();
        InputStream datos = gestor.cargarFotoEmpleado(id);
        int tem = datos.read();

        while (tem >= 0) {
            ouput.write((char) tem);
            tem = datos.read();
        }

        Image imagen = Toolkit.getDefaultToolkit().createImage(ouput.toByteArray());
        imagen = imagen.getScaledInstance(Vempleado.lblImagen.getHeight(), Vempleado.lblImagen.getWidth(), Image.SCALE_DEFAULT);

        Vempleado.lblImagen.setIcon(new ImageIcon(imagen));
        Vempleado.lblImagen.updateUI();
    }

    public void cargarContactos() {
        ArrayList<Empleado> empleados = gestor.cargarEmpleados();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apelldio");
        modelo.addColumn("Cargo");
        modelo.addColumn("Salario");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Email");

        for (Empleado empleado : empleados) {
            Object[] fila = new Object[8];
            fila[0] = empleado.getId();
            fila[1] = empleado.getNombre();
            fila[2] = empleado.getApellido();
            fila[3] = empleado.getCargo();
            fila[4] = empleado.getSalario();
            fila[5] = empleado.getTelefono();
            fila[6] = empleado.getDireccion();
            fila[7] = empleado.getEmail();
            modelo.addRow(fila);
        }
        Vempleado.tblEmpleado.setModel(modelo);
    }

    public void lanzarBuscarCliente() {
        VcomCliente.txtNombre.setEnabled(false);
        VcomCliente.txtApellido.setEnabled(false);
        VcomCliente.txtDireccion.setEnabled(false);
        VcomCliente.txtTelefono.setEnabled(false);
        VcomCliente.txtEmail.setEnabled(false);
        VcomCliente.btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });

        VcomCliente.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });

        VcomCliente.btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });
        
        VcomCliente.btnBucarClienteAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VcomCliente.hide();
            }
        });
        
        VcomCliente.setVisible(true);
    }

    public void buscarCliente() {
        ArrayList<Cliente> clientes = gestor.cargarClientes(Integer.parseInt(VcomCliente.txtIdCliente.getText()));
        System.out.println("" + clientes.size());
        if (clientes.size() > 0) {
            VcomCliente.txtNombre.setEnabled(true);
            VcomCliente.txtApellido.setEnabled(true);
            VcomCliente.txtDireccion.setEnabled(true);
            VcomCliente.txtTelefono.setEnabled(true);
            VcomCliente.txtEmail.setEnabled(true);

            for (Cliente cliente : clientes) {
                VcomCliente.txtNombre.setText(cliente.getNombre());
                VcomCliente.txtApellido.setText(cliente.getApellido());
                VcomCliente.txtDireccion.setText(cliente.getDireccion());
                VcomCliente.txtTelefono.setText("" + cliente.getTelefono());
                VcomCliente.txtEmail.setText(cliente.getEmail());
            }
        } else {
            VcomCliente.txtNombre.setEnabled(false);
            VcomCliente.txtApellido.setEnabled(false);
            VcomCliente.txtDireccion.setEnabled(false);
            VcomCliente.txtTelefono.setEnabled(false);
            VcomCliente.txtEmail.setEnabled(false);

            VcomCliente.txtNombre.setText(null);
            VcomCliente.txtApellido.setText(null);
            VcomCliente.txtDireccion.setText(null);
            VcomCliente.txtTelefono.setText(null);
            VcomCliente.txtEmail.setText(null);
        }
    }

    public void eliminarCliente() {

        if (gestor.elieminarCliente(Integer.parseInt(VcomCliente.txtIdCliente.getText()))) {
            VcomCliente.txtIdCliente.setText(null);
            VcomCliente.txtNombre.setText(null);
            VcomCliente.txtApellido.setText(null);
            VcomCliente.txtDireccion.setText(null);
            VcomCliente.txtTelefono.setText(null);
            VcomCliente.txtEmail.setText(null);
        }
    }

    public void actualizarCliente() {

        int id = Integer.parseInt(VcomCliente.txtIdCliente.getText());
        String nombre = VcomCliente.txtNombre.getText();
        String ape = VcomCliente.txtApellido.getText();
        String dir = VcomCliente.txtDireccion.getText();
        double tel = Double.parseDouble(VcomCliente.txtTelefono.getText());
        String ema = VcomCliente.txtEmail.getText();

        Cliente cli = new Cliente(id, nombre, ape, dir, tel, ema);
        if (gestor.actualizarCliente(cli)) {
            VcomCliente.txtNombre.setText(null);
            VcomCliente.txtApellido.setText(null);
            VcomCliente.txtDireccion.setText(null);
            VcomCliente.txtTelefono.setText(null);
            VcomCliente.txtEmail.setText(null);
        }
    }

    public void actulizarEmpleado() {
        Empleado emple = new Empleado(Integer.parseInt(Vempleado.txtId.getText()), Vempleado.txtNombre.getText(), Vempleado.txtApellido.getText(), Vempleado.txtCargo.getText(), Double.parseDouble(Vempleado.txtSalario.getText()), Float.parseFloat(Vempleado.txtTelefono.getText()), Vempleado.txtDireccion.getText(), Vempleado.txtEmail.getText(), ima, longBytes);
        gestor.actulizarEmpleado(emple);
        cargarContactos();
    }

    public void eliminarEmpleado() {
        int row = Vempleado.tblEmpleado.getSelectedRow();
        int id = (Vempleado.tblEmpleado.getModel().getValueAt(row, 0).hashCode());
        gestor.elieminarEmpleado(id);
        cargarContactos();
    }

    public void lanzarInventario() {
        Vinventario.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarInventario();
            }
        });

        Vinventario.btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarInventario();
            }
        });
        
        Vinventario.btnInventarioAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vinventario.hide();
            }
        });
        
        Vinventario.setVisible(true);
    }

    public void guardarInventario() {
        java.util.Date ingreso = Vinventario.jDateChooser1.getDate();
        java.sql.Date Fingreso = new java.sql.Date(ingreso.getTime());

        Inventario inv = new Inventario(Integer.parseInt(Vinventario.txtCodigo.getText()), Vinventario.txtNombre.getText(), Vinventario.txtDetalle.getText(), Fingreso, Float.parseFloat(Vinventario.txtCosto.getText()), Vinventario.cmbCantidad.getSelectedIndex() + 1);
        if (gestor.agregarInventario(inv)) {
            Vinventario.txtCodigo.setText(null);
            Vinventario.txtCosto.setText(null);
            Vinventario.txtDetalle.setText(null);
            Vinventario.txtNombre.setText(null);
            Vinventario.cmbCantidad.setSelectedIndex(0);
            Vinventario.jDateChooser1.setCalendar(null);
        }
    }

    public void buscarInventario() {
        
        VbusInventario.tblInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    cargarDatosInventario(e);
                } catch (IOException ex) {
                }
            }
        });;

        VbusInventario.btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarInventario();
            }
        });

        VbusInventario.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarInventario();
            }
        });
        
        VbusInventario.btnBuscarInventarioAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VbusInventario.hide();
            }
        });
        
        cargarInventario();
        VbusInventario.setVisible(true);
    }

    public void cargarInventario() {
        ArrayList<Inventario> invetarios = gestor.cargarInventario();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Fecha Ingreso");
        modelo.addColumn("Costo");
        modelo.addColumn("Cantidad");

        for (Inventario invetario : invetarios) {
            Object[] fila = new Object[6];
            fila[0] = invetario.getCodigo();
            fila[1] = invetario.getNombre();
            fila[2] = invetario.getDescripcion();
            fila[3] = invetario.getFechaIngreso();
            fila[4] = invetario.getPrecio();
            fila[5] = invetario.getCantidad();
            modelo.addRow(fila);
        }
        VbusInventario.tblInventario.setModel(modelo);
    }

    public void cargarDatosInventario(java.awt.event.MouseEvent evt) throws IOException {

        int row = VbusInventario.tblInventario.getSelectedRow();
        int id = (VbusInventario.tblInventario.getModel().getValueAt(row, 0).hashCode());
        ArrayList<Inventario> invetarios = gestor.cargarInventarioTabla(id);

        System.out.println(invetarios.size());
        for (Inventario invetario : invetarios) {
            VbusInventario.txtCodigo.setText("" + invetario.getCodigo());
            VbusInventario.txtCosto.setText("" + invetario.getPrecio());
            VbusInventario.txtDetalle.setText("" + invetario.getDescripcion());
            VbusInventario.txtNombre.setText("" + invetario.getNombre());
            VbusInventario.cmbCantidad.setSelectedIndex(invetario.getCantidad() - 1);
            VbusInventario.jDateChooser1.setDate(invetario.getFechaIngreso());
        }
    }

    public void actualizarInventario() {
        java.util.Date ingreso = VbusInventario.jDateChooser1.getDate();
        java.sql.Date Fingreso = new java.sql.Date(ingreso.getTime());

        Inventario inv = new Inventario(Integer.parseInt(VbusInventario.txtCodigo.getText()), VbusInventario.txtNombre.getText(), VbusInventario.txtDetalle.getText(), Fingreso, Float.parseFloat(VbusInventario.txtCosto.getText()), VbusInventario.cmbCantidad.getSelectedIndex() + 1);
        if (gestor.actulizarInventario(inv)) {
            VbusInventario.txtCodigo.setText(null);
            VbusInventario.txtCosto.setText(null);
            VbusInventario.txtDetalle.setText(null);
            VbusInventario.txtNombre.setText(null);
            VbusInventario.jDateChooser1.setDate(null);
            VbusInventario.cmbCantidad.setSelectedIndex(0);
        }
        cargarInventario();
    }

    public void eliminarInventario() {
        int row = VbusInventario.tblInventario.getSelectedRow();
        int id = (VbusInventario.tblInventario.getModel().getValueAt(row, 0).hashCode());

        if (gestor.elieminarInvetario(id)) {
            VbusInventario.txtCodigo.setText(null);
            VbusInventario.txtCosto.setText(null);
            VbusInventario.txtDetalle.setText(null);
            VbusInventario.txtNombre.setText(null);
            VbusInventario.jDateChooser1.setDate(null);
            VbusInventario.cmbCantidad.setSelectedIndex(0);
            cargarInventario();
        }
    }
    
    public void lanzarPedido(){
         ArrayList<Cliente> clientes = gestor.cargarClientes();
        Vpedido.cmbIdCliente.removeAllItems();
        Vpedido.cmbIdCliente.addItem("Seleccione una Cliente");
        for (Cliente cliente : clientes) {
            Vpedido.cmbIdCliente.addItem(cliente);
        }
        
        ArrayList<Empleado> empleados = gestor.cargarEmpleados();
        Vpedido.cmbIdEmpledo.removeAllItems();
        Vpedido.cmbIdEmpledo.addItem("Seleccione una Empleadp");
        for (Empleado empleado : empleados) {
            Vpedido.cmbIdEmpledo.addItem(empleado);
        }
        
        Vpedido.btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPedido();
            }
        });
        
        Vpedido.btnPedidosAtras.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Vpedido.hide();
             }
         });
        
        cargarPedidos();
        Vpedido.setVisible(true);
    }
    
    public void guardarPedido(){
        java.util.Date inicio = Vpedido.jDateChooserInicio.getDate();
        java.sql.Date Finico = new java.sql.Date(inicio.getTime());
        
         java.util.Date entrega = Vpedido.jDateChooserEntrega.getDate();
        java.sql.Date Fentrega = new java.sql.Date(entrega.getTime());
        
        Pedido ped = new Pedido(Integer.parseInt(Vpedido.txtCodigo.getText()), ((Cliente)Vpedido.cmbIdCliente.getSelectedItem()).getId(), ((Empleado)Vpedido.cmbIdEmpledo.getSelectedItem()).getId(), Finico, Fentrega, Vpedido.txtDetalle.getText());
        if (gestor.agregarNuevoPedido(ped)) {
            cargarPedidos();
            Vpedido.txtCodigo.setText(null);
            Vpedido.txtDetalle.setText(null);
            Vpedido.cmbIdCliente.setSelectedIndex(0);
            Vpedido.cmbIdEmpledo.setSelectedIndex(0);
            Vpedido.jDateChooserInicio.setDate(null);
            Vpedido.jDateChooserEntrega.setDate(null);
        }
    }
    
    
    public void cargarPedidos() {
        ArrayList<Pedido> pedidos = gestor.cargarPedidos();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Cliente");
        modelo.addColumn("Empleado a cargo");
        modelo.addColumn("Fecha Solicitud");
        modelo.addColumn("Fecha Entrega");
        modelo.addColumn("Detalle");
        
        
        for (Pedido pedido : pedidos) {
            Object[] fila = new Object[6];
            fila[0] = pedido.getCodigo();
            fila[1] = pedido.getCli();
            fila[2] = pedido.getEmp();
            fila[3] = pedido.getPedido();
            fila[4] = pedido.getEntrega();
            fila[5] = pedido.getDetalle();
            modelo.addRow(fila);
        }
        Vpedido.tblPedidos.setModel(modelo);
    }
    
    public void enviarAtras(){
        
    }
}
