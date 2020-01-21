/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Luis
 */
public class Producto {
    private int codigoProducto;
    private String nombre;
    private double precio;
    private int codInventario;
    private int estado;

    public Producto(int codigoProducto, String nombre, double precio,int codInventario, int estado) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.codInventario = codInventario;
        this.estado=estado;
    }


    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodInventario() {
        return codInventario;
    }

    public void setCodInventario(int codInventario) {
        this.codInventario = codInventario;
    }
    
    
    public static Producto retornarProducto(String nombre,LinkedList<Producto> productos){
        Iterator<Producto> it=productos.iterator();
        while (it.hasNext()){
            Producto p=it.next();
            if(p.getNombre().equals(nombre))
                return p;
        }
        return null;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    

    
    
    
    
}
