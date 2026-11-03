
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Empleado {
    
    String codigo,nombreE,puesto,turno,nom_usr,nip,estadoE;

    public Empleado() {
    }

    public Empleado(String codigo, String nombreE, String puesto, String turno, String nom_usr, String nip, String estadoE) {
        this.codigo = codigo;
        this.nombreE = nombreE;
        this.puesto = puesto;
        this.turno = turno;
        this.nom_usr = nom_usr;
        this.nip = nip;
        this.estadoE = estadoE;
    }
    
    
    
    

    public int insertar(Connection con) {
        int resultado = 0;

        try {
            String sql = "INSERT INTO empleados VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
            return resultado;
        }
        return 0;

    }

    public int borrar(Connection con) {
        int resultado = 0;

        try {
            String sql = "DELETE FROM empleados WHERE codigo = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, codigo);
            return ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
            return resultado;
        }

    }

    public int actualizar(Connection con) {
        int resultado = 0;

        try {
            String sql = "UPDATE empleados  SET nombreE = ?,puesto = ?,turno =?,nom_usr = ?,nip = ?,estadoE = ? WHERE codigo = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, codigo);
            ps.setString(2, nombreE);
            ps.setString(3, puesto);
            ps.setString(4, turno);
            ps.setString(5, nom_usr);
            ps.setString(6, nip);
            ps.setString(7, estadoE);
            return ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
            return resultado;
        }

    }

}
    
