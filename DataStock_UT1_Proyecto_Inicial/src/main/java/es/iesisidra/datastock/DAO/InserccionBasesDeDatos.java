package es.iesisidra.datastock.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.iesisidra.datastock.modelo.Categoria;
import es.iesisidra.datastock.modelo.Producto;
import es.iesisidra.datastock.modelo.Proveedor;

public class InserccionBasesDeDatos {
 
    /**
     *   
     */
     private static final String inserccionDeProdcutos="insert into producto (codigo,nombre,precio,stock,categoria_id,proveedor_id) values (?,?,?,?,?,?)";
     private static final String inserccionDeProveedor= "insert into proveedor(id,nombre,email) values(?,?,?)";
     private static final String inserccionDeCategoria = "insert into categoria(id,nombre) values(?,?)";
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
        } catch (SQLException e) {
            System.out.println("Fallo la inserccion en los productos");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void inserccionDeProveedores(Proveedor proveedor){
        try(Connection c = ConexionBasesDatos.abrir();PreparedStatement p = c.prepareStatement(inserccionDeProveedor)) {
            p.setInt(1, proveedor.getId());
            p.setString(2, proveedor.getNombre());
            p.setString(3, proveedor.getEmail());
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo la inserccion en los proveedores");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void inserccionDeCategorias(Categoria categoria){
        try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement p = c.prepareStatement(inserccionDeCategoria)) {
            p.setInt(1, categoria.getId());
            p.setString(2, categoria.getNombre());
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo la inserccion en la categoria");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
