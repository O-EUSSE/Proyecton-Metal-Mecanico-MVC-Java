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
public class Pedido {
    
    private int codigo;
    private int cli;
    private int emp;
    private Date pedido;
    private Date entrega;
    private String detalle;

    public Pedido(int codigo, int cli, int emp, Date pedido, Date entrega, String detalle) {
        this.codigo = codigo;
        this.cli = cli;
        this.emp = emp;
        this.pedido = pedido;
        this.entrega = entrega;
        this.detalle = detalle;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @return the cli
     */
    public int getCli() {
        return cli;
    }

    /**
     * @return the emp
     */
    public int getEmp() {
        return emp;
    }

    /**
     * @return the pedido
     */
    public Date getPedido() {
        return pedido;
    }

    /**
     * @return the entrega
     */
    public Date getEntrega() {
        return entrega;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }
    
    
}
