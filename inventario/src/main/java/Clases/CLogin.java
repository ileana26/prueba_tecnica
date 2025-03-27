/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import com.mycompany.inventario.Vistas.Almacenista;
import com.mycompany.inventario.Vistas.MenuPrincipal;
import java.io.Console;
import static java.lang.System.console;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Hp
 */
public class CLogin {
    
    public void ValidarUsuario(JTextField usuario, JPasswordField contrasenia){
        try{
            ResultSet rs = null;
            ResultSet sr = null;
            PreparedStatement ps = null;
            PreparedStatement sp = null;
            Clases.conexion objetoConexion = new Clases.conexion();
            
            String consulta = "select * from usuarios where usuarios.correo = (?) and usuarios.contrasenia = (?);";
            String pase = "select c.idRol from rol as c inner join usuarios as p on c.idRol = p.idRol where p.correo=(?) and p.contrasenia = (?) and c.idRol = 1";

            ps = objetoConexion.conectar().prepareStatement(consulta);
            sp = objetoConexion.conectar().prepareStatement(pase);
             
             //convertir contraseña
             String contra = String .valueOf(contrasenia.getPassword());
             
             ps.setString(1, usuario.getText());
             ps.setString(2, contra);
             
             sp.setString(1, usuario.getText());
             sp.setString(2, contra);
             
             rs = ps.executeQuery();
             sr = sp.executeQuery();
             
            /* if(rs.next()){
                 JOptionPane.showMessageDialog(null, "El usuario es correcto");
             }else{
                 JOptionPane.showMessageDialog(null, "El usuario no es correcto");
             }*/
             
             if(sr.next()){
                 MenuPrincipal objetoMenu = new MenuPrincipal();
                 objetoMenu.setVisible(true);
             }else
             {
                Almacenista objetoAlmacenista = new Almacenista();
                 objetoAlmacenista.setVisible(true);
             }
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: "+e.toString());
        }
    }
    
}
