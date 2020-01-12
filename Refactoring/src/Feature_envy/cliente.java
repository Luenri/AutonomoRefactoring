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
public class cliente {
    protected telefono telefon;
    
    public String getTelefon(){
        return telefon.getNumero()+telefon.getPrefijo();
    }
}
