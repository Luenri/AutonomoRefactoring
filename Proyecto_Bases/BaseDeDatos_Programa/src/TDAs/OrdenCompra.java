/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import java.time.LocalDate;

/**
 *
 * @author Luis
 */
public class OrdenCompra {
    private int idCompra;
    private String idCliente;
    private LocalDate fechaCompra;
    private double total;
    private int estado;

    public OrdenCompra(int idCompra, String idCliente, LocalDate fechaCompra, double total, int estado) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.estado=estado;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
}
