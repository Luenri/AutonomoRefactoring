/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Primitive_obsession;

/**
 *
 * @author Luis
 */
public class Persona {
    protected String nombre;
    protected String dirección;
    protected int edad;
    
    public Persona(String nombre,String direccion,int edad){
        this.nombre=nombre;
        this.dirección=direccion;
        this.edad=edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
    
}
