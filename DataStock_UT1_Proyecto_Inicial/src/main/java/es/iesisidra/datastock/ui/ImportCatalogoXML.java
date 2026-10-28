package es.iesisidra.datastock.ui;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.iesisidra.datastock.modelo.Producto;
import es.iesisidra.datastock.servicio.Inventario;
import es.iesisidra.datastock.util.LectorConsola;
import es.iesisidra.datastock.util.RutasFile;
import es.iesisidra.datastock.util.TextoMenu;

public class ImportCatalogoXML {
    public static void importarXML(Inventario inventario){
        String rutaFinal = LectorConsola.leerTexto("Introduzca la ruta del archivo xml que desee leer");
        File ruta = new File(RutasFile.rutaOriginalDataStock()+rutaFinal);
        comprobadorRuta(ruta, inventario);
        
        
    }
    private static void comprobadorRuta(File ruta,Inventario inventario){
        if(ruta.exists()){
                DocumentBuilderFactory dbm = DocumentBuilderFactory.newInstance();
                
                try {
                    dbm.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                    dbm.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                    dbm.setFeature("http://xml.org/sax/features/external-general-entities", false);
                    dbm.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

                    dbm.setXIncludeAware(false);
                    dbm.setExpandEntityReferences(false);

                    dbm.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
                    dbm.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
                    DocumentBuilder constructor = dbm.newDocumentBuilder();
                    Document documento = constructor.parse(ruta);
                    documento.getDocumentElement().normalize();

                    if(!comprobarRaizXML(documento)){
                    TextoMenu.mensajeErrorRutaErronea();
                    }else{
                        importarArchivosXML(inventario, documento);
                    }
                }catch(IOException e){}catch(SAXException e){} catch (ParserConfigurationException e) {}
        }else{
            TextoMenu.mensajeErrorRutaErronea();
        }
    }
    private static boolean comprobarRaizXML(Document db){
        boolean salida = false;
        Element raiz = db.getDocumentElement();
        String comprobador= raiz.getTagName();
        
       if(comprobador.equals("catalogo")){
        salida = true;
       }
        return salida;
    }
    private static void importarArchivosXML(Inventario inventario, Document documento){
        NodeList productos = documento.getElementsByTagName("producto");
        int productosRechazados =0;
        for(int i = 0; i<productos.getLength(); i++){
            Element producto = (Element) productos.item(i);
            String codigo= producto.getElementsByTagName("codigo").item(0).getTextContent();
            String nombre= producto.getElementsByTagName("nombre").item(0).getTextContent();
            BigDecimal precio= new BigDecimal(producto.getElementsByTagName("precio").item(0).getTextContent().trim()) ;
            int stock = Integer.parseInt(producto.getElementsByTagName("stock").item(0).getTextContent());
            int categoriaId =Integer.parseInt(producto.getElementsByTagName("categoriaId").item(0).getTextContent()) ;
            int proveedorId =Integer.parseInt(producto.getElementsByTagName("proveedorId").item(0).getTextContent()) ;
            Producto p = comprobadorProducto(codigo, nombre, precio, stock, categoriaId, proveedorId, inventario);
            if(p== null){
                productosRechazados++;
            }else{
                comprobadorDuplicados(p, inventario,productosRechazados);
            }
            
        }
    }
    private static Producto comprobadorProducto(String codigo, String nombre, BigDecimal precio, int stock,int categoriaId, int proveedorId,Inventario inventario){
        Producto p = null;
        if (codigo.matches("[A-Z0-9-]{1,12}")&& nombre.matches(".{3,100}")&& precio.compareTo(BigDecimal.ZERO) > 0&& stock >= 0&& categoriaId > 0&& proveedorId > 0) {
            p = new Producto(codigo,nombre,precio,stock,categoriaId,proveedorId);

            } else {
                System.out.println("Producto Rechazado");
}
        return p;
    }

    private static void comprobadorDuplicados(Producto p,Inventario inventario,int productosRechazados){
        boolean duplicado = false;
        for(Producto pr : inventario.getProductos()){
            if(pr.getCodigo().trim().equalsIgnoreCase(p.getCodigo().trim())){
                duplicado = true;
            }
        }
        if(!duplicado){
            inventario.addProducto(p);
        }else{
            productosRechazados++;
        }
        System.out.print("Productos rechazados " + productosRechazados);
    }
}
