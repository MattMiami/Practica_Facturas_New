/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.UIManager.LookAndFeelInfo;
import Controladores.Ctrl_Articulos;
import Controladores.Ctrl_Clientes;
import Controladores.Ctrl_Entrada;
import Controladores.Ctrl_Facturas;
import Controladores.ExportarXml;
import Controladores.ImportarXml;
import Controladores.Procedure;
import Modelos.Articulos;
import Modelos.Clientes;
import Modelos.EstadisticasClientes;
import Modelos.FacturasCab;
import Modelos.FacturasLin;
import Modelos.FacturasLinId;
import Modelos.FacturasTot;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Antonio Mateos
 */
public class Main extends javax.swing.JFrame {
    
    Procedure p = new Procedure();
    Ctrl_Clientes cc;
    Ctrl_Articulos ca;
    Ctrl_Facturas cf;
    private Ctrl_Entrada controlE = new Ctrl_Entrada();
    private List<Clientes> cl = new ArrayList<Clientes>();
    private List<Articulos> al = new ArrayList<Articulos>();
    private List<FacturasCab> fl = new ArrayList<FacturasCab>();
    private List<EstadisticasClientes> el = new ArrayList<EstadisticasClientes>();
    
    Integer day1;
    Integer day2;
    Integer month1;
    Integer month2;
    Integer year1;
    Integer year2;
    java.sql.Date date1;
    java.sql.Date date2;
    
    DefaultTableModel modelArt;
    String[] columnasArt = {"Referencia", "Descripción", "Precio", "IVA", "Cantidad Stock"};

    /*
    Nos muestra las columnas y la informacion de la tabla Artículos
     */
    public void showTableArticulos() {
        modelArt = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelArt.addColumn(columnasArt[0]);
        modelArt.addColumn(columnasArt[1]);
        modelArt.addColumn(columnasArt[2]);
        modelArt.addColumn(columnasArt[3]);
        modelArt.addColumn(columnasArt[4]);
        al = ca.articulosList();
        for (Articulos a : al) {
            modelArt.addRow(new Object[]{a.getReferencia(), a.getDescripcion(), a.getPrecio(), a.getPorciva(), a.getStock()});
        }
        jTableArticulo.setModel(modelArt);
    }

    /*
    Nos muestra las columnas y la informacion de la tabla Clientes
     */
    DefaultTableModel modelCli;
    String[] columnasCli = {"DNI-CIF", "Nombre"};
    
    public void showTableClientes() {
        modelCli = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelCli.addColumn(columnasCli[0]);
        modelCli.addColumn(columnasCli[1]);
        cl = cc.clientesList();
        for (Clientes c : cl) {
            modelCli.addRow(new Object[]{c.getDnicif(), c.getNombrecli()});
        }
        
        jTableClientes.setModel(modelCli);
    }

    /*
    Nos muestra las columnas y la informacion de la tabla Facturas
     */
    DefaultTableModel modelFac;
    String[] columnasFac = {"NºFactura", "Fecha", "DNI-CIF"};
    
    public void showTableFacturas() {
        modelFac = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelFac.addColumn(columnasFac[0]);
        modelFac.addColumn(columnasFac[1]);
        modelFac.addColumn(columnasFac[2]);
        fl = cf.facturasCabList();
        fl.sort(FacturasCab.ordenarFactura);
        for (FacturasCab f : fl) {
            modelFac.addRow(new Object[]{f.getNumfac(), f.getFechaFormat(), f.getClientes().getDnicif()});
        }
        jTableCab.setModel(modelFac);
    }

    /*
    Nos muestra las columnas de la tabla Linea de facturas
     */
    DefaultTableModel modelLin;
    String[] columnasLin = {"NºFactura", "Linea factura", "Referencia", "Cantidad", "Precio", "Descuento", "IVA linea"};
    
    public void showTableFacturasLin() {
        modelLin = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelLin.addColumn(columnasLin[0]);
        modelLin.addColumn(columnasLin[1]);
        modelLin.addColumn(columnasLin[2]);
        modelLin.addColumn(columnasLin[3]);
        modelLin.addColumn(columnasLin[4]);
        modelLin.addColumn(columnasLin[5]);
        modelLin.addColumn(columnasLin[6]);
        
        jTableLinea.setModel(modelLin);
    }

    /*
    Nos muestra las columnas y la informacion de la tabla de totales
     */
    DefaultTableModel modelTot;
    String[] columnasTot = {"NºFactura", "Base imponible", "Importe descuento", "Importe IVA", "Total factura"};
    
    public void showTableFacturasTot() {
        modelTot = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelTot.addColumn(columnasTot[0]);
        modelTot.addColumn(columnasTot[1]);
        modelTot.addColumn(columnasTot[2]);
        modelTot.addColumn(columnasTot[3]);
        modelTot.addColumn(columnasTot[4]);
        
        jTableTotales.setModel(modelTot);
    }
    
    DefaultTableModel modelEstadisticas;
    String[] columnasEst = {"Año", "Número Mes", "Nombre Mes", "DNI-CIF", "NOMBRE CLIENTE", "SUMA BASE", "SUMA DTO", "SUMA IVA", "SUMA TOTALES"};
    
    public void showTableEstadisticas() {
        modelEstadisticas = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelEstadisticas.addColumn(columnasEst[0]);
        modelEstadisticas.addColumn(columnasEst[1]);
        modelEstadisticas.addColumn(columnasEst[2]);
        modelEstadisticas.addColumn(columnasEst[3]);
        modelEstadisticas.addColumn(columnasEst[4]);
        modelEstadisticas.addColumn(columnasEst[5]);
        modelEstadisticas.addColumn(columnasEst[6]);
        modelEstadisticas.addColumn(columnasEst[7]);
        modelEstadisticas.addColumn(columnasEst[8]);
        
        jTableEstadisticas.setModel(modelEstadisticas);
    }
    
    public Main() {
        initComponents();
        cc = new Ctrl_Clientes();
        ca = new Ctrl_Articulos();
        cf = new Ctrl_Facturas();
        
        jdArticulos.pack();
        jdArticulos.setLocationRelativeTo(null);
        jdArticulos.setResizable(false);
        jdArticulos.setTitle("Gestión de artículos");
        jdClientes.pack();
        jdClientes.setLocationRelativeTo(null);
        jdClientes.setResizable(false);
        jdClientes.setTitle("Gestión de clientes");
        jdFacturas.pack();
        jdFacturas.setLocationRelativeTo(null);
        jdFacturas.setResizable(false);
        jdFacturas.setTitle("Gestión de facturas");
        jdLineaFactura.pack();
        jdLineaFactura.setLocationRelativeTo(null);
        jdLineaFactura.setResizable(false);
        jdLineaFactura.setTitle("Gestión de lineas de facturas");
        jdEstadisticas.setResizable(false);
        jdEstadisticas.setLocationRelativeTo(jdClientes);
        jdEstadisticas.pack();
        jdEstadisticas.setTitle("Estadíticas de ventas");
        
        showTableClientes();
        showTableArticulos();
        showTableFacturas();
        showTableFacturasLin();
        showTableFacturasTot();
        showTableEstadisticas();
        cc.fillComboBox(cbClientesDisponibles);
        cc.fillComboBox(cbClienteUno);
        cc.fillComboBox(cbClienteDos);
        cf.fillComboDescripcion(cbDescripcion);
        cf.fillComboRef(cbRef);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdClientes = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txDniCli = new javax.swing.JTextField();
        txNombre = new javax.swing.JTextField();
        btFacAsociadas = new javax.swing.JButton();
        btEstadisticas = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        txBuscarCli = new javax.swing.JTextField();
        btBuscarCli = new javax.swing.JButton();
        btAddCli = new javax.swing.JButton();
        btDeleteCli = new javax.swing.JButton();
        btModCli = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        btActualizarCli = new javax.swing.JButton();
        menuBar1 = new javax.swing.JMenuBar();
        menuOpciones1 = new javax.swing.JMenu();
        itemFac1 = new javax.swing.JMenuItem();
        itemStock1 = new javax.swing.JMenuItem();
        itemInicio1 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        itemHelpClientes = new javax.swing.JMenuItem();
        jdArticulos = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableArticulo = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txRefArticulo = new javax.swing.JTextField();
        txDescripcion = new javax.swing.JTextField();
        txPrecioArticulo = new javax.swing.JTextField();
        txIvaArt = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txStockDisponible = new javax.swing.JTextField();
        txBuscarArt = new javax.swing.JTextField();
        btBuscarArt = new javax.swing.JButton();
        btAddArt = new javax.swing.JButton();
        btDeleteArt = new javax.swing.JButton();
        btModArt = new javax.swing.JButton();
        btActualizarArt = new javax.swing.JButton();
        menuBar2 = new javax.swing.JMenuBar();
        menuOpciones2 = new javax.swing.JMenu();
        itemCli2 = new javax.swing.JMenuItem();
        itemFac2 = new javax.swing.JMenuItem();
        itemInicio2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        itemAyudaArticulos = new javax.swing.JMenuItem();
        jdFacturas = new javax.swing.JDialog();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCab = new javax.swing.JTable();
        txNumFacCab = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btModifyFac = new javax.swing.JButton();
        btDeleteFac = new javax.swing.JButton();
        btAddFac = new javax.swing.JButton();
        txBuscarFac = new javax.swing.JTextField();
        btBuscarFac = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        cbClientesDisponibles = new javax.swing.JComboBox<>();
        txFecha = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        btDetalles = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableTotales = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btCalcular = new javax.swing.JButton();
        btImportar = new javax.swing.JButton();
        btExportar = new javax.swing.JButton();
        menuBar3 = new javax.swing.JMenuBar();
        menuOpciones3 = new javax.swing.JMenu();
        itemCli3 = new javax.swing.JMenuItem();
        itemStock3 = new javax.swing.JMenuItem();
        itemInicio3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jdLineaFactura = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableLinea = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txLineaFac = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txCantidad = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txPrecio = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txDto = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txIva = new javax.swing.JTextField();
        btAddLin = new javax.swing.JButton();
        btDeleteLin = new javax.swing.JButton();
        btModifyLin = new javax.swing.JButton();
        cbDescripcion = new javax.swing.JComboBox<>();
        cbRef = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        lbNumFac = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btBuscarLin = new javax.swing.JButton();
        txBuscarLin = new javax.swing.JTextField();
        btCalculoLineas = new javax.swing.JButton();
        menuBar4 = new javax.swing.JMenuBar();
        menuOpciones4 = new javax.swing.JMenu();
        itemCli1 = new javax.swing.JMenuItem();
        itemFac3 = new javax.swing.JMenuItem();
        itemStock2 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        itemAyudaLineas = new javax.swing.JMenuItem();
        jdEstadisticas = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableEstadisticas = new javax.swing.JTable();
        cbClienteUno = new javax.swing.JComboBox<>();
        cbClienteDos = new javax.swing.JComboBox<>();
        txFechaUno = new javax.swing.JFormattedTextField();
        txFechaDos = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btMostrarEstadisticas = new javax.swing.JButton();
        jdHelpInicio = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jdHelpClientes = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jdHelpFacturas = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jdHelpArticulos = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jdHelpLineas = new javax.swing.JDialog();
        jPanel19 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuOpciones = new javax.swing.JMenu();
        itemCli = new javax.swing.JMenuItem();
        itemFac = new javax.swing.JMenuItem();
        itemStock = new javax.swing.JMenuItem();
        itemLinea = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemInicio = new javax.swing.JMenuItem();

        jdClientes.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jdClientesWindowClosing(evt);
            }
        });

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableClientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );

        jLabel1.setText("DNI-CIF");

        jLabel2.setText("Nombre Cliente");

        btFacAsociadas.setText("Consultar Facturas Cliente");
        btFacAsociadas.setEnabled(false);
        btFacAsociadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFacAsociadasActionPerformed(evt);
            }
        });

        btEstadisticas.setText("Estadísticas clientes");
        btEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEstadisticasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 34, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btFacAsociadas)
                    .addComponent(btEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txDniCli))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txDniCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(38, 38, 38)
                .addComponent(btFacAsociadas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEstadisticas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btBuscarCli.setText("Buscar cliente");
        btBuscarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarCliActionPerformed(evt);
            }
        });

        btAddCli.setText("Añadir");
        btAddCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddCliActionPerformed(evt);
            }
        });

        btDeleteCli.setText("Eliminar");
        btDeleteCli.setEnabled(false);
        btDeleteCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteCliActionPerformed(evt);
            }
        });

        btModCli.setText("Modificar");
        btModCli.setEnabled(false);
        btModCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModCliActionPerformed(evt);
            }
        });

        btActualizarCli.setText("Actualizar tabla");
        btActualizarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizarCliActionPerformed(evt);
            }
        });

        menuBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        menuOpciones1.setText("Opciones");

        itemFac1.setText("Gestionar Facturas");
        itemFac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFac1ActionPerformed(evt);
            }
        });
        menuOpciones1.add(itemFac1);

        itemStock1.setText("Gestionar Stock");
        itemStock1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemStock1ActionPerformed(evt);
            }
        });
        menuOpciones1.add(itemStock1);

        itemInicio1.setText("Inicio");
        itemInicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInicio1ActionPerformed(evt);
            }
        });
        menuOpciones1.add(itemInicio1);

        jMenuItem1.setText("Linea facturas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuOpciones1.add(jMenuItem1);

        menuBar1.add(menuOpciones1);

        jMenu3.setText("Ayuda");

        itemHelpClientes.setText("Ayuda gestión clientes");
        itemHelpClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHelpClientesActionPerformed(evt);
            }
        });
        jMenu3.add(itemHelpClientes);

        menuBar1.add(jMenu3);

        jdClientes.setJMenuBar(menuBar1);

        javax.swing.GroupLayout jdClientesLayout = new javax.swing.GroupLayout(jdClientes.getContentPane());
        jdClientes.getContentPane().setLayout(jdClientesLayout);
        jdClientesLayout.setHorizontalGroup(
            jdClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jdClientesLayout.createSequentialGroup()
                        .addComponent(btAddCli)
                        .addGap(5, 5, 5)
                        .addComponent(btDeleteCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btModCli))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jdClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdClientesLayout.createSequentialGroup()
                        .addComponent(txBuscarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btBuscarCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btActualizarCli)
                        .addGap(17, 17, 17))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jSeparator5)
            .addComponent(jSeparator4)
        );
        jdClientesLayout.setVerticalGroup(
            jdClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jdClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBuscarCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscarCli)
                    .addComponent(btAddCli)
                    .addComponent(btDeleteCli)
                    .addComponent(btModCli)
                    .addComponent(btActualizarCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jdClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jdArticulos.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jdArticulosWindowClosing(evt);
            }
        });

        jTableArticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableArticuloMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableArticulo);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setText("Precio");

        jLabel7.setText("IVA Artículo");

        jLabel8.setText("Catidad en Stock");

        jLabel28.setText("Referencia");

        jLabel29.setText("Descripción");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txRefArticulo)
                            .addComponent(txDescripcion)
                            .addComponent(txPrecioArticulo)
                            .addComponent(txIvaArt)
                            .addComponent(txStockDisponible)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel6))
                        .addGap(0, 189, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txRefArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(txDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txPrecioArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txIvaArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txStockDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        btBuscarArt.setText("Buscar artículo");
        btBuscarArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarArtActionPerformed(evt);
            }
        });

        btAddArt.setText("Añadir");
        btAddArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddArtActionPerformed(evt);
            }
        });

        btDeleteArt.setText("Eliminar");
        btDeleteArt.setEnabled(false);
        btDeleteArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteArtActionPerformed(evt);
            }
        });

        btModArt.setText("Modificar");
        btModArt.setEnabled(false);
        btModArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModArtActionPerformed(evt);
            }
        });

        btActualizarArt.setText("Actualizar tabla");
        btActualizarArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizarArtActionPerformed(evt);
            }
        });

        menuBar2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        menuOpciones2.setText("Opciones");

        itemCli2.setText("Gestionar Clientes");
        itemCli2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCli2ActionPerformed(evt);
            }
        });
        menuOpciones2.add(itemCli2);

        itemFac2.setText("Gestionar Facturas");
        itemFac2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFac2ActionPerformed(evt);
            }
        });
        menuOpciones2.add(itemFac2);

        itemInicio2.setText("Inicio");
        itemInicio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInicio2ActionPerformed(evt);
            }
        });
        menuOpciones2.add(itemInicio2);

        jMenuItem3.setText("Linea facturas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuOpciones2.add(jMenuItem3);

        menuBar2.add(menuOpciones2);

        jMenu4.setText("Ayuda");

        itemAyudaArticulos.setText("Ayuda gestión stock");
        itemAyudaArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAyudaArticulosActionPerformed(evt);
            }
        });
        jMenu4.add(itemAyudaArticulos);

        menuBar2.add(jMenu4);

        jdArticulos.setJMenuBar(menuBar2);

        javax.swing.GroupLayout jdArticulosLayout = new javax.swing.GroupLayout(jdArticulos.getContentPane());
        jdArticulos.getContentPane().setLayout(jdArticulosLayout);
        jdArticulosLayout.setHorizontalGroup(
            jdArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdArticulosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdArticulosLayout.createSequentialGroup()
                        .addComponent(btAddArt)
                        .addGap(5, 5, 5)
                        .addComponent(btDeleteArt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btModArt))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdArticulosLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jdArticulosLayout.createSequentialGroup()
                        .addComponent(txBuscarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btBuscarArt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btActualizarArt)
                        .addGap(19, 19, 19))))
        );
        jdArticulosLayout.setVerticalGroup(
            jdArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdArticulosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBuscarArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscarArt)
                    .addComponent(btAddArt)
                    .addComponent(btDeleteArt)
                    .addComponent(btModArt)
                    .addComponent(btActualizarArt))
                .addGap(7, 7, 7)
                .addGroup(jdArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdArticulosLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jdFacturas.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jdFacturasWindowClosing(evt);
            }
        });

        jTableCab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableCab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCabMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableCab);

        jLabel13.setText("NºFactura");

        jLabel10.setText("DNI-CIF");

        btModifyFac.setText("Modificar");
        btModifyFac.setEnabled(false);
        btModifyFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModifyFacActionPerformed(evt);
            }
        });

        btDeleteFac.setText("Eliminar");
        btDeleteFac.setEnabled(false);
        btDeleteFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteFacActionPerformed(evt);
            }
        });

        btAddFac.setText("Añadir");
        btAddFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddFacActionPerformed(evt);
            }
        });

        btBuscarFac.setText("Buscar");
        btBuscarFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarFacActionPerformed(evt);
            }
        });

        jButton8.setText("Actualizar Tabla");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        cbClientesDisponibles.setMaximumRowCount(20);

        txFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jLabel4.setText("Fecha");

        btDetalles.setText("Detalles factura");
        btDetalles.setEnabled(false);
        btDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDetallesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btAddFac)
                        .addGap(1, 1, 1)
                        .addComponent(btDeleteFac)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btModifyFac))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txNumFacCab, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbClientesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btDetalles)))
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(txBuscarFac, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btBuscarFac)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txBuscarFac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btBuscarFac)
                            .addComponent(jButton8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btAddFac)
                            .addComponent(btDeleteFac)
                            .addComponent(btModifyFac))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txNumFacCab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(cbClientesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btDetalles)
                        .addContainerGap())))
        );

        jTableTotales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTableTotales);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        btCalcular.setText("Calcular total");
        btCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCalcularActionPerformed(evt);
            }
        });

        btImportar.setText("Importar factura");
        btImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImportarActionPerformed(evt);
            }
        });

        btExportar.setText("Exportar factura");
        btExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btImportar)
                        .addGap(18, 18, 18)
                        .addComponent(btExportar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(btCalcular)
                        .addGap(78, 78, 78))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btCalcular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btImportar)
                    .addComponent(btExportar))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        menuBar3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        menuOpciones3.setText("Opciones");

        itemCli3.setText("Gestionar Clientes");
        itemCli3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCli3ActionPerformed(evt);
            }
        });
        menuOpciones3.add(itemCli3);

        itemStock3.setText("Gestionar Stock");
        itemStock3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemStock3ActionPerformed(evt);
            }
        });
        menuOpciones3.add(itemStock3);

        itemInicio3.setText("Inicio");
        itemInicio3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInicio3ActionPerformed(evt);
            }
        });
        menuOpciones3.add(itemInicio3);

        jMenuItem4.setText("Linea faturas");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuOpciones3.add(jMenuItem4);

        menuBar3.add(menuOpciones3);

        jMenu5.setText("Ayuda");

        jMenuItem5.setText("Ayuda gestión Facturas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        menuBar3.add(jMenu5);

        jdFacturas.setJMenuBar(menuBar3);

        javax.swing.GroupLayout jdFacturasLayout = new javax.swing.GroupLayout(jdFacturas.getContentPane());
        jdFacturas.getContentPane().setLayout(jdFacturasLayout);
        jdFacturasLayout.setHorizontalGroup(
            jdFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdFacturasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jdFacturasLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jdFacturasLayout.setVerticalGroup(
            jdFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdFacturasLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jdFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jdLineaFactura.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jdLineaFacturaWindowClosing(evt);
            }
        });

        jTableLinea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableLinea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLineaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableLinea);

        jLabel12.setText("NºFactura");

        jLabel16.setText("Linea Factura");

        jLabel17.setText("Referencia");

        jLabel18.setText("Cantidad");

        jLabel19.setText("Precio");

        jLabel20.setText("Descuento");

        jLabel21.setText("Iva");

        btAddLin.setText("Añadir");
        btAddLin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddLinActionPerformed(evt);
            }
        });

        btDeleteLin.setText("Eliminar");
        btDeleteLin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteLinActionPerformed(evt);
            }
        });

        btModifyLin.setText("Modificar");
        btModifyLin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModifyLinActionPerformed(evt);
            }
        });

        cbDescripcion.setMaximumRowCount(20);
        cbDescripcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cbDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDescripcionActionPerformed(evt);
            }
        });

        cbRef.setMaximumRowCount(20);
        cbRef.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cbRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRefActionPerformed(evt);
            }
        });

        jLabel11.setText("Descripcion");

        lbNumFac.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btAddLin, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel17)))
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txIva, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbRef, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btDeleteLin, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btModifyLin))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbNumFac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txLineaFac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                    .addComponent(txPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txDto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btModifyLin)
                            .addComponent(btDeleteLin)
                            .addComponent(btAddLin))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel12)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNumFac, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txLineaFac, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(txPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txDto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btBuscarLin.setText("Buscar");
        btBuscarLin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarLinActionPerformed(evt);
            }
        });

        btCalculoLineas.setText("Calcular Factura");
        btCalculoLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCalculoLineasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(btCalculoLineas, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txBuscarLin, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btBuscarLin)
                .addGap(15, 15, 15))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btBuscarLin)
                    .addComponent(txBuscarLin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCalculoLineas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        menuBar4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        menuOpciones4.setText("Opciones");

        itemCli1.setText("Gestionar Clientes");
        itemCli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCli1ActionPerformed(evt);
            }
        });
        menuOpciones4.add(itemCli1);

        itemFac3.setText("Gestionar Facturas");
        itemFac3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFac3ActionPerformed(evt);
            }
        });
        menuOpciones4.add(itemFac3);

        itemStock2.setText("Gestionar Stock");
        itemStock2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemStock2ActionPerformed(evt);
            }
        });
        menuOpciones4.add(itemStock2);

        jMenuItem2.setText("Inicio");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuOpciones4.add(jMenuItem2);

        menuBar4.add(menuOpciones4);

        jMenu6.setText("Ayuda");

        itemAyudaLineas.setText("Ayuda gestión desglose facturas");
        itemAyudaLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAyudaLineasActionPerformed(evt);
            }
        });
        jMenu6.add(itemAyudaLineas);

        menuBar4.add(jMenu6);

        jdLineaFactura.setJMenuBar(menuBar4);

        javax.swing.GroupLayout jdLineaFacturaLayout = new javax.swing.GroupLayout(jdLineaFactura.getContentPane());
        jdLineaFactura.getContentPane().setLayout(jdLineaFacturaLayout);
        jdLineaFacturaLayout.setHorizontalGroup(
            jdLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdLineaFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jdLineaFacturaLayout.setVerticalGroup(
            jdLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdLineaFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jdEstadisticas.setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);

        jTableEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTableEstadisticas);

        txFechaUno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        txFechaDos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jLabel15.setText("Cliente");

        jLabel22.setText("Cliente");

        jLabel23.setText("Fecha");

        jLabel24.setText("Fecha");

        btMostrarEstadisticas.setText("Mostrar estadísticas");
        btMostrarEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMostrarEstadisticasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(jLabel15))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbClienteUno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txFechaUno)
                    .addComponent(cbClienteDos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txFechaDos)
                    .addComponent(btMostrarEstadisticas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbClienteUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbClienteDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addComponent(txFechaUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txFechaDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addComponent(btMostrarEstadisticas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdEstadisticasLayout = new javax.swing.GroupLayout(jdEstadisticas.getContentPane());
        jdEstadisticas.getContentPane().setLayout(jdEstadisticasLayout);
        jdEstadisticasLayout.setHorizontalGroup(
            jdEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdEstadisticasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jdEstadisticasLayout.setVerticalGroup(
            jdEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdEstadisticasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Se encuentra usted en la página de inicio de gestión de su tienda,\n donde podrá realizar las siguientes actiones:\n\t\n\t*Gestión de Clientes:\n\t\t- Podrá agregar, borrar o modifcar los datos sus clientes.\n\t\t- Tendrá acceso a las facturas realcionadas con sus clientes.\n\t\t- Obtener estadísticas de ventas.\n\t*Gestión  de Facturas:\n\t\t- Podrá agregar, borrar o modifcar las facturas.\n\t\t- Acceder al desglose de  cada factura.\n\t\t- Calcular el total de cada factura.\n\t*Gestión  de Stock:\n\t\t- Podrá agregar, borrar o modifcar los datos sus artículos.\n\t\t\n\n\t\n\t");
        jScrollPane7.setViewportView(jTextArea1);

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 48)); // NOI18N
        jLabel3.setText("Bienvedido");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jLabel3)
                .addContainerGap(235, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdHelpInicioLayout = new javax.swing.GroupLayout(jdHelpInicio.getContentPane());
        jdHelpInicio.getContentPane().setLayout(jdHelpInicioLayout);
        jdHelpInicioLayout.setHorizontalGroup(
            jdHelpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdHelpInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jdHelpInicioLayout.setVerticalGroup(
            jdHelpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdHelpInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 1, 48)); // NOI18N
        jLabel5.setText("Gestión de Clientes");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText("Manual de uso ventana gestión de clientes:\n\n\t* Para añadir un cliente debe rellenar todos los campos y pulsar el boton \"Añadir\".\n\t    Tras ser añadido tenga en cuenta que la tabla mostrará únicamente el cliente añadido, \n\t    si quiere volver a ver todos los clientes, porfavor refresque la tabla en el boton \"Actializar tabla\".\n\t* Para eliminar debe selecionar un cliente en la tabla y pulsar \"Eliminar\".\n\t* Para modificar un cliente debe selecionar un cliente en la tabla, modificar sus campos \n\t     y pulsar \"Modificar\".\n\t\n\t* Para consultar las facturas asociada a un cliente, selecione el cliente en la tabla\n\t    y a continuación pulse \"Consultar Factuas Cliente\". Este boton le abrirá directamente \n\t    la ventana de facturas con únicamente las facturas del cliente selecionado.\n\n\t* Para ver las estadísticas de venta, pulse directamente en el boton \"Estadíticas Clientes\",\n\t   este abrirá una ventana donde:\n\t\t- Debe seleccionar un rago de entre dos clientes en los menus desplegables\n\t\t- Debe insertar dos fechas en los recuadros marcados como fecha\n\t\t- Obtendrá un resultado basados en los datos anteriores.\n\t");
        jScrollPane8.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jdHelpClientesLayout = new javax.swing.GroupLayout(jdHelpClientes.getContentPane());
        jdHelpClientes.getContentPane().setLayout(jdHelpClientesLayout);
        jdHelpClientesLayout.setHorizontalGroup(
            jdHelpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdHelpClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdHelpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jdHelpClientesLayout.setVerticalGroup(
            jdHelpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdHelpClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Lucida Bright", 1, 48)); // NOI18N
        jLabel9.setText("Gestión Facturas");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jLabel9)
                .addContainerGap(253, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jTextArea3.setRows(5);
        jTextArea3.setText("Ayuda gestión de facturas:\n\t\n\t* Para agregar una nueva factura rellene todos los campos y pulse \"Añadir\", tenga en cuenta\n\t   que tras agregar una factura para su comodidad de identificacion y verificacion de que se agregó\n\t   correctamente en la tabla sólo aparecerá esta nueva factura, si desea ver todas las facturas porfavor\n\t   refresque la tabla en el boton \"Actualizar tabla\".\n\t\n\t* Para borrar una factura, seleccione un resgistro de la tabla y pulse \"Eliminar\", tenga en cuenta\n\t   que una factura no podrá ser eliminada si tiene artículos añadidos.\n\n\t* Para modificar una factura elija una factura de la tabla y modifique sus campos \"Fecha\" y \"DNI-CIF\".\n\n\t* Si desea buscar una factura en concreto intoduzca el número de factura en la varra de búsqueda\n\t   y pulse \"Buscar\" (refresque la tabla para ver todas las facturas de nuevo).\n\n\t* En el caso de que quiera desglosar los detalles de una factura, seleccione una factura en la tabla\n\t   y pulse \"Detalles facturas\", esto le abrirá directamente la ventana de la factura desglosada.\n\n\t* Usted puede calcular el costo total de una factura simplemente seleccionando una en la tabla\n\t   y a continuación presionar \"Calcular total\".\n\n\t*Si lo que desea es importar o exportar una factura:\n\t\n\t\t- Para exportar, elija una factura en la tabla, pulse \"Exportar\" y selecione el directorio\n\t\t   donde quiere guardar el archivo.\n\t\t- Para importar, pulse directamente \"Importar\" y seleccione desde su equipo el \n\t\t   archivo que desea importar.\n\nPara gestionar Clientes o Articulos porfavor pulse en el menú Opciones y seleccione a donde quiere dirijirse.\n");
        jScrollPane9.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jdHelpFacturasLayout = new javax.swing.GroupLayout(jdHelpFacturas.getContentPane());
        jdHelpFacturas.getContentPane().setLayout(jdHelpFacturasLayout);
        jdHelpFacturasLayout.setHorizontalGroup(
            jdHelpFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdHelpFacturasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdHelpFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jdHelpFacturasLayout.setVerticalGroup(
            jdHelpFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdHelpFacturasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("Lucida Bright", 1, 48)); // NOI18N
        jLabel14.setText("Gestión de Artículos");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel14)
                .addContainerGap(213, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextArea4.setEditable(false);
        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jTextArea4.setRows(5);
        jTextArea4.setText("Ayuda gestión de stock:\n\t\n\t* Para añadir un artículo rellene todos los campos y pulse \"Añadir\", \n\t  (refresque la tabla para ver todos los atículos de nuevo).\n\t\n\t* Para eliminar un artículo selecione un registro de la tabla y pulse \"Eliminar\".\n\t   Teng en cuenta que un artículo asociado a una factura no podrá ser eliminado.\n\t\n\t* Proceda a modificar un artículo seleccionando un registro en la tabla cambie los campos \n\t  deseados y pulse \"Modificar\", para modificar un artículo debe haber existencias del mismo.\n\n\t* Realíce búsqueda rellenando la barra del buscardor con el número de referencia del artículo.\n\nPara gestionar Clientes o Faturas diríjase al menú de Opciones y seleccione donde quiere dirijirse.");
        jScrollPane10.setViewportView(jTextArea4);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jdHelpArticulosLayout = new javax.swing.GroupLayout(jdHelpArticulos.getContentPane());
        jdHelpArticulos.getContentPane().setLayout(jdHelpArticulosLayout);
        jdHelpArticulosLayout.setHorizontalGroup(
            jdHelpArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdHelpArticulosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdHelpArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jdHelpArticulosLayout.setVerticalGroup(
            jdHelpArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdHelpArticulosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel26.setFont(new java.awt.Font("Lucida Bright", 1, 48)); // NOI18N
        jLabel26.setText("Gestión de Líneas de Factura");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(171, 171, 171))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addContainerGap())
        );

        jTextArea5.setEditable(false);
        jTextArea5.setColumns(20);
        jTextArea5.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jTextArea5.setRows(5);
        jTextArea5.setText("Ayuda gestión líneas de factura:\n\t\n\t* Para agregar una línea de factura deberá haber llegado aquí tras haber seleccionado una factura en\n\t    la ventana de Facturas, rellene todos los campos y pulse \"Añadir\", si selecciona en los\n\t    desplegables de \"Descripción\" o \"Referencia\" los campos cantidad precio e iva automáticamente\n\t    se rellenarán con la cantidad en stock disponible, el precio y el iva que le corresponden. Tenga en cuenta\n\t    que una factura no puede tener dos líneas de factura con el mismo número.\n\t\n\tSi agrega una línea de factura con un artículo del cual no dispone en tienda el programa le avisará.\n\t\n\t* Para eliminar una línea de factura selecione la línea en la tabla y pulse \"Eliminar\". (Es stock se modificará\n\t    automáticamente).\n\n\t* Para modificar una línea de factura seleccione la línea en la tabla y pulse \"Modificar\". (El programa le avisará \n\t    en caso de que no disponga de más unidades de ese artículo o modificará la cantidad del mismo en el stock).\n\n\t* Si ha seleccionado una factura en la ventana de Facturas, desde la gestión del desglose de ésta podrá calcular\n\t    su total, pulse \"Calcular Factura\", esta acción hará que el programa nuevamente le lleve hasta la ventana de \n\t    Facturas con el cáculo total de la misma.\n\n\t* Si desea buscar una línea de factura de una factura en concreto, debe asegurase de haber seleccionado una \n\t    factura en la ventana de Facturas, a continuación proceda a insertar el número de línea esacto en la barra del\n\t    buscador y por último presione \"Buscar\".\n\nTenga en cuenta que siempre puede acceder a la ventana de Facturas, Clientes o Stock desde el menú de Opciones.\n\n\t   \n\n\t\n\t");
        jScrollPane11.setViewportView(jTextArea5);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jdHelpLineasLayout = new javax.swing.GroupLayout(jdHelpLineas.getContentPane());
        jdHelpLineas.getContentPane().setLayout(jdHelpLineasLayout);
        jdHelpLineasLayout.setHorizontalGroup(
            jdHelpLineasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdHelpLineasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdHelpLineasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jdHelpLineasLayout.setVerticalGroup(
            jdHelpLineasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdHelpLineasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel25.setFont(new java.awt.Font("Imprint MT Shadow", 2, 48)); // NOI18N
        jLabel25.setText("INICIO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        menuOpciones.setText("Opciones");

        itemCli.setText("Gestionar Clientes");
        itemCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCliActionPerformed(evt);
            }
        });
        menuOpciones.add(itemCli);

        itemFac.setText("Gestionar Facturas");
        itemFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFacActionPerformed(evt);
            }
        });
        menuOpciones.add(itemFac);

        itemStock.setText("Gestionar Stock");
        itemStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemStockActionPerformed(evt);
            }
        });
        menuOpciones.add(itemStock);

        itemLinea.setText("Gestionar Líneas de faturas");
        itemLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLineaActionPerformed(evt);
            }
        });
        menuOpciones.add(itemLinea);

        menuBar.add(menuOpciones);

        jMenu2.setText("Help");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        itemInicio.setText("Ayuda Inicio");
        itemInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInicioActionPerformed(evt);
            }
        });
        jMenu2.add(itemInicio);

        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFacActionPerformed
        jdFacturas.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_itemFacActionPerformed

    private void itemCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCliActionPerformed
        jdClientes.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_itemCliActionPerformed

    private void itemStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemStockActionPerformed
        jdArticulos.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_itemStockActionPerformed

    private void itemFac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFac1ActionPerformed
        jdFacturas.setVisible(true);
        this.setVisible(false);
        jdArticulos.setVisible(false);
        jdClientes.setVisible(false);
    }//GEN-LAST:event_itemFac1ActionPerformed

    private void itemStock1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemStock1ActionPerformed
        jdArticulos.setVisible(true);
        jdFacturas.setVisible(false);
        this.setVisible(false);
        jdClientes.setVisible(false);
    }//GEN-LAST:event_itemStock1ActionPerformed

    private void itemInicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInicio1ActionPerformed
        this.setVisible(true);
        jdClientes.setVisible(false);
        jdArticulos.setVisible(false);
        jdFacturas.setVisible(false);
    }//GEN-LAST:event_itemInicio1ActionPerformed

    private void itemCli2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCli2ActionPerformed
        jdClientes.setVisible(true);
        this.setVisible(false);
        jdArticulos.setVisible(false);
        jdFacturas.setVisible(false);
    }//GEN-LAST:event_itemCli2ActionPerformed

    private void itemFac2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFac2ActionPerformed
        jdFacturas.setVisible(true);
        this.setVisible(false);
        jdArticulos.setVisible(false);
        jdClientes.setVisible(false);
    }//GEN-LAST:event_itemFac2ActionPerformed

    private void itemInicio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInicio2ActionPerformed
        this.setVisible(true);
        jdClientes.setVisible(false);
        jdArticulos.setVisible(false);
        jdFacturas.setVisible(false);
    }//GEN-LAST:event_itemInicio2ActionPerformed

    private void itemCli3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCli3ActionPerformed
        jdClientes.setVisible(true);
        this.setVisible(false);
        jdArticulos.setVisible(false);
        jdFacturas.setVisible(false);
    }//GEN-LAST:event_itemCli3ActionPerformed

    private void itemStock3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemStock3ActionPerformed
        jdArticulos.setVisible(true);
        jdFacturas.setVisible(false);
        this.setVisible(false);
        jdClientes.setVisible(false);
    }//GEN-LAST:event_itemStock3ActionPerformed

    private void itemInicio3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInicio3ActionPerformed
        this.setVisible(true);
        jdClientes.setVisible(false);
        jdArticulos.setVisible(false);
        jdFacturas.setVisible(false);
    }//GEN-LAST:event_itemInicio3ActionPerformed

    private void jdClientesWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jdClientesWindowClosing
        System.exit(0);
    }//GEN-LAST:event_jdClientesWindowClosing

    private void jdArticulosWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jdArticulosWindowClosing
        System.exit(0);
    }//GEN-LAST:event_jdArticulosWindowClosing

    private void jdFacturasWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jdFacturasWindowClosing
        System.exit(0);
    }//GEN-LAST:event_jdFacturasWindowClosing

    private void btAddCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddCliActionPerformed
        /*
        Añadir clientes
         */
        Clientes c = new Clientes();
        c.setDnicif(txDniCli.getText());
        c.setNombrecli(txNombre.getText());
        if (cc.addCliente(c)) {
            if (!txDniCli.getText().isEmpty() || !txNombre.getText().isEmpty()) {
                modelCli.setRowCount(0);
                modelCli.addRow(new Object[]{
                    c.getDnicif(),
                    c.getNombrecli()
                });
                cbClientesDisponibles.removeAllItems();
                cc.fillComboBox(cbClientesDisponibles);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No puedes dejar campos vacíos o añadir un cliente que ya exista.");
        }
        

    }//GEN-LAST:event_btAddCliActionPerformed

    private void btDeleteCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteCliActionPerformed
        /*
        Eliminar clientes
         */
        try {
            int i = jTableClientes.getSelectedRow();
            Clientes c = new Clientes();
            String dni = modelCli.getValueAt(i, 0).toString();
            String nombre = modelCli.getValueAt(i, 1).toString();
            c.setNombrecli(nombre);
            c.setDnicif(dni);
            if (cc.deleteCliente(c)) {
                modelCli.removeRow(i);
                cbClientesDisponibles.removeAllItems();
                cc.fillComboBox(cbClientesDisponibles);
            }
        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "Asegúrate de haber seleccionado un cliente en la tabla para eliminar.");
        }
        

    }//GEN-LAST:event_btDeleteCliActionPerformed

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
        
        btFacAsociadas.setEnabled(true);
        btDeleteCli.setEnabled(true);
        btModCli.setEnabled(true);
        int i = jTableClientes.getSelectedRow();
        txDniCli.setText(modelCli.getValueAt(i, 0).toString());
        txNombre.setText(modelCli.getValueAt(i, 1).toString());

    }//GEN-LAST:event_jTableClientesMouseClicked

    private void btActualizarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizarCliActionPerformed
        showTableClientes();
    }//GEN-LAST:event_btActualizarCliActionPerformed

    private void btActualizarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizarArtActionPerformed
        showTableArticulos();
    }//GEN-LAST:event_btActualizarArtActionPerformed

    private void btModCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModCliActionPerformed
        /*
        Modificar clientes
         */
        Clientes c = new Clientes();
        c.setNombrecli(txNombre.getText());
        c.setDnicif(txDniCli.getText());
        if (cc.modifyCliente(c)) {
            modelCli.setRowCount(0);
            modelCli.addRow(new Object[]{
                c.getDnicif(),
                c.getNombrecli()
            });
        } else {
            JOptionPane.showMessageDialog(null, "Para modificar no dejes campos vacíos.");
        }
    }//GEN-LAST:event_btModCliActionPerformed

    private void btBuscarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarCliActionPerformed
        /*
        Buscamos el cliente por el numero de dni
         */
        if (!txBuscarCli.getText().isEmpty()) {
            List cliente = cc.bucarClientes(txBuscarCli.getText());
            if (!cliente.isEmpty()) {
                for (Iterator it = cliente.iterator(); it.hasNext();) {
                    Clientes c = (Clientes) it.next();
                    {
                        modelCli.setRowCount(0);
                        modelCli.addRow(new Object[]{
                            c.getDnicif(),
                            c.getNombrecli()});
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El cliente no existe.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Inserta el nombre del cliente para buscar.");
        }
        

    }//GEN-LAST:event_btBuscarCliActionPerformed

    private void btAddArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddArtActionPerformed
        /*
        Añadimos la informacion en los campos y la recogemos para añadir un artículo
         */
        try {
            Articulos a = new Articulos();
            a.setReferencia(txRefArticulo.getText());
            a.setDescripcion(txDescripcion.getText());
            BigDecimal bd1 = a.getFormattedPrecio(txPrecioArticulo.getText());
            a.setPrecio(bd1);
            BigDecimal bd2 = a.getFormattedIva(txIvaArt.getText());
            a.setPorciva(bd2);
            BigDecimal bd3 = a.getFormattedStock(txStockDisponible.getText());
            a.setStock(bd3);
            
            if (ca.addArticulo(a)) {
                if (!txRefArticulo.getText().isEmpty() || !txDescripcion.getText().isEmpty() || !txPrecioArticulo.getText().isEmpty() || !txIvaArt.getText().isEmpty() || !txStockDisponible.getText().isEmpty()) {
                    modelArt.setRowCount(0);
                    modelArt.addRow(new Object[]{
                        a.getReferencia(),
                        a.getDescripcion(),
                        a.getPrecio(),
                        a.getPorciva(),
                        a.getStock()
                    });
                    cbDescripcion.removeAllItems();
                    cbRef.removeAllItems();
                    cf.fillComboDescripcion(cbDescripcion);
                    cf.fillComboRef(cbRef);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El codigo del articulo o la referencia ya existe");
            }
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "No puedes dejar campos vacíos, además asegúrate de insertar correctamente los datos. Ejemplo: el campo cantidad o IVA deben ser numéricos.");
            
        }
        

    }//GEN-LAST:event_btAddArtActionPerformed

    private void btDeleteArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteArtActionPerformed
        /*
        Obtenemos los valores para eliminar al estar obligados a selecionar un registro
         */
        try {
            int i = jTableArticulo.getSelectedRow();
            Articulos a = new Articulos();
            String ref = modelArt.getValueAt(i, 0).toString();
            String des = modelArt.getValueAt(i, 1).toString();
            BigDecimal precio = (BigDecimal) modelArt.getValueAt(i, 2);
            BigDecimal iva = (BigDecimal) modelArt.getValueAt(i, 3);
            BigDecimal stock = (BigDecimal) modelArt.getValueAt(i, 4);
            a.setReferencia(ref);
            a.setDescripcion(des);
            a.setPrecio(precio);
            a.setPorciva(iva);
            a.setStock(stock);
            if (ca.deleteArticulos(a)) {
                modelArt.removeRow(i);
                cbDescripcion.removeAllItems();
                cbRef.removeAllItems();
                cf.fillComboDescripcion(cbDescripcion);
                cf.fillComboRef(cbRef);
            }
        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "Asegúrate de haber seleccionado un cliente en la tabla para eliminar.");
        }

    }//GEN-LAST:event_btDeleteArtActionPerformed

    private void jTableArticuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableArticuloMouseClicked
        int i = jTableArticulo.getSelectedRow();
        btDeleteArt.setEnabled(true);
        btModArt.setEnabled(true);
        txRefArticulo.setText((String) modelArt.getValueAt(i, 0));
        txDescripcion.setText((String) modelArt.getValueAt(i, 1));
        txPrecioArticulo.setText(modelArt.getValueAt(i, 2).toString());
        txIvaArt.setText(modelArt.getValueAt(i, 3).toString());
        txStockDisponible.setText(modelArt.getValueAt(i, 4).toString());
    }//GEN-LAST:event_jTableArticuloMouseClicked

    private void btModArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModArtActionPerformed
        /*
        Obtendremos los valores de los campos de introduccion de informacion de artículo
        estamos obligados a seleccionar una registro de la tabla
         */
        try {
            Articulos a = new Articulos();
            a.setReferencia(txRefArticulo.getText());
            a.setDescripcion(txDescripcion.getText());
            BigDecimal bd1 = a.getFormattedPrecio(txPrecioArticulo.getText());
            a.setPrecio(bd1);
            BigDecimal bd2 = a.getFormattedIva(txIvaArt.getText());
            a.setPorciva(bd2);
            BigDecimal bd3 = a.getFormattedStock(txStockDisponible.getText());
            a.setStock(bd3);
            if (ca.modifyArticulos(a)) {
                modelArt.setRowCount(0);
                modelArt.addRow(new Object[]{
                    a.getReferencia(),
                    a.getDescripcion(),
                    a.getPrecio(),
                    a.getPorciva(),
                    a.getStock()
                });
            } else {
                JOptionPane.showMessageDialog(null, "Añade el artículo antes de modificarlo.");
            }
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "No puedes dejar campos vacíos, además asegúrate de insertar correctamente los datos. Ejemplo: el campo cantidad o IVA deben ser numéricos.");
        }
        

    }//GEN-LAST:event_btModArtActionPerformed

    private void btFacAsociadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFacAsociadasActionPerformed
        /*
        Primero limpia la tabla de facturas cabeceras para posteriormente 
        llenarla con las facturas asociadas al cliente seleccionado
         */
        try {
            modelFac.setRowCount(0);
            String dniCif = (String) modelCli.getValueAt(jTableClientes.getSelectedRow(), 0);
            Clientes c = cc.getClientesDni(dniCif);
            List listFacCliente = new ArrayList(c.getFacturasCabs());
            if (listFacCliente != null) {
                listFacCliente.sort(FacturasCab.ordenarFactura);
                jdFacturas.setVisible(true);
                jdClientes.setVisible(false);
                for (Iterator it = listFacCliente.iterator(); it.hasNext();) {
                    FacturasCab fc = (FacturasCab) it.next();
                    modelFac.addRow(new Object[]{
                        fc.getNumfac(),
                        fc.getFechaFormat(),
                        fc.getClientes().getDnicif()
                    });
                }
            }
        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "Asegúrate de selecionar un cliente para ver sus facturas");
        }

    }//GEN-LAST:event_btFacAsociadasActionPerformed

    private void jTableCabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCabMouseClicked
        int i = jTableCab.getSelectedRow();
        txNumFacCab.setText(modelFac.getValueAt(i, 0).toString());
        txFecha.setText(modelFac.getValueAt(i, 1).toString());
        cbClientesDisponibles.setSelectedItem(modelFac.getValueAt(i, 2).toString());
        Long numFac;
        /*
        Activa los botones para ver mas detalles eliminarla o modificar  la factura seleccionada
         */
        btDetalles.setEnabled(true);
        btDeleteFac.setEnabled(true);
        btModifyFac.setEnabled(true);
        /*
        Mostramos los detalles de la factura cabecera selecionada
        Si la tabla FacturasLin esta vacía, procede a rellenarla.
         */
        if (modelLin.getRowCount() == -1) {
            numFac = (Long) modelFac.getValueAt(jTableCab.getSelectedRow(), 0);
            FacturasCab fc = cf.getNumFacCab(numFac);
            lbNumFac.setText(modelFac.getValueAt(jTableCab.getSelectedRow(), 0).toString());
            List listaLineaFactura = new ArrayList(fc.getFacturasLins());
            if (listaLineaFactura != null) {
                listaLineaFactura.sort(FacturasLin.ordenarLineas);
                for (Iterator it = listaLineaFactura.iterator(); it.hasNext();) {
                    FacturasLin fl = (FacturasLin) it.next();
                    modelLin.addRow(new Object[]{
                        fl.getFacturasCab().getNumfac(),
                        fl.getId().getLineafac(),
                        fl.getArticulos().getReferencia(),
                        fl.getCantidad().toString(),
                        fl.getPrecio().toString(),
                        fl.getDtolinea().toString(),
                        fl.getIvalinea()});
                }
            }
            /*
             Si no está vacía la limpia antes de rellenarla de nuevo
             */
        } else {
            modelLin.setRowCount(0);
            /*Esto a continuación se debe cuando importamos una factura el numero de factura viene como string
            sabiendo esto lo convertiremos a long , de esta forma al hacer clic en la factura nos devulverá todas sus lineas
             */
            if (modelFac.getValueAt(jTableCab.getSelectedRow(), 0) instanceof String) {
                numFac = Long.valueOf(modelFac.getValueAt(jTableCab.getSelectedRow(), 0).toString());
            } else {
                numFac = (Long) modelFac.getValueAt(jTableCab.getSelectedRow(), 0);
            }
            
            FacturasCab fc = cf.getNumFacCab(numFac);
            lbNumFac.setText(modelFac.getValueAt(jTableCab.getSelectedRow(), 0).toString());
            List listaLineaFactura = new ArrayList(fc.getFacturasLins());
            if (listaLineaFactura != null) {
                listaLineaFactura.sort(FacturasLin.ordenarLineas);
                for (Iterator it = listaLineaFactura.iterator(); it.hasNext();) {
                    
                    FacturasLin fl = (FacturasLin) it.next();
                    modelLin.addRow(new Object[]{
                        fl.getFacturasCab().getNumfac(),
                        fl.getId().getLineafac(),
                        fl.getArticulos().getReferencia(),
                        fl.getCantidad().toString(),
                        fl.getPrecio().toString(),
                        fl.getDtolinea().toString(),
                        fl.getIvalinea()});
                }
                
            }
        }
        

    }//GEN-LAST:event_jTableCabMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        showTableFacturas();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btAddFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddFacActionPerformed
        /*
        Obtenemos los valores de los campos para añadir una nueva factura
         */
        
        try {
            Clientes c = cc.getClientesDni((String) cbClientesDisponibles.getSelectedItem());
            FacturasCab f = new FacturasCab(Long.parseLong(txNumFacCab.getText()), c);
            FacturasTot ft = new FacturasTot(f, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE);
            HashSet hs = new HashSet();
            FacturasCab f2 = new FacturasCab();
            Date date = new Date();
            date = f2.getFormattedDate(txFecha.getText());
            f2 = new FacturasCab(Long.parseLong(txNumFacCab.getText()), c, date, ft, hs);
            if (!txFecha.getText().isEmpty()) {
                if (cf.addFacturasCab(f2)) {
                    if (!txNumFacCab.getText().isEmpty()) {
                        cf.addToFacturaTot(ft);
                        modelFac.setRowCount(0);
                        modelFac.addRow(new Object[]{f2.getNumfac(), f2.getFechaFormat(), f2.getClientes().getDnicif(), f2.getFacturasTot(), f2.getFacturasLins()});
                        cbDescripcion.removeAllItems();
                        cbRef.removeAllItems();
                        cf.fillComboDescripcion(cbDescripcion);
                        cf.fillComboRef(cbRef);
                    }
                }
            }
            
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        }
        

    }//GEN-LAST:event_btAddFacActionPerformed

    private void itemLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLineaActionPerformed
        this.setVisible(false);
        jdLineaFactura.setVisible(true);
    }//GEN-LAST:event_itemLineaActionPerformed

    private void itemCli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCli1ActionPerformed
        jdLineaFactura.setVisible(false);
        jdClientes.setVisible(true);
    }//GEN-LAST:event_itemCli1ActionPerformed

    private void itemFac3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFac3ActionPerformed
        jdLineaFactura.setVisible(false);
        jdFacturas.setVisible(true);
    }//GEN-LAST:event_itemFac3ActionPerformed

    private void itemStock2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemStock2ActionPerformed
        jdLineaFactura.setVisible(false);
        jdArticulos.setVisible(true);
    }//GEN-LAST:event_itemStock2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jdLineaFactura.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jdClientes.setVisible(false);
        jdLineaFactura.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jdArticulos.setVisible(false);
        jdLineaFactura.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jdFacturas.setVisible(false);
        jdLineaFactura.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jdLineaFacturaWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jdLineaFacturaWindowClosing
        System.exit(0);
    }//GEN-LAST:event_jdLineaFacturaWindowClosing

    private void btDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDetallesActionPerformed
        jdFacturas.setVisible(false);
        jdLineaFactura.setVisible(true);

    }//GEN-LAST:event_btDetallesActionPerformed

    private void btDeleteFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteFacActionPerformed
        /*
        Obtenemos los valores de una fila selecionada para eliminar la factura
         */
        try {
            int i = jTableCab.getSelectedRow();
            FacturasCab fc = new FacturasCab();
            Long numFac = (Long) modelFac.getValueAt(i, 0);
            String fecha = (String) modelFac.getValueAt(i, 1);
            String dni = (String) modelFac.getValueAt(i, 2);
            
            fc.setNumfac(numFac);
            fc.setFechafac(fc.getFormattedDate(fecha));
            fc.setClientes(cc.getClientesDni(dni));
            cf.deleteFacturasCab(fc);
            modelFac.removeRow(i);
            showTableFacturas();
        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "Asegúrate de selecionar un registro para eliminar.");
        }

    }//GEN-LAST:event_btDeleteFacActionPerformed

    private void btModifyFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModifyFacActionPerformed
        /*
        Se deberá selecionar un registro de la tabla, luego obtendremos los valores de los campos
        de introduccion de informacion para ser modificada la fatura.
         */
        try {
            FacturasCab fc = new FacturasCab();
            Long nf = Long.valueOf(txNumFacCab.getText());
            fc.setNumfac(nf);
            Date date = fc.getFormattedDate(txFecha.getText());
            fc.setFechafac(date);
            Clientes c = cc.getClientesDni(cbClientesDisponibles.getSelectedItem().toString());
            fc.setClientes(c);
            if (cf.modifyFactura(fc)) {
                modelFac.setRowCount(0);
                modelFac.addRow(new Object[]{
                    fc.getNumfac(),
                    fc.getFechaFormat(),
                    fc.getClientes().getDnicif()
                });
            } else {
                JOptionPane.showMessageDialog(null, "Elige una factura para modifcar.");
            }
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Campos vacíos.");
        }
        

    }//GEN-LAST:event_btModifyFacActionPerformed

    private void btAddLinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddLinActionPerformed
        /*
        Debemos haber seleccionado una factura en la vantana de facturas para seguidamente añadir una linea
        obteniendo los valores de los campos de inserción de informacion de lineas de facturas.
         */
        try {
            FacturasLin fl = new FacturasLin();
            Long nl = Long.valueOf(txLineaFac.getText());
            //FacturasLinId fli = new FacturasLinId((Long) cbNumFac.getSelectedItem(), nl);
            FacturasLinId fli = new FacturasLinId(Long.parseLong(lbNumFac.getText()), nl);
            Articulos a = ca.getRefArticulo((String) cbRef.getSelectedItem());
            FacturasCab fc = cf.getNumFacCab(Long.parseLong(lbNumFac.getText()));
            BigDecimal can = fl.getFormattedCantidad(txCantidad.getText());
            BigDecimal precio = fl.getFormattedPrecio(txPrecio.getText());
            BigDecimal dto = fl.getFormattedDto(txDto.getText());
            BigDecimal ivalinea = fl.getFormattedIvaLinea(txIva.getText());
            
            fl = new FacturasLin(fli, a, fc, can, precio, dto, ivalinea);

            /*
            Comprobamos el stock en tienda
             */
            if (a.getStock().subtract(can).signum() < 0) {
                JOptionPane.showMessageDialog(null, "Out of Stock. "
                        + " Disponemos de " + a.getStock() + " unidad(es) en tienda.");
            } else {
                /*
            Si añadimos correctamente, modificamos el stock y actualizamos la tabla.
                 */
                if (cf.addFacturasLin(fl)) {
                    fl.getArticulos();
                    
                    a.setStock(a.getStock().subtract(can));
                    ca.modifyArticulos(a);
                    showTableArticulos();
                    
                    modelLin.addRow(new Object[]{
                        fl.getId().getNumfac(),
                        fl.getId().getLineafac(),
                        fl.getArticulos().getReferencia(),
                        fl.getCantidad(),
                        fl.getPrecio(),
                        fl.getDtolinea(),
                        fl.getIvalinea()
                    });
                }
            }
            
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "No puedes dejar campos vacios, además asegúrese de que todos los datos introducidos son numéricos.");
            
        }
        

    }//GEN-LAST:event_btAddLinActionPerformed

    private void btDeleteLinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteLinActionPerformed
        try {
            /*
            Recogemos los valores de una fila seleccionada para eliminar ese objeto linea
             */
            
            int i = jTableLinea.getSelectedRow();
            FacturasLin fl = new FacturasLin();
            Long numFac = (Long) modelLin.getValueAt(i, 0);
            Long nLinea = (Long) modelLin.getValueAt(i, 1);
            FacturasLinId fli = new FacturasLinId(numFac, nLinea);
            FacturasCab fc = cf.getNumFacCab(numFac);
            String ref = (String) modelLin.getValueAt(i, 2);
            
            Articulos a = ca.getRefArticulo(ref);
            
            BigDecimal can = fl.getFormattedCantidad(modelLin.getValueAt(i, 3).toString());
            BigDecimal precio = fl.getFormattedPrecio(modelLin.getValueAt(i, 4).toString());
            BigDecimal descuento = fl.getFormattedDto(modelLin.getValueAt(i, 5).toString());
            BigDecimal ivaLinea = fl.getFormattedIvaLinea(modelLin.getValueAt(i, 6).toString());
            
            fl.setId(fli);
            fl.setArticulos(a);
            fl.setFacturasCab(fc);
            fl.setCantidad(can);
            fl.setPrecio(precio);
            fl.setDtolinea(descuento);
            fl.setIvalinea(ivaLinea);
            
            fl = new FacturasLin(fli, a, fc, can, precio, descuento, ivaLinea);

            /*
            Moficicamos el stock si la operación eliminar tiene éxito.
             */
            if (cf.deleteFacturasLin(fl)) {
                fl.getArticulos();
                a.setStock(a.getStock().add(can));
                ca.modifyArticulos(a);
                showTableArticulos();
                modelLin.removeRow(i);
            }
            
        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "Asegúrese de elegir una linea de factura en la tabla para eliminar.");
        }
        

    }//GEN-LAST:event_btDeleteLinActionPerformed

    private void btModifyLinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModifyLinActionPerformed

        /*
        Para modificar una linea de factura debemos haber seleccionado una linea en la vantana de facturas,
        tras acceder a la pantalla de lineas de factura eligiremos un registro en la tabla para obtener los 
        valores a modificar de los campos de insercion de información.
         */
        try {
            
            FacturasLin fl = new FacturasLin();

            //Obtenemos el ID de la fila
            FacturasLinId fli = new FacturasLinId();
            Long numFac = Long.valueOf(lbNumFac.getText());
            fli.setNumfac(numFac);
            Long nLinea = Long.valueOf(txLineaFac.getText());
            fli.setLineafac(nLinea);

            //Elegimos la modificacion de articulo
            String ref = cbRef.getSelectedItem().toString();
            Articulos a = ca.getRefArticulo(ref);
            fl.getArticulos();
            
            FacturasCab fc = cf.getNumFacCab(numFac);
            //Recogemos valores cantidad, precio, descuento e IVA
            BigDecimal can = fl.getFormattedCantidad(txCantidad.getText());
            BigDecimal precio = fl.getFormattedPrecio(txPrecio.getText());
            BigDecimal descuento = fl.getFormattedDto(txDto.getText());
            BigDecimal ivaLinea = fl.getFormattedIvaLinea(txIva.getText());
            
            fl = new FacturasLin(fli, a, fc, can, precio, descuento, ivaLinea);

            /*
            Debemos haber seleccionado correctamente un registro para que el stock sea modificado automaticamente.
             */
            try {
                int i = jTableLinea.getSelectedRow();
                Long l = Long.parseLong(jTableLinea.getValueAt(i, 3).toString());
                BigDecimal cantidadActual = BigDecimal.valueOf(l);
                /*
                Modificamos la linea
                 */
 /*
                Comprobamos el stock en tienda
                 */
                if (a.getStock().subtract(can).signum() < 0) {
                    JOptionPane.showMessageDialog(null, "Out of Stock. "
                            + " Disponemos de " + a.getStock() + " unidad(es) en tienda.");
                } else {
                    if (cf.modifyFacturaLin(fl)) {
                        /*
                    Actualizamos la tabla Lineas de facturas
                         */
                        try {
                            modelLin.setRowCount(0);
                            FacturasCab fcActualizar = cf.getNumFacCab(Long.parseLong(lbNumFac.getText()));
                            List listaLineaFactura = new ArrayList(fcActualizar.getFacturasLins());
                            listaLineaFactura.sort(FacturasLin.ordenarLineas);
                            for (Iterator it = listaLineaFactura.iterator(); it.hasNext();) {
                                FacturasLin factLin = (FacturasLin) it.next();
                                
                                modelLin.addRow(new Object[]{
                                    factLin.getFacturasCab().getNumfac(),
                                    factLin.getId().getLineafac(),
                                    factLin.getArticulos().getReferencia(),
                                    factLin.getCantidad().toString(),
                                    factLin.getPrecio().toString(),
                                    factLin.getDtolinea().toString(),
                                    factLin.getIvalinea()});
                            }
                        } catch (NumberFormatException n) {
                        }
                    }
                    /*modificamos el stock del artículo, si añadimos más unidades 
                restará a la cantidad total del stock, si por el contrario quitamos
                unidades de la linea de factura sumará esas cantidades al stock.
                     */
                    if (cantidadActual.compareTo(can) < 0) {
                        a.setStock(a.getStock().subtract(can));
                        ca.modifyArticulos(a);
                        showTableArticulos();
                    } else {
                        a.setStock(a.getStock().add(cantidadActual).subtract(can));
                        ca.modifyArticulos(a);
                        showTableArticulos();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ax) {
                JOptionPane.showMessageDialog(null, "Porfavor seleccione la línea de factura para que el stock sea modificado correctamente.");
            }
            
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Asegúrese de seleccionar un registro en la tabla para ser modificado. "
                    + "Tenga en cuenta que los todos los campos a rellenar son numéricos y recuerde no dejar campos vacíos .");
        }
    }//GEN-LAST:event_btModifyLinActionPerformed

    private void cbDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDescripcionActionPerformed
        /*
        Buscamos el artículo por descripcion
         */
        List articulo = ca.bucarInfoArticulo(cbDescripcion.getSelectedItem().toString());
        for (Iterator it = articulo.iterator(); it.hasNext();) {
            Articulos a = (Articulos) it.next();
            cbRef.setSelectedItem(a.getReferencia());
            txCantidad.setText(a.getStock().toString());
            txPrecio.setText(a.getPrecio().toString());
            txIva.setText(a.getPorciva().toString());
            
        }
        

    }//GEN-LAST:event_cbDescripcionActionPerformed

    private void btCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalcularActionPerformed
        /*
        Caculamos una factura seleccionada en la ventana de Facturas
         */
        try {
            Long numFac;
            modelTot.setRowCount(0);
            if (modelFac.getValueAt(jTableCab.getSelectedRow(), 0) instanceof String) {
                numFac = Long.valueOf(modelFac.getValueAt(jTableCab.getSelectedRow(), 0).toString());
            } else {
                numFac = (Long) modelFac.getValueAt(jTableCab.getSelectedRow(), 0);
            }
            FacturasCab fc = cf.getNumFacCab(numFac);
            modelTot.addRow(new Object[]{
                fc.getNumfac(), fc.getBaseImp(), fc.getDto(), fc.getIvaTotal(), fc.getTotal()
            });
        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una factura para calcular su total");
        }
        

    }//GEN-LAST:event_btCalcularActionPerformed

    private void btCalculoLineasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalculoLineasActionPerformed
        /*
        Caculamos una factura seleccionada en la ventana de Linea de Facturas
         */
        try {
            if (!lbNumFac.getText().isEmpty()) {
                jdLineaFactura.setVisible(false);
                jdFacturas.setVisible(true);
                modelTot.setRowCount(0);
                Long numFac = Long.parseLong(lbNumFac.getText());
                FacturasCab fc = cf.getNumFacCab(numFac);
                modelTot.addRow(new Object[]{
                    fc.getNumfac(), fc.getBaseImp(), fc.getDto(), fc.getIvaTotal(), fc.getTotal()
                });
            } else {
                JOptionPane.showMessageDialog(null, "Debes elegir una factura en la ventana de facturas para calcular el total");
            }
            
        } catch (NumberFormatException n) {
            
        }

    }//GEN-LAST:event_btCalculoLineasActionPerformed

    private void jTableLineaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLineaMouseClicked
        int i = jTableLinea.getSelectedRow();
        lbNumFac.setText(modelLin.getValueAt(i, 0).toString());
        txLineaFac.setText(modelLin.getValueAt(i, 1).toString());
        Articulos a = ca.getRefArticulo((modelLin.getValueAt(i, 2).toString()));
        cbDescripcion.setSelectedItem(a.getDescripcion());
        cbRef.setSelectedItem(a.getReferencia());
        txCantidad.setText(modelLin.getValueAt(i, 3).toString());
        txPrecio.setText(modelLin.getValueAt(i, 4).toString());
        txDto.setText(modelLin.getValueAt(i, 5).toString());
        txIva.setText(modelLin.getValueAt(i, 6).toString());
    }//GEN-LAST:event_jTableLineaMouseClicked

    private void btEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEstadisticasActionPerformed
        jdEstadisticas.setVisible(true);

    }//GEN-LAST:event_btEstadisticasActionPerformed

    private void btMostrarEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMostrarEstadisticasActionPerformed
        
        day1 = Integer.parseInt(txFechaUno.getText().substring(0, 2));
        month1 = Integer.parseInt(txFechaUno.getText().substring(3, 5));
        year1 = Integer.parseInt(txFechaUno.getText().substring(6, 10));
        date1 = new java.sql.Date(year1 - 1900, month1 - 1, day1);
        
        day2 = Integer.parseInt(txFechaDos.getText().substring(0, 2));
        month2 = Integer.parseInt(txFechaDos.getText().substring(3, 5));
        year2 = Integer.parseInt(txFechaDos.getText().substring(6, 10));
        date2 = new java.sql.Date(year2 - 1900, month2 - 1, day2);
        
        p.Procedure(
                cbClienteUno.getSelectedItem().toString(),
                cbClienteDos.getSelectedItem().toString(),
                date1,
                date2);
        
        el = cc.estadisticasList();
        for (EstadisticasClientes e : el) {
            modelEstadisticas.addRow(new Object[]{
                e.getId().getAnio(),
                e.getId().getMesNum(),
                e.getMesNom(),
                e.getId().getDnicif(),
                e.getNombrecli(),
                e.getSumaBase(),
                e.getSumaDtos(),
                e.getSumaIva(),
                e.getSumaTotales()});
        }
    }//GEN-LAST:event_btMostrarEstadisticasActionPerformed

    private void btExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExportarActionPerformed
        try {
            int i = jTableCab.getSelectedRow();
            Long numFac = Long.parseLong(jTableCab.getValueAt(i, 0).toString());
            FacturasCab fc = cf.getNumFacCab(numFac);
            
            ExportarXml xml = new ExportarXml();
            xml.generarDocumento(fc);
            xml.crearXml();
            
        } catch (ParserConfigurationException | TransformerException ex) {
            ex.getMessage();
        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "Porfavor, elije el registro en la tabla de facturas que desea exportar");
        }
        

    }//GEN-LAST:event_btExportarActionPerformed

    private void cbRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRefActionPerformed
        try {
            Articulos a = ca.getRefArticulo(cbRef.getSelectedItem().toString());
            cbDescripcion.setSelectedItem(a.getDescripcion());
            txCantidad.setText(a.getStock().toString());
            txPrecio.setText(a.getPrecio().toString());
            txIva.setText(a.getPorciva().toString());
        } catch (NullPointerException n) {
            
        }

    }//GEN-LAST:event_cbRefActionPerformed

    private void btImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImportarActionPerformed
        try {
            ImportarXml importar = new ImportarXml(jTableCab);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btImportarActionPerformed

    private void btBuscarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarArtActionPerformed
        /*
        Buscamos el artículo por referencia
         */
        if (!txBuscarArt.getText().isEmpty()) {
            List articulo = ca.bucarArticulos(txBuscarArt.getText());
            if (!articulo.isEmpty()) {
                for (Iterator it = articulo.iterator(); it.hasNext();) {
                    Articulos a = (Articulos) it.next();
                    {
                        modelArt.setRowCount(0);
                        modelArt.addRow(new Object[]{
                            a.getReferencia(),
                            a.getDescripcion(),
                            a.getPrecio(),
                            a.getPorciva(),
                            a.getStock()});
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El artículo no existe.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Inserta la referencia del articulo para buscar.");
        }

    }//GEN-LAST:event_btBuscarArtActionPerformed

    private void btBuscarFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarFacActionPerformed
        try {
            if (!txBuscarFac.getText().isEmpty()) {
                List factura = cf.buscarFactura(Long.valueOf(txBuscarFac.getText()));
                if (!factura.isEmpty()) {
                    for (Iterator it = factura.iterator(); it.hasNext();) {
                        FacturasCab f = (FacturasCab) it.next();
                        {
                            modelFac.setRowCount(0);
                            modelFac.addRow(new Object[]{
                                f.getNumfac(),
                                f.getFechaFormat(),
                                f.getClientes().getDnicif()
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La factura no existe.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Inserta el número de factura para buscar.");
            }
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Por favor, verifíca que has introducido un número.");
        }

    }//GEN-LAST:event_btBuscarFacActionPerformed

    private void btBuscarLinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarLinActionPerformed
        try {
            if (!txBuscarLin.getText().isEmpty()) {
                List linea = cf.bucarLineaFac(Long.valueOf(lbNumFac.getText()), Long.valueOf(txBuscarLin.getText()));
                if (!linea.isEmpty()) {
                    modelLin.setRowCount(0);
                    for (Iterator it = linea.iterator(); it.hasNext();) {
                        FacturasLin f = (FacturasLin) it.next();
                        {
                            modelLin.addRow(new Object[]{
                                f.getFacturasCab().getNumfac(),
                                f.getId().getLineafac(),
                                f.getArticulos().getReferencia(),
                                f.getCantidad(),
                                f.getPrecio(),
                                f.getDtolinea(),
                                f.getIvalinea()
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La linea de factura no existe.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Inserta el número de linea para buscar.");
            }
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Por favor verifique que ha introducido el número de línea de forma numérica. Además tenga en cuenta que para buscar una línea de factura debe elegir una factura en la ventana de Facturas.");
        }
    }//GEN-LAST:event_btBuscarLinActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed

    }//GEN-LAST:event_jMenu2ActionPerformed

    private void itemInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInicioActionPerformed
        jdHelpInicio.setVisible(true);
        jdHelpInicio.pack();
        jdHelpInicio.setLocationRelativeTo(null);
        jdHelpInicio.setResizable(false);
    }//GEN-LAST:event_itemInicioActionPerformed

    private void itemHelpClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHelpClientesActionPerformed
        jdHelpClientes.setVisible(true);
        jdHelpClientes.pack();
        jdHelpClientes.setLocationRelativeTo(null);
        jdHelpClientes.setResizable(false);
    }//GEN-LAST:event_itemHelpClientesActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jdHelpFacturas.setVisible(true);
        jdHelpFacturas.pack();
        jdHelpFacturas.setLocationRelativeTo(null);
        jdHelpFacturas.setResizable(false);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void itemAyudaArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAyudaArticulosActionPerformed
        jdHelpArticulos.setVisible(true);
        jdHelpArticulos.pack();
        jdHelpArticulos.setLocationRelativeTo(null);
        jdHelpArticulos.setResizable(false);
    }//GEN-LAST:event_itemAyudaArticulosActionPerformed

    private void itemAyudaLineasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAyudaLineasActionPerformed
        jdHelpLineas.setVisible(true);
        jdHelpLineas.pack();
        jdHelpLineas.setLocationRelativeTo(null);
        jdHelpLineas.setResizable(false);
    }//GEN-LAST:event_itemAyudaLineasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                /*if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }*/
            }
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main main = new Main();
                main.setVisible(true);
                main.setLocationRelativeTo(null);
                main.setResizable(false);
                main.setTitle("Ventana de acceso a tienda");
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btActualizarArt;
    private javax.swing.JButton btActualizarCli;
    private javax.swing.JButton btAddArt;
    private javax.swing.JButton btAddCli;
    private javax.swing.JButton btAddFac;
    private javax.swing.JButton btAddLin;
    private javax.swing.JButton btBuscarArt;
    private javax.swing.JButton btBuscarCli;
    private javax.swing.JButton btBuscarFac;
    private javax.swing.JButton btBuscarLin;
    private javax.swing.JButton btCalcular;
    private javax.swing.JButton btCalculoLineas;
    private javax.swing.JButton btDeleteArt;
    private javax.swing.JButton btDeleteCli;
    private javax.swing.JButton btDeleteFac;
    private javax.swing.JButton btDeleteLin;
    private javax.swing.JButton btDetalles;
    private javax.swing.JButton btEstadisticas;
    private javax.swing.JButton btExportar;
    private javax.swing.JButton btFacAsociadas;
    private javax.swing.JButton btImportar;
    private javax.swing.JButton btModArt;
    private javax.swing.JButton btModCli;
    private javax.swing.JButton btModifyFac;
    private javax.swing.JButton btModifyLin;
    private javax.swing.JButton btMostrarEstadisticas;
    private javax.swing.JComboBox<String> cbClienteDos;
    private javax.swing.JComboBox<String> cbClienteUno;
    private javax.swing.JComboBox<String> cbClientesDisponibles;
    private javax.swing.JComboBox<String> cbDescripcion;
    private javax.swing.JComboBox<String> cbRef;
    private javax.swing.JMenuItem itemAyudaArticulos;
    private javax.swing.JMenuItem itemAyudaLineas;
    private javax.swing.JMenuItem itemCli;
    private javax.swing.JMenuItem itemCli1;
    private javax.swing.JMenuItem itemCli2;
    private javax.swing.JMenuItem itemCli3;
    private javax.swing.JMenuItem itemFac;
    private javax.swing.JMenuItem itemFac1;
    private javax.swing.JMenuItem itemFac2;
    private javax.swing.JMenuItem itemFac3;
    private javax.swing.JMenuItem itemHelpClientes;
    private javax.swing.JMenuItem itemInicio;
    private javax.swing.JMenuItem itemInicio1;
    private javax.swing.JMenuItem itemInicio2;
    private javax.swing.JMenuItem itemInicio3;
    private javax.swing.JMenuItem itemLinea;
    private javax.swing.JMenuItem itemStock;
    private javax.swing.JMenuItem itemStock1;
    private javax.swing.JMenuItem itemStock2;
    private javax.swing.JMenuItem itemStock3;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTableArticulo;
    private javax.swing.JTable jTableCab;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableEstadisticas;
    private javax.swing.JTable jTableLinea;
    private javax.swing.JTable jTableTotales;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JDialog jdArticulos;
    private javax.swing.JDialog jdClientes;
    private javax.swing.JDialog jdEstadisticas;
    private javax.swing.JDialog jdFacturas;
    private javax.swing.JDialog jdHelpArticulos;
    private javax.swing.JDialog jdHelpClientes;
    private javax.swing.JDialog jdHelpFacturas;
    private javax.swing.JDialog jdHelpInicio;
    private javax.swing.JDialog jdHelpLineas;
    private javax.swing.JDialog jdLineaFactura;
    private javax.swing.JLabel lbNumFac;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuBar menuBar1;
    private javax.swing.JMenuBar menuBar2;
    private javax.swing.JMenuBar menuBar3;
    private javax.swing.JMenuBar menuBar4;
    private javax.swing.JMenu menuOpciones;
    private javax.swing.JMenu menuOpciones1;
    private javax.swing.JMenu menuOpciones2;
    private javax.swing.JMenu menuOpciones3;
    private javax.swing.JMenu menuOpciones4;
    private javax.swing.JTextField txBuscarArt;
    private javax.swing.JTextField txBuscarCli;
    private javax.swing.JTextField txBuscarFac;
    private javax.swing.JTextField txBuscarLin;
    private javax.swing.JTextField txCantidad;
    private javax.swing.JTextField txDescripcion;
    private javax.swing.JTextField txDniCli;
    private javax.swing.JTextField txDto;
    private javax.swing.JFormattedTextField txFecha;
    private javax.swing.JFormattedTextField txFechaDos;
    private javax.swing.JFormattedTextField txFechaUno;
    private javax.swing.JTextField txIva;
    private javax.swing.JTextField txIvaArt;
    private javax.swing.JTextField txLineaFac;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txNumFacCab;
    private javax.swing.JTextField txPrecio;
    private javax.swing.JTextField txPrecioArticulo;
    private javax.swing.JTextField txRefArticulo;
    private javax.swing.JTextField txStockDisponible;
    // End of variables declaration//GEN-END:variables
}
