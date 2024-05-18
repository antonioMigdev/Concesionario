/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import Controles.Controles;

/**
 *
 * @author Usuario
 */
public class CocheDAO {

    //creamos el metodo para insertar en la base de datos
    public int insertar(Coche c) {

        Controles conexion = new Controles();
        Connection cn = conexion.Conectar();

        //el objeto ps va a encargarse de hacer las consultas psql por medio de la clase PrepareStatement
        PreparedStatement ps = null;

        //variable para comprobar que se ha hecho bien la insercion
        int result = 0;

        try {
            ps = cn.prepareStatement("INSERT INTO concesionario VALUES (?,?,?)");

            ps.setInt(1, c.getNum_serie());
            ps.setString(2, c.getMarca());
            ps.setString(3, c.getModelo());

            //si toma valor 1 indica que se ha insertado bien los datos ?????
            result = ps.executeUpdate();

            System.out.println("Insercion correcta");

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            System.out.println("");
        } finally {
            try {
                ps.close();
                conexion.Desconectar(cn);;
            } catch (SQLException ex) {
            }
        }

        return result;

    }
    
    
     //creo metodo para buscar en la base de datos
    public ArrayList<Coche> buscar() {

        //Para conectarme y para acceder y buscar en la base de datos
        Controles conexion = new Controles();
        Connection cn = conexion.Conectar();

        //como voy  a devolver un array list  tengo que crear un objeto de eso
        ArrayList<Coche> listaCoches = new ArrayList<Coche>();
        
        //Statament es el objeto que usa java para hacer consultas y obtener resultados en una BD.
        //mas tarde tendre que cerrar el estatement y el resultset
      
        Statement st = null;
        
        
        // el ResulSet Proporciona una forma de acceder y recorrer las filas de datos devueltas por la consulta.
        ResultSet rs = null;

          //dentro del try catch para que no nos de error, los ponemos a null al principio y fuera del try
        //para mandar a la base de datos una selectçç
        try {
            //Statement = sentencia. Creo una sentencia st que es donde realizare la consulta a la bbdd
            //
            st = cn.createStatement();

            //el Resultset es un set pero de oracle. Devuelve los datos de la base de datos
            //los resultados del Resultset me los mete en un arraylist para en el main
            //trabajar con datos de java no de sql
            rs = st.executeQuery("Select * from concesionario");
            //la sentencia creada antes st hace la consulta a la base de datos que indico

            //en el while recorro el Resultset y voy añadiendo usuarios en la listaUsuarios.add
            while (rs.next()) {
                Coche u = new Coche(rs.getInt(1), rs.getString(2), rs.getString(3));
                listaCoches.add(u);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();
            System.out.println(ex.getMessage());

        } finally {

            try {
                //cierro el st y el st declarados a null antres del try.
                st.close();
                rs.close();

                //cierro la conexion a la bbdd despues de la consulta
                conexion.Desconectar(cn);
            } catch (SQLException ex) {
                System.out.println("Error al cerrar servicios");
            }

        }
        return listaCoches;
    }
    
    
    
    
    
    
    
    

}
