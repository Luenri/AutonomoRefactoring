/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import TDAs.OrdenCompra;
import TDAs.PedidoRealizado;
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
public class MostrarPedidosController implements Initializable {

    @FXML
    private TableColumn<Integer, PedidoRealizado> codigo;
    @FXML
    private TableColumn<String, PedidoRealizado> cedula;
    @FXML
    private TableColumn<LocalDate, PedidoRealizado> fecha;
    @FXML
    private TableColumn<Double, PedidoRealizado> total;
    @FXML
    private ComboBox<String> filtrador;
    @FXML
    private TextField filtro;
    @FXML
    private TableView<PedidoRealizado> tablaPedidos;
    @FXML
    private TextField txCodigo;
    @FXML
    private TextField txProveedor;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbProveedor;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbTotal;
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
            ObservableList<String> opciones=FXCollections.observableArrayList("Proveedor","Fecha","Precio");
            filtrador.setItems(opciones);
            
            codigo.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
            cedula.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
            fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            total.setCellValueFactory(new PropertyValueFactory<>("total"));
            
            
            String sql="SELECT * FROM pedidorealizado ";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql);
            ObservableList<PedidoRealizado> pedidos=FXCollections.observableArrayList();
            while(rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                int cod=Integer.valueOf(rs.getString("idPedido"));
                String clt=rs.getString("idProveedor");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rs.getString("fecha").replace("\'","");
                LocalDate fech=LocalDate.parse(fe, formatter);
                double tot=Double.valueOf(rs.getString("total"));
                
                PedidoRealizado p=new PedidoRealizado(cod,clt,fech,tot,Integer.valueOf(rs.getString("estado")));
                pedidos.add(p);
            }
                
            }
            tablaPedidos.setItems(pedidos);
            tablaPedidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MostrarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }    

    @FXML
    private void filtrarDatos(ActionEvent event) throws SQLException {
        String flt=null;
        if(filtro.getText().isEmpty())
            flt="SELECT * FROM pedidorealizado ";
        else if (filtrador.getValue().equals("Proveedor"))
            flt="SELECT * FROM pedidorealizado  where idProveedor like "+" \'"
                    +filtro.getText()+"%\' ;";
        else if (filtrador.getValue().equals("Fecha"))
            flt="SELECT * FROM pedidorealizado  where fecha = "+" \'"
                    +filtro.getText()+"\' ;";
        else if(filtrador.getValue().equals("Precio"))
            flt="SELECT * FROM pedidorealizado  where total = "+
                    filtro.getText()+";";
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(flt);
        ObservableList<PedidoRealizado> pedidos=FXCollections.observableArrayList();
        while(rs.next()){
            if(Integer.valueOf(rs.getString("estado"))!=0){
                int cod=Integer.valueOf(rs.getString("idPedido"));
                String clt=rs.getString("idProveedor");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rs.getString("fecha").replace("\'","");
                LocalDate fech=LocalDate.parse(fe, formatter);
                double tot=Double.valueOf(rs.getString("total"));
                
                PedidoRealizado p=new PedidoRealizado(cod,clt,fech,tot,Integer.valueOf(rs.getString("estado")));
                pedidos.add(p);
            }
                
                
            }
            tablaPedidos.setItems(pedidos);
            tablaPedidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void regresar(ActionEvent event) {
        BaseDeDatos.ventana.setScene(BaseDeDatos.sc);
    }

    @FXML
    private void agregarPedido(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("pedidos.fxml"));
        Scene sce=new Scene(root);
        BaseDeDatos.ventana.setScene(sce);
    }

    @FXML
    private void modificar(ActionEvent event) {
        lbCodigo.setVisible(true);
        lbProveedor.setVisible(true);
        lbFecha.setVisible(true);
        lbTotal.setVisible(true);
        txCodigo.setVisible(true);
        txProveedor.setVisible(true);
        txFecha.setVisible(true);
        txTotal.setVisible(true);
        modify.setVisible(true);
        PedidoRealizado o=tablaPedidos.getSelectionModel().getSelectedItem();
        txCodigo.setText(String.valueOf(o.getIdPedido()));
        txProveedor.setText(o.getIdProveedor());
        txFecha.setText(String.valueOf(o.getFecha()));
        txTotal.setText(String.valueOf(o.getTotal()));
    }

    @FXML
    private void eliminar(ActionEvent event) throws SQLException {
        PedidoRealizado c1=tablaPedidos.getSelectionModel().getSelectedItem();
        String sql="update pedidorealizado set estado=0 where idpedido="+c1.getIdPedido()+";";
        Statement st=conexion.createStatement();
        st.execute(sql);
        String sql2="SELECT * FROM pedidorealizado ";
            
            ResultSet rs=st.executeQuery(sql2);
            ObservableList<PedidoRealizado> compras=FXCollections.observableArrayList();
        while(rs.next()){
            if(Integer.valueOf(rs.getString("estado"))!=0){
                int cod=Integer.valueOf(rs.getString("idPedido"));
                String clt=rs.getString("idProveedor");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rs.getString("fecha").replace("\'","");
                LocalDate fech=LocalDate.parse(fe, formatter);
                double tot=Double.valueOf(rs.getString("total"));
                
                PedidoRealizado o=new PedidoRealizado(cod,clt,fech,tot,Integer.valueOf(rs.getString("estado")));
                compras.add(o);
            }
                
                
            }
            tablaPedidos.setItems(compras);
            tablaPedidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        
    }

    @FXML
    private void modificarElemento(ActionEvent event) throws SQLException {
        String sql="UPDATE pedidorealizado SET idProveedor='"+txProveedor.getText()+"',fecha='"+txFecha.getText()+"',total="+txTotal.getText()
                +" WHERE idPedido="+txCodigo.getText()+";";
        Statement st=conexion.createStatement();
        st.execute(sql);
        String sql2="SELECT * FROM pedidorealizado ";
            Statement st2=conexion.createStatement();
            ResultSet rs=st2.executeQuery(sql2);
            ObservableList<PedidoRealizado> compras=FXCollections.observableArrayList();
        while(rs.next()){
            if(Integer.valueOf(rs.getString("estado"))!=0){
                int cod=Integer.valueOf(rs.getString("idPedido"));
                String clt=rs.getString("idProveedor");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fe=rs.getString("fecha").replace("\'","");
                LocalDate fech=LocalDate.parse(fe, formatter);
                double tot=Double.valueOf(rs.getString("total"));
                
                PedidoRealizado o=new PedidoRealizado(cod,clt,fech,tot,Integer.valueOf(rs.getString("estado")));
                compras.add(o);
            }
                
                
            }
            tablaPedidos.setItems(compras);
            tablaPedidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        lbCodigo.setVisible(false);
        lbProveedor.setVisible(false);
        lbFecha.setVisible(false);
        lbTotal.setVisible(false);
        txCodigo.setVisible(false);
        txProveedor.setVisible(false);
        txFecha.setVisible(false);
        txTotal.setVisible(false);
        modify.setVisible(false);
    }
    
}
