/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import TDAs.Cliente;
import TDAs.ClienteProveedor;
import TDAs.Reporte1;
import TDAs.Reporte2;
import TDAs.Reporte3;
import TDAs.Reporte4;
import TDAs.Reporte5;
import TDAs.Reporte6;
import static basededatos.BaseDeDatos.conexion;
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class MostrarReportesController implements Initializable {

    @FXML
    private Pane contenedor;
    @FXML
    private Label titulo;
    @FXML
    private VBox tabla;
    @FXML
    private Button buttonClienteProveedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reporte1(ActionEvent event) throws SQLException {
        tabla.getChildren().clear();
        TableView<Reporte1> tablaReporte1=new TableView<>();
        TableColumn<Reporte1, String> empresa=new TableColumn<>("Empresa");
        TableColumn<Reporte1, String> celular=new TableColumn<>("Celular");
        empresa.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresa"));
        celular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        tablaReporte1.getColumns().addAll(empresa,celular);
        
        String sql="Select * from reporte1;";
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(sql);
        ObservableList<Reporte1> datos=FXCollections.observableArrayList();
        while(rs.next()){
            String empresa_=rs.getString("nombreEmpresa");
            String celular_=rs.getString("celular");
            datos.add(new Reporte1(empresa_,celular_));
        }
        tablaReporte1.setItems(datos);
        tablaReporte1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        titulo.setText("Reporte de Distribuidores con su numero celular");
        tabla.getChildren().add(tablaReporte1);
        
        
    }

    @FXML
    private void reporte2(ActionEvent event) throws SQLException {
        tabla.getChildren().clear();
        TableView<Reporte2> tablaReporte2=new TableView<>();
        TableColumn<Reporte2, String> cedula=new TableColumn<>("Cedula");
        TableColumn<Reporte2, String> nombre=new TableColumn<>("Nombre");
        TableColumn<Reporte2, String> apellido=new TableColumn<>("Apellido");
        TableColumn<Reporte2, String> mes=new TableColumn<>("Mes");
        TableColumn<Reporte2, String> total=new TableColumn<>("Total");
        cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        mes.setCellValueFactory(new PropertyValueFactory<>("mes"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        tablaReporte2.getColumns().addAll(cedula,nombre,apellido,mes,total);
        String sql="Select * from reporte2;";
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(sql);
        ObservableList<Reporte2> datos=FXCollections.observableArrayList();
        while(rs.next()){
            String cedula_=rs.getString("cedula");
            String nombre_=rs.getString("nombre");
            String apellido_=rs.getString("apellido");
            String mes_=rs.getString("mes");
            String total_=rs.getString("total");
            datos.add(new Reporte2(cedula_,nombre_,apellido_,mes_,total_));
        }
        tablaReporte2.setItems(datos);
        tablaReporte2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        titulo.setText("Reporte de compras por mes de clientes");
        tabla.getChildren().add(tablaReporte2);
        
    }

    @FXML
    private void reporte3(ActionEvent event) throws SQLException {
        tabla.getChildren().clear();
        TableView<Reporte3> tablaReporte3=new TableView<>();
        TableColumn<Reporte3, String> nombre=new TableColumn<>("Nombre");
        TableColumn<Reporte3, String> mes=new TableColumn<>("Mes");
        TableColumn<Reporte3, String> total=new TableColumn<>("Total");
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        mes.setCellValueFactory(new PropertyValueFactory<>("mes"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        tablaReporte3.getColumns().addAll(nombre,mes,total);
        
        String sql="Select * from reporte3;";
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(sql);
        ObservableList<Reporte3> datos=FXCollections.observableArrayList();
        while(rs.next()){
            String nombre_=rs.getString("nombre");
            String mes_=rs.getString("mes");
            String total_=rs.getString("total");
            datos.add(new Reporte3(nombre_,mes_,total_));
        }
        tablaReporte3.setItems(datos);
        tablaReporte3.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        titulo.setText("Reporte de clientes con mayores compras por mes");
        tabla.getChildren().add(tablaReporte3);
        
    }

    @FXML
    private void reporte4(ActionEvent event) throws SQLException {
        tabla.getChildren().clear();
        TableView<Reporte4> tablaReporte4=new TableView<>();
        TableColumn<Reporte4, String> codigo=new TableColumn<>("Codigo Inventario");
        TableColumn<Reporte4, String> ubicacion=new TableColumn<>("Ubicacion");
        TableColumn<Reporte4, String> total=new TableColumn<>("Total de Productos");
        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        tablaReporte4.getColumns().addAll(codigo,ubicacion,total);
        
        String sql="Select * from reporte4;";
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(sql);
        ObservableList<Reporte4> datos=FXCollections.observableArrayList();
        while(rs.next()){
            String codigo_=rs.getString("codigoinventario");
            String ubicacion_=rs.getString("ubicacion");
            String total_=rs.getString("totalproductos");
            datos.add(new Reporte4(codigo_,ubicacion_,total_));
        }
        tablaReporte4.setItems(datos);
        tablaReporte4.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        titulo.setText("Reporte de cantidad de productos por inventarios");
        tabla.getChildren().add(tablaReporte4);
    }

    @FXML
    private void reporte5(ActionEvent event) throws SQLException {
        tabla.getChildren().clear();
        TableView<Reporte5> tablaReporte5=new TableView<>();
        TableColumn<Reporte5, String> nombre=new TableColumn<>("Nombre");
        TableColumn<Reporte5, String> apellido=new TableColumn<>("Apellido");
        TableColumn<Reporte5, String> codigo=new TableColumn<>("Codigo Producto");
        TableColumn<Reporte5, String> nombrep=new TableColumn<>("Nombre Producto");
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nombrep.setCellValueFactory(new PropertyValueFactory<>("nombrep"));
        tablaReporte5.getColumns().addAll(nombre,apellido,codigo,nombrep);
        
        String sql="Select * from reporte5;";
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(sql);
        ObservableList<Reporte5> datos=FXCollections.observableArrayList();
        while(rs.next()){
            String nombre_=rs.getString("nombre");
            String apellido_=rs.getString("apellido");
            String codigo_=rs.getString("codigo");
            String nombrep_=rs.getString("nombrepro");
            datos.add(new Reporte5(nombre_,apellido_,codigo_,nombrep_));
        }
        tablaReporte5.setItems(datos);
        tablaReporte5.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        titulo.setText("Reporte de Proveedores y Productos");
        tabla.getChildren().add(tablaReporte5);
        
    }

    @FXML
    private void reporte6(ActionEvent event) throws SQLException {
        tabla.getChildren().clear();
        TableView<Reporte6> tablaReporte6=new TableView<>();
        TableColumn<Reporte6, String> cedula=new TableColumn<>("Cedula");
        TableColumn<Reporte6, String> nombre=new TableColumn<>("Nombre");
        TableColumn<Reporte6, String> apellido=new TableColumn<>("Apellido");
        cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tablaReporte6.getColumns().addAll(cedula,nombre,apellido);
        String sql="Select * from reporte6;";
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(sql);
        ObservableList<Reporte6> datos=FXCollections.observableArrayList();
        while(rs.next()){
            String cedula_=rs.getString("cedula");
            String nombre_=rs.getString("nombre");
            String apellido_=rs.getString("apellido");
            datos.add(new Reporte6(cedula_,nombre_,apellido_));
        }
        tablaReporte6.setItems(datos);
        tablaReporte6.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        titulo.setText("Reporte de Clientes que que la empresa ha tenido");
        tabla.getChildren().add(tablaReporte6);
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Menu_Principal.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void buttonClienteProveedor(ActionEvent event) throws SQLException {
        tabla.getChildren().clear();
        TableView<ClienteProveedor> tablaClienteProveedor=new TableView<>();
        TableColumn<ClienteProveedor, String> cedula=new TableColumn<>("Cedula");
        TableColumn<ClienteProveedor,String> nombre=new TableColumn<>("Nombre");
        TableColumn<ClienteProveedor, String> apellido=new TableColumn<>("Apellido");
        cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tablaClienteProveedor.getColumns().addAll(cedula,nombre,apellido);
        String sql="Select * from clienteproveedor;";
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(sql);
        ObservableList<ClienteProveedor> datos=FXCollections.observableArrayList();
        while(rs.next()){
            String cedula_=rs.getString("cedula");
            String nombre_=rs.getString("nombre");
            String apellido_=rs.getString("apellido");
            datos.add(new ClienteProveedor(cedula_,nombre_,apellido_));
        }
        tablaClienteProveedor.setItems(datos);
        tablaClienteProveedor.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        titulo.setText("Reporte de Clientes que a la vez son Proveedores");
        tabla.getChildren().add(tablaClienteProveedor);
        
        
    }
    
}
