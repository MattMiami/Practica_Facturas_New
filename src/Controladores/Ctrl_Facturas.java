/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Articulos;
import Modelos.FacturasCab;
import Modelos.FacturasLin;
import Modelos.FacturasLinId;
import Modelos.FacturasTot;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Antonio Mateos
 */
public class Ctrl_Facturas {

    SessionFactory sf;
    Session ss;
    Transaction tx;

    public Ctrl_Facturas() {
        sf = NewHibernateUtil.getSessionFactory();
    }

    private void operar() {
        ss = sf.openSession();
        tx = ss.beginTransaction();
    }

    public boolean addFacturasCab(FacturasCab f) {
        boolean result;
        try {
            operar();
            ss.save(f);
            ss.getTransaction().commit();
            result = true;
            JOptionPane.showMessageDialog(null, "Factura añadida correctamente");
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "La factura ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public boolean addFacturasLin(FacturasLin fl) {
        boolean result;
        try {
            operar();
            ss.save(fl);
            ss.getTransaction().commit();
            result = true;
            JOptionPane.showMessageDialog(null, "Linea de factura añadida correctamente.");
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public void deleteFacturasCab(FacturasCab f) {
        try {
            operar();
            ss.delete(f);
            ss.getTransaction().commit();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "Esta factura tiene articulos", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
    }

    public void deleteFacturasLin(FacturasLin fl) {
        try {
            operar();
            ss.delete(fl);
            ss.getTransaction().commit();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
    }

    public boolean modifyFactura(FacturasCab f) {
        boolean result;
        try {
            operar();
            ss.saveOrUpdate(f);
            ss.getTransaction().commit();
            result = true;
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "Debes modificar una factura existente.", "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public List facturasCabList() {
        List<FacturasCab> facturasCabList = new ArrayList<FacturasCab>();
        try {
            operar();
            Criteria c = ss.createCriteria(FacturasCab.class);
            facturasCabList = c.list();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            //ss.close();
        }
        return facturasCabList;
    }

    public FacturasCab getNumFacCab(Long numFac) {
        FacturasCab f = new FacturasCab();
        try {
            operar();
            f = (FacturasCab) ss.get(FacturasCab.class, numFac);
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
        return f;
    }
    
    public FacturasLin getNumFacLin(FacturasLinId fli) {
        FacturasLin f = new FacturasLin();
        try {
            operar();
            f = (FacturasLin) ss.get(FacturasLin.class, fli);
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
        return f;
    }

    public void fillComboDescripcion(JComboBox cb) {
        List<Articulos> lista = new ArrayList<Articulos>();
        try {
            operar();
            Criteria cr = ss.createCriteria(Articulos.class);
            lista = cr.list();
            for (Articulos a : lista) {
                cb.addItem(a.getDescripcion());

            }
        } catch (HibernateException he) {
            JOptionPane.showInternalMessageDialog(null, "Error al cargar los tipos de pago", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
    }

    public void fillComboRef(JComboBox cb) {
        List<Articulos> lista = new ArrayList<Articulos>();
        try {
            operar();
            Criteria cr = ss.createCriteria(Articulos.class);
            lista = cr.list();
            for (Articulos a : lista) {
                cb.addItem(a.getReferencia());
            }
        } catch (HibernateException he) {
            JOptionPane.showInternalMessageDialog(null, "Error al cargar los tipos de pago", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
    }

    public List facturasLinList(Long numFac) {
        List<FacturasLin> facturasLinList = new ArrayList<FacturasLin>();
        try {
            operar();
            Criteria c = ss.createCriteria(FacturasLin.class);
            facturasLinList = c.list();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
        return facturasLinList;
    }

 public boolean modifyFacturaLin(FacturasLin f) {
        boolean result;
        try {
            operar();
            ss.update(f);
            ss.getTransaction().commit();
            result = true;
             JOptionPane.showMessageDialog(null, "Linea de factura modificada correctamente.");
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "Debes modificar una factura existente.", "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public boolean addToFacturaTot(FacturasTot ft) {
        boolean result;
        try {
            operar();
            ss.save(ft);
            ss.getTransaction().commit();
            result = true;
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        } finally {
            ss.close();
        }
        return result;
    }

    public FacturasTot getFacTotal(Long numFac) {
        FacturasTot f = new FacturasTot();
        try {
            operar();
            f = (FacturasTot) ss.get(FacturasTot.class, numFac);
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, "" + he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
        return f;
    }

}
