package es.iesisidra.datastock.ui;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
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

        if(ruta.exists()&&ruta.getName().endsWith(".json")){
            System.out.println("Esti funciona");
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
            int i = 0;
            inventarioProvisional = gson.fromJson(lector, productosType);
            for(Producto p : inventarioProvisional.getProductos()){
                if(inventario.getProductos().equals(p)){
                    i++;
                }else{
                    inventario.addProducto(p);
                    System.out.println(inventario.getProductos());
                }
            }
            System.out.println("no se han añadido " + i + " productos a la lista de inventario final");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
   


}
