package es.iesisidra.datastock.ui;
import java.io.File;
import java.io.IOException;

import es.iesisidra.datastock.util.ColoresConsola;
import es.iesisidra.datastock.util.LectorConsola;

public class ExploradorRutas {
    private static final String rutaInicial = "./DataStock_UT1_Proyecto_Inicial/";
    public static void leerRutas(){
        String entradaRuta,opcionSalida;
        
        File ruta;
        boolean salida = false; 
        while (!salida) { 
            System.out.println("==== EXPLORAR RUTA ====");
        
            entradaRuta= LectorConsola.leerTexto("Introduce ruta: ");
        
            ruta = new File(rutaInicial + entradaRuta);
                
            comprobadorRuta(ruta);
            
            

            opcionSalida = LectorConsola.leerTexto("Pulse 0 si desea salir: ");
            if(opcionSalida.equals("0")){
                salida = true;
            }
            
        }
        
    }

    private static void comprobadorRuta(File ruta){
        
        if(ruta.exists()){
            try {
                mostrarInformacionRuta(ruta);
            } catch (IOException e) {
            }
            
        }else if(ruta.length()==0){
            System.out.println(ColoresConsola.ROJO + "La ruta que has introducido esta vacia" + ColoresConsola.RESET);
        }else{
            System.out.println(ColoresConsola.ROJO + "La ruta que has introducido es inexistente" + ColoresConsola.RESET);
        }
        
    }
    private static void mostrarInformacionRuta(File ruta)throws IOException
    {
        if(ruta.isFile())
        {
            System.out.print("FICHERO "+"Nombre: "+ruta.getName()+"\nRuta absoluta: "+ruta.getCanonicalPath()+"\nTamaño en bytes: "+ ruta.length() + " bytes");
        }else
        {
            boolean contenido = comprobadorDirectorioVacio(ruta);
            if(contenido==true)
            {
                File[]listadoArchivos = ruta.listFiles();
                for(int i =0; i < listadoArchivos.length;i++)
                {
                    if(listadoArchivos[i].isFile())
                    {
                        System.out.println("FICHERO "+"Nombre: "+listadoArchivos[i].getName()+"\nRuta absoluta: "+listadoArchivos[i].getCanonicalPath()+"\nTamaño en bytes: "+ listadoArchivos[i].length() + " bytes");
                    }else if(listadoArchivos[i].isDirectory())
                    {
                        System.out.println("DIRECTORIO " + "Nombre : "+listadoArchivos[i].getName());
                    }
                }
            }else
            {
                    System.out.println("esta vacia");
            }
        }
             
    }
       
    

    private static boolean comprobadorDirectorioVacio(File ruta){
        boolean lleno = false;
        
        if(ruta.listFiles() == null){
            lleno = false;
        }else{
            lleno = true;
        }
        return lleno;
        
    }
}

    
    

/*
    File pathOriginal = new File("./DataStock_UT1_Proyecto_Inicial");
        String [] listado = pathOriginal.list();
        for(int i = 0; i<listado.length;i++){
            System.out.println(listado[i]);
        } 
*/
