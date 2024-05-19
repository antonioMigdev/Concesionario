/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controles;

import java.sql.*;

/**
 *
 * @author Usuario
 */
public class Controles {
    
  
    public Connection Conectar(){
        
        Connection cn=null;
        
       String url = "jdbc:oracle:thin:@localhost:49715:XE";
        String usuario = "programacion";
        String contraseña = "programacion";
        
        try {
            cn=DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexion correcta");
        }catch (SQLException ex){
            
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            
        }
        
        
        return cn;       
        
    }
    
    public void Desconectar (Connection cn){
    
    try {
        cn.close();
        
    }catch (SQLException ex){
        
        ex.printStackTrace();
            System.out.println(ex.getMessage());
    }    
    }
    
    
}
