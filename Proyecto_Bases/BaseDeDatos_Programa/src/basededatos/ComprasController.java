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
import TDAs.Servicio;
import static TDAs.Servicio.retornarServicio;
import static basededatos.BaseDeDatos.conexion;
import static basededatos.BaseDeDatos.productos;
import static basededatos.BaseDeDatos.servicios;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
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
public class ComprasController implements Initializable {

    @FXML
    private TextField cedula;
    @FXML
    private TextField fecha;
    @FXML
    private TextField total;
    @FXML
    private ComboBox<String> comboProductos;
    @FXML
    private ComboBox<String> comboServicios;
    @FXML
    private TextField codPro;
    @FXML
    private TextField precioPro;
    @FXML
    private TextField cantidadPro;
    @FXML
    private TextField codSer;
    @FXML
    private TextField precioSer;
    @FXML
    private TextField cantidadSer;
    @FXML
    private VBox visor;
    
    static LinkedList<String> detalles=new LinkedList<>();
    @FXML
    private TextField numCompra;
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
        comboProductos.setItems(opciones);
        
        ObservableList<String> opciones2=FXCollections.observableArrayList();
        for (Servicio s:servicios){
            opciones2.add(s.getTipo());
        }
        comboServicios.setItems(opciones2);
        
        try {
            numCompra.setText(numPedido("src/recursos/numCompra"));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }    

    @FXML
    private void guardarDatos(ActionEvent event) throws SQLException, IOException {
        String linea="Insert Into ordencompra (idCliente, fecha,total,estado) values ('"+cedula.getText()+"\',\'"+fecha.getText()+"\',"+total.getText()+",1"+");";
        
        Statement st=conexion.createStatement();
        st.execute(linea);
        
        Iterator<String> it=detalles.iterator();
        while (it.hasNext()){
            st.execute(it.next());
        }
        
        detalles.clear();
        sumPedido("src/recursos/numCompra");
        mensaje.setText("Guardado Correctamente!!!");
        
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        cedula.setText("");
        fecha.setText("");
        total.setText("");
        codPro.setText("");
        precioPro.setText("");
        cantidadPro.setText("");
        codSer.setText("");
        precioSer.setText("");
        cantidadSer.setText("");
        visor.getChildren().clear();
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        
        detalles.clear();
        Parent root = FXMLLoader.load(getClass().getResource("mostrarCompras.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void productosCom(ActionEvent event) {
        Producto p=retornarProducto(comboProductos.getValue(),productos);
        codPro.setText(String.valueOf(p.getCodigoProducto()));
        precioPro.setText(String.valueOf(p.getPrecio()));
    }

    @FXML
    private void serviciosCom(ActionEvent event) {
        Servicio p=retornarServicio(comboServicios.getValue(),servicios);
        codSer.setText(String.valueOf(p.getCodServicio()));
        precioSer.setText(String.valueOf(p.getCosto()));
    }

    @FXML
    private void añadirProducto(ActionEvent event) {
        double subtotal=(Double.valueOf(precioPro.getText())*Double.valueOf(cantidadPro.getText()));
        String s="Nombre: "+comboProductos.getValue()+"  Codigo: "+codPro.getText()+"  Precio unitario: "+precioPro.getText()+"  SubTotal: "+subtotal;
        String s2="Insert into detalleCompra values("+numCompra.getText()+",null, "+codPro.getText()+", "+cantidadPro.getText()+", "+subtotal+");";
        detalles.add(s2);
        Label l=new Label(s);
        visor.getChildren().add(l);
        double totalP=Double.valueOf(total.getText())+subtotal;
        total.setText(String.valueOf(totalP));
        
        
    }

    @FXML
    private void añadirServicio(ActionEvent event) {
        double subtotal=(Double.valueOf(precioSer.getText())*Double.valueOf(cantidadSer.getText()));
        String s="Nombre: "+comboServicios.getValue()+"  Codigo: "+codSer.getText()+"  Precio unitario: "+precioSer.getText()+"  SubTotal: "+subtotal;
        String s2="Insert into detalleCompra values("+numCompra.getText()+","+codSer.getText()+",null, "+cantidadPro.getText()+", "+subtotal+");";
        detalles.add(s2);
        Label l=new Label(s);
        visor.getChildren().add(l);
        double totalP=Double.valueOf(total.getText())+subtotal;
        total.setText(String.valueOf(totalP));
        
        
    }
    
}
