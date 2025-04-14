<<<<<<< HEAD
package uam.mx.dal;
=======
package uam.mx.Dal;
import uam.mx.Dal.EntityDao;
import uam.mx.Dal.ConexionDB;

>>>>>>> 9d70db475441f98ac9697e7277e97052ec42acd9
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD

import uam.mx.dal.entities.Cafe;
=======
import uam.mx.Dal.entities.Cafe;
>>>>>>> 9d70db475441f98ac9697e7277e97052ec42acd9



public class CafeDao implements EntityDao<Cafe>{

    private Connection connection;

    public CafeDao(){
        ConexionDB db = new ConexionDB();
        db.conectar();
        this.connection = db.getconexion();
    }

    @Override
    public List<Cafe> getAll() {
        List<Cafe> cafes = new ArrayList<>();
        String sql = "SELECT * FROM cafe";

        try (Statement stmt = this.connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cafe cafe = new Cafe();
                cafe.setNombre(rs.getString("nombre"));
                cafe.setCantidad(rs.getFloat("cantidad"));
                cafes.add(cafe);
            }

            System.out.println("Cafes obtenidos: " + cafes.size());
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
            e.printStackTrace();
        }

        return cafes;
    }

    public List<Cafe> getByExample(Cafe cafe) {
        List<Cafe> foundClientes = new ArrayList<>();
        String sql = "SELECT * FROM almacencafe WHERE Nombre=? OR Locacion=? OR Cantidad=?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, cafe.getNombre());
            stmt.setString(2, cafe.getLocacion());
            stmt.setFloat(3, cafe.getCantidad());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cafe found = new Cafe();
                    found.setId(rs.getInt("id"));
                    found.setNombre(rs.getString("Nombre"));
                    found.setLocacion(rs.getString("Locacion"));
                    found.setCantidad(rs.getFloat("Cantidad"));
                    foundClientes.add(found);
                }
            }

            System.out.println("Clientes encontrados: " + foundClientes.size());
        } catch (SQLException e) {
            System.err.println("Error en getByExample: " + e.getMessage());
            e.printStackTrace();
        }

        return foundClientes;
    }

    @Override
    public Cafe save(Cafe cafe) {
        String sql = "INSERT INTO almacencafe (Nombre, Locacion, Cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cafe.getNombre());
            stmt.setString(2, cafe.getLocacion());
            stmt.setFloat(3, cafe.getCantidad());
            stmt.setInt(4, cafe.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        cafe.setId(rs.getInt(1));
                        System.out.println("El cafe se ha guardado: " + cafe);
                        return cafe;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en save: " + ex.getMessage());
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Cafe update(Cafe entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean delete(Cafe entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
