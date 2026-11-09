package es.iesisidra.datastock.comprobadores;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

import es.iesisidra.datastock.DAO.ConexionBasesDatos;
import es.iesisidra.datastock.modelo.Categoria;
import es.iesisidra.datastock.modelo.Producto;
import es.iesisidra.datastock.modelo.Proveedor;
import es.iesisidra.datastock.util.Consultas;
import es.iesisidra.datastock.util.LectorConsola;

public class Comprobador {
    private static String regexCorreoElectronico = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static String regexNombreProvedor = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s-]{1,120}$";
    private static String regexNombreCategoria = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s-]{1,60}$";

    public static Proveedor comprobadorProveedor(Proveedor proveedor){
        boolean salida = false;
        int id = LectorConsola.leerEntero("Introduce codigo proveedor: ");
        String nombre = LectorConsola.leerTexto("Introduce el nombre del proveedor: ");
        String email = LectorConsola.leerTexto("Introduce el email del proveedor: ");
        while (!salida) { 
            try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement pr = c.prepareStatement(Consultas.comprobacionExistenciaDeCategoria(id))) {
                System.out.println("El id no puede ser un numero negarivo");
               id= LectorConsola.leerEntero("Introduce el codigo proveedor: ");
            } catch (Exception e) {
            }
            if(id<0){
               
            }else if(nombre.isEmpty()|| !nombre.matches(regexNombreProvedor)){
                System.out.println("El nombre no puede estar vacio ni pude superar x caracteres");
                nombre = LectorConsola.leerTexto("Introduce el nombre del proveedor: ");
                
            }else if(!email.isEmpty() && !email.matches(regexCorreoElectronico)){
               System.out.println("Debes introducir un email valido");
               email = LectorConsola.leerTexto("Introduce el email: ");
            }else{
                 salida = true;
            }
        }
        nombre = nombre.trim();
        
        return new Proveedor(id, nombre, email);
    }
    public static Categoria comprovadorCategoria(Categoria categoria){
        boolean salida = false;
        int codigo = LectorConsola.leerEntero("Introduce el codigo de la categoria: ");
        String nombre = LectorConsola.leerTexto("Introduce el nombre de la categoria: ");
        while (!salida) { 
            if(codigo<0){
                System.out.println("El id no puede ser un numero negarivo");
                codigo= LectorConsola.leerEntero("Introduce el codigo proveedor: ");
            }else if(nombre.isBlank()|| !nombre.matches(regexNombreCategoria)){
                System.out.println("El nombre no puede estar vacio ni pude superar x caracteres");
                nombre = LectorConsola.leerTexto("Introduce el nombre de la categoria: ");
            }else{
                salida = true;
            }
            nombre = nombre.trim();
            
        }
        return new Categoria(codigo,nombre);
    }
    //codigo.matches("[A-Z0-9-]{1,12}")&& nombre.matches(".{3,100}")&& precio.compareTo(BigDecimal.ZERO) > 0&& stock >= 0&& categoriaId > 0&& proveedorId > 0
    public static Producto comprobadorProdcuto(Producto producto){
        boolean salida = false;
        String codigo = LectorConsola.leerTexto("Introduce el codigo del producto que deseas introducir: ");
        String nombre = LectorConsola.leerTexto("Introduce el nombre del texto que deseas introducir: ");
        BigDecimal precio = new BigDecimal(LectorConsola.leerDouble("Introduce el precio del producto: "));
        int stock = LectorConsola.leerEntero("Introduce el stock del producto: ");
        int CategoriaId = LectorConsola.leerEntero("Introduce la categoria del producto: ");
        int proveedorId = LectorConsola.leerEntero("Introduce la el id del proveedor: ");
        while (!salida) { 
            if(!codigo.matches("[a-zA-Z0-9-]{1,12}")){
                System.out.println("codigo invalido");
                codigo = LectorConsola.leerTexto("Introduce el codigo del producto que deseas introducir: ");
            }else if(!nombre.matches(".{3,100}")){
                 System.out.println("nombre invalido");
                 nombre = LectorConsola.leerTexto("Introduce el nombre del texto que deseas introducir: ");
            }else if(precio.compareTo(BigDecimal.ZERO)<0){
                 System.out.println("precio invalido");
                 precio = new BigDecimal(LectorConsola.leerDouble("Introduce el precio del producto: "));
            }else if(stock<0){
                 System.out.println("Stock invalido");
                 stock = LectorConsola.leerEntero("Introduce el stock del producto: ");
            }else if(proveedorId<0){
                 System.out.println("proveedor invalido");
                 proveedorId = LectorConsola.leerEntero("Introduce la el id del proveedor: ");
            }else if(CategoriaId<0){
                 System.out.println("categoria invalida");
                CategoriaId = LectorConsola.leerEntero("Introduce la categoria del producto: ");
            }else{
                salida = true;
            }
        }
        codigo = codigo.toUpperCase().trim();
        nombre = nombre.trim();
        return new Producto(codigo,nombre,precio,stock,CategoriaId,proveedorId);
    }
}
