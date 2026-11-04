package es.iesisidra.datastock.comprobadores;

import es.iesisidra.datastock.modelo.Categoria;
import es.iesisidra.datastock.modelo.Proveedor;
import es.iesisidra.datastock.util.LectorConsola;

public class Comprobador {
    public static Proveedor comprobadorProveedor(){
        boolean salida = false;
        int id = LectorConsola.leerEntero("Introduce codigo proveedor: ");
        String nombre = LectorConsola.leerTexto("Introduce el nombre del proveedor: ");
        String email = LectorConsola.leerTexto("Introduce el email del proveedor: ");
        while (!salida) { 
            if(id<0){
               System.out.println("El id no puede ser un numero negarivo");
               id= LectorConsola.leerEntero("Introduce el codigo proveedor: ");
            }else if(nombre.isEmpty()){
                System.out.println("El nombre no puede estar vacio ni pude superar x caracteres");
                nombre = LectorConsola.leerTexto("Introduce el nombre del proveedor: ");
                
            }else if(!email.isEmpty()&& email.matches("")){
               System.out.println("Debes introducir un email valido");
            }else{
                 salida = true;
            }
        }
        System.out.println(id);
        System.out.println(nombre);
        System.out.println(email);
        return new Proveedor(id, nombre, email);
    }
    public static Categoria comprovadorCategoria(){
        
    }
}
