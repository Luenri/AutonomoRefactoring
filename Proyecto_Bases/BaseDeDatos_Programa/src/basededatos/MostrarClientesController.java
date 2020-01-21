   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import TDAs.Cliente;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import static basededatos.BaseDeDatos.conexion;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author Luis
 */
public class MostrarClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private ComboBox<String> filtrado;
    @FXML
    private TextField opcionFiltrado;
    @FXML
    private TableColumn<Cliente, String> direccion;
    @FXML
    private TableColumn<Cliente, String> cedula;
    
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnRegresar;
    @FXML
    private TableColumn<Cliente, String> nombre;
    @FXML
    private TableColumn<Cliente, String> apellido;
    @FXML
    private TableColumn<Cliente, String> celular;
    @FXML
    private Pane parent;
    @FXML
    private TableColumn<Cliente, LocalDate> fecha;
    @FXML
    private Label lbCedula;
    @FXML
    private TextField txCedula;
    @FXML
    private TextField txNombre;
    @FXML
    private TextField txApellido;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellido;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbCelular;
    @FXML
    private Label lbDireccion;
    @FXML
    private TextField txFecha;
    @FXML
    private TextField txcelular;
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
            
            ObservableList<String> opciones=FXCollections.observableArrayList("Cedula","Nombre","Apellido","Celular","Direccion");
            filtrado.setItems(opciones);
            


            String sql="SELECT * FROM persona p inner join cliente c on p.cedula= c.cedula ";
            Statement st=conexion.createStatement();
            
            
            ResultSet rt=st.executeQuery(sql);
            cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            fecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
            celular.setCellValueFactory(new PropertyValueFactory<>("celular"));
            direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            
            
            ObservableList<Cliente> datos=FXCollections.observableArrayList();
            
            
            while(rt.next()){
                if (Integer.valueOf(rt.getString("estado"))!=0){
                    String cedula_=rt.getString("cedula");
                String nombre_=rt.getString("nombre");
                String apellido_=rt.getString("Apellido");
                
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rt.getString("fechaNacimiento").replace("\'","");
                LocalDate fecha_=LocalDate.parse(fe, formatter);
                
                String celular_=rt.getString("celular");
                String direccion_=rt.getString("direccion");
                datos.add(new Cliente(cedula_,nombre_,apellido_,fecha_,celular_,Integer.valueOf(rt.getString("estado")),direccion_));
                }
                
                
            }
            
            
            tablaClientes.setItems(datos);
                
            
            tablaClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
           
            
                    } catch (SQLException ex) {
            Logger.getLogger(MostrarClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void filttrarDatos(ActionEvent event) throws SQLException {
        String flt=null;
        if(opcionFiltrado.getText().isEmpty())
            flt="SELECT * FROM persona p inner join cliente c on p.cedula= c.cedula ";
        else if (filtrado.getValue().equals("Cedula"))
            flt="SELECT * FROM persona p inner join cliente c on p.cedula= c.cedula where p.cedula like "+" \'"
                    +opcionFiltrado.getText()+"%\' ;";
        else if(filtrado.getValue().equals("Nombre"))
            flt="SELECT * FROM persona p inner join cliente c on p.cedula= c.cedula where p.nombre like "+" \'"
                    +opcionFiltrado.getText()+"%\' ;";
        else if(filtrado.getValue().equals("Apellido"))
            flt="SELECT * FROM persona p inner join cliente c on p.cedula= c.cedula where p.apellido like "+" \'"
                    +opcionFiltrado.getText()+"%\' ;";
        else if(filtrado.getValue().equals("Celular"))
            flt="SELECT * FROM persona p inner join cliente c on p.cedula= c.cedula where p.celular like "+" \'"
                    +opcionFiltrado.getText()+"%\' ;";
        else if(filtrado.getValue().equals("Direccion"))
            flt="SELECT * FROM persona p inner join cliente c on p.cedula= c.cedula where c.direccion like "+" \'"
                    +opcionFiltrado.getText()+"%\' ;";
            
        
        Statement st2=conexion.createStatement();
        ResultSet rt=st2.executeQuery(flt);
        
        ObservableList<Cliente> datos2=FXCollections.observableArrayList();
        while(rt.next()){
            if (Integer.valueOf(rt.getString("estado"))!=0){
                String cedula_=rt.getString("cedula");
                String nombre_=rt.getString("nombre");
                String apellido_=rt.getString("Apellido");
                
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rt.getString("fechaNacimiento").replace("\'","");
                LocalDate fecha_=LocalDate.parse(fe, formatter);
                
                String celular_=rt.getString("celular");
                String direccion_=rt.getString("direccion");
                datos2.add(new Cliente(cedula_,nombre_,apellido_,fecha_,celular_,Integer.valueOf(rt.getString("estado")),direccion_));
            }
                
                
            }
            
            
            tablaClientes.setItems(datos2);
                
            
            tablaClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        
        
    }

    @FXML
    private void agregarCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("clientes.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void regresar(ActionEvent event) {
        BaseDeDatos.ventana.setScene(BaseDeDatos.sc);
    }

    @FXML
    private void modificar(ActionEvent event) {
        lbCedula.setVisible(true);
        lbNombre.setVisible(true);
        lbApellido.setVisible(true);
        lbFecha.setVisible(true);
        lbCelular.setVisible(true);
        lbDireccion.setVisible(true);
        txCedula.setVisible(true);
        txNombre.setVisible(true);
        txApellido.setVisible(true);
        txFecha.setVisible(true);
        txcelular.setVisible(true);
        txDireccion.setVisible(true);
        modify.setVisible(true);
        Cliente c=tablaClientes.getSelectionModel().getSelectedItem();
        txCedula.setText(c.getCedula());
        txNombre.setText(c.getNombre());
        txApellido.setText(c.getApellido());
        txFecha.setText(String.valueOf(c.getFechaNacimiento()));
        txcelular.setText(c.getCelular());
        txDireccion.setText(c.getDireccion());
        
        
    }

    @FXML
    private void eliminar(ActionEvent event) throws SQLException {
        Cliente c1=tablaClientes.getSelectionModel().getSelectedItem();
        Statement st=conexion.createStatement();
        
        String sql1="Call eliminarCliente('"+c1.getCedula()+"')";
        st.execute(sql1);
        
        
        String sql3="SELECT * FROM persona p inner join cliente c on p.cedula= c.cedula ";
            
            
            ResultSet rt=st.executeQuery(sql3);
            cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            fecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
            celular.setCellValueFactory(new PropertyValueFactory<>("celular"));
            direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            
            
            ObservableList<Cliente> datos=FXCollections.observableArrayList();
            
            
            while(rt.next()){
                if(Integer.valueOf(rt.getString("estado"))!=0){
                    String cedula_=rt.getString("cedula");
                String nombre_=rt.getString("nombre");
                String apellido_=rt.getString("Apellido");
                
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rt.getString("fechaNacimiento").replace("\'","");
                LocalDate fecha_=LocalDate.parse(fe, formatter);
                
                String celular_=rt.getString("celular");
                String direccion_=rt.getString("direccion");
                datos.add(new Cliente(cedula_,nombre_,apellido_,fecha_,celular_,Integer.valueOf(rt.getString("estado")),direccion_));
                }
                
                
            }
            
            
            tablaClientes.setItems(datos);
                
            
            tablaClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void modificarElemento(ActionEvent event) throws SQLException {
        Cliente c=tablaClientes.getSelectionModel().getSelectedItem();
        
        Statement st=conexion.createStatement();
        
        String sql1="call modificarCliente('"+c.getCedula()+"','"+txNombre.getText()+"','"+txApellido.getText()+"','"+
                txFecha.getText()+"','"+txDireccion.getText()+"','"+txcelular.getText()+"');";
        
        
        st.execute(sql1);
        
        
        String sql3="SELECT * FROM persona p inner join cliente c on p.cedula= c.cedula ";
            
            
            
            ResultSet rt=st.executeQuery(sql3);
            cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            fecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
            celular.setCellValueFactory(new PropertyValueFactory<>("celular"));
            direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            
            
            ObservableList<Cliente> datos=FXCollections.observableArrayList();
            
            
            while(rt.next()){
                if(Integer.valueOf(rt.getString("estado"))!=0){
                    String cedula_=rt.getString("cedula");
                String nombre_=rt.getString("nombre");
                String apellido_=rt.getString("Apellido");
                
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rt.getString("fechaNacimiento").replace("\'","");
                LocalDate fecha_=LocalDate.parse(fe, formatter);
                
                String celular_=rt.getString("celular");
                String direccion_=rt.getString("direccion");
                datos.add(new Cliente(cedula_,nombre_,apellido_,fecha_,celular_,Integer.valueOf(rt.getString("estado")),direccion_));
                }
                
            }
            
            
            tablaClientes.setItems(datos);
                
            
            tablaClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        lbCedula.setVisible(false);
        lbNombre.setVisible(false);
        lbApellido.setVisible(false);
        lbFecha.setVisible(false);
        lbCelular.setVisible(false);
        lbDireccion.setVisible(false);
        txCedula.setVisible(false);
        txNombre.setVisible(false);
        txApellido.setVisible(false);
        txFecha.setVisible(false);
        txcelular.setVisible(false);
        txDireccion.setVisible(false);
        modify.setVisible(false);
    }
    
}
