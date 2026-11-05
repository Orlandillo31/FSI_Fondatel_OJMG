import java.sql.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Alimento {
    String idalimento, nombre, descripcion, precio, foto, categoria, estado;
    // foto contiene la ruta del archivo de imagen.

    public Alimento() { }

    public Alimento(String idalimento, String nombre, String descripcion, String precio, String foto, String categoria, String estado) {
        this.idalimento = idalimento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
        this.categoria = categoria;
        this.estado = estado;
    }

    public int insertar(Connection con) {
        int resultado = 0;
        try {
            byte[] imagen = Files.readAllBytes(Paths.get(foto));
            String sql = "INSERT INTO alimentos (nombre, descripcion, precio, foto, categoria, estado) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setString(3, precio);
                ps.setBytes(4, imagen);
                ps.setString(5, categoria);
                ps.setString(6, estado);
                resultado = ps.executeUpdate();
                try (ResultSet claves = ps.getGeneratedKeys()) {
                    if (claves.next()) idalimento = claves.getString(1);
                }
                return resultado;
            }
        } catch (Exception e) {
            System.out.println("Error en Alimento.insertar: " + e.getMessage());
            return resultado;
        }
    }

    public int borrar(Connection con) {
        int resultado = 0;
        try {
            String sql = "DELETE FROM alimentos WHERE idalimento = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, idalimento);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Alimento.borrar: " + e.getMessage());
            return resultado;
        }
    }

    public int actualizar(Connection con) {
        int resultado = 0;
        try {
            byte[] imagen = Files.readAllBytes(Paths.get(foto));
            String sql = "UPDATE alimentos SET nombre = ?, descripcion = ?, precio = ?, foto = ?, categoria = ?, estado = ? WHERE idalimento = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setString(3, precio);
                ps.setBytes(4, imagen);
                ps.setString(5, categoria);
                ps.setString(6, estado);
                ps.setString(7, idalimento);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Alimento.actualizar: " + e.getMessage());
            return resultado;
        }
    }
}
