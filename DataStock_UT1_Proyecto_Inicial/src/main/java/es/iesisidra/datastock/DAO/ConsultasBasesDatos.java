package es.iesisidra.datastock.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsultasBasesDatos {
     private static final String consulta = "select * form producto";
    public static void consultarBaseDatos(){
       try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement p = c.prepareStatement(consulta);ResultSet rs = p.executeQuery()) {
          
       } catch (Exception e) {
       }
    }
}
