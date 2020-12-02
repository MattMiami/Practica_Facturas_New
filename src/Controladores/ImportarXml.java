/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Antonio Mateos
 */
public class ImportarXml {

    public ImportarXml(JTable cab, JTable lin, JTable cli) throws ParserConfigurationException, SAXException, IOException {

        //Creamos el selector de archivos, filtramos la busqueda con archivos xml
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("xml files (*.xml)", "xml");
        jfc.setFileFilter(filtro);

        int seleccion = jfc.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            DocumentBuilder dbf = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //Definimos la ruta
            String RUTA = jfc.getSelectedFile().getAbsolutePath();
            //Creamos el objeto Document y su ruta
            Document doc = dbf.parse(new File(RUTA));

            //Element raiz = doc.getDocumentElement();
            //Realizamos una busqueda de la etiqueta "cabecera"
            NodeList nl = doc.getElementsByTagName("cabecera");

            String[] st = new String[3];

            /*Recorremos esa etiqueta obteniendo los datos de los campos que queramos
                en mi caso obtendré los datos de numero factura, fecha y dni
             */
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {

                    st[0] = n.getChildNodes().item(1).getTextContent();
                    st[1] = n.getChildNodes().item(7).getTextContent();
                    st[2] = n.getChildNodes().item(3).getTextContent();

                }

            }
            DefaultTableModel model = (DefaultTableModel) cab.getModel();
            model.setRowCount(0);
            model.addRow(st);

            NodeList nl2 = doc.getElementsByTagName("lineasFactura");
            String[] lineas = new String[7];
            for (int i = 0; i < nl2.getLength(); i++) {
                Node n = nl2.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {

                    lineas[0] = n.getChildNodes().item(1).getTextContent();
                    lineas[1] = n.getChildNodes().item(3).getTextContent();
                    lineas[2] = n.getChildNodes().item(5).getTextContent();
                    lineas[3] = n.getChildNodes().item(7).getTextContent();
                    lineas[4] = n.getChildNodes().item(9).getTextContent();
                    lineas[5] = n.getChildNodes().item(11).getTextContent();
                    lineas[6] = n.getChildNodes().item(13).getTextContent();
                }
            }
            DefaultTableModel model2 = (DefaultTableModel) lin.getModel();
            model2.addRow(lineas);

            NodeList nl3 = doc.getElementsByTagName("cabecera");
            String[] cliente = new String[3];
            
            for (int i = 0; i < nl3.getLength(); i++) {
                Node n = nl3.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    cliente[0] = n.getChildNodes().item(3).getTextContent();
                    cliente[1] = n.getChildNodes().item(5).getTextContent();
                }

            }
            
            DefaultTableModel model3 = (DefaultTableModel) cli.getModel();
            model3.setRowCount(0);
            model3.addRow(cliente);

        }
    }

}
