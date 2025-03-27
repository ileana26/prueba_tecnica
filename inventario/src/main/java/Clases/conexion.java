/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Hp
 */
public class conexion {
    Connection conexion;
    
    String usuario="root";
    String contrasenia="ileanarivera26";
    String db="productos";
    String ip="localhost";
    String puerto="3306";

String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+db;

public Connection conectar(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(cadena,usuario,contrasenia);
       // JOptionPane.showMessageDialog(null,"Se conecto exitosamente");
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"No se pudo conectar"+e.toString());
    }
    return conexion;
}

   //actualizar 
     
     public void ActualizarP (String id,JTextField jcantidad){
         
         
         String query = "update inventario set productos_salida='"+jcantidad.getText()+"' where idProducto = '"+id+"'";
         Statement st;
        conexion con = new conexion();
        
        Connection conexion = con.conectar();
        
        try{
        st = conexion.createStatement();
        int rs = st.executeUpdate(query);
        JOptionPane.showMessageDialog(null, "Producto actualizado");
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "error"+ e.toString());
        }
        
     }
     
     //actualizar p admin
     
     public void ActualizarPAdmin (String id,JTextField jnombre, JTextField jcantidad, JTextField jprecio,JTextField jestatus){
         
         String query = "update inventario, productos set productos.nombre = '"+jnombre.getText()+"', inventario.cantidad='"+jcantidad.getText()+"', inventario.estatus='"+jestatus.getText()+"', productos.precio='"+jprecio.getText()+"' where inventario.idProducto = '"+id+"' and productos.idProducto = '"+id+"'";
         Statement st;
        conexion con = new conexion();
        
        Connection conexion = con.conectar();
        
        try{
        st = conexion.createStatement();
        int rs = st.executeUpdate(query);
        JOptionPane.showMessageDialog(null, "Producto actualizado");
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "error"+ e.toString());
        }
        
     }
     
     //actualizar p admin
     
     public void AgregarP (JTextField jnombre, JTextField jcantidad, JTextField jprecio,JTextField jestatus){
         
        try{
            Connection conecta = conectar();
            CallableStatement proc = conecta.prepareCall("insert into productos  (nombre, precio) values (?,?)");
            CallableStatement proc2 = conecta.prepareCall("insert into inventario (producto, cantidad, estatus) values (?,?,?)");
            proc.setString(1, jnombre.getText());
            proc2.setString(1, jnombre.getText());
            proc.setString(2, jprecio.getText());
            proc2.setString(2, jcantidad.getText());
            proc2.setString(3, jestatus.getText());
            proc.execute();
            proc2.execute();
        JOptionPane.showMessageDialog(null, "Producto guardado");
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "error"+ e.toString());
        }
        
       
     }
     
  
    
}



