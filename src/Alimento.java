import java.sql.*;

public class Alimento {
    
//    static String todos = "SELECT * FROM alimentos ORDER BY nombreI";
//    static String curps = "SELECT curp FROM infantes ORDER BY curp";
    
    //Declaración de variables
    String idalimento, nombre, descripcion,precio, foto, categoria,estado;

    public Alimento() {
    }

    public Alimento(String idalimentos, String nombre, String descripcion, String precio, String foto, String categoria, String estado) {
        this.idalimento = idalimentos;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
        this.categoria = categoria;
        this.estado = estado;
    }
    
    public int insertar(Connection con){
        int resultado = 0;
        
        try {
            String sql = "INSERT INTO alimentos VALUES(?, ?, ?, ?, ?, ?, ?)";
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
            String sql = "DELETE FROM alimentos WHERE idalimento = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, idalimento);
            return ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage());
            return resultado;
        }
       
        
    }
    public int actualizar(Connection con){
        int resultado = 0;
        
        try {
            String sql = "UPDATE alimentos  SET nombre = ?,descripcion = ?,precio = ?,foto = ?,categoria = ?, estado = ? WHERE idalimento = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, idalimento);
            ps.setString(2, nombre);
            ps.setString(3, descripcion);
            ps.setString(4, precio);
            ps.setString(5,foto );
            ps.setString(6,categoria );
            ps.setString(7,idalimento );
            return ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage());
            return resultado;
        }
        
       
        
    }
    
    
    
    
    
}
