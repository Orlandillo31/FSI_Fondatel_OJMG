import java.sql.*;

public class Mesa {
    String numero, estadoM, capacidad;

    public Mesa() { }

    public Mesa(String numero, String estadoM, String capacidad) {
        this.numero = numero;
        this.estadoM = estadoM;
        this.capacidad = capacidad;
    }

    public int insertar(Connection con) {
        int resultado = 0;
        try {
            String sql = "INSERT INTO mesas (numero, estadoM, capacidad) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, numero);
                ps.setString(2, estadoM);
                ps.setString(3, capacidad);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Mesa.insertar: " + e.getMessage());
            return resultado;
        }
    }

    public int borrar(Connection con) {
        int resultado = 0;
        try {
            String sql = "DELETE FROM mesas WHERE numero = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, numero);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Mesa.borrar: " + e.getMessage());
            return resultado;
        }
    }

    public int actualizar(Connection con) {
        int resultado = 0;
        try {
            String sql = "UPDATE mesas SET estadoM = ?, capacidad = ? WHERE numero = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, estadoM);
                ps.setString(2, capacidad);
                ps.setString(3, numero);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Mesa.actualizar: " + e.getMessage());
            return resultado;
        }
    }
}
