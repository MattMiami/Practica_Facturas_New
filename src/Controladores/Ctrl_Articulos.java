/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Articulos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Antonio Mateos
 */
public class Ctrl_Articulos {

    SessionFactory sf;
    Session ss;
    Transaction tx;

    public Ctrl_Articulos() {
        sf = NewHibernateUtil.getSessionFactory();
    }

    private void operar() {
        ss = sf.openSession();
        tx = ss.beginTransaction();
    }

    public Articulos getRefArticulo(String ref) {
        Articulos a = new Articulos();
        try {
            operar();
            a = (Articulos) ss.get(Articulos.class, ref);
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
        return a;
    }
    
    public boolean addArticulo(Articulos a) {
        boolean result;
        try {
            operar();
            ss.save(a);
            ss.getTransaction().commit();
            result = true;
            JOptionPane.showMessageDialog(null, "Añadido correctamente");
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "Error al añadir artículo", "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public boolean deleteArticulos(Articulos a) {
        boolean result;
        try {
            operar();
            ss.delete(a);
            ss.getTransaction().commit();
            result = true;
            JOptionPane.showMessageDialog(null, "Eliminado correctamente");
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "Este artículo tiene facturas asociadas", "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public boolean modifyArticulos(Articulos a) {
        boolean result;
        try {
            operar();
            ss.update(a);
            ss.getTransaction().commit();
            result = true;
            JOptionPane.showMessageDialog(null, "Artículo modificado correctamente");
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "Error al modificar, sólo puedes modificar un artículo que ya exista.", "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public List articulosList() {
        List<Articulos> articulosList = new ArrayList<Articulos>();
        try {
            ss = sf.openSession();
            Criteria c = ss.createCriteria(Articulos.class);
            articulosList = c.list();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
        return articulosList;
    }
    
    public List bucarArticulos(String ref){
        List<Articulos> la = new ArrayList<Articulos>();
        try{
            operar();
            Query hql = ss.createQuery("from Articulos where referencia = :ref");
            hql.setParameter("ref", ref);
            la = hql.list();
            tx.commit();
        }catch(HibernateException he){
            JOptionPane.showMessageDialog(null, "" + he.getMessage());
        }finally{
            ss.close();
        }
        return la;
    }

}
