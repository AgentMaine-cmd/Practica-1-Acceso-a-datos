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
    public static void textoMenuSQL(){
        System.out.println("==================\nGESTION MSQL-UT2\n============");
        System.out.println("1. Comprobar conexion  y mostrar  informacion de la BD");
        System.out.println("2. Gestionar  Categorias");
        System.out.println("3. Gestionar Proveedores");
        System.out.println("4. Gestionar Prodcutos (CRUD)");
        System.out.println("5. Consultar y buscar productos");
        System.out.println("6. Registrar entrada o salida de stock");
        System.out.println("7. Consultar historial de movimientos");
        System.out.println("8. Imposrtar productos");
        System.out.println("9. Domstracion de transaccion con commit/trollback");
        System.out.println("0. Volver al menu principal");
    }
}
