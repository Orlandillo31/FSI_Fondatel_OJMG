
import java.sql.Connection;
import java.sql.PreparedStatement;
    
    //    static String todos = "SELECT * FROM alimentos ORDER BY nombreI";
    //    static String curps = "SELECT curp FROM infantes ORDER BY curp";

     

public class Cliente {
    
    String codigoC,nombreC,rfc; 

    public Cliente() {
    }

    public Cliente(String codigoC, String nombreC, String rfc) {
        this.codigoC = codigoC;
        this.nombreC = nombreC;
        this.rfc = rfc;
    }
    
    
    
    
    public int insertar(Connection con){
        int resultado = 0;
        
        try {
            String sql = "INSERT INTO clienes VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage());
            return resultado;
        }
        return 0;
       
        
    }
    
    public int borrar(Connection con){
        int resultado = 0;
        
        try {
            String sql = "DELETE FROM clientes WHERE codigoC = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, codigoC);
            return ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage());
            return resultado;
        }
        
       
        
    }
    public int actualizar(Connection con){
        int resultado = 0;
        
        try {
            String sql = "DELETE FROM clientes WHERE codigoC = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1,codigoC );
            return ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage());
            return resultado;
        }
       
        
    }
    
}
