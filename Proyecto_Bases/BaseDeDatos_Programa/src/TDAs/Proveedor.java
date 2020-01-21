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
public class Proveedor extends Persona {
    private String nombreEmpresa;
    private String direccion;

    public Proveedor(String cedula, String nombre, String apellido, LocalDate fechaNacimiento, String celular,int estado ,String nombreEmpresa, String direccion) {
        super(cedula, nombre, apellido, fechaNacimiento, celular,estado);
        this.nombreEmpresa = nombreEmpresa;
        this.direccion = direccion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
