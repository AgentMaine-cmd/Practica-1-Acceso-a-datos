package es.iesisidra.datastock.ui;
import java.io.File;

import es.iesisidra.datastock.util.ColoresConsola;
public class ImportCatalogojson {
    private static void comprobadorRutas(File ruta){
          if(ruta.exists()){
            System.out.print("s");
            
        }else{
            System.out.println(ColoresConsola.ROJO + "La ruta que has introducido es inexistente" + ColoresConsola.RESET);
        }
    }
    private static void comprobadorGSON(File ruta){
        
    }
}
