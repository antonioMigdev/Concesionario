/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import Controles.Controles;
import java.util.HashSet;

/**
 *
 * @author Usuario
 */
public class CocheDAO {
    //creamos el metodo para insertar en la base de datos
    public int insertar(Coche c) {
        //Creo un objeto conexion de mi clase Controles.
        Controles conexion = new Controles();
        //Creo un objeto cn de la ClaseJava Connectio que ENLAZO= a mi objeto java en su
        //metodo conectar.
        Connection cn = conexion.Conectar();

        //el objeto ps va a encargarse de hacer las consultas psql a la BBDD por medio de 
        // la ClaseJava PrepareStatement y devolverlas a java.
        PreparedStatement ps = null;

        //variable para comprobar que se ha hecho bien la insercion
        int result = 0;

        try {
            //El objeto que hara la consulta lo ENLAZO= con mi objeto de conexion cn
            ps = cn.prepareStatement("INSERT INTO concesionario VALUES (?,?,?)");
            
            //El objeto de consulta ps introducira en BD lo que obtenga de mi 
            //objeto c de la Clase Coche en sus get num_serie, get_marca y get_modelo.
            ps.setInt(1, c.getNum_serie());
            ps.setString(2, c.getMarca());
            ps.setString(3, c.getModelo());

            //si la variable result toma valor 1 indica que se ha insertado bien los datos ?????
            //¿para que la igualo a ps.executeUpdate?
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

    //creo metodo para listar en la base de datos
    public ArrayList<Coche> listar() {

        //Para conectarme y para acceder y listar en la base de datos
        Controles conexion = new Controles();
        Connection cn = conexion.Conectar();

        //Como voy  a devolver un array list  tengo que crear un objeto de eso
        ArrayList<Coche> listaCoches = new ArrayList<Coche>();

        //Statament es el objeto que usa java para hacer consultas y obtener resultados en una BD.
        //mas tarde tendre que cerrar el estatement y el resultset
        Statement st = null;

        // El ResulSet Proporciona una forma de acceder y recorrer las filas de datos devueltas por
        //  la consulta.
        ResultSet rs = null;

        //dentro del try catch para que no nos de error, los ponemos a null al principio y 
        //fuera del try para mandar a la base de datos una select
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


    
    public void borrar (Coche c){
        Controles conexion = new Controles();
        Connection cn = conexion.Conectar();

        PreparedStatement ps = null;
        
        try {
            //de las distintas opciones prepareStatement
            ps = cn.prepareStatement("Delete from concesionario where num_serie = ?");
            ps.setInt(1, c.getNum_serie());
            ps.executeUpdate();
            System.out.println("Coche eliminado");

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

    
 //creo metodo para modificar los datos de la bbdd
    public void modificar(Coche c) {

        //mismos pasos que el anterios solo modifico la parte que en lugar de para 
        //consulta es para modificar
        //Para conectarme y para acceder y buscar en la base de datos
        Controles conexion = new Controles();
        Connection cn = conexion.Conectar();

        //Para esto no usaremos un Statement ya que voy a lanzar una sentencia e incluye muchas '' que pueden dar error
        //mejor lo hago con Prepared
        //cuando me van a pasar algun dato uso preparedStatment
        PreparedStatement ps = null;

        try {
            //Statement = sentencia. Creo una sentencia st que es donde realizare la consulta a la bbdd
            //
            
            ps = cn.prepareStatement("UPDATE concesionario set marca = ?, modelo = ? where num_serie = ?");
            ps.setInt(3, c.getNum_serie());
            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModelo());
            ps.executeUpdate();
            System.out.println("modificado correctamente ");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());

        } finally {

            try {
                ps.close();
                cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }

        }

    }    
    
    
}
//    public Coche buscarPorNumSerie(Int numSerie) //devuelve un objeto Pelicula si el titulo coincide con el titulo

              
              
      
//    private HashSet<Coche> listaCoches;
//
//    Coche coche1 = null;
//    //CON ESTE FOR (CREO VARIABLE P DE CLASE PELICULA EN LA QUE IRA INTRODUCIENDO
//    //TEMPORALMENTE LO QUE LEE PARA COMPARARLO EN EL: DE LISTApELICULAS
//
//    
//        for (){Coche c: this.listaCoches
//        
//            ){
//        if (c.getNum_serie().equals(num_Serie)) {
//                coche1 = c;
//
//            }
//        }
//
//    }
//
//    if (p.getTitulo () 
//        .equals(titulo)) {
//                pel = p;
//    }
//}
//return pel;
//    }
//   
    
