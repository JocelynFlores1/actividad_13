package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Jocelyn
 */
public class ControllerAgenda {
   ModelAgenda modelAgenda;
    ViewAgenda viewAgenda;
    private ResultSet rs;
    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener;
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == viewAgenda.jb_primero) {
                    jb_primero_actionPerformed();
                } else if (e.getSource() == viewAgenda.jb_anterior) {
                    jb_anterior_actionPerformed();
                } else if (e.getSource() == viewAgenda.jb_siguiente) {
                    jb_siguiente_actionPerformed();
                } else if (e.getSource() == viewAgenda.jb_ultimo) {
                    jb_ultimo_actionPerformed();
                }else if (e.getSource() == viewAgenda.jb_nuevo){
                    jb_nuevo_actionPerformed();
                }else if(e.getSource() == viewAgenda.jb_insertar){
                    jb_insertar_actionPerformed();
                }else if(e.getSource() == viewAgenda.jb_modificar){
                    jb_modificar_actionPerformed();
                }else if(e.getSource() == viewAgenda.jb_eliminar){
                    jb_eliminar_actionPerformed();
                }
            }
        };
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        initComponents();
        setActionListener();
        initDB();
    }
/**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    public void initDB(){
        modelAgenda.conectarDB();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
    }
    /**
     * Metodo para inicializar la ViewAgenda
     */
    public void initComponents() {
        viewAgenda.setLocationRelativeTo(null);
        viewAgenda.setTitle("Agenda MVC");
        viewAgenda.setVisible(true);
    }

    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    public void setActionListener() {
        viewAgenda.jb_primero.addActionListener(actionListener);
        viewAgenda.jb_anterior.addActionListener(actionListener);
        viewAgenda.jb_siguiente.addActionListener(actionListener);
        viewAgenda.jb_ultimo.addActionListener(actionListener);
        viewAgenda.jb_nuevo.addActionListener(actionListener);
        viewAgenda.jb_insertar.addActionListener(actionListener);
        viewAgenda.jb_modificar.addActionListener(actionListener);
        viewAgenda.jb_eliminar.addActionListener(actionListener);
    }
    
    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jb_primero_actionPerformed() {
            System.out.println("Action del boton jb_primero");
 
      }

    private void jb_anterior_actionPerformed() {
    System.out.println("Action del boton jb_anterior");
    
    }

    private void jb_siguiente_actionPerformed() {
       System.out.println("Action del boton jb_siguiente");
       
    }

    private void jb_ultimo_actionPerformed() {
        System.out.println("Action del boton jb_ultimo");
         
    }
    private void jb_nuevo_actionPerformed() {
         System.out.println("Action del boton jb_nuevo");
         
    }

    private void jb_insertar_actionPerformed() {
        System.out.println("Action del boton jb_insertar");
    }

    private void jb_modificar_actionPerformed() {
         System.out.println("Action del boton jb_modificar");
    }

    private void jb_eliminar_actionPerformed() {
         System.out.println("Action del boton jb_eliminar");
         
    } 
}