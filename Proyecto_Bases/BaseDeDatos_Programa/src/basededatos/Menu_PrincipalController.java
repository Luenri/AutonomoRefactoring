/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class Menu_PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mostrarClientes(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MostrarClientes.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void mostrarProveedores(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mostrarProveedores.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
        
    }

    @FXML
    private void mostrarProductos(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mostrarProductos.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void mostrarServicios(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mostrarServicios.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
        
    }

    @FXML
    private void mostrarInventarios(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mostrarInventarios.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }
    

    @FXML
    private void mostrarCompras(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mostrarCompras.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void mostrarPedidos(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mostrarPedidos.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void mostrarReportes(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mostrarReportes.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
        
    }

    @FXML
    private void calcular(MouseEvent event) throws IOException {
        Stage s=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("descuento.fxml"));
        
        Scene sc = new Scene(root);
        s.setTitle("VENTANA DE DESCUENTO");
        s.setScene(sc);
        s.show();
        
    }
    
}
