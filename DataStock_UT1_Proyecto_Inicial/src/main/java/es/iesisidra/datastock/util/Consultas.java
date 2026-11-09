package es.iesisidra.datastock.util;

public class Consultas {
    public static final String consultaCategoria = "select * from categoria";
    public static final String consultaProveedor = "select * from proveedor";
    public static final String consultaProdcutoPorCodigo = "select codigo,nombre from producto where codigo like ?";
    public static final String productoRepetido = "select count(*) from producto where codigo = ?";
    public static final String categoriaRepetida = "select count(*) from categoria where id = ?";
    public static final String ProveedorRepetido = "select count(*) from proveedor where id  = ?";
    public static final String emeailRepetido = "select count(*) from proveedor where email = ?";
    public static final String comprobacionProductosConCategoria = "select COUNT(*)from producto pr left join categoria c on pr.codigo = c.id where pr.categoria_id = ?";
    public static final String comprobacionProductosConProveedor="select count(*) from producto pr inner join proveedor p on pr.proveedor_id = p.id where pr.proveedor_id = ?";
    public static String comprobacionExistenciaDeCategoria(int id){
        String comprobacion = "select count(*) from categoria where id = ?";
        return comprobacion;
    }
    
}
