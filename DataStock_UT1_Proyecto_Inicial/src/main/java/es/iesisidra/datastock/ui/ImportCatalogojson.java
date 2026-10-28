package es.iesisidra.datastock.ui;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import es.iesisidra.datastock.modelo.Producto;
import es.iesisidra.datastock.servicio.Inventario;
import es.iesisidra.datastock.util.ColoresConsola;
import es.iesisidra.datastock.util.LectorConsola;
public class ImportCatalogojson {
    private static final String rutaInicial = "./DataStock_UT1_Proyecto_Inicial/";

    public static void importarJson(Inventario inventario){
        String rutafinal = LectorConsola.leerTexto("Introduzca la ruta del archivo json que desee importar: ");
        File ruta = new File(rutaInicial+rutafinal);
        comprobadorGSON(ruta, inventario);
    }

    private static void comprobadorGSON(File ruta, Inventario inventario){

        if(ruta.exists()&&ruta.getName().toLowerCase().endsWith(".json")){
            JsonAObjeto(ruta, inventario);
        }else{
            System.out.println(ColoresConsola.ROJO+"La ruta que has introducido no es valida"+ColoresConsola.RESET);
        }
    }

    private static void JsonAObjeto(File ruta,Inventario inventario){
        Gson gson = new Gson();
        Inventario inventarioProvisional;

        Type productosType = new TypeToken<Inventario>(){}.getType();

        try(FileReader lector = new FileReader(ruta)) {
            int numeroDeProdcutosNoImportados = 0;
            inventarioProvisional = gson.fromJson(lector, productosType);
            for(Producto p : inventarioProvisional.getProductos()){
                if(comprobadorProdcutos(p)!=null){
                    numeroDeProdcutosNoImportados = comprobadorProdcutosRepetidos(inventario, numeroDeProdcutosNoImportados, p);
                }else{
                    numeroDeProdcutosNoImportados++;
                }
                
                
            }
            System.out.println("no se han añadido " + numeroDeProdcutosNoImportados + " productos a la lista de inventario final");
        }catch(NullPointerException e){
            System.out.println("El archivo json esta vacio");
        }catch(JsonSyntaxException e){
            System.out.println("No se ha podido exportar el archivo por un error en la formacion del json");
        } catch (EOFException e) {
            System.out.println("No se ha podido exportar el archivo por un error en la formacion del json");
        }catch(IOException e){
             System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }



    private static int comprobadorProdcutosRepetidos(Inventario inventario,int numeroProdcutosNoImportados,Producto p){
        int n = numeroProdcutosNoImportados;
        boolean repetido = false;
        for(Producto pr : inventario.getProductos()){
            if(p.getCodigo().equalsIgnoreCase(pr.getCodigo().trim())){
               n++;
               repetido = true;
            }else{
                
            }
        }

        if(!repetido){
            inventario.addProducto(p);
        }
        return n;
    }



   private static Producto comprobadorProdcutos(Producto p){
    Producto pr = null;
         if (p.getCodigo().matches("[A-Z0-9-]{1,12}")&& p.getNombre().matches(".{3,100}")&& p.getPrecio().compareTo(BigDecimal.ZERO) > 0&& p.getStock() >= 0&& p.getCategoriaId() > 0&& p.getProveedorId() > 0) {
            pr = p;
            } else {
                System.out.println("Producto Rechazado");
            }
    return pr;
   }


}
