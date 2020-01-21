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
public class Servicio {
    private int codServicio;
    private String tipo;
    private double costo;
    private int estado;

    public Servicio(int codServicio, String tipo, double costo,int estado) {
        this.codServicio = codServicio;
        this.tipo = tipo;
        this.costo = costo;
        this.estado=estado;
    }

    public int getCodServicio() {
        return codServicio;
    }

    public void setCodServicio(int codServicio) {
        this.codServicio = codServicio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    public static Servicio retornarServicio(String nombre,LinkedList<Servicio> servicios){
        Iterator<Servicio> it=servicios.iterator();
        while (it.hasNext()){
            Servicio s=it.next();
            if(s.getTipo().equals(nombre))
                return s;
        }
        return null;
    }
    
}
