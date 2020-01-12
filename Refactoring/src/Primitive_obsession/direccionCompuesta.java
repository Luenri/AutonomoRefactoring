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
public class direccionCompuesta {
    protected String sector;
    protected int manzana;
    protected int villa;
    
    public direccionCompuesta(String sector, int manzana, int villa){
        this.sector=sector;
        this.manzana=manzana;
        this.villa=villa;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getManzana() {
        return manzana;
    }

    public void setManzana(int manzana) {
        this.manzana = manzana;
    }

    public int getVilla() {
        return villa;
    }

    public void setVilla(int villa) {
        this.villa = villa;
    }
    
    
}
