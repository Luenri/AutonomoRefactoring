/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import TDAs.Inventario;
import static basededatos.BaseDeDatos.conexion;
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
public class MostrarInventariosController implements Initializable {

    @FXML
    private ComboBox<String> filtro;
    @FXML
    private TableView<Inventario> tablaInventarios;
    @FXML
    private TextField variableFiltro;
    @FXML
    private TableColumn<Inventario, Integer> codigo;
    @FXML
    private TableColumn<Inventario, String> descripcion;
    @FXML
    private TableColumn<Inventario, String> ubicacion;
    @FXML
    private TableColumn<Inventario, String> categoria;
    @FXML
    private TextField txCodigo;
    @FXML
    private TextField txDescripcion;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbDescripcion;
    @FXML
    private Label lbUbicacion;
    @FXML
    private Label lbCategoria;
    @FXML
    private TextField txUbicacion;
    @FXML
    private TextField txCategoria;
    @FXML
    private Button modify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            
            ObservableList<String> opciones=FXCollections.observableArrayList("Descripcion","Ubicacion","Categoria");
            filtro.setItems(opciones);
           
            
            
            codigo.setCellValueFactory(new PropertyValueFactory<>("codigoInventario"));
            descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
            categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            
            String sql="SELECT * FROM inventario ";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql);
            ObservableList<Inventario> inventario=FXCollections.observableArrayList();
            while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoInv=Integer.valueOf(rs.getString("codigoInventario"));
                String descripcionInv=rs.getString("descripcion");
                String ubicacionInv=rs.getString("ubicacion");
                String categoriaInv=rs.getString("categoria");
                
                Inventario i=new Inventario(codigoInv,descripcionInv,ubicacionInv,categoriaInv,Integer.valueOf(rs.getString("estado")));
                inventario.add(i);
                }
                
                
            }
            
            tablaInventarios.setItems(inventario);
            tablaInventarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (SQLException ex) {
            Logger.getLogger(MostrarInventariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void agregarInventario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("inventarios.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
        
    }

    @FXML
    private void filtrarInventario(ActionEvent event) throws SQLException {
        String flt=null;
        if(variableFiltro.getText().isEmpty())
            flt="SELECT * FROM inventario ";
        else if (filtro.getValue().equals("Descripcion"))
            flt="SELECT * FROM inventario  where descripcion like "+" \'"
                    +variableFiltro.getText()+"%\' ;";
        else if (filtro.getValue().equals("Ubicacion"))
            flt="SELECT * FROM inventario  where ubicacion like "+" \'"
                    +variableFiltro.getText()+"%\' ;";
        else if (filtro.getValue().equals("Categoria"))
            flt="SELECT * FROM inventario  where categoria like "+" \'"
                    +variableFiltro.getText()+"%\' ;";
        
        String sql="SELECT * FROM inventario ";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(flt);
            ObservableList<Inventario> inventario=FXCollections.observableArrayList();
            while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoInv=Integer.valueOf(rs.getString("codigoInventario"));
                String descripcionInv=rs.getString("descripcion");
                String ubicacionInv=rs.getString("ubicacion");
                String categoriaInv=rs.getString("categoria");
                
                Inventario i=new Inventario(codigoInv,descripcionInv,ubicacionInv,categoriaInv,Integer.valueOf(rs.getString("estado")));
                inventario.add(i);
                }
                
            }
            
            tablaInventarios.setItems(inventario);
            tablaInventarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
    }

    @FXML
    private void regresar(ActionEvent event) {
        BaseDeDatos.ventana.setScene(BaseDeDatos.sc);
        
    }

    @FXML
    private void modificar(ActionEvent event) {
        lbCodigo.setVisible(true);
        lbDescripcion.setVisible(true);
        lbUbicacion.setVisible(true);
        lbCategoria.setVisible(true);
        txCodigo.setVisible(true);
        txDescripcion.setVisible(true);
        txUbicacion.setVisible(true);
        txCategoria.setVisible(true);
        modify.setVisible(true);
        Inventario i=tablaInventarios.getSelectionModel().getSelectedItem();
        txCodigo.setText(String.valueOf(i.getCodigoInventario()));
        txDescripcion.setText(i.getDescripcion());
        txUbicacion.setText(i.getUbicacion());
        txCategoria.setText(i.getCategoria());
        
    }

    @FXML
    private void eliminar(ActionEvent event) throws SQLException {
        Inventario i2=tablaInventarios.getSelectionModel().getSelectedItem();
        //String sql="DELETE FROM inventario WHERE codigoInventario="+i2.getCodigoInventario()+";";
        String sql="update inventario set estado=0 where codigoInventario="+i2.getCodigoInventario()+";";
        Statement st=conexion.createStatement();
        st.execute(sql);
        String sql2="SELECT * FROM inventario ";
            //Statement st2=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql2);
            ObservableList<Inventario> inventario=FXCollections.observableArrayList();
            
        while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoInv=Integer.valueOf(rs.getString("codigoInventario"));
                String descripcionInv=rs.getString("descripcion");
                String ubicacionInv=rs.getString("ubicacion");
                String categoriaInv=rs.getString("categoria");
                
                Inventario i=new Inventario(codigoInv,descripcionInv,ubicacionInv,categoriaInv,Integer.valueOf(rs.getString("estado")));
                inventario.add(i);
                }
                
            }
            
            tablaInventarios.setItems(inventario);
            tablaInventarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
    }

    @FXML
    private void modificarElemento(ActionEvent event) throws SQLException {
        String sql="UPDATE inventario SET descripcion='"+txDescripcion.getText()+"',ubicacion='"+txUbicacion.getText()+
                "',categoria='"+txCategoria.getText()+
                "' WHERE codigoInventario="+txCodigo.getText()+";";
        Statement st=conexion.createStatement();
        st.execute(sql);
        String sql2="SELECT * FROM inventario ";
            Statement st2=conexion.createStatement();
            ResultSet rs=st2.executeQuery(sql2);
            ObservableList<Inventario> inventario=FXCollections.observableArrayList();
            
        while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoInv=Integer.valueOf(rs.getString("codigoInventario"));
                String descripcionInv=rs.getString("descripcion");
                String ubicacionInv=rs.getString("ubicacion");
                String categoriaInv=rs.getString("categoria");
                
                Inventario i=new Inventario(codigoInv,descripcionInv,ubicacionInv,categoriaInv,Integer.valueOf(rs.getString("estado")));
                inventario.add(i);
                }
                
            }
            
            tablaInventarios.setItems(inventario);
            tablaInventarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        lbCodigo.setVisible(false);
        lbDescripcion.setVisible(false);
        lbUbicacion.setVisible(false);
        lbCategoria.setVisible(false);
        txCodigo.setVisible(false);
        txDescripcion.setVisible(false);
        txUbicacion.setVisible(false);
        txCategoria.setVisible(false);
        modify.setVisible(false);
        
    }
    
    


    
    
}
