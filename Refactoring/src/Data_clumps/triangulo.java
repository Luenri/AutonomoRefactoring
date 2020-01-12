/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_clumps;

/**
 *
 * @author Luis
 */
public class triangulo {
    protected int ladoA;
    protected int ladoB;
    protected int ladoC;
    
    public triangulo(int ladoA, int ladoB, int ladoC){
        this.ladoA=ladoA;
        this.ladoB=ladoB;
        this.ladoC=ladoC;
    }
    
    @Override
    public String toString(){
        return "Los lados del triangulo son: Lado A: "+ ladoA+" Lado B: "+ladoB+" Lado C: "+ladoC+"\n";
    }
}
