/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author Luis
 */
public class PedidoRealizado {
    
    private int idPedido;
    private String idProveedor;
    private LocalDate fecha;
    private double total;
    private int estado;

    public PedidoRealizado(int idPedido, String idProveedor, LocalDate fecha, double total,int estado) {
        this.idPedido = idPedido;
        this.idProveedor = idProveedor;
        this.fecha = fecha;
        this.total = total;
        this.estado=estado;
    }
    

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    //"src/recursos/numPedido"
    public static String numPedido(String ruta) throws IOException{
        String s;
        try(BufferedReader out=new BufferedReader(new FileReader(ruta))){
            s=out.readLine();
        }
        return s;
    }
    
     public static void sumPedido(String ruta) throws IOException{
        int i=Integer.valueOf(numPedido(ruta))+1;
        try(BufferedWriter out=new BufferedWriter(new FileWriter(ruta))){
            
            out.write(String.valueOf(i));
            out.newLine();
            
            
        }
    }

    
    
    
}
