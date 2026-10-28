package es.iesisidra.datastock.ui;



import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

import es.iesisidra.datastock.modelo.Producto;
import es.iesisidra.datastock.servicio.Inventario;
import es.iesisidra.datastock.util.ColoresConsola;
import es.iesisidra.datastock.util.LectorConsola;
import es.iesisidra.datastock.util.RutasFile;
import es.iesisidra.datastock.util.TextoMenu;

public class GuardaryRecuperarInventario {
    private String codigo;
    private String nombre;
    private BigDecimal precio;
    private int stock;
    private int categoriaId;
    private int proveedorId;
    public static void subMenuGuardaryRecuperarDatos(Inventario inventario){
        boolean salida = false;
        int opcion =0;
            while (!salida) { 
                TextoMenu.textoSubMenuSerializar();
                opcion = LectorConsola.leerOpcion("Introduce opcion menu: ", 0, 2);
                switch (opcion) {
                    case 0:
                        salida = true;
                        break;
                    case 1:
                         if(inventario.listaVacia()){
                            TextoMenu.textoInventarioVacio();
                        }else{
                            serializarObjeto(inventario);
                        }
                        break;
                    case 2:
                        deserialziarObjetos(inventario);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }


    private static void serializarObjeto(Inventario inventario){
        String rutaDestino= LectorConsola.leerTexto("Introduce  la carpeta donde deseas guardar los datos: ");
        File rutaCarpeta = new File(RutasFile.rutaOriginalDataStock()+rutaDestino);
       
        if(!rutaCarpeta.exists()){
            rutaCarpeta.mkdir();
        }
        String ficheroDestino = LectorConsola.leerTexto("Introduce donde deseas serializar el inventario: ");
         File fichero = new File(rutaCarpeta,ficheroDestino);
         if(!fichero.exists()){
            try {
                fichero.createNewFile();
            } catch (IOException e) {
            }
            
         }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            for(Producto p : inventario.getProductos()){
                oos.writeObject(p);
            }
        }catch(EOFException e){
            System.out.println("Error al recuperar el inventario: " + e.getMessage());
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }


    private static void deserialziarObjetos(Inventario inventario){
        String rutaDestino= LectorConsola.leerTexto("Introduce la ruta del archivo que deseas deserializar: ");
        File ruta = new File(RutasFile.rutaOriginalDataStock()+rutaDestino);
       
        if(ruta.exists()){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
                while (true) {
                    Producto p = (Producto) ois.readObject();
                    inventario.addProducto(p);
                    
                }

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }else{
            System.out.println(ColoresConsola.ROJO + "La ruta que has introducido es inexistente" + ColoresConsola.RESET);
        }
    }
}
