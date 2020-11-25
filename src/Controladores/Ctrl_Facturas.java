/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Articulos;
import Modelos.FacturasCab;
import Modelos.FacturasLin;
import Modelos.FacturasTot;
import java.math.BigDecimal;
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
            he.printStackTrace();
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

    public void fillComboNumFac(JComboBox cb) {
        List<FacturasCab> lista = new ArrayList<FacturasCab>();
        try {
            operar();
            Criteria cr = ss.createCriteria(FacturasCab.class);
            lista = cr.list();
            for (FacturasCab f : lista) {
                cb.addItem(f.getNumfac());
            }
        } catch (HibernateException he) {
            JOptionPane.showInternalMessageDialog(null, "Error al cargar facturas disponibles", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ss.close();
        }
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

    public boolean modifyFacturaTot(FacturasTot ft) {
        boolean result;
        try {
            operar();
            ss.saveOrUpdate(ft);
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

   /* public void calculoTotal(FacturasCab fc) {

        List<FacturasLin> facturasLinList = facturasLinList(fc.getNumfac());
        BigDecimal baseTotal = BigDecimal.ZERO;
        BigDecimal dtoTotal = BigDecimal.ZERO;
        BigDecimal ivaTotal = BigDecimal.ZERO;
        BigDecimal totalFac = BigDecimal.ZERO;

        for (FacturasLin fl : facturasLinList) {

            if (fc.getNumfac() == fl.getId().getNumfac()) {
                BigDecimal total = null;
                BigDecimal p = fl.getPrecio();
                BigDecimal c = fl.getCantidad();
                BigDecimal descuento = fl.getDtolinea();
                BigDecimal ivaL = fl.getIvalinea();

                BigDecimal base = p.multiply(c);
                BigDecimal dto = base.multiply(descuento.multiply(BigDecimal.valueOf(0.01)));
                BigDecimal iva = (base.subtract(dto)).multiply(ivaL.multiply(BigDecimal.valueOf(0.01)));
                total = base.add(iva);

                baseTotal = baseTotal.add(base);
                dtoTotal = dtoTotal.add(dto);
                ivaTotal = ivaTotal.add(iva);
                totalFac = totalFac.add(total);
                System.out.println(fl.getId().getLineafac());
            }
        }

        FacturasTot ft = new FacturasTot(fc,baseTotal, ivaTotal, ivaTotal, totalFac);

        ft.setFacturasCab(fc);
        ft.setNumfac(fc.getNumfac());
        ft.setBaseimp(baseTotal);
        ft.setImpDto(dtoTotal);
        ft.setImpIva(ivaTotal);
        ft.setTotalfac(totalFac);

        if (addToFacturaTot(ft) == false) {

        } else {

            modifyFacturaTot(ft);
        }

    }*/

}
