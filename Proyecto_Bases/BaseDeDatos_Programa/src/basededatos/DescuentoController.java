/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import static basededatos.BaseDeDatos.conexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class DescuentoController implements Initializable {

    @FXML
    private TextField cantidad;
    @FXML
    private TextField dscto;
    @FXML
    private TextField porcentaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void calcular(ActionEvent event) throws SQLException {
        String sql="call calcularDescuento("+porcentaje.getText()+","+cantidad.getText()+",@r);";
        String sql2="select @r as total";
        Statement st=conexion.createStatement();
        st.execute(sql);
        ResultSet rs=st.executeQuery(sql2);
        String descuento=null;
        while(rs.next()){
            descuento=rs.getString("total");
        }
        dscto.setText(descuento);
    }
    
}
