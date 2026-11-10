import java.sql.*;

public class Ordene {
    String folio, fecha_hora, observaciones, estadoO, silla, clientes_codigoC, mesas_numero, alimentos_idalimento, empleados_mesero, empleados_cocinero;

    public Ordene() {
    }

    public Ordene(String folio, String fecha_hora, String observaciones, String estadoO, String silla, String clientes_codigoC, String mesas_numero, String alimentos_idalimento, String empleados_mesero, String empleados_cocinero) {
        this.folio = folio;
        this.fecha_hora = fecha_hora;
        this.observaciones = observaciones;
        this.estadoO = estadoO;
        this.silla = silla;
        this.clientes_codigoC = clientes_codigoC;
        this.mesas_numero = mesas_numero;
        this.alimentos_idalimento = alimentos_idalimento;
        this.empleados_mesero = empleados_mesero;
        this.empleados_cocinero = empleados_cocinero;
    }

    public int insertar(Connection con) {
        int resultado = 0;
        try {
            String sql = "INSERT INTO ordenes (fecha_hora, observaciones, estadoO, silla, clientes_codigoC, mesas_numero, alimentos_idalimento, empleados_mesero, empleados_cocinero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, fecha_hora);
                ps.setString(2, observaciones);
                ps.setString(3, estadoO);
                ps.setString(4, silla);
                ps.setString(5, clientes_codigoC);
                ps.setString(6, mesas_numero);
                ps.setString(7, alimentos_idalimento);
                ps.setString(8, empleados_mesero);
                ps.setString(9, empleados_cocinero);
                resultado = ps.executeUpdate();
                try (ResultSet claves = ps.getGeneratedKeys()) {
                    if (claves.next()) folio = claves.getString(1);
                }
                return resultado;
            }
        } catch (Exception e) {
            System.out.println("Error en Ordene.insertar: " + e.getMessage());
            return resultado;
        }
    }

    public int borrar(Connection con) {
        int resultado = 0;
        try {
            String sql = "DELETE FROM ordenes WHERE folio = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, folio);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Ordene.borrar: " + e.getMessage());
            return resultado;
        }
    }

    public int actualizar(Connection con) {
        int resultado = 0;
        try {
            String sql = "UPDATE ordenes SET fecha_hora = ?, observaciones = ?, estadoO = ?, silla = ?, clientes_codigoC = ?, mesas_numero = ?, alimentos_idalimento = ?, empleados_mesero = ?, empleados_cocinero = ? WHERE folio = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, fecha_hora);
                ps.setString(2, observaciones);
                ps.setString(3, estadoO);
                ps.setString(4, silla);
                ps.setString(5, clientes_codigoC);
                ps.setString(6, mesas_numero);
                ps.setString(7, alimentos_idalimento);
                ps.setString(8, empleados_mesero);
                ps.setString(9, empleados_cocinero);
                ps.setString(10, folio);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Ordene.actualizar: " + e.getMessage());
            return resultado;
        }
    }
}
