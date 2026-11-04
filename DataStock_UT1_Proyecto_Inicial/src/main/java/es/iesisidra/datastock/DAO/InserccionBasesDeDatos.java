package es.iesisidra.datastock.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.iesisidra.datastock.comprobadores.Comprobador;
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
    public static void introducirProductos(Producto producto){
        try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement p = c.prepareStatement(inserccionDeProdcutos) ) {
            p.setString(2, producto.getNombre());
            p.setString(1,producto.getCodigo());
            p.setBigDecimal(3, producto.getPrecio());
            p.setInt(4, producto.getStock());
            p.setInt(5, producto.getCategoriaId());
            p.setInt(6, producto.getProveedorId());
            int filas = p.executeUpdate();
            System.out.print("Filas insertadas " + filas);
        } catch (SQLException e) {
            System.out.println("Fallo la inserccion en los productos" + e.getLocalizedMessage());
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void inserccionDeProveedores(){
        Proveedor proveedor = Comprobador.comprobadorProveedor();
        try(Connection c = ConexionBasesDatos.abrir();PreparedStatement p = c.prepareStatement(inserccionDeProveedor)) {
            p.setInt(1, proveedor.getId());
            p.setString(2, proveedor.getNombre());
            p.setString(3, proveedor.getEmail());
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo la inserccion en los proveedores"+e.getLocalizedMessage());
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void inserccionDeCategorias(){
        Categoria categoria= Comprobador.comprovadorCategoria();
        try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement p = c.prepareStatement(inserccionDeCategoria)) {
            p.setInt(1, categoria.getId());
            p.setString(2, categoria.getNombre());
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo la inserccion en la categoria" +e.getLocalizedMessage());
        }catch(Exception e){
            System.out.println();
        }
    }
}
