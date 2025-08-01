package Zonafit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        String basedatos = "zona_fit_db";
        String url = "jdbc:mysql://localhost:3306/" + basedatos;
        String user = "root";
        String password = "Kingmaster7+";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return conexion;
    }
}
