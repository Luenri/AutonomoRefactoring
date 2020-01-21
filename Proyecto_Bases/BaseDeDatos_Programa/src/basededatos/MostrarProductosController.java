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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class MostrarProductosController implements Initializable {

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Integer, Producto> codigo;
    @FXML
    private TableColumn<String, Producto> nombre;
    @FXML
    private TableColumn<Double, Producto> precio;
    @FXML
    private TableColumn<Integer, Producto> inventario;
    @FXML
    private ComboBox<String> filtro;
    @FXML
    private TextField variableFiltro;
    @FXML
    private TextField txCodigo;
    @FXML
    private TextField txNombre;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbPrecio;
    @FXML
    private Label lbCod;
    @FXML
    private TextField txPrecio;
    @FXML
    private TextField txCod;
    @FXML
    private Button modify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ObservableList<String> opciones=FXCollections.observableArrayList("Nombre","Precio","Cod-Inventario");
            filtro.setItems(opciones);
            
            
            codigo.setCellValueFactory(new PropertyValueFactory<>("codigoProducto"));
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            inventario.setCellValueFactory(new PropertyValueFactory<>("codInventario"));
            
            String sql="SELECT * FROM producto ";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql);
            ObservableList<Producto> datos=FXCollections.observableArrayList();
            while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoP=Integer.valueOf(rs.getString("codigo"));
                String nombreP=rs.getString("nombrePro");
                double precioP=Double.valueOf(rs.getString("precioUnitario"));
                int inventarioP=Integer.valueOf(rs.getString("codInv"));
                Producto p=new Producto(codigoP,nombreP,precioP,inventarioP,Integer.valueOf(rs.getString("estado")));
                datos.add(p);
                }
                
                
                
            }
            tablaProductos.setItems(datos);
            tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MostrarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }    

    @FXML
    private void filtrar(ActionEvent event) throws SQLException {
        String flt=null;
        if(variableFiltro.getText().isEmpty())
            flt="SELECT * FROM producto ";
        else if (filtro.getValue().equals("Nombre"))
            flt="SELECT * FROM producto  where nombrePro like "+" \'"
                    +variableFiltro.getText()+"%\' ;";
        else if(filtro.getValue().equals("Precio"))
            flt="SELECT * FROM producto  where precioUnitario = "+
                    variableFiltro.getText()+";";
        else if(filtro.getValue().equals("Cod-Inventario"))
            flt="SELECT * FROM producto  where codInv = "+
                    variableFiltro.getText()+";";
        
        Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(flt);
            ObservableList<Producto> datos=FXCollections.observableArrayList();
            while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoP=Integer.valueOf(rs.getString("codigo"));
                String nombreP=rs.getString("nombrePro");
                double precioP=Double.valueOf(rs.getString("precioUnitario"));
                int inventarioP=Integer.valueOf(rs.getString("codInv"));
                Producto p=new Producto(codigoP,nombreP,precioP,inventarioP,Integer.valueOf(rs.getString("estado")));
                datos.add(p);
                }
            }
            tablaProductos.setItems(datos);
            tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        
    }

    @FXML
    private void regresar(ActionEvent event) {
        BaseDeDatos.ventana.setScene(BaseDeDatos.sc);
    }

    @FXML
    private void agregarProducto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("productos.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
        
    }

    @FXML
    private void modificar(ActionEvent event) {
        lbCodigo.setVisible(true);
        lbNombre.setVisible(true);
        lbPrecio.setVisible(true);
        lbCod.setVisible(true);
        txCodigo.setVisible(true);
        txNombre.setVisible(true);
        txPrecio.setVisible(true);
        txCod.setVisible(true);
        modify.setVisible(true);
        Producto p=tablaProductos.getSelectionModel().getSelectedItem();
        txCodigo.setText(String.valueOf(p.getCodigoProducto()));
        txNombre.setText(p.getNombre());
        txPrecio.setText(String.valueOf(p.getPrecio()));
        txCod.setText(String.valueOf(p.getCodInventario()));
    }

    @FXML
    private void eliminar(ActionEvent event) throws SQLException {
         productos.clear();
        Producto p1=tablaProductos.getSelectionModel().getSelectedItem();
        //String sql="DELETE FROM producto WHERE codigo="+p1.getCodigoProducto()+";";
        String sql="update producto set estado=0 where codigo="+p1.getCodigoProducto()+";";
        Statement st=conexion.createStatement();
        st.execute(sql);
        String sql2="SELECT * FROM producto ";
            //Statement st2=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql2);
            ObservableList<Producto> datos=FXCollections.observableArrayList();
        while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoP=Integer.valueOf(rs.getString("codigo"));
                String nombreP=rs.getString("nombrePro");
                double precioP=Double.valueOf(rs.getString("precioUnitario"));
                int inventarioP=Integer.valueOf(rs.getString("codInv"));
                Producto p=new Producto(codigoP,nombreP,precioP,inventarioP,Integer.valueOf(rs.getString("estado")));
                datos.add(p);
                productos.add(p);
                }
                
            }
            tablaProductos.setItems(datos);
            tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void modificarElemento(ActionEvent event) throws SQLException {
        productos.clear();
        String sql="UPDATE producto SET nombrePro='"+txNombre.getText()+"',precioUnitario="+
                txPrecio.getText()+",codInv="+txCod.getText()+
                " WHERE codigo="+txCodigo.getText()+";";
        Statement st=conexion.createStatement();
        st.execute(sql);
        String sql2="SELECT * FROM producto ";
            Statement st2=conexion.createStatement();
            ResultSet rs=st2.executeQuery(sql2);
            ObservableList<Producto> datos=FXCollections.observableArrayList();
        while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoP=Integer.valueOf(rs.getString("codigo"));
                String nombreP=rs.getString("nombrePro");
                double precioP=Double.valueOf(rs.getString("precioUnitario"));
                int inventarioP=Integer.valueOf(rs.getString("codInv"));
                Producto p=new Producto(codigoP,nombreP,precioP,inventarioP,Integer.valueOf(rs.getString("estado")));
                datos.add(p);
                productos.add(p);
                }
            }
            tablaProductos.setItems(datos);
            tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        lbCodigo.setVisible(false);
        lbNombre.setVisible(false);
        lbPrecio.setVisible(false);
        lbCod.setVisible(false);
        txCodigo.setVisible(false);
        txNombre.setVisible(false);
        txPrecio.setVisible(false);
        txCod.setVisible(false);
        modify.setVisible(false);
    }
    
}
