package es.iesisidra.datastock.util;

import java.io.File;

public class RutasFile {
    public static String rutaOriginalDataStock(){
        String ruta = "./DataStock_UT1_Proyecto_Inicial/";
        return ruta;
    }
    public static boolean comprobadorRuta(File ruta){
        boolean existe = true;
        if(ruta.exists()){
            
        }else{
            existe = false;
        }
        return existe;
    }
}
