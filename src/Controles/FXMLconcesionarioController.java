/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controles;

import Modelo.Coche;
import Modelo.CocheDAO;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLconcesionarioController implements Initializable {

    @FXML
    private TextField num_serie;
    @FXML
    private TextField marca;
    @FXML
    private TextField modelo;
    @FXML
    private Button guardar;
    @FXML
    private Button buscar;
    @FXML
    private Button actualizar;
    @FXML
    private Button borrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardarDatos(ActionEvent event) {
        
         if (num_serie.getText().isEmpty() || marca.getText().isEmpty() || modelo.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            
            
//            ArrayList<String> lista = new ArrayList<String>();
//            lista.add("Nombre");
//            lista.add("Dirección");
//            lista.add("Telefono");
//            alert.setHeaderText("Mensaje de error");
//            String salida = "";
//            for (String datos : lista) {
//                salida = salida + datos + "\n";
//            }
// alert.setContentText("Debe introducir los DATOS de cliente: \n" + salida);
            
           alert.setContentText("Debe introducir los datos dl coche:  \n numero de serie \n marca \n modelo");            
           alert.showAndWait();
           
        } else {
             
            Coche c = new Coche();
            c.setNum_serie(Integer.parseInt(num_serie.getText()));
            c.setMarca(marca.getText());
            c.setModelo(modelo.getText());
            
            
            
//creamos un objeto DAO para usarlo para meter los datos en la b.d.
            CocheDAO cDao = new CocheDAO();
            int result = cDao.insertar(c);
          if (result != 0) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("INFORMACION");
                alert2.setHeaderText("Mensaje de información");
                alert2.setContentText("Se ha insertado correctamente");
                alert2.showAndWait();
         }

         
    }
    }

    
    
    @FXML
    private void Buscar(ActionEvent event) {
        
       ArrayList<Coche> busquedaC = new ArrayList<Coche>();
        
     if (num_serie.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            
            
//            ArrayList<String> lista = new ArrayList<String>();
//            lista.add("Nombre");
//            lista.add("Dirección");
//            lista.add("Telefono");
//            alert.setHeaderText("Mensaje de error");
//            String salida = "";
//            for (String datos : lista) {
//                salida = salida + datos + "\n";
//            }
// alert.setContentText("Debe introducir los DATOS de cliente: \n" + salida);
            
           alert.setContentText("Debe introducir los datos del numero de serie");     
           alert.showAndWait();
        } else {
             
            Coche c = new Coche();
            c.setNum_serie(Integer.parseInt(num_serie.getText()));
           
            
//creamos un objeto DAO para usarlo para meter los datos en la b.d.
            CocheDAO cDao = new CocheDAO();
            
            
            busquedaC = cDao.buscar();
            
            String bucle= "";
            for(Coche c2 : busquedaC){
                
                bucle=bucle+" "+c2.toString();
            }
            
           System.out.println(bucle);
         
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("INFORMACION");
                alert2.setHeaderText("Mensaje de información");
                alert2.setContentText("El resultado es: \n"+bucle);
                alert2.showAndWait();
        
    }
     //return busquedaC;
    }   
        
        
        
        
    

    @FXML
    private void Actualizar(ActionEvent event) {
        
        
        
        
        
    }

  
    @FXML
    private void Borrar(ActionEvent event) {
        
        Controles conexion = new Controles();
        Connection cn = conexion.Conectar();

        PreparedStatement ps = null;
        try {
            //de las distintas opciones prepareStatement
            ps = cn.prepareStatement("Delete coche where num_serie = ?");
            ps.setString(1, u.getDni());
            ps.executeUpdate();
            System.out.println("Usuario eliminado");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());

        } finally {
            try {
                ps.close();
                conexion.Desconectar(cn);

            } catch (SQLException ex) {

                System.out.println("Error al cerrar recursos" + ex);
            }
        }
        
        
        
        
    }
}
