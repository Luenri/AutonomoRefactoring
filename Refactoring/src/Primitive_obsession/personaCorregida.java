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
public class personaCorregida {
    protected nombreCompleto nombre;
    protected direccionCompuesta direccion;
    protected int edad;

    public personaCorregida(nombreCompleto nombre, direccionCompuesta direccion, int edad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.edad = edad;
    }

    public nombreCompleto getNombre() {
        return nombre;
    }

    public void setNombre(nombreCompleto nombre) {
        this.nombre = nombre;
    }

    public direccionCompuesta getDireccion() {
        return direccion;
    }

    public void setDireccion(direccionCompuesta direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
}
