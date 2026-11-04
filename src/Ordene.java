
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Ordene {
    
    int resultado = 0;
    String folio,fecha_hora,observaciones,estadoO,silla,clientes_codigoC,mesas_numero,alimentos_idalimento,empleados_mesero,empleados_cocinero;

    public Ordene() {
    }

    public Ordene(String folio, String fecha_hora, String observaciones, String estadoO, String silla, String clientes_codgioC, String mesas_numero, String alimentos_idalimento, String empleados_mesero, String empleados_cocinero) {
        this.folio = folio;
        this.fecha_hora = fecha_hora;
        this.observaciones = observaciones;
        this.estadoO = estadoO;
        this.silla = silla;
        this.clientes_codigoC = clientes_codgioC;
        this.mesas_numero = mesas_numero;
        this.alimentos_idalimento = alimentos_idalimento;
        this.empleados_mesero = empleados_mesero;
        this.empleados_cocinero = empleados_cocinero;
    }
    
    
    
    

    public int insertar(Connection con) {
        
        int resultado = 0;
        
        try {
            String sql = "INSERT INTO ordenes VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
             ps.setString(1, folio);
             ps.setString(2, fecha_hora);
             ps.setString(3, observaciones);
             ps.setString(4, estadoO);
             ps.setString(5, silla);
             ps.setString(6, clientes_codigoC);
             ps.setString(7, mesas_numero);
             ps.setString(8, alimentos_idalimento);
             ps.setString(9, empleados_mesero);
             ps.setString(10, empleados_cocinero);
            return ps.executeUpdate();
            
            
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage());
            return resultado;
        }
        
        
        
        

    }

    public int borrar(Connection con) {
        int resultado = 0;

        try {
             String sql = "DELETE FROM ordenes WHERE folio = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, folio);
            return ps.executeUpdate();
            

        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
            return resultado;
        }
        

    }

    public int actualizar(Connection con) {
        
        try {
            String sql = "UPDATE ordenes SET fecha_hora = ?,observaciones = ?,estadoO = ?,silla = ?,clientes_codigoC = ?,mesas_numero = ?,alimentos_idalimento = ?, empleados_mesero = ?,empleados_cocinero = ? WHERE folio = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, fecha_hora);
            ps.setString(2, observaciones);
            ps.setString(3, estadoO);
            ps.setString(4, silla);
            ps.setString(5, clientes_codigoC);
            ps.setString(6, mesas_numero);
            ps.setString(7, alimentos_idalimento);
            ps.setString(8, empleados_mesero );
            ps.setString(9, empleados_cocinero);
            ps.setString(10, folio);
            return ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            return resultado;
        }

    }

}
