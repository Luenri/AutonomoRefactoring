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
public class InventariosController implements Initializable {

    @FXML
    private TextField descripcion;
    @FXML
    private TextField ubicacion;
    @FXML
    private TextField categoria;
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
        String linea="Insert Into inventario (descripcion, ubicacion, categoria,estado) values ('"+descripcion.getText()+"','"
                +ubicacion.getText()+"','"+categoria.getText()+"',1"+");";
        
        Statement st=conexion.createStatement();
        st.execute(linea);
        
        mensaje.setText("Guardado Correctamente!!!");
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        descripcion.setText("");
        ubicacion.setText("");
        categoria.setText("");
        mensaje.setText("");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mostrarInventarios.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }
    
}
