package es.iesisidra.datastock.ui;
import java.io.File;
import java.io.IOException;

import es.iesisidra.datastock.util.ColoresConsola;
import es.iesisidra.datastock.util.LectorConsola;
import es.iesisidra.datastock.util.RutasFile;

public class ExploradorRutas {
   
    public static void leerRutas(){
        String entradaRuta,opcionSalida;
        
        File ruta;
        boolean salida = false; 
        while (!salida) { 
            System.out.println("==== EXPLORAR RUTA ====");
        
            entradaRuta= LectorConsola.leerTexto("Introduce ruta: ");
        
            ruta = new File(RutasFile.rutaOriginalDataStock() + entradaRuta);
                
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
                e.printStackTrace();
            }
            
        }else{
            System.out.println(ColoresConsola.ROJO + "La ruta que has introducido es inexistente" + ColoresConsola.RESET);
        }
        
    }
    private static void mostrarInformacionRuta(File ruta)throws IOException
    {
        int numerodeArchivo = 0;
        int numeroFicheros = 0;
        int numeroDirectorios=0;
        if(ruta.isFile())
        {
            System.out.print("FICHERO "+"Nombre: "+ruta.getName()+"\nRuta absoluta: "+ruta.getCanonicalPath()+"\nTamaño en bytes: "+ ruta.length() + " bytes");
            numeroFicheros++;
        }else
        {
           int contenido = comprobadorDirectorioVacio(ruta);
            if(contenido==1)
            {
                File[]listadoArchivos = ruta.listFiles();
                numerodeArchivo = listadoArchivos.length;
                for(int i =0; i < listadoArchivos.length;i++)
                {
                    if(listadoArchivos[i].isFile())
                    {
                        numeroFicheros++;
                        System.out.println("FICHERO "+"Nombre: "+listadoArchivos[i].getName()+"\nRuta absoluta: "+listadoArchivos[i].getCanonicalPath()+"\nTamaño en bytes: "+ listadoArchivos[i].length() + " bytes");
                    }else if(listadoArchivos[i].isDirectory())
                    {
                        numeroDirectorios++;
                        System.out.println("DIRECTORIO " + "Nombre : "+listadoArchivos[i].getName());
                    }
                }
                System.out.println("Numero de ficheros: " + numeroFicheros + " Numero de directorios: " + numeroDirectorios + " Numero archivos: " + numerodeArchivo);
            }else if(contenido == 2)
            {
                    System.out.println("esta vacia");
            }else if(contenido==0){
                System.out.println("No se ha podido acceder al fichero");
            }
            
        }
             
    }
       
    

    private static int comprobadorDirectorioVacio(File ruta){
        int lleno = 0;
        File[]ficherosProvisional = ruta.listFiles();
          if(ficherosProvisional == null){
                lleno = 0;
            }else if(ficherosProvisional.length>0){
                lleno = 1;
            }else if(ficherosProvisional.length==0){
                lleno = 2;
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
