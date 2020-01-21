/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import TDAs.Servicio;
import static basededatos.BaseDeDatos.conexion;
import static basededatos.BaseDeDatos.servicios;
import java.io.IOException;
//import static basededatos.MostrarServiciosController.tablaServicios;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class ServiciosController implements Initializable {

    @FXML
    private TextArea tipo;
    @FXML
    private TextField costo;
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
    private void guardarServicio(ActionEvent event) throws SQLException {
        
        
        String linea="Insert Into servicio (tipo, costo,estado) values ('"+tipo.getText()+"\',"+costo.getText()+",1"+");";
        
        Statement st=conexion.createStatement();
        st.execute(linea);
        servicios.clear();
        String sql2="SELECT * FROM Servicio ";
            ResultSet rs=st.executeQuery(sql2);
            ObservableList<Servicio> service=FXCollections.observableArrayList();
            while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoSer=Integer.valueOf(rs.getString("CodigoServicio"));
                String tipoSer=rs.getString("tipo");
                double costoSer=Double.valueOf(rs.getString("costo"));
                
                Servicio s=new Servicio(codigoSer,tipoSer,costoSer,Integer.valueOf(rs.getString("estado")));
                service.add(s);
                servicios.add(s);
                }
                
                
            }
        mensaje.setText("Guardado Correctamente!!!");
        
        
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        tipo.setText("");
        costo.setText("");
        mensaje.setText("");
    }

    @FXML
    private void regresarAServicios(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mostrarServicios.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
        
    }
    
}
