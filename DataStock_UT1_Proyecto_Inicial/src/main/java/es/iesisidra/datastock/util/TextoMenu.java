package es.iesisidra.datastock.util;

public class TextoMenu {
    public static void textoSubMenuConsulta(){
        System.out.println("CONSULTA DE PRODUCTOS");
        System.out.println("1, Listar los Productos");
        System.out.println("2. Buscar Productos");
        System.out.println("3. Buscar Productos por nombre");
        System.out.println("0. Salir");

    }
    public static void textoSubMenuSerializar(){
        System.out.println("CONSULTA DE PRODUCTOS");
        System.out.println("1, Guardar Datos");
        System.out.println("2. Recuperar Datos");
        System.out.println("0. Salir");

    }
    public static void textoInventarioVacio(){
        System.out.println(ColoresConsola.AMARILLO+ "la lista esta vacia" + ColoresConsola.RESET);
    }
    public static void mensajeErrorRutaErronea(){
        System.out.println(ColoresConsola.ROJO + "La ruta que has introducido es inexistente" + ColoresConsola.RESET);
    }
    public static void menuXpath(){
        System.out.println("=====Consultas XPATH=====");
        System.out.println("1. Por Codigo | 2. Por Categoria | 3. Precio Superior | 4. Contar");
    }
}
