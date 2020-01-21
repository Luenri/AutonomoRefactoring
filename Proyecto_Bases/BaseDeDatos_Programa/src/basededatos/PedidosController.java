/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import static TDAs.PedidoRealizado.numPedido;
import static TDAs.PedidoRealizado.sumPedido;
import TDAs.Producto;
import static TDAs.Producto.retornarProducto;
import static basededatos.BaseDeDatos.conexion;
import static basededatos.BaseDeDatos.productos;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class PedidosController implements Initializable {

    @FXML
    private TextField cedula;
    @FXML
    private TextField fecha;
    @FXML
    private TextField total;
    @FXML
    private TextField detalleCodigo;
    @FXML
    private TextField detalleCantidad;
    @FXML
    private VBox visorDetalle;
    @FXML
    private ComboBox<String> detallePed;
    @FXML
    private TextField precioUni;
    
    static LinkedList<String> detalles=new LinkedList<>();
    @FXML
    private TextField numPedido;
    @FXML
    private Label mensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        total.setText("0");
        ObservableList<String> opciones=FXCollections.observableArrayList();
        for (Producto p: productos){
            opciones.add(p.getNombre());
        }
        detallePed.setItems(opciones);
        try {
            numPedido.setText(numPedido("src/recursos/numPedido"));
        } catch (IOException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void limpiarCampos(ActionEvent event) {
        cedula.setText("");
        fecha.setText("");
        total.setText("");
        detalleCodigo.setText("");
        detalleCantidad.setText("");
        precioUni.setText("");
        visorDetalle.getChildren().clear();
        mensaje.setText("");
    }

    @FXML
    private void guardarDatos(ActionEvent event) throws SQLException, IOException {
        String linea="Insert Into pedidorealizado (idProveedor, fecha,total,estado) values ('"+cedula.getText()+"\',\'"+fecha.getText()+"\',"+total.getText()+",1"+");";
        
        Statement st=conexion.createStatement();
        st.execute(linea);
        Iterator<String> it=detalles.iterator();
        while (it.hasNext()){
            st.execute(it.next());
        }
        
        detalles.clear();
        sumPedido("src/recursos/numPedido");
        mensaje.setText("Guardado Correctamente!!!");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        detalles.clear();
        Parent root = FXMLLoader.load(getClass().getResource("mostrarPedidos.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void detallePedido(ActionEvent event) {
        Producto p=retornarProducto(detallePed.getValue(),productos);
        detalleCodigo.setText(String.valueOf(p.getCodigoProducto()));
        precioUni.setText(String.valueOf(p.getPrecio()));
        
        
    }

    @FXML
    private void añadirProducto(ActionEvent event) {
        double subtotal=(Double.valueOf(precioUni.getText())*Double.valueOf(detalleCantidad.getText()));
        String s="Nombre: "+detallePed.getValue()+"  Codigo: "+detalleCodigo.getText()+"  Precio unitario: "+precioUni.getText()+"  SubTotal: "+subtotal;
        String s2="Insert into detallepedido values("+numPedido.getText()+", "+detalleCodigo.getText()+", "+detalleCantidad.getText()+", "+subtotal+");";
        detalles.add(s2);
        Label l=new Label(s);
        visorDetalle.getChildren().add(l);
        double totalP=Double.valueOf(total.getText())+subtotal;
        total.setText(String.valueOf(totalP));
    }
    
}
