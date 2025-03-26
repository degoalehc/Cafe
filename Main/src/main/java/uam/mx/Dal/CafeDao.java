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

    @Override
    public List<Cafe> getAll() {
        List<Cafe> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Statement stmt = this.connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setUsername(rs.getString("username"));
                cliente.setName(rs.getString("name"));
                cliente.setLastname(rs.getString("lastname"));
                cliente.setAge(rs.getString("age"));
                clientes.add(cliente);
            }

            System.out.println("Clientes obtenidos: " + clientes.size());
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
            e.printStackTrace();
        }

        return clientes;
    }

    public List<Cliente> getByExample(Cliente cliente) {
        List<Cliente> foundClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE username=? OR name=? OR lastname=? OR age=?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getUsername());
            stmt.setString(2, cliente.getName());
            stmt.setString(3, cliente.getLastname());
            stmt.setString(4, cliente.getAge());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente found = new Cliente();
                    found.setId(rs.getInt("id"));
                    found.setUsername(rs.getString("username"));
                    found.setName(rs.getString("name"));
                    found.setLastname(rs.getString("lastname"));
                    found.setAge(rs.getString("age"));
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
    public Cliente save(Cliente cliente) {
        String sql = "INSERT INTO cliente (username, name, lastname, age) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getUsername());
            stmt.setString(2, cliente.getName());
            stmt.setString(3, cliente.getLastname());
            stmt.setString(4, cliente.getAge());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        cliente.setId(rs.getInt(1));
                        System.out.println("Cliente guardado: " + cliente);
                        return cliente;
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
    public Cliente update(Cliente cliente) {
        String sql = "UPDATE cliente SET username=?, name=?, lastname=?, age=? WHERE id=?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getUsername());
            stmt.setString(2, cliente.getName());
            stmt.setString(3, cliente.getLastname());
            stmt.setString(4, cliente.getAge());
            stmt.setInt(5, cliente.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente actualizado: " + cliente);
                return cliente;
            }
        } catch (SQLException ex) {
            System.err.println("Error en update: " + ex.getMessage());
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM cliente WHERE id=?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente eliminado con ID: " + id);
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Error en delete: " + ex.getMessage());
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Cliente cliente) {
        return delete(cliente.getId());
    }
}
