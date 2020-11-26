/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controladores.Ctrl_Articulos;
import Controladores.Ctrl_Clientes;
import Controladores.Ctrl_Entrada;
import Controladores.Ctrl_Facturas;
import Modelos.Articulos;
import Modelos.Clientes;
import Modelos.FacturasCab;
import Modelos.FacturasLin;
import Modelos.FacturasLinId;
import Modelos.FacturasTot;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Antonio Mateos
 */
public class Main extends javax.swing.JFrame {

    Ctrl_Clientes cc;
    Ctrl_Articulos ca;
    Ctrl_Facturas cf;
    private Ctrl_Entrada controlE = new Ctrl_Entrada();
    private List<Clientes> cl = new ArrayList<Clientes>();
    private List<Articulos> al = new ArrayList<Articulos>();
    private List<FacturasCab> fl = new ArrayList<FacturasCab>();

    DefaultTableModel modelArt;
    String[] columnasArt = {"Referencia", "Descripción", "Precio", "IVA", "Cantidad Stock"};

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
        for (FacturasCab f : fl) {
            modelFac.addRow(new Object[]{f.getNumfac(), f.getFechaFormat(), f.getClientes().getDnicif()});
        }
        jTableCab.setModel(modelFac);
    }
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

    public Main() {
        initComponents();
        cc = new Ctrl_Clientes();
        ca = new Ctrl_Articulos();
        cf = new Ctrl_Facturas();
        jdArticulos.pack();
        jdArticulos.setLocationRelativeTo(null);
        jdArticulos.setResizable(false);
        jdClientes.pack();
        jdClientes.setLocationRelativeTo(null);
        jdClientes.setResizable(false);
        jdFacturas.pack();
        jdFacturas.setLocationRelativeTo(null);
        jdFacturas.setResizable(false);
        jdLineaFactura.pack();
        jdLineaFactura.setLocationRelativeTo(null);
        jdLineaFactura.setResizable(false);

        showTableClientes();
        showTableArticulos();
        showTableFacturas();
        showTableFacturasLin();
        showTableFacturasTot();
        cc.fillComboBox(cbClientesDisponibles);
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
        jLabel27 = new javax.swing.JLabel();
        btFacAsociadas = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        txBucarCli = new javax.swing.JTextField();
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
        jdArticulos = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableArticulo = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
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
        txBucarArt = new javax.swing.JTextField();
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
        jdFacturas = new javax.swing.JDialog();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCab = new javax.swing.JTable();
        txNumFacCab = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
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
        jLabel3 = new javax.swing.JLabel();
        btCalcular = new javax.swing.JButton();
        menuBar3 = new javax.swing.JMenuBar();
        menuOpciones3 = new javax.swing.JMenu();
        itemCli3 = new javax.swing.JMenuItem();
        itemStock3 = new javax.swing.JMenuItem();
        itemInicio3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jdLineaFactura = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableLinea = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
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
        btCalculoLineas = new javax.swing.JButton();
        lbRef = new javax.swing.JLabel();
        btActualizarLineas = new javax.swing.JButton();
        menuBar4 = new javax.swing.JMenuBar();
        menuOpciones4 = new javax.swing.JMenu();
        itemCli1 = new javax.swing.JMenuItem();
        itemFac3 = new javax.swing.JMenuItem();
        itemStock2 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        menuOpciones = new javax.swing.JMenu();
        itemCli = new javax.swing.JMenuItem();
        itemFac = new javax.swing.JMenuItem();
        itemStock = new javax.swing.JMenuItem();
        itemLinea = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabel1.setText("DNI-CIF");

        jLabel2.setText("Nombre Cliente");

        jLabel27.setText("Clientes");

        btFacAsociadas.setText("Consultar Facturas Cliente");
        btFacAsociadas.setEnabled(false);
        btFacAsociadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFacAsociadasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel27)
                        .addGap(0, 99, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txDniCli)
                            .addComponent(txNombre)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btFacAsociadas)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txDniCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btFacAsociadas)
                .addContainerGap(73, Short.MAX_VALUE))
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

        jMenu3.setText("Help");
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
                        .addComponent(txBucarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txBucarCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscarCli)
                    .addComponent(btAddCli)
                    .addComponent(btDeleteCli)
                    .addComponent(btModCli)
                    .addComponent(btActualizarCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jdClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel5.setText("Artículos");

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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
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
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel5))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel28))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel6))))
                        .addGap(0, 120, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btBuscarArt.setText("Buscar artículo");

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

        jMenu4.setText("Help");
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
                        .addComponent(txBucarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txBucarArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jLabel9.setText("Cabeceras");

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
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(btAddFac)
                                        .addGap(1, 1, 1)
                                        .addComponent(btDeleteFac)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btModifyFac))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txNumFacCab, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbClientesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btDetalles))))
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txBuscarFac, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btBuscarFac)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jLabel9)
                        .addGap(1, 1, 1)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Facturas totales");

        btCalcular.setText("Calcular total");
        btCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCalcularActionPerformed(evt);
            }
        });

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

        jMenu5.setText("Help");
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
                        .addGap(0, 95, Short.MAX_VALUE)
                        .addGroup(jdFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btCalcular))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jdFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jdFacturasLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btCalcular)))
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

        jLabel14.setText("Linea Facturas");

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

        jLabel11.setText("Descripcion");

        btCalculoLineas.setText("Calcular Factura");
        btCalculoLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCalculoLineasActionPerformed(evt);
            }
        });

        btActualizarLineas.setText("Actualizar");
        btActualizarLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizarLineasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btAddLin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btDeleteLin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btModifyLin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btActualizarLineas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel20))
                                        .addGap(34, 34, 34))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(79, 79, 79)))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txIva, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txDto, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel17))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(lbNumFac, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btCalculoLineas))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(cbRef, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lbRef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(cbDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txLineaFac, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(1, 1, 1)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btAddLin)
                                    .addComponent(btDeleteLin)
                                    .addComponent(btModifyLin)
                                    .addComponent(btActualizarLineas))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNumFac, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)))
                            .addComponent(btCalculoLineas))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txLineaFac, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRef, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17)))
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
                .addContainerGap(9, Short.MAX_VALUE))
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

        jMenu6.setText("Help");
        menuBar4.add(jMenu6);

        jdLineaFactura.setJMenuBar(menuBar4);

        javax.swing.GroupLayout jdLineaFacturaLayout = new javax.swing.GroupLayout(jdLineaFactura.getContentPane());
        jdLineaFactura.getContentPane().setLayout(jdLineaFacturaLayout);
        jdLineaFacturaLayout.setHorizontalGroup(
            jdLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1067, Short.MAX_VALUE)
            .addGroup(jdLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jdLineaFacturaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jdLineaFacturaLayout.setVerticalGroup(
            jdLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
            .addGroup(jdLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jdLineaFacturaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 563, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
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
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    }//GEN-LAST:event_btBuscarCliActionPerformed

    private void btAddArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddArtActionPerformed
        /*
        Añadir articulos
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
        Eliminar artículos
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
        Modificar articulos
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
                //orderar
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
            Long numFac = (Long) modelFac.getValueAt(jTableCab.getSelectedRow(), 0);
            FacturasCab fc = cf.getNumFacCab(numFac);
            lbNumFac.setText(modelFac.getValueAt(jTableCab.getSelectedRow(), 0).toString());
            List listaLineaFactura = new ArrayList(fc.getFacturasLins());
            if (listaLineaFactura != null) {
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
            Long numFac = (Long) modelFac.getValueAt(jTableCab.getSelectedRow(), 0);
            FacturasCab fc = cf.getNumFacCab(numFac);
            lbNumFac.setText(modelFac.getValueAt(jTableCab.getSelectedRow(), 0).toString());
            List listaLineaFactura = new ArrayList(fc.getFacturasLins());
            if (listaLineaFactura != null) {
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
        Añadimos una nueva factura
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
        Eliminamos factura cabecera
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
        Modificar factura
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
            cf.addFacturasLin(fl);
            //modelLin.setRowCount(0);
            modelLin.addRow(new Object[]{
                fl.getId().getNumfac(),
                fl.getId().getLineafac(),
                fl.getArticulos().getReferencia(),
                fl.getCantidad(),
                fl.getPrecio(),
                fl.getDtolinea(),
                fl.getIvalinea()
            });

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "No puedes dejar campos vacios, además asegúrese de que todos los datos introducidos son numéricos.");

        }


    }//GEN-LAST:event_btAddLinActionPerformed

    private void btDeleteLinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteLinActionPerformed
        //FacturasLinId id, Articulos articulos, FacturasCab facturasCab, BigDecimal cantidad, BigDecimal precio, BigDecimal dtolinea, BigDecimal ivalinea    
        try {
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

            cf.deleteFacturasLin(fl);
            modelLin.removeRow(i);
        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "Asegúrese de elegir una linea de factura en la tabla para eliminar.");
        }


    }//GEN-LAST:event_btDeleteLinActionPerformed

    private void btModifyLinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModifyLinActionPerformed

        try {

            FacturasLin fl = new FacturasLin();

            //Definimos el ID de la fila
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

            //modificamos el stock y actualizamos tabla articulos
            a.setStock(a.getStock().subtract(can));
            ca.modifyArticulos(a);
            showTableArticulos();
            //Modificamos la linea
            cf.modifyFacturaLin(fl);

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Asegúrese de seleccionar un registro en la tabla para ser modificado. "
                    + "Tenga en cuenta que los todos los campos a rellenar son numéricos y recuerde no dejar campos vacíos .");
        }
    }//GEN-LAST:event_btModifyLinActionPerformed

    private void cbDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDescripcionActionPerformed
        /*String des = (String) cbDescripcion.getSelectedItem();
        ca.getInfoArticulo(des, lbRef, txCantidad, txPrecio, txIva);*/

    }//GEN-LAST:event_cbDescripcionActionPerformed

    private void btCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalcularActionPerformed
        /*
        Facturas total
         */
        try {
            modelTot.setRowCount(0);
            Long numFac = (Long) modelFac.getValueAt(jTableCab.getSelectedRow(), 0);
            FacturasCab fc = cf.getNumFacCab(numFac);
            modelTot.addRow(new Object[]{
                fc.getNumfac(), fc.getBaseImp(), fc.getDto(), fc.getIvaTotal(), fc.getTotal()
            });
        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una factura para calcular su total");
        }


    }//GEN-LAST:event_btCalcularActionPerformed

    private void btCalculoLineasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalculoLineasActionPerformed
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
        lbRef.setText(modelLin.getValueAt(i, 2).toString());
        txCantidad.setText(modelLin.getValueAt(i, 3).toString());
        txPrecio.setText(modelLin.getValueAt(i, 4).toString());
        txDto.setText(modelLin.getValueAt(i, 5).toString());
        txIva.setText(modelLin.getValueAt(i, 6).toString());
    }//GEN-LAST:event_jTableLineaMouseClicked

    private void btActualizarLineasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizarLineasActionPerformed
        try {
            modelLin.setRowCount(0);
            FacturasCab fc = cf.getNumFacCab(Long.parseLong(lbNumFac.getText()));
            List listaLineaFactura = new ArrayList(fc.getFacturasLins());
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

    }//GEN-LAST:event_btActualizarLineasActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
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

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btActualizarArt;
    private javax.swing.JButton btActualizarCli;
    private javax.swing.JButton btActualizarLineas;
    private javax.swing.JButton btAddArt;
    private javax.swing.JButton btAddCli;
    private javax.swing.JButton btAddFac;
    private javax.swing.JButton btAddLin;
    private javax.swing.JButton btBuscarArt;
    private javax.swing.JButton btBuscarCli;
    private javax.swing.JButton btBuscarFac;
    private javax.swing.JButton btCalcular;
    private javax.swing.JButton btCalculoLineas;
    private javax.swing.JButton btDeleteArt;
    private javax.swing.JButton btDeleteCli;
    private javax.swing.JButton btDeleteFac;
    private javax.swing.JButton btDeleteLin;
    private javax.swing.JButton btDetalles;
    private javax.swing.JButton btFacAsociadas;
    private javax.swing.JButton btModArt;
    private javax.swing.JButton btModCli;
    private javax.swing.JButton btModifyFac;
    private javax.swing.JButton btModifyLin;
    private javax.swing.JComboBox<String> cbClientesDisponibles;
    private javax.swing.JComboBox<String> cbDescripcion;
    private javax.swing.JComboBox<String> cbRef;
    private javax.swing.JMenuItem itemCli;
    private javax.swing.JMenuItem itemCli1;
    private javax.swing.JMenuItem itemCli2;
    private javax.swing.JMenuItem itemCli3;
    private javax.swing.JMenuItem itemFac;
    private javax.swing.JMenuItem itemFac1;
    private javax.swing.JMenuItem itemFac2;
    private javax.swing.JMenuItem itemFac3;
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
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTableArticulo;
    private javax.swing.JTable jTableCab;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableLinea;
    private javax.swing.JTable jTableTotales;
    private javax.swing.JDialog jdArticulos;
    private javax.swing.JDialog jdClientes;
    private javax.swing.JDialog jdFacturas;
    private javax.swing.JDialog jdLineaFactura;
    private javax.swing.JLabel lbNumFac;
    private javax.swing.JLabel lbRef;
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
    private javax.swing.JTextField txBucarArt;
    private javax.swing.JTextField txBucarCli;
    private javax.swing.JTextField txBuscarFac;
    private javax.swing.JTextField txCantidad;
    private javax.swing.JTextField txDescripcion;
    private javax.swing.JTextField txDniCli;
    private javax.swing.JTextField txDto;
    private javax.swing.JFormattedTextField txFecha;
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
