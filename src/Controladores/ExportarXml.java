/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.FacturasCab;
import Modelos.FacturasLin;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author Antonio Mateos
 */
public class ExportarXml {

    private Document doc;

    public ExportarXml() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        doc = db.newDocument();

    }

    public void generarDocumento(FacturasCab f) {
        Set facturasLineas = new HashSet(0);
        facturasLineas = f.getFacturasLins();

        Element factura = doc.createElement("Factura");
        doc.appendChild(factura);

        /*
        Cabecera de facturas
         */
        Element facCab = doc.createElement("cabecera");
        factura.appendChild(facCab);

        Element numFac = doc.createElement("numFactura");
        facCab.appendChild(numFac);
        numFac.appendChild(doc.createTextNode(String.valueOf(f.getNumfac())));

        Element dni = doc.createElement("dniCif");
        facCab.appendChild(dni);
        dni.appendChild(doc.createTextNode(f.getClientes().getDnicif()));

        Element nombre = doc.createElement("nombre");
        facCab.appendChild(nombre);
        nombre.appendChild(doc.createTextNode(f.getClientes().getNombrecli()));

        Element fecha = doc.createElement("fecha");
        facCab.appendChild(fecha);
        fecha.appendChild(doc.createTextNode(f.getFechaFormat()));

        /*
        Nodos de Lineas de factura
         */
        for (Iterator it = facturasLineas.iterator(); it.hasNext();) {
            FacturasLin fl = (FacturasLin) it.next();
            Element lineasFac = doc.createElement("lineasFactura");
            factura.appendChild(lineasFac);

            Element nFac = doc.createElement("numFactura");
            lineasFac.appendChild(nFac);
            nFac.appendChild(doc.createTextNode(String.valueOf(f.getNumfac())));

            Element nLinea = doc.createElement("numeroLinea");
            lineasFac.appendChild(nLinea);
            nLinea.appendChild(doc.createTextNode(String.valueOf(fl.getId().getLineafac())));

            Element referencia = doc.createElement("referencia");
            lineasFac.appendChild(referencia);
            referencia.appendChild(doc.createTextNode(String.valueOf(fl.getArticulos().getReferencia())));

            Element cantidad = doc.createElement("cantidad");
            lineasFac.appendChild(cantidad);
            cantidad.appendChild(doc.createTextNode(fl.getCantidad().toString()));

            Element precio = doc.createElement("precio");
            lineasFac.appendChild(precio);
            precio.appendChild(doc.createTextNode(fl.getPrecio().toString()));

            Element dto = doc.createElement("dto");
            lineasFac.appendChild(dto);
            dto.appendChild(doc.createTextNode(fl.getDtolinea().toString()));

            Element iva = doc.createElement("iva");
            lineasFac.appendChild(iva);
            iva.appendChild(doc.createTextNode(fl.getIvalinea().toString()));
        }
        /*
        Nodos de Totales
         */
        Element totales = doc.createElement("totalesFacturas");
        factura.appendChild(totales);

        Element nf = doc.createElement("numFactura");
        totales.appendChild(nf);
        nf.appendChild(doc.createTextNode(String.valueOf(f.getNumfac())));

        Element baseImp = doc.createElement("baseImp");
        totales.appendChild(baseImp);
        baseImp.appendChild(doc.createTextNode(f.getBaseImp().toString()));

        Element impDto = doc.createElement("impDot");
        totales.appendChild(impDto);
        impDto.appendChild(doc.createTextNode(f.getDto().toString()));

        Element impIva = doc.createElement("impIva");
        totales.appendChild(impIva);
        impIva.appendChild(doc.createTextNode(f.getIvaTotal().toString()));

        Element totalFac = doc.createElement("totalFac");
        totales.appendChild(totalFac);
        totalFac.appendChild(doc.createTextNode(f.getTotal().toString()));

    }

    public File crearXml() throws TransformerException {
        boolean confirmar;
        File file = null;
        try {
            Transformer trFac = TransformerFactory.newInstance().newTransformer();

            //Propiedades del fichero de salida
            trFac.setOutputProperty(OutputKeys.METHOD, "xml");
            trFac.setOutputProperty(OutputKeys.INDENT, "yes");

            //Creamos el componente JFileChooser para elegir destino del archivo
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int seleccion = jfc.showSaveDialog(null);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                //Definimos la ruta de destino y añadimos extension
                String RUTA = jfc.getSelectedFile().getAbsolutePath();
                if (!RUTA.endsWith(".xml")) {
                    RUTA += ".xml";
                //Definimos la entrada y la salida
                Source src = new DOMSource(doc);
                Result result = new StreamResult(new File(RUTA));
                //Transformamos
                trFac.transform(src, result);
                confirmar = true;
                JOptionPane.showMessageDialog(null, "Exportado correctamente");
                }
            }
        } catch (TransformerConfigurationException | TransformerFactoryConfigurationError ex) {
            confirmar = false;
            JOptionPane.showMessageDialog(null, "Error al exportar el registo al fichero xml", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return file;
    }

}
