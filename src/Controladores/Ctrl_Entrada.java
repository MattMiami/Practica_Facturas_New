/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Antonio Mateos
 */
public class Ctrl_Entrada {
    
    SessionFactory sf;
    Session ss;
    

    public Ctrl_Entrada() {
        try{
            sf = NewHibernateUtil.getSessionFactory();
        }catch(ExceptionInInitializerError ex){
        
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    
}
