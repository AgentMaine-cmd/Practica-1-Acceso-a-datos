package es.iesisidra.datastock.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsultasBasesDatos {
   private static final String consultaCategoria = "select * from categoria";
   private static final String consultaProveedor = "select * from proveedor";
   public static void consultarCategoria(){
       try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement p = c.prepareStatement(consultaCategoria);) {

          try(ResultSet rs = p.executeQuery()) {

              while (rs.next()) {
                  System.out.printf("%-10s%-10s\n", rs.getInt("id"),rs.getString("nombre"));
              }
          } catch (Exception e) {
            System.out.println("error" + e.getLocalizedMessage());
          }

       } catch (Exception e) {
         System.out.println("error" + e.getLocalizedMessage());
       }
    }

    public static void consultarProveedor(){
      try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement p = c.prepareStatement(consultaProveedor)) {

          try(ResultSet rs = p.executeQuery()) {
            while (rs.next()) {
                System.out.printf("%-10s%-10s%-10s\n",rs.getInt("id"),rs.getString("nombre"),rs.getString("email"));
                
            }
              
          } catch (Exception e) {
          }
      } catch (Exception e) {
      }
    }
}
