/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Antonio Mateos
 */
public class ImportarXml {
    
    public ImportarXml(JTable cab) throws ParserConfigurationException, SAXException, IOException {

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
            
            Element raiz = doc.getDocumentElement();
            NodeList nl = doc.getElementsByTagName("cabecera");
            System.out.println(nl.getLength());
            
            String[] st = new String[3];
            
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(n);
                    st[0] = n.getChildNodes().item(1).getTextContent();
                    st[1] = n.getChildNodes().item(7).getTextContent();
                    st[2] = n.getChildNodes().item(3).getTextContent();
                    
                }
                
            }
            DefaultTableModel model = (DefaultTableModel) cab.getModel();
            model.setRowCount(0);
            model.addRow(st);
            
        }
    }
    
}
