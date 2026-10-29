package es.iesisidra.datastock.complementarias;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import es.iesisidra.datastock.modelo.Producto;
import es.iesisidra.datastock.servicio.Inventario;
import es.iesisidra.datastock.util.LectorConsola;
import es.iesisidra.datastock.util.RutasFile;

/**
 * Punto de entrada reservado para RF-FIC-08: RandomAccessFile.
 * <p>Actividad obligatoria e independiente del menú principal.</p>
 */
public final class AccesoAleatorioActividad {
    private AccesoAleatorioActividad() { }
    /** TODO: generar, consultar y modificar registros de 36 bytes. */
    public static void main(String[] args) {
        System.out.println("RF-FIC-08 · RandomAccessFile: actividad pendiente de implementar.");
        Inventario inventario = new Inventario();
        Producto p = new Producto("MON-003",2.22,8);
        inventario.addProducto(p);
        iniciar(inventario);
    }
    private static boolean listaVacia(Inventario inventario){
        boolean vacia = false;
        if(inventario.listaVacia()){
            vacia = true;
        }
        return vacia;
    }

    private static void creacionFicheroBIN(Inventario inventario){
        String aldo= "datos/ejemplos";
        File rutaPadre = new File(RutasFile.rutaOriginalDataStock()+aldo);
        File ruta = new File(rutaPadre,"Stock.bin");
        if(ruta.exists()){
            escribirDatosEnFichero(inventario, ruta);
        }else{
            try {
                ruta.createNewFile();
            } catch (IOException e) {
            }
            System.out.println("algo");
            
        }
    }

    private static void iniciar(Inventario inventario){
        if(listaVacia(inventario)){
            System.out.print("No se accede aqui");
        }else{
            creacionFicheroBIN(inventario);
        }
    }

    private static void escribirDatosEnFichero(Inventario inventario,File ruta){
        List<Producto> copia = new ArrayList<>(inventario.getProductos());
        copia.sort(Comparator.comparing(Producto::getCodigo));
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.setLength(0);

            for (Producto p : copia) {
                String codigo = p.getCodigo();

                if (codigo.length() > 12) {
                    throw new IOException("El código supera los 12 caracteres: " + codigo);
                }

                codigo = String.format("%-12s", codigo);

                raf.writeChars(codigo);
                raf.writeDouble(p.getPrecio2());
                raf.writeInt(p.getStock());
            }
        } catch (IOException e) {
            System.out.println("Error al generar stock.bin: " + e.getMessage());
        }
        consultarRegistro(ruta);
    }

    private static void consultarRegistro(File ruta){
        int posicion = LectorConsola.leerEntero("Introduce el espacio que deseas leer");
         try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {

    
        if (raf.length() % 36 != 0) {
            System.out.println("El fichero tiene un formato inválido.");
            return;
        }

        long numeroRegistros = raf.length() / 36;

        if (posicion < 0 || posicion >= numeroRegistros) {
            System.out.println("Posición fuera de rango.");
            return;
        }

        raf.seek((long) posicion * 36);

        char[] caracteres = new char[12];

        for (int i = 0; i < caracteres.length; i++) {
            caracteres[i] = raf.readChar();
        }

        String codigo = new String(caracteres).trim();
        double precio = raf.readDouble();
        int stock = raf.readInt();

        System.out.println("Código: " + codigo);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);

    } catch (IOException e) {
        System.out.println("Error al consultar el registro: " + e.getMessage());
    }
    modificarStock(ruta, posicion);
    }
    private static void modificarStock(File ruta, int posicion) {
        int nuevoStock = LectorConsola.leerEntero("Introduce el nuevo stock que deseas");
    try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {

        if (raf.length() % 36 != 0) {
            System.out.println("El fichero tiene un formato inválido.");
            return;
        }

        long numeroRegistros = raf.length() / 36;

        if (posicion < 0 || posicion >= numeroRegistros) {
            System.out.println("Posición fuera de rango.");
            return;
        }

        if (nuevoStock < 0) {
            System.out.println("El stock no puede ser negativo.");
            return;
        }

        raf.seek((long) posicion * 36 + 32);
        raf.writeInt(nuevoStock);

        System.out.println("Stock modificado correctamente.");

    } catch (IOException e) {
        System.out.println("Error al modificar el stock: " + e.getMessage());
    }
    mostrarTodosLosRegistros(ruta);
}

    private static void mostrarTodosLosRegistros(File ruta) {
    try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {

        if (raf.length() % 36 != 0) {
            System.out.println("El fichero tiene un formato inválido.");
            return;
        }

        if (raf.length() == 0) {
            System.out.println("El fichero está vacío.");
            return;
        }

        while (raf.getFilePointer() < raf.length()) {
            char[] caracteres = new char[12];

            for (int i = 0; i < caracteres.length; i++) {
                caracteres[i] = raf.readChar();
            }

            String codigo = new String(caracteres).trim();
            double precio = raf.readDouble();
            int stock = raf.readInt();

            System.out.println("Código: " + codigo);
            System.out.println("Precio: " + precio);
            System.out.println("Stock: " + stock);
            System.out.println("----------------------");
        }

    } catch (IOException e) {
        System.out.println("Error al leer los registros: " + e.getMessage());
    }
}

}
