/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Clientes;
import Modelos.EstadisticasClientes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Antonio Mateos
 */
public class Ctrl_Clientes {

    SessionFactory sf;
    Session ss;
    Transaction tx;

    public Ctrl_Clientes() {
        sf = NewHibernateUtil.getSessionFactory();
    }

    private void operar() {
        ss = sf.openSession();
        tx = ss.beginTransaction();
    }

    public boolean addCliente(Clientes c) {
        boolean result;
        try {
            operar();
            ss.save(c);
            ss.getTransaction().commit();
            result = true;
            JOptionPane.showMessageDialog(null, "Añadido correctamente");
        } catch (ConstraintViolationException he) {
            JOptionPane.showMessageDialog(null, "Error al añadir cliente", "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public boolean deleteCliente(Clientes c) {
        boolean result;
        try {
            operar();
            ss.delete(c);
            ss.getTransaction().commit();
            result = true;
            JOptionPane.showMessageDialog(null, "Eliminado correctamente");
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "Este cliente tiene facturas asociadas", "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public boolean modifyCliente(Clientes c) {
        boolean result;
        try {
            operar();
            ss.update(c);
            ss.getTransaction().commit();
            result = true;
            JOptionPane.showMessageDialog(null, "Modificado correctamente");
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "Error al modificar, sólo puedes modificar un cliente que ya exista.", "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public List clientesList() {
        List<Clientes> clientesList = new ArrayList<Clientes>();
        try {
            operar();
            Criteria c = ss.createCriteria(Clientes.class);
            clientesList = c.list();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
        return clientesList;
    }

    public Clientes getClientesDni(String dniCif) {
        Clientes c = new Clientes();
        try {
            operar();
            c = (Clientes) ss.get(Clientes.class, dniCif);
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
        return c;
    }

    public void fillComboBox(JComboBox cb) {
        List<Clientes> listaCli = new ArrayList<Clientes>();
        try {
            operar();
            Criteria cr = ss.createCriteria(Clientes.class);
            listaCli = cr.list();
            for (Clientes c : listaCli) {
                cb.addItem(c.getDnicif());
            }
        } catch (HibernateException he) {
            JOptionPane.showInternalMessageDialog(null, "Error al cargar los tipos de pago", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
    }
    
    public List estadisticasList() {
        List<EstadisticasClientes> estadisticasList = new ArrayList<EstadisticasClientes>();
        try {
            operar();
            Criteria c = ss.createCriteria(EstadisticasClientes.class);
            estadisticasList = c.list();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
        return estadisticasList;
    }
}
