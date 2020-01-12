/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Feature_envy;

/**
 *
 * @author Luis
 */
public class telefonoCorregido {
    protected String numero;
    
    public telefonoCorregido (String numero){
        this.numero=numero;
    }
    
    public String getPrefijo(){
        return numero.substring(0,3);
    }
    
    public String getNumero(){
        return numero.substring(0,13);
    }
    
    @Override
    public String toString(){
        return getPrefijo()+getNumero();
    }
}
