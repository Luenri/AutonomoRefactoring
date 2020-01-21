/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import TDAs.Producto;
import TDAs.Servicio;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//0912546589 
//1025478695
/**
 *
 * @author Luis
 */
public class BaseDeDatos extends Application {
    static Connection conexion;
    static Scene sc;
    static Stage ventana=new Stage();
    static LinkedList<Producto> productos=new LinkedList<>();
    static LinkedList<Servicio> servicios=new LinkedList<>();
    @Override
    public void start(Stage stage) throws Exception {
        conexion cn=new conexion();
        conexion=cn.getConnection();
        
        String sql="SELECT * FROM producto ";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){
                if(Integer.valueOf(rs.getString("estado"))!=0){
                int codigoP=Integer.valueOf(rs.getString("codigo"));
                String nombreP=rs.getString("nombrePro");
                double precioP=Double.valueOf(rs.getString("precioUnitario"));
                int inventarioP=Integer.valueOf(rs.getString("codInv"));
                Producto p=new Producto(codigoP,nombreP,precioP,inventarioP,Integer.valueOf(rs.getString("estado")));
                productos.add(p);
            }
                
            }
        
        String sql2="SELECT * FROM Servicio ";
            ResultSet rs2=st.executeQuery(sql2);
            while (rs2.next()){
                if(Integer.valueOf(rs2.getString("estado"))!=0){
                    int codigoSer=Integer.valueOf(rs2.getString("CodigoServicio"));
                String tipoSer=rs2.getString("tipo");
                double costoSer=Double.valueOf(rs2.getString("costo"));
                
                Servicio s=new Servicio(codigoSer,tipoSer,costoSer,Integer.valueOf(rs2.getString("estado")));
                servicios.add(s);
                }
                
                
            }
        
        /*if(c==null){
            JOptionPane.showMessageDialog(null," no conecta");
        }else{
            JOptionPane.showMessageDialog(null," conectada");
        }
        
    }*/
       
        
        Parent root = FXMLLoader.load(getClass().getResource("/TDAs/Menu_Principal.fxml"));
        
        sc = new Scene(root);
        ventana.setTitle("SISTEMA EMPRESA MULTICHECK");
        ventana.setScene(sc);
        ventana.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        launch(args);
        
    }
    
}
