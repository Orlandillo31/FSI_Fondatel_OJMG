import java.sql.*;

public class Cliente {
    String codigoC, nombreC, rfc;

    public Cliente() { }

    public Cliente(String codigoC, String nombreC, String rfc) {
        this.codigoC = codigoC;
        this.nombreC = nombreC;
        this.rfc = rfc;
    }

    public int insertar(Connection con) {
        int resultado = 0;
        try {
            String sql = "INSERT INTO clientes (codigoC, nombreC, rfc) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, codigoC);
                ps.setString(2, nombreC);
                ps.setString(3, rfc);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Cliente.insertar: " + e.getMessage());
            return resultado;
        }
    }

    public int borrar(Connection con) {
        int resultado = 0;
        try {
            String sql = "DELETE FROM clientes WHERE codigoC = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, codigoC);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Cliente.borrar: " + e.getMessage());
            return resultado;
        }
    }

    public int actualizar(Connection con) {
        int resultado = 0;
        try {
            String sql = "UPDATE clientes SET nombreC = ?, rfc = ? WHERE codigoC = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nombreC);
                ps.setString(2, rfc);
                ps.setString(3, codigoC);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Cliente.actualizar: " + e.getMessage());
            return resultado;
        }
    }
}
