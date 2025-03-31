package uam.mx.dal;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConexionDB {
    private String database;
    private String host;
    private String port;
    private String scheme;
    private String user;
    private String pass;

    private Connection connection;

    public ConexionDB(){
        port = "3306";
    }

    private void accesoDB(){
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")){
            if(input == null){
                System.out.println("Archivo db.properties no encontrado");
            }
            properties.load(input);
            database = properties.getProperty("base");
            host = properties.getProperty("host");
            database = properties.getProperty("scheme");
            host = properties.getProperty("usuario");
            port = properties.getProperty("contrasena");
        } catch (IOException ex){
            System.err.println("IOException" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String getAcceso(){
        StringBuilder cdn = new StringBuilder();
        cdn.append(database);
        cdn.append(host);
        cdn.append(":");
        cdn.append(port);
        cdn.append("/");
        cdn.append(scheme);
        return cdn.toString();
    }

    public void conectar(){
        try {
            String acceso = getAcceso();
            this.connection = DriverManager.getConnection(acceso,user,pass);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getconexion(){
        return this.connection;
    }
    public void desconectar() throws SQLException {
        if(this.connection != null){
            this.connection.close();
        }
    }






}
