package es.iesisidra.datastock.servicio;

import java.util.ArrayList;
import java.util.List;

import es.iesisidra.datastock.modelo.Producto;

/**
 * Estado común del inventario durante la ejecución de UT1.
 * <p>Esta clase solo proporciona la colección compartida. No implementa
 * importación, validación, búsqueda, exportación ni persistencia: esos son
 * ejercicios que deberá desarrollar el alumnado.</p>
 */
public class Inventario {
    private final List<Producto> productos = new ArrayList<>();
    
    /**
     * Obtiene la colección que comparten las opciones del menú.
     * <p>ATENCIÓN: la lista es modificable. El alumnado deberá aplicar las
     * reglas del enunciado antes de añadir o sustituir elementos.</p>
     */
    public Inventario(){}

    public void addProducto(Producto p){productos.add(p);}

    public boolean listaVacia(){
        boolean vacia = true;
        if(productos.isEmpty()){
            vacia = true;
        }else{
            vacia = false;
        }
        return vacia;
    }
    public void sustituirInventario(List<Producto> productos){
        this.productos.clear();
        this.productos.addAll(productos);
    }
    public Producto productoEnConcreto(){
        Producto p = new Producto();
        return p;
    }
    public void reemplazarProductos(List<Producto> nuevosProductos) {
    productos.clear();
    productos.addAll(nuevosProductos);
    }
    public List<Producto> getProductos() { return productos; }
}
