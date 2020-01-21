/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import TDAs.Proveedor;
import static basededatos.BaseDeDatos.conexion;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class MostrarProveedoresController implements Initializable {

    @FXML
    private TableView<Proveedor> tablaProveedores;
    @FXML
    private TableColumn<String, Proveedor> cedula;
    @FXML
    private TableColumn<String, Proveedor> nombre;
    @FXML
    private TableColumn<String, Proveedor> apellido;
    @FXML
    private TableColumn<String, Proveedor> celular;
    @FXML
    private TableColumn<String, Proveedor> empresa;
    @FXML
    private TableColumn<String, Proveedor> direccion;
    @FXML
    private ComboBox<String> filtrarProveedores;
    @FXML
    private TextField filtrarVariable;
    @FXML
    private TableColumn<LocalDate, Proveedor> fecha;
    @FXML
    private TextField txCedula;
    @FXML
    private TextField txNombre;
    @FXML
    private TextField txApellido;
    @FXML
    private Label lbCedula;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellido;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbCelular;
    @FXML
    private Label lbEmpresa;
    @FXML
    private TextField txFecha;
    @FXML
    private TextField txCelular;
    @FXML
    private TextField txEmpresa;
    @FXML
    private Label lbDireccion;
    @FXML
    private TextField txDireccion;
    @FXML
    private Button modify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ObservableList<String> opciones=FXCollections.observableArrayList("Cedula","Nombre","Apellido","Celular","Empresa","Direccion");
            filtrarProveedores.setItems(opciones);
            
            cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            fecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
            celular.setCellValueFactory(new PropertyValueFactory<>("celular"));
            empresa.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresa"));
            direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            
            String sql="SELECT * FROM persona p inner join proveedor pr on pr.cedula= p.cedula ";
            Statement st=conexion.createStatement();
            ResultSet rt=st.executeQuery(sql);
            
            ObservableList<Proveedor> datos=FXCollections.observableArrayList();
            
            while(rt.next()){
                if(Integer.valueOf(rt.getString("estado"))!=0){
                    String cedulaP=rt.getString("cedula");
                String nombreP=rt.getString("nombre");
                String apellidoP=rt.getString("apellido");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rt.getString("fechaNacimiento").replace("\'","");
                LocalDate fecha_=LocalDate.parse(fe, formatter);
                
                String celularP=rt.getString("celular");
                String empresaP=rt.getString("nombreEmpresa");
                String direccionP=rt.getString("direccion");
                
                Proveedor p=new Proveedor(cedulaP,nombreP,apellidoP,fecha_,celularP,Integer.valueOf(rt.getString("estado")),empresaP,direccionP);
                datos.add(p);
                }
                
            }
            
            tablaProveedores.setItems(datos);
            tablaProveedores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MostrarProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void filtrarDatos(ActionEvent event) throws SQLException {
        
        String flt=null;
        if(filtrarVariable.getText().isEmpty())
            flt="SELECT * FROM persona p inner join proveedor pr on pr.cedula= p.cedula ";
        else if (filtrarProveedores.getValue().equals("Cedula"))
            flt="SELECT * FROM persona p inner join proveedor pr on pr.cedula= p.cedula where p.cedula like "+" \'"
                    +filtrarVariable.getText()+"%\' ;";
        else if(filtrarProveedores.getValue().equals("Nombre"))
            flt="SELECT * FROM persona p inner join proveedor pr on pr.cedula= p.cedula where p.nombre like "+" \'"
                    +filtrarVariable.getText()+"%\' ;";
        else if(filtrarProveedores.getValue().equals("Apellido"))
            flt="SELECT * FROM persona p inner join proveedor pr on pr.cedula= p.cedula where p.apellido like "+" \'"
                    +filtrarVariable.getText()+"%\' ;";
        else if(filtrarProveedores.getValue().equals("Celular"))
            flt="SELECT * FROM persona p inner join proveedor pr on pr.cedula= p.cedula where p.celular like "+" \'"
                    +filtrarVariable.getText()+"%\' ;";
        else if(filtrarProveedores.getValue().equals("Empresa"))
            flt="SELECT * FROM persona p inner join proveedor pr on pr.cedula= p.cedula where pr.nombreEmpresa like "+" \'"
                    +filtrarVariable.getText()+"%\' ;"; 
        else if(filtrarProveedores.getValue().equals("Direccion"))
            flt="SELECT * FROM persona p inner join proveedor pr on pr.cedula= p.cedula where pr.direccion like "+" \'"
                    +filtrarVariable.getText()+"%\' ;";
            
        
        Statement st2=conexion.createStatement();
        ResultSet rt=st2.executeQuery(flt);
        
        ObservableList<Proveedor> datos2=FXCollections.observableArrayList();
        while(rt.next()){
            if(Integer.valueOf(rt.getString("estado"))!=0){
                String cedulaP=rt.getString("cedula");
                String nombreP=rt.getString("nombre");
                String apellidoP=rt.getString("apellido");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rt.getString("fechaNacimiento").replace("\'","");
                LocalDate fecha_=LocalDate.parse(fe, formatter);
                
                String celularP=rt.getString("celular");
                String empresaP=rt.getString("nombreEmpresa");
                String direccionP=rt.getString("direccion");
                
                Proveedor p=new Proveedor(cedulaP,nombreP,apellidoP,fecha_,celularP,Integer.valueOf(rt.getString("estado")),empresaP,direccionP);
                datos2.add(p);
            }
                
            }
            
            
            tablaProveedores.setItems(datos2);
                
            
            tablaProveedores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        
        
        
        
    }

    @FXML
    private void regresar(ActionEvent event) {
        BaseDeDatos.ventana.setScene(BaseDeDatos.sc);
    }

    @FXML
    private void nuevoProveedor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("proveedores.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void modificar(ActionEvent event) {
        lbCedula.setVisible(true);
        lbNombre.setVisible(true);
        lbApellido.setVisible(true);
        lbFecha.setVisible(true);
        lbCelular.setVisible(true);
        lbEmpresa.setVisible(true);
        lbDireccion.setVisible(true);
        txCedula.setVisible(true);
        txNombre.setVisible(true);
        txApellido.setVisible(true);
        txFecha.setVisible(true);
        txCelular.setVisible(true);
        txEmpresa.setVisible(true);
        txDireccion.setVisible(true);
        modify.setVisible(true);
        Proveedor p=tablaProveedores.getSelectionModel().getSelectedItem();
        txCedula.setText(p.getCedula());
        txNombre.setText(p.getNombre());
        txApellido.setText(p.getApellido());
        txFecha.setText(String.valueOf(p.getFechaNacimiento()));
        txCelular.setText(p.getCelular());
        txEmpresa.setText(p.getNombreEmpresa());
        txDireccion.setText(p.getDireccion());
        
    }

    @FXML
    private void eliminar(ActionEvent event) throws SQLException {
        Proveedor p1=tablaProveedores.getSelectionModel().getSelectedItem();
        
        String sql1="call eliminarProveedor('"+p1.getCedula()+"')";
        Statement st=conexion.createStatement();
        st.execute(sql1);
        
        
        String sql3="SELECT * FROM persona p inner join proveedor c on p.cedula= c.cedula ";
        
            ResultSet rt=st.executeQuery(sql3);
            
            ObservableList<Proveedor> datos=FXCollections.observableArrayList();
            
            while(rt.next()){
                if(Integer.valueOf(rt.getString("estado"))!=0){
                    String cedulaP=rt.getString("cedula");
                String nombreP=rt.getString("nombre");
                String apellidoP=rt.getString("apellido");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rt.getString("fechaNacimiento").replace("\'","");
                LocalDate fecha_=LocalDate.parse(fe, formatter);
                
                String celularP=rt.getString("celular");
                String empresaP=rt.getString("nombreEmpresa");
                String direccionP=rt.getString("direccion");
                
                Proveedor p=new Proveedor(cedulaP,nombreP,apellidoP,fecha_,celularP,Integer.valueOf(rt.getString("estado")),empresaP,direccionP);
                datos.add(p);
                }
                
            }
            
            tablaProveedores.setItems(datos);
            tablaProveedores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
    }

    @FXML
    private void modificarElemento(ActionEvent event) throws SQLException {
        Proveedor c=tablaProveedores.getSelectionModel().getSelectedItem();
        
        String sql1="call modificarProveedor('"+c.getCedula()+"','"+txNombre.getText()+"','"+txApellido.getText()+
                "','"+txFecha.getText()+"','"+txDireccion.getText()+"','"+txEmpresa.getText()+"','"+txCelular.getText()+"');";
        
        Statement st=conexion.createStatement();
        st.execute(sql1);
        
        
        String sql3="SELECT * FROM persona p inner join proveedor c on p.cedula= c.cedula ";
        
            ResultSet rt=st.executeQuery(sql3);
            
            ObservableList<Proveedor> datos=FXCollections.observableArrayList();
            
            while(rt.next()){
                if(Integer.valueOf(rt.getString("estado"))!=0){
                    String cedulaP=rt.getString("cedula");
                String nombreP=rt.getString("nombre");
                String apellidoP=rt.getString("apellido");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rt.getString("fechaNacimiento").replace("\'","");
                LocalDate fecha_=LocalDate.parse(fe, formatter);
                
                String celularP=rt.getString("celular");
                String empresaP=rt.getString("nombreEmpresa");
                String direccionP=rt.getString("direccion");
                
                Proveedor p=new Proveedor(cedulaP,nombreP,apellidoP,fecha_,celularP,Integer.valueOf(rt.getString("estado")),empresaP,direccionP);
                datos.add(p);
                }
                
            }
            
            tablaProveedores.setItems(datos);
            tablaProveedores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        lbCedula.setVisible(false);
        lbNombre.setVisible(false);
        lbApellido.setVisible(false);
        lbFecha.setVisible(false);
        lbCelular.setVisible(false);
        lbEmpresa.setVisible(false);
        lbDireccion.setVisible(false);
        txCedula.setVisible(false);
        txNombre.setVisible(false);
        txApellido.setVisible(false);
        txFecha.setVisible(false);
        txCelular.setVisible(false);
        txEmpresa.setVisible(false);
        txDireccion.setVisible(false);
        modify.setVisible(false);
    }
    
}
