/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import TDAs.Producto;
import static basededatos.BaseDeDatos.conexion;
import static basededatos.BaseDeDatos.productos;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
public class ProductosController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField precio;
    @FXML
    private TextField codigoInv;
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
        String linea="Insert Into producto (nombrePro,precioUnitario,codInv,estado) values ('"+nombre.getText()+"\',"+precio.getText()+","+codigoInv.getText()+",1"+");";
        
        Statement st=conexion.createStatement();
        st.execute(linea);
        productos.clear();
        String sql="SELECT * FROM producto ";
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoP=Integer.valueOf(rs.getString("codigo"));
                String nombreP=rs.getString("nombrePro");
                double precioP=Double.valueOf(rs.getString("precioUnitario"));
                int inventarioP=Integer.valueOf(rs.getString("codInv"));
                Producto p=new Producto(codigoP,nombreP,precioP,inventarioP,Integer.valueOf(rs.getString("estado")));
                productos.add(p);
                }
                
            }
        mensaje.setText("Guardado Correctamente!!!");
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        nombre.setText("");
        precio.setText("");
        codigoInv.setText("");
        mensaje.setText("");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mostrarProductos.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }
    
}
