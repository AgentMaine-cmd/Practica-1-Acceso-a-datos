package es.iesisidra.datastock.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.iesisidra.datastock.util.Consultas;
import es.iesisidra.datastock.util.LectorConsola;

public class ConsultasBasesDatos {




   public static void consultarCategoria(){
       try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement p = c.prepareStatement(Consultas.consultaCategoria);) {

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
      try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement p = c.prepareStatement(Consultas.consultaProveedor)) {

          try(ResultSet rs = p.executeQuery()) {
            while (rs.next()) {
                System.out.printf("%-10s%-10s%-10s\n",rs.getInt("id"),rs.getString("nombre"),rs.getString("email"));
                
            }
              
          } catch (Exception e) {
            System.out.println("ERROR" + e.getLocalizedMessage());
          }
      } catch (Exception e) {
         System.out.println("ERROR" + e.getLocalizedMessage());
      }
    }

    public static void consultarProdcutoPorCodigo(){

      String codigo = LectorConsola.leerTexto("Itroduzca el codigo del objeto que desea buscar: ");

      try(Connection c = ConexionBasesDatos.abrir(); PreparedStatement p = c.prepareStatement(Consultas.consultaProdcutoPorCodigo)) {

         codigo = codigo.toUpperCase();
         p.setString(1, "%"+codigo+"%");//debemos poner las condiciones aqui para que asi executequeri los pueda leer

         try(ResultSet rs = p.executeQuery()) {
             
               while (rs.next()) {
                  
                   System.out.printf("%-10s%-10s\n",rs.getString("codigo"),rs.getString("nombre"));
                  }     
               }
             
          catch (Exception e) {
            System.out.println("ERROR 2: " + e.getLocalizedMessage());
         }
          
      } catch (Exception e) {
         System.out.println("ERROR 1: " + e.getLocalizedMessage());
      }
   }


}

/**
 * if(rs.getString("codigo").contains(codigo)){el resulttest ya contiene los resultado de la queri, por eso no podemos filtrar una vez llegados aqui
                  }
 */
