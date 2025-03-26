package uam.mx.Dal;

import java.lang.classfile.ClassFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CafeDao  {

    private Connection connection;

    public CafeDao(){
        DbConnection db = new DbConnection();
        db.connect();
        this.connection = db.getConnection();
    }

    
}
