package es.iesisidra.datastock.complementarias;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.iesisidra.datastock.util.LectorConsola;
import es.iesisidra.datastock.util.RutasFile;
import es.iesisidra.datastock.util.TextoMenu;

/**
 * Punto de entrada reservado para RF-FIC-07: consultas XPath.
 * <p>Actividad obligatoria e independiente del menú principal.</p>
 */
public final class XPathActividad {
    private XPathActividad() { }
    /** TODO: implementar las cuatro consultas XPath descritas en el enunciado. */
    public static void main(String[] args) {
        menu();
       
    }
    private static void menu(){
        TextoMenu.menuXpath();
        int opcion = LectorConsola.leerOpcion("Introduce opcion: ", 1, 4);
        Document d = analisisXML();
      
        if(d!=null&&comprobarRaizXML(d)){
            switch (opcion) {
            
            case 1:
                buscarPorCodigo(d);
                break;
            case 2:
                buscarPorCategoria(d);
                break;
            case 3:
                precioMinimo(d);
                break;
            case 4:
                contar(d);
                break;
            default:
                throw new AssertionError();
        }
        }else{
            System.out.println("El archivo no se puede leer");
        }
        
    }
    private static Document analisisXML(){
        String rutaFichero = LectorConsola.leerTexto("Introduce el fichero que deseas leer: ");
        File ruta = new File(RutasFile.rutaOriginalDataStock()+rutaFichero);
        Document documento = null;
        if(RutasFile.comprobadorRuta(ruta)){
              try {
            DocumentBuilderFactory dbm = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = dbm.newDocumentBuilder();
            documento = constructor.parse(ruta);
        }catch(IOException e){
            System.out.println("Error al momento de leer archivo");
        }catch(SAXException e){
             System.out.println("Error el archivo xml esta mal formado");
        } catch (ParserConfigurationException e) {
            System.out.println("No se ha podido configurar el analizador xml");
        }
        }else{
            TextoMenu.mensajeErrorRutaErronea();
        }
        
      
        
        return documento;
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
    private static void buscarPorCodigo(Document d){
        String id  = LectorConsola.leerTexto("Introduce el codigo que deseas buscar :");
        String consulta = "//producto[codigo='"+id+"']";
        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            NodeList producto = (NodeList) xpath.evaluate(consulta, d,XPathConstants.NODESET);
            if(producto.getLength()>0){
                 mostrarInfomacion(producto);
            }else{
                System.out.println("No se ha encontrado ningun prodcuto con ese codigo");
            }
           
        } catch (XPathExpressionException e) {
            System.out.println("comporobar que es lo que hace que salte este error");
        }
        
    }
    private static void mostrarInfomacion(NodeList producto){
        for(int i=0; i<producto.getLength();i++){
           
             try {
                     Element e = (Element) producto.item(i);
                    String codigo= e.getElementsByTagName("codigo").item(0).getTextContent();
                    String nombre= e.getElementsByTagName("nombre").item(0).getTextContent();
                    BigDecimal precio= new BigDecimal(e.getElementsByTagName("precio").item(0).getTextContent().trim()) ;
                    int stock = Integer.parseInt(e.getElementsByTagName("stock").item(0).getTextContent());
                    int categoriaId =Integer.parseInt(e.getElementsByTagName("categoriaId").item(0).getTextContent()) ;
                    int proveedorId =Integer.parseInt(e.getElementsByTagName("proveedorId").item(0).getTextContent()) ;
                    System.out.println("\n========== DATOS DEL PRODUCTO ==========");
                    System.out.printf("Código:          %s%n", codigo);
                    System.out.printf("Nombre:          %s%n", nombre);
                    System.out.printf("Precio:          %s €%n", precio);
                    System.out.printf("Stock:           %d unidades%n", stock);
                    System.out.printf("ID de categoría: %d%n", categoriaId);
                    System.out.printf("ID de proveedor: %d%n", proveedorId);
                    System.out.println("========================================\n");
            } catch (NumberFormatException e) {
                System.out.println("error al momento de leer el xml, producto mal formado");
            } catch(NullPointerException e){
                System.out.println("error al momento de leer el xml faltan datos al producto");
            }
        }
        
    }
    private static void buscarPorCategoria(Document d){
          String id  = LectorConsola.leerTexto("Introduce la categoria que deseas buscar :");
        String consulta = "//producto[categoriaId='"+id+"']";
        XPath xpath = XPathFactory.newInstance().newXPath();
         try {
            NodeList producto = (NodeList) xpath.evaluate(consulta, d,XPathConstants.NODESET);
            if(producto.getLength()>0){
                 mostrarInfomacion(producto);
            }else{
                System.out.println("No se ha encontrado ningun prodcuto con ese codigo de categoria");
            }
           
        } catch (XPathExpressionException e) {
            System.out.println("comporobar que es lo que hace que salte este error");
        }
        
    }

    private static void precioMinimo(Document d){
            String precio  = LectorConsola.leerTexto("Introduce el precio minimo :");
        String consulta = "//producto[precio>='"+precio+"']";
        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            NodeList producto = (NodeList) xpath.evaluate(consulta, d,XPathConstants.NODESET);
            if(producto.getLength()>0){
                 mostrarInfomacion(producto);
            }else{
                System.out.println("No se ha encontrado ningun prodcuto con un minimo de ese precio");
            }
           
        } catch (XPathExpressionException e) {
            System.out.println("comporobar que es lo que hace que salte este error");
        }
    }

    private static void contar(Document d){
        String consulta = "//producto";
        XPath xpath = XPathFactory.newInstance().newXPath();
        int n =0;
        try {
            NodeList producto = (NodeList) xpath.evaluate(consulta, d,XPathConstants.NODESET);
          
            System.out.println("se han encontrado "+ producto.getLength() + " elementos");
           
        } catch (XPathExpressionException e) {
            System.out.println("comporobar que es lo que hace que salte este error");
        }
    }
        
    }

