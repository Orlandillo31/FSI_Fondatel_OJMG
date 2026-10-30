import java.sql.*;

public class Alimento {
    
//    static String todos = "SELECT * FROM alimentos ORDER BY nombreI";
//    static String curps = "SELECT curp FROM infantes ORDER BY curp";
    
    //Declaración de variables
    String idalimentos, nombre, descripcion,precio, foto, categoria,estado;

    public Alimento() {
    }

    public Alimento(String idalimentos, String nombre, String descripcion, String precio, String foto, String categoria, String estado) {
        this.idalimentos = idalimentos;
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
            
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage());
            return resultado;
        }
        return 0;
       
        
    }
    public int borrar(Connection con){
        int resultado = 0;
        
        try {
            
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage());
            return resultado;
        }
        return 0;
       
        
    }
    public int actualizar(Connection con){
        int resultado = 0;
        
        try {
            
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage());
            return resultado;
        }
        return 0;
       
        
    }
    
    
    
    
    
}
