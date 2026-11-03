package es.iesisidra.datastock.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionBasesDatos {
    private static final String URL= "jdbc:mysql://localhost:3306/Data_Stock_MySQL";
    private static final String USUARIO = "root";
    private static final String CLAVE="Password1234";

    public static Connection abrir()throws SQLException{
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
