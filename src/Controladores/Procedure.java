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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;

/**
 *
 * @author tarde
 */
public class Procedure {

    Connection cc;
    public static Connection conexion;
    String clienteUno;
    String clienteDos;
    Date fechaUno;
    Date fechaDos;

    public Procedure() {

    }

    public static boolean conectarDB() {
        boolean conectarDB = false;
        try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AD_TEMA_03_FACTURAS", "AD_TEMA_03_FACTURAS");
            conectarDB = true;
        } catch (SQLException seo) {
            conectarDB = false;
        }
        return conectarDB;
    }

    public void Procedure(String clienteUno, String clienteDos, Date fechaUno, Date fechaDos) {

        try {
            if (conectarDB()) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                CallableStatement cs = conexion.prepareCall("{call SP_ESTADISTICAS (?,?,?,?)}");
                System.out.println(cs);
                System.out.println(clienteUno + "" + clienteDos + "" + fechaUno + "" + fechaDos);
                cs.setString(1, clienteUno);
                cs.setString(2, clienteDos);
                cs.setDate(3, fechaUno);
                cs.setDate(4, fechaDos);
                cs.execute();
                System.out.println("entra");
            } else {
                System.out.println("No conectado");
            }

        } catch (SQLException ex) {
            System.out.println("Error sql  " + ex.getMessage());
        } catch (HibernateException h) {
            System.out.println("Error hibernate  " + h.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error clase  " + ex.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (SQLException sql) {
                System.out.println("Error cerrar  " + sql.getMessage());
            }
        }
    }
}
