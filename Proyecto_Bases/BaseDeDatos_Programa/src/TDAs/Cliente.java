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
public class Cliente extends Persona{
    private String direccion;

    /*public Cliente(String cedula, String nombre, String apellido, LocalDate fechaNacimiento, String celular) {
        super(cedula, nombre, apellido, fechaNacimiento, celular);
    }*/

    public Cliente(String cedula, String nombre, String apellido, LocalDate fechaNacimiento, String celular,int estado,String direccion) {
        super(cedula, nombre, apellido, fechaNacimiento, celular,estado);
        this.direccion = direccion;
    }
    
    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
    
}
