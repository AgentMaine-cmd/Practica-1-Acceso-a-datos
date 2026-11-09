package es.iesisidra.datastock.ui;

import es.iesisidra.datastock.util.LectorConsola;
import es.iesisidra.datastock.util.TextoMenu;

public class MenuProveedor {
    public static void menuProveedor(){
          boolean salida = false;
        int opciones;
        while (!salida) {
            TextoMenu.textoMenuProveedor();
            opciones= LectorConsola.leerOpcion("Introduce opcion: ", 0, 4);
            switch (opciones) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 0:
                    salida = true;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
