/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import java.util.List;

/**
 *
 * @author Luis
 */
public class DetalleCompra {
    private int idCompra;
    private int codigoServicio;
    private int codigoProducto;
    private int cantidad;
    private double subtotal;

    public DetalleCompra(int idCompra, int codigoServicio, int codigoProducto, int cantidad, double subtotal) {
        this.idCompra = idCompra;
        this.codigoServicio = codigoServicio;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    
    
    

    
    
    
    
}
