package es.iesisidra.datastock.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConexionBasesDatos {
    
    //debo cambiar esta version cuando ponga este proyecto publico en github
    public static Connection abrir()throws SQLException{
            Properties datos = new Properties();
        try (InputStream entrada = ConexionBasesDatos.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (entrada == null) {
                throw new SQLException("No se encuentra db.properties");
            }
            datos.load(entrada);
        } catch (IOException e) {
            throw new SQLException("Error leyendo db.properties", e);
        }
        String url = datos.getProperty("jdbc.url");
        String usuario = datos.getProperty("jdbc.user");
        String clave = datos.getProperty("jdbc.password");
        return DriverManager.getConnection(url, usuario, clave);
    }
}
