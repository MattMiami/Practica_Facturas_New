package Modelos;
// Generated 18-nov-2020 18:07:36 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * FacturasCab generated by hbm2java
 */
public class FacturasCab implements java.io.Serializable {

    private long numfac;
    private Clientes clientes;
    private Date fechafac;
    private FacturasTot facturasTot;
    private Set facturasLins = new HashSet(0);

    public FacturasCab() {

    }

    public BigDecimal getBaseImp() {
        BigDecimal base = BigDecimal.ZERO;
        for (Iterator it = facturasLins.iterator(); it.hasNext();) {
            FacturasLin fl = (FacturasLin) it.next();
            base = base.add(fl.getPrecio().multiply(fl.getCantidad()));
        }
        return base;
    }

    public BigDecimal getDto() {
        BigDecimal dt = BigDecimal.ZERO;
        for (Iterator it = facturasLins.iterator(); it.hasNext();) {
            FacturasLin fl = (FacturasLin) it.next();
            dt = dt.add((fl.getPrecio().multiply(fl.getCantidad())).multiply(fl.getDtolinea().divide(new BigDecimal(100))));
        }
        return dt;
    }

    public BigDecimal getIvaTotal() {
        BigDecimal iva = BigDecimal.ZERO;
        for (Iterator it = facturasLins.iterator(); it.hasNext();) {
            FacturasLin fl = (FacturasLin) it.next();
            iva = iva.add((fl.getCantidad().multiply(fl.getPrecio()))
                    .subtract(fl.getPrecio().multiply(fl.getCantidad()).multiply(fl.getDtolinea().divide(new BigDecimal(100))))
                    .multiply(fl.getIvalinea().divide(new BigDecimal(100))));
        }

        return iva;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Iterator it = facturasLins.iterator(); it.hasNext();) {
            FacturasLin fl = (FacturasLin) it.next();
            total = total.add(fl.getPrecio().multiply(fl.getCantidad())).add(((fl.getCantidad().multiply(fl.getPrecio()))
                    .subtract(fl.getPrecio().multiply(fl.getCantidad()).multiply(fl.getDtolinea().divide(new BigDecimal(100))))
                    .multiply(fl.getIvalinea().divide(new BigDecimal(100)))));
        }

        return total;
    }

    public FacturasCab(long numfac, Clientes clientes) {
        this.numfac = numfac;
        this.clientes = clientes;
    }

    public FacturasCab(long numfac, Clientes clientes, Date fechafac, FacturasTot facturasTot, Set facturasLins) {
        this.numfac = numfac;
        this.clientes = clientes;
        this.fechafac = fechafac;
        this.facturasTot = facturasTot;
        this.facturasLins = facturasLins;
    }

    public long getNumfac() {
        return this.numfac;
    }

    public void setNumfac(long numfac) {
        this.numfac = numfac;
    }

    public Clientes getClientes() {
        return this.clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Date getFechafac() {
        return this.fechafac;
    }

    public Date getFormattedDate(String date) {
        Date fecha = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            fecha = new java.sql.Date(sdf.parse(date).getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Formato de fecha no válido. Ejemplo: dd/mm/aaaa.");
        }
        return fecha;
    }

    public String getFechaFormat() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(this.fechafac);
    }

    public void setFechafac(Date fechafac) {
        this.fechafac = fechafac;
    }

    public FacturasTot getFacturasTot() {
        return this.facturasTot;
    }

    public void setFacturasTot(FacturasTot facturasTot) {
        this.facturasTot = facturasTot;
    }

    public Set getFacturasLins() {
        return this.facturasLins;
    }

    public void setFacturasLins(Set facturasLins) {
        this.facturasLins = facturasLins;
    }
    
    public static Comparator<FacturasCab> ordenarFactura = new Comparator<FacturasCab>() {

        @Override
        public int compare(FacturasCab o1, FacturasCab o2) {
            long numFac1 = o1.getNumfac();
            long numFac2 = o2.getNumfac();

            return (int) numFac1 - (int) numFac2;
        }

    };

}
