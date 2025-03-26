package uam.mx.dal;

import java.awt.image.DataBuffer;
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
        accesoDB();
    }

    pivate void accesoDB(){
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(DataBuffer.properties)){
            if(input == null){
                System.out.println("Archivo db.properties no encontrado");
            }
            properties.load(input);
            database = properties.getProperty("database");
            host = properties.getProperty("host");
            port = properties.getProperty("port");
            database = properties.getProperty("scheme");
            host = properties.getProperty("user");
            port = properties.getProperty("port");
        }
    }

}
