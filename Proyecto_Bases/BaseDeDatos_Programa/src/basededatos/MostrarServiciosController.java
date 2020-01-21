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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class MostrarServiciosController implements Initializable {

    @FXML
    private TableColumn<Servicio, Integer> codigo;
    @FXML
    private TableColumn<Servicio, String> tipo;
    @FXML
    private TableColumn<Servicio, Double> costo;
    @FXML
    private TextField filtrar;
    @FXML
    private TableView<Servicio> tablaServicios;
    @FXML
    private ComboBox<String> filtrarServicios;
    @FXML
    private TextField txCodigo;
    @FXML
    private TextArea txTipo;
    @FXML
    private TextField txCosto;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbTipo;
    @FXML
    private Label lbCosto;
    @FXML
    private Button modify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            ObservableList<String> opciones=FXCollections.observableArrayList("Tipo","Costo");
            filtrarServicios.setItems(opciones);
            
            
            codigo.setCellValueFactory(new PropertyValueFactory<>("codServicio"));
            tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            costo.setCellValueFactory(new PropertyValueFactory<>("costo"));
            
            String sql="SELECT * FROM Servicio ";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql);
            ObservableList<Servicio> service=FXCollections.observableArrayList();
            while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoSer=Integer.valueOf(rs.getString("CodigoServicio"));
                String tipoSer=rs.getString("tipo");
                double costoSer=Double.valueOf(rs.getString("costo"));
                
                Servicio s=new Servicio(codigoSer,tipoSer,costoSer,Integer.valueOf(rs.getString("estado")));
                service.add(s);
                }
                
                
            }
            
            tablaServicios.setItems(service);
            tablaServicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MostrarServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    


    @FXML
    private void filtrarDatos(ActionEvent event) throws SQLException {
        String flt=null;
        if(filtrar.getText().isEmpty())
            flt="SELECT * FROM Servicio ";
        else if (filtrarServicios.getValue().equals("Tipo"))
            flt="SELECT * FROM servicio  where tipo like "+" \'"
                    +filtrar.getText()+"%\' ;";
        else if(filtrarServicios.getValue().equals("Costo"))
            flt="SELECT * FROM servicio  where costo = "+
                    filtrar.getText()+";";
        Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(flt);
            ObservableList<Servicio> service=FXCollections.observableArrayList();
            while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int codigoSer=Integer.valueOf(rs.getString("CodigoServicio"));
                String tipoSer=rs.getString("tipo");
                double costoSer=Double.valueOf(rs.getString("costo"));
                
                Servicio s=new Servicio(codigoSer,tipoSer,costoSer,Integer.valueOf(rs.getString("estado")));
                service.add(s);
                }
                
            }
            
            tablaServicios.setItems(service);
            tablaServicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
    }

    @FXML
    private void regresar(ActionEvent event) {
        BaseDeDatos.ventana.setScene(BaseDeDatos.sc);
    }

    @FXML
    private void agregarServicio(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("servicios.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
           
    }

    @FXML
    private void modificar(ActionEvent event) {
        txCodigo.setVisible(true);
        txTipo.setVisible(true);
        txCosto.setVisible(true);
        lbCodigo.setVisible(true);
        lbTipo.setVisible(true);
        lbCosto.setVisible(true);
        modify.setVisible(true);
        Servicio s=tablaServicios.getSelectionModel().getSelectedItem();
        txCodigo.setText(String.valueOf(s.getCodServicio()));
        txTipo.setText(s.getTipo());
        txCosto.setText(String.valueOf(s.getCosto()));
        
    }

    @FXML
    private void eliminar(ActionEvent event) throws SQLException {
        servicios.clear();
        Servicio s1=tablaServicios.getSelectionModel().getSelectedItem();
        String sql="update servicio set estado=0 WHERE codigoServicio="+s1.getCodServicio()+";";
        Statement st=conexion.createStatement();
        st.execute(sql);
        String sql2="SELECT * FROM Servicio ";
            Statement st2=conexion.createStatement();
            ResultSet rs=st2.executeQuery(sql2);
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
            
        tablaServicios.setItems(service);
        tablaServicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void modificarElemento(ActionEvent event) throws SQLException {
        servicios.clear();
        String sql="UPDATE servicio SET tipo='"+txTipo.getText()+"',costo="+txCosto.getText()+
                " WHERE codigoServicio="+txCodigo.getText()+";";
        Statement st=conexion.createStatement();
        st.execute(sql);
        String sql2="SELECT * FROM Servicio ";
            Statement st2=conexion.createStatement();
            ResultSet rs=st2.executeQuery(sql2);
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
            
        tablaServicios.setItems(service);
        tablaServicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        txCodigo.setVisible(false);
        txTipo.setVisible(false);
        txCosto.setVisible(false);
        lbCodigo.setVisible(false);
        lbTipo.setVisible(false);
        lbCosto.setVisible(false);
        modify.setVisible(false);
        
    }

    
    
    
    
    
}
