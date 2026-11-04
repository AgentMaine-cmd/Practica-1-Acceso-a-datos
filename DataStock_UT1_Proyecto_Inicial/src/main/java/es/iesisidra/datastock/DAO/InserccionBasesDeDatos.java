package es.iesisidra.datastock.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import es.iesisidra.datastock.modelo.Producto;

public class InserccionBasesDeDatos {
 
    /**
     *   
     */
     private static final String inserccionDeProdcutos="insert into producto (codigo,nombre,precio,stock,categoria_id,proveedor_id) values (?,?,?,?,?,?)";
    public static void introducirProductos(Producto p){
        try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement d = c.prepareStatement(inserccionDeProdcutos) ) {
            d.setString(2, p.getNombre());
            d.setString(1,p.getCodigo());
            d.setBigDecimal(3, p.getPrecio());
            d.setInt(4, p.getStock());
            d.setInt(5, p.getCategoriaId());
            d.setInt(6, p.getProveedorId());
            int filas = d.executeUpdate();
            System.out.print("Filas insertadas " + filas);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
