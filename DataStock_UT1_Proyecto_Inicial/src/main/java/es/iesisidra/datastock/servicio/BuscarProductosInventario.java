package es.iesisidra.datastock.servicio;

import es.iesisidra.datastock.modelo.Producto;
import es.iesisidra.datastock.util.ColoresConsola;
import es.iesisidra.datastock.util.LectorConsola;
import es.iesisidra.datastock.util.TextoMenu;

public class BuscarProductosInventario {
    public static void subMenuConsultaProductos(Inventario inventario){
        boolean salida = false;
        int opcionMenu = 0;
        while (!salida) { 
            TextoMenu.textoSubMenuConsulta();
            opcionMenu = LectorConsola.leerOpcion("Selecciona opcion", 0, 3);
            switch (opcionMenu) {
                case 1:
                    mostrarPodcutos(inventario);
                    break;
                case 2:
                    buscarProductoPorSuID(inventario);
                    break;
                case 3:
                    buscarProductoPorSuNombre(inventario);
                    break;
                case 0:
                    salida = true;
                    break;
                default:
                    throw new AssertionError();
            }
        }
        
    }


    private static void mostrarPodcutos(Inventario inventario){
        if(inventario.listaVacia()==false){
              for(Producto p : inventario.getProductos()){
            System.out.println(p.toString()); 
            }
            
        }else{
           System.out.println(ColoresConsola.AMARILLO+ "la lista esta vacia" + ColoresConsola.RESET);
        }
        
    }

    private static void buscarProductoPorSuID(Inventario inventario){
        String porductoID = LectorConsola.leerTexto("Introduce el id del producto que deseas buscar").toUpperCase().trim();
        boolean encontrado = false;
        for(Producto p : inventario.getProductos()){
            if(p.getCodigo().equals(porductoID)){
                System.out.println(p.toString());
                encontrado = true;
            }
        }
        if(!encontrado){
             System.out.println(ColoresConsola.ROJO+"No existe ningun producto con ese id"+ColoresConsola.RESET);
        }
        
        
    }
     private static void buscarProductoPorSuNombre(Inventario inventario){
        String porductoNombre = LectorConsola.leerTexto("Introduce el nombre del producto que deseas buscar").toUpperCase().trim();
         boolean encontrado = false;
        for(Producto p : inventario.getProductos()){
            if(p.getNombre().toUpperCase().contains(porductoNombre)){
                System.out.println(p.toString());
                encontrado = true;
            }
        }
        if(!encontrado){
             System.out.println(ColoresConsola.ROJO+"No existe ningun producto con ese nombre"+ColoresConsola.RESET);
        }
        
        
    }
}
