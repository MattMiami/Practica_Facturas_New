/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author tarde
 */
public class Procedure {
    Connection cc;
    String clienteUno;
    String clienteDos;
    Date fechaUno;
    Date fechaDos;
    public Procedure(){
        
    }
    
    public void Procedure(String clienteUno, String clienteDos, Date fechaUno,Date fechaDos){
        try {
            cc = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","AD_TEMA_03_FACTURAS","AD_TEMA_03_FACTURAS");
            CallableStatement cs = cc.prepareCall("{call SP_ESTADISTICAS (?,?,?,?)}");
            System.out.println(cs);
            System.out.println(clienteUno + "" +clienteDos + "" +fechaUno + "" +fechaDos);
            cs.setString(1, clienteUno);
            cs.setString(2, clienteUno);
            cs.setDate(3, fechaUno);
            cs.setDate(4, fechaDos);
            cs.execute();
            
        } catch (SQLException ex) {
           // JOptionPane.showMessageDialog(null, "Fallo al ejecutar el procedimiento almacenado", "Error", JOptionPane.ERROR_MESSAGE);
           ex.printStackTrace();
        }
    }
}
