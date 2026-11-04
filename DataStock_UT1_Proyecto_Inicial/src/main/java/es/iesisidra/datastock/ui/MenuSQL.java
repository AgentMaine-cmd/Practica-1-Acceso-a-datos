package es.iesisidra.datastock.ui;

import java.sql.Connection;
import java.sql.SQLException;

import es.iesisidra.datastock.DAO.ConexionBasesDatos;
import es.iesisidra.datastock.servicio.Inventario;
import es.iesisidra.datastock.util.LectorConsola;
import es.iesisidra.datastock.util.TextoMenu;

public class MenuSQL {
    public static void menuSQL(Inventario inventario){
        boolean salida = false;
        int opcion;
        try(Connection conexion = ConexionBasesDatos.abrir()) {
             while (!salida) { 
          TextoMenu.textoMenuSQL();  
          opcion= LectorConsola.leerOpcion("Introduce opcion", 0, 9);
          switch (opcion) {
                case 1:
                  
                  break;
                case 2:
                  
                  break;
                case 3:
                  
                  break;
                case 4:
                  
                  break;
                case 5:
                  
                  break;
                case 6:
                  
                  break;
                case 7:
                  
                  break;
                case 8:
                  
                  break;
                case 9:
                  
                  break;
                case 0:
                    salida = true;
                  break;
              default:
                  throw new AssertionError();
          }  
        }
        } catch (SQLException e) {
            System.err.println("Error de conexion " + e.getMessage());
        }
        
      
    }
}
