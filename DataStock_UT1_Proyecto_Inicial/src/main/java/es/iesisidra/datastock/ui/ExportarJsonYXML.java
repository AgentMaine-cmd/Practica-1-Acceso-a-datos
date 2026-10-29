package es.iesisidra.datastock.ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.iesisidra.datastock.modelo.Producto;
import es.iesisidra.datastock.servicio.Inventario;
import es.iesisidra.datastock.util.LectorConsola;
import es.iesisidra.datastock.util.RutasFile;

public class ExportarJsonYXML {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void importarInventario(Inventario inventario){
        if(inventarioVacio(inventario)==0){
            System.out.println("El inventario esta vacio no se puede importar");
        }  else{
            File ruta = comporbadorRuta();
            if(ruta == null){
                System.out.println("la ruta que has introducido no es valida");
            }else{
                comprobacionFichero(ruta,inventario);
            }
            
        }
    }
    private static int inventarioVacio( Inventario inventario){
        int validacion = 0;
    if(inventario.listaVacia()){
        System.out.println("No se puede ejecutar el inventario esta vacio");
        
    }else{
        validacion = 1;
    }
    return validacion;
    }

    private static File comporbadorRuta(){
        String rutaFinal = LectorConsola.leerTexto("Introduce la ruta que deseas usar");
        File ruta = new File(RutasFile.rutaOriginalDataStock()+rutaFinal);
        if(!ruta.exists()){
            ruta = null;
        }else if(ruta.exists()&&ruta.isDirectory()){
        
        }else{
            ruta=null;
        }
        return ruta;
    }
    private static void comprobacionFichero(File ruta,Inventario inventario){
        String fichero = LectorConsola.leerTexto("Introduce el fichero que deseas usar");
        File rutaFichero = new File(ruta,fichero);
        if(rutaFichero.exists()&&fichero.toLowerCase().endsWith(".json")&&rutaFichero.isFile()){
            creacionGson(rutaFichero, inventario);
        }else if(rutaFichero.exists()&&fichero.toLowerCase().endsWith(".xml")&rutaFichero.isFile()){
            creacionXML(rutaFichero, inventario);
        }else{
            System.out.println("No son archivos compatibles para la operacion");
        }
    }

    private static void creacionGson(File ruta, Inventario inventario){
        try(FileWriter writer = new FileWriter(ruta)){
            gson.toJson(inventario,writer);
        }catch(IOException e ){
            e.printStackTrace();
        }
    }


private static void creacionXML(File ruta, Inventario inventario) {
    try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document documento = builder.newDocument();

        Element catalogo = documento.createElement("catalogo");
        documento.appendChild(catalogo);

        for (Producto p : inventario.getProductos()) {
            Element producto = documento.createElement("producto");
            catalogo.appendChild(producto);

            Element codigo = documento.createElement("codigo");
            codigo.setTextContent(p.getCodigo());
            producto.appendChild(codigo);

            Element precio = documento.createElement("precio");
            precio.setTextContent(String.valueOf(p.getPrecio()));
            producto.appendChild(precio);

            Element stock = documento.createElement("stock");
            stock.setTextContent(String.valueOf(p.getStock()));
            producto.appendChild(stock);
        }

       
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(
            "{http://xml.apache.org/xslt}indent-amount", "4"
        );

        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(ruta);

        transformer.transform(source, result);

    } catch (ParserConfigurationException e) {
        System.out.println("Error al crear el documento XML: " + e.getMessage());
    } catch (TransformerException e) {
        System.out.println("Error al guardar el documento XML: " + e.getMessage());
    }
}
}
