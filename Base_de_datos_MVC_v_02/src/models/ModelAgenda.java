/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import views.ViewAgenda;
/**
 *
 * @author Jocelyn
 */
public class ModelAgenda {
    ModelAgenda modelAgenda;
    ViewAgenda viewAgenda;
    private Connection conexion;
    private Statement st;
    private ResultSet rs;

    private String nombre;
    private String email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     /**
     * Método que realiza las siguietnes acciones:
     * 1.- Conecta con la base agenda_mvc.
     * 2.- Consulta todo los registros de la tabla contactos.
     * 3.- Obtiene el nombre y el email y los guarda en las variables globales
     * nombre y email.
     */
    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "", "");
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM contactos;");
            rs.next();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }

    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro(){
        System.out.print("Programa accion moverPrimerRegistro");
        try {
            rs.first();
            initDB();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 004" + ex.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al siguiente registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverSiguienteRegistro(){
        System.out.print("Programa accion moverSiguienteRegistro");
        try {
            if (!rs.isLast()) {
                rs.next();
                initDB();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 002" + ex.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al anterior registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverAnteriorRegistro(){
        System.out.print("Programa accion moverAnteriorRegistro");
        try {
            if (!rs.isFirst()) {
                rs.previous();
                initDB();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 002" + ex.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro(){
        System.out.print("Programa accion moverUltimoRegistro");
        try {
            rs.last();
            initDB();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error 003"+ex.getMessage());
        }
    }
    public void seleccionarTodosLosRegistros() {
        try {
            String sql = "SELECT * FROM contactos;";
            PreparedStatement pst  = null;
            conexion.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            initDB();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 007" + ex.getMessage());
        }
    }
    public void crudNuevoRegistro(){
        System.out.print("Programa crud nuevoRegistro");
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
    }
    public void crudInsertarRegistro(){
        System.out.print("Programa crud InsertarRegistro");
        try {
        String sql = "INSERT into contactos(nombre,email) values(?,?)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1,viewAgenda.jtf_nombre.getText());
            pst.setString(2,viewAgenda.jtf_email.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro insertado exitosamente");
            seleccionarTodosLosRegistros();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 006" + ex.getMessage());
        }
    }
    public void crudModificarRegistro(){
        System.out.print("Programa crud ModificarRegistro");
        try {     
        String sql =("UPDATE contactos SET nombre=?,where email =?");
        PreparedStatement pst = conexion.prepareStatement(sql);        
        pst.setString(1,viewAgenda.jtf_nombre.getText());
        pst.setString(2,viewAgenda.jtf_email.getText());
        pst.executeUpdate();
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error 007" + ex.getMessage());
         }
    }
    public void crudEliminarRegistro(){
        System.out.print("Programa crud EliminarRegistro");

        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar los datos actuales?");

        if(confirmar == JOptionPane.YES_OPTION){
        try{
            String sql = "Delete * from contactos where nombre=?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1,viewAgenda.jtf_nombre.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se eliminó el registro exitosamente");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error 008" + ex.getMessage());
        }
    }
}
    private void initDB() {
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        }

    
}
