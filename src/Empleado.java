import java.sql.*;

public class Empleado {
    String codigo, nombreE, puesto, turno, estadoE;

    public Empleado() { 
    }

    public Empleado(String codigo, String nombreE, String puesto, String turno, String estadoE) {
        this.codigo = codigo;
        this.nombreE = nombreE;
        this.puesto = puesto;
        this.turno = turno;
        this.estadoE = estadoE;
    }

    public int insertar(Connection con) {
        int resultado = 0;
        try {
            String sql = "INSERT INTO empleados (codigo, nombreE, puesto, turno, estadoE) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, codigo);
                ps.setString(2, nombreE);
                ps.setString(3, puesto);
                ps.setString(4, turno);
                ps.setString(5, estadoE);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Empleado.insertar: " + e.getMessage());
            return resultado;
        }
    }

    public int borrar(Connection con) {
        int resultado = 0;
        try {
            String sql = "DELETE FROM empleados WHERE codigo = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, codigo);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Empleado.borrar: " + e.getMessage());
            return resultado;
        }
    }

    public int actualizar(Connection con) {
        int resultado = 0;
        try {
            String sql = "UPDATE empleados SET nombreE = ?, puesto = ?, turno = ?, estadoE = ? WHERE codigo = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nombreE);
                ps.setString(2, puesto);
                ps.setString(3, turno);
                ps.setString(4, estadoE);
                ps.setString(5, codigo);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error en Empleado.actualizar: " + e.getMessage());
            return resultado;
        }
    }
}
