package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/portaria", //servidor
                    "tiago", //usuario
                    "300985"); //senha
            System.out.println("Conexão estabelecida.");
            return conn;
        } catch (ClassNotFoundException e) {            
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao banco de dados.");
            return null;
        } 
        
    } 
}