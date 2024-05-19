/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controles;

import Modelo.Coche;
import Modelo.CocheDAO;
import static java.lang.Integer.parseInt;
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
    @FXML
    private Button listar;

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
            
            
            busquedaC = cDao.listar();
            
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
        
        Coche c1 = new Coche();
        c1.setNum_serie(parseInt(num_serie.getText()));
        
        //quiero conectar con base de datos asi que creo objeto dao
        CocheDAO cDAO= new CocheDAO();
        cDAO.borrar(c1);   
      
        
         Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("INFORMACION");
                alert2.setHeaderText("Mensaje de información");
                alert2.setContentText("Se ha eliminado el"+num_serie.getText());
                alert2.showAndWait();
        
        
    }

    @FXML
    private void listar(ActionEvent event) {
        
        ArrayList<Coche> listaCoches = new ArrayList<Coche>();
        
        //como voy a conectar el boton con la base de datos tengo que crear un objeto dao
        CocheDAO cDAO = new CocheDAO();
        listaCoches=cDAO.listar();
        
        
        //PARA IMPRIMIRLO POR PANTALLA O CONSOLA:
        for (Coche cIntermedio :listaCoches){
            System.out.println(cIntermedio.toString()+"\n");
            
            
        }      
        
        
        
    }
    
    
}
