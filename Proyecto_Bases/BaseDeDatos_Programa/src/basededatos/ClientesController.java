/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import static basededatos.BaseDeDatos.conexion;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Luis
 */
public class ClientesController implements Initializable {

    @FXML
    private TextField cedula;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField fecha;
    @FXML
    private TextField celular;
    @FXML
    private TextField direccion;
    @FXML
    private Label mensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
            }    

    @FXML
    private void guardarDatos(ActionEvent event) throws SQLException {
        
        String sql="call personacliente('"+cedula.getText()+"','"+nombre.getText()+"','"+apellido.getText()+"','"+
                fecha.getText()+"','"+direccion.getText()+"','"+celular.getText()+"');";
        Statement st=conexion.createStatement();
        st.execute(sql);
        mensaje.setText("Guardado Correctamente!!!");
        
        
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        cedula.setText("");
        nombre.setText("");
        apellido.setText("");
        fecha.setText("");
        celular.setText("");
        direccion.setText("");
        mensaje.setText("");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MostrarClientes.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
        
    }
    
    

    
    


    
}
