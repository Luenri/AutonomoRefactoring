/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import static TDAs.PedidoRealizado.numPedido;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Luis
 */
public class DetallePedido {
    private PedidoRealizado idPedido;
    private List<Producto> codProducto;
    private int cantidad;
    private double subtotal;

    public DetallePedido(PedidoRealizado idPedido, List<Producto> codProducto, int cantidad, double subtotal) {
        this.idPedido = idPedido;
        this.codProducto = codProducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public PedidoRealizado getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(PedidoRealizado idPedido) {
        this.idPedido = idPedido;
    }

    public List<Producto> getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(List<Producto> codProducto) {
        this.codProducto = codProducto;
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
