/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import TDAs.OrdenCompra;
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
public class MostrarComprasController implements Initializable {

    @FXML
    private TableView<OrdenCompra> tablaCompras;
    @FXML
    private TableColumn<Integer,OrdenCompra> codigo;
    @FXML
    private TableColumn<String,OrdenCompra> cliente;
    @FXML
    private TableColumn<LocalDate, OrdenCompra> fecha;
    @FXML
    private TableColumn<Double,OrdenCompra> total;
    @FXML
    private ComboBox<String> filtrar;
    @FXML
    private TextField filtrador;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbCliente;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbTotal;
    @FXML
    private TextField txCodigo;
    @FXML
    private TextField txCliente;
    @FXML
    private TextField txFecha;
    @FXML
    private TextField txTotal;
    @FXML
    private Button modify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ObservableList<String> opciones=FXCollections.observableArrayList("Cliente","Fecha","Precio");
            filtrar.setItems(opciones);
            
            codigo.setCellValueFactory(new PropertyValueFactory<>("idCompra"));
            cliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
            fecha.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
            total.setCellValueFactory(new PropertyValueFactory<>("total"));
            
            
            String sql="SELECT * FROM ordencompra ";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql);
            ObservableList<OrdenCompra> compras=FXCollections.observableArrayList();
            while(rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int cod=Integer.valueOf(rs.getString("idCompra"));
                String clt=rs.getString("idCliente");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rs.getString("fecha").replace("\'","");
                LocalDate fech=LocalDate.parse(fe, formatter);
                double tot=Double.valueOf(rs.getString("total"));
                
                OrdenCompra o=new OrdenCompra(cod,clt,fech,tot,Integer.valueOf(rs.getString("estado")));
                compras.add(o);
                }
                
                
            }
            tablaCompras.setItems(compras);
            tablaCompras.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MostrarComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }    

    @FXML
    private void agregarCompra(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("compras.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void regresar(ActionEvent event) {
        BaseDeDatos.ventana.setScene(BaseDeDatos.sc);
    }

    @FXML
    private void filtrarDatos(ActionEvent event) throws SQLException {
        String flt=null;
        if(filtrador.getText().isEmpty())
            flt="SELECT * FROM ordencompra ";
        else if (filtrar.getValue().equals("Cliente"))
            flt="SELECT * FROM ordencompra  where idCliente like "+" \'"
                    +filtrador.getText()+"%\' ;";
        else if (filtrar.getValue().equals("Fecha"))
            flt="SELECT * FROM ordencompra  where fecha = "+" \'"
                    +filtrador.getText()+"\' ;";
        else if(filtrar.getValue().equals("Precio"))
            flt="SELECT * FROM ordencompra  where total = "+
                    filtrador.getText()+";";
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(flt);
        ObservableList<OrdenCompra> compras=FXCollections.observableArrayList();
        while(rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int cod=Integer.valueOf(rs.getString("idCompra"));
                String clt=rs.getString("idCliente");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rs.getString("fecha").replace("\'","");
                LocalDate fech=LocalDate.parse(fe, formatter);
                double tot=Double.valueOf(rs.getString("total"));
                
                OrdenCompra o=new OrdenCompra(cod,clt,fech,tot,Integer.valueOf(rs.getString("estado")));
                compras.add(o);
                }
                
            }
            tablaCompras.setItems(compras);
            tablaCompras.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
    }

    @FXML
    private void modificar(ActionEvent event) {
        lbCodigo.setVisible(true);
        lbCliente.setVisible(true);
        lbFecha.setVisible(true);
        lbTotal.setVisible(true);
        txCodigo.setVisible(true);
        txCliente.setVisible(true);
        txFecha.setVisible(true);
        txTotal.setVisible(true);
        modify.setVisible(true);
        OrdenCompra o=tablaCompras.getSelectionModel().getSelectedItem();
        txCodigo.setText(String.valueOf(o.getIdCompra()));
        txCliente.setText(o.getIdCliente());
        txFecha.setText(String.valueOf(o.getFechaCompra()));
        txTotal.setText(String.valueOf(o.getTotal()));
    }

    @FXML
    private void eliminar(ActionEvent event) throws SQLException {
        OrdenCompra c1=tablaCompras.getSelectionModel().getSelectedItem();
        String sql="update ordencompra set estado=0 where idcompra="+c1.getIdCompra()+";";
        Statement st=conexion.createStatement();
        st.execute(sql);
        String sql2="SELECT * FROM ordencompra ";
            
            ResultSet rs=st.executeQuery(sql2);
            ObservableList<OrdenCompra> compras=FXCollections.observableArrayList();
        while(rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int cod=Integer.valueOf(rs.getString("idCompra"));
                String clt=rs.getString("idCliente");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rs.getString("fecha").replace("\'","");
                LocalDate fech=LocalDate.parse(fe, formatter);
                double tot=Double.valueOf(rs.getString("total"));
                
                OrdenCompra o=new OrdenCompra(cod,clt,fech,tot,Integer.valueOf(rs.getString("estado")));
                compras.add(o);
                }
                
            }
            tablaCompras.setItems(compras);
            tablaCompras.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
    }

    @FXML
    private void modificarElemento(ActionEvent event) throws SQLException {
        String sql="UPDATE ordencompra SET idCliente='"+txCliente.getText()+"',fecha='"+txFecha.getText()+"',total="+txTotal.getText()
                +" WHERE idCompra="+txCodigo.getText()+";";
        Statement st=conexion.createStatement();
        st.execute(sql);
        String sql2="SELECT * FROM ordencompra ";
            Statement st2=conexion.createStatement();
            ResultSet rs=st2.executeQuery(sql2);
            ObservableList<OrdenCompra> compras=FXCollections.observableArrayList();
        while(rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                    int cod=Integer.valueOf(rs.getString("idCompra"));
                String clt=rs.getString("idCliente");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rs.getString("fecha").replace("\'","");
                LocalDate fech=LocalDate.parse(fe, formatter);
                double tot=Double.valueOf(rs.getString("total"));
                
                OrdenCompra o=new OrdenCompra(cod,clt,fech,tot,Integer.valueOf(rs.getString("estado")));
                compras.add(o);
                }
                
            }
            tablaCompras.setItems(compras);
            tablaCompras.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        lbCodigo.setVisible(false);
        lbCliente.setVisible(false);
        lbFecha.setVisible(false);
        lbTotal.setVisible(false);
        txCodigo.setVisible(false);
        txCliente.setVisible(false);
        txFecha.setVisible(false);
        txTotal.setVisible(false);
        modify.setVisible(false);
    }
    
}
