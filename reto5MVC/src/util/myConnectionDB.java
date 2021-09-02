package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
Durante la ejecucion de este metodo se conecta a la base de dtos mediante un objeto Connection
que toma el url de la base de datos, el usuario y la contraseña, todo almacenado en util.properties
 */
public class myConnectionDB {

    public static Connection getConnection() {
        
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(properties.dbURL, properties.userName, properties.password);

            if (conn != null) {
                System.out.println("Conectado");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return conn;
    }
}
