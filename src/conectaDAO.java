import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conectaDAO {
    Connection conn = null;
    public String url = "jdbc:mysql://localhost:3306/uc11"; 
    public String user = "root";
    public String password = "#Montanha0";
    
    public boolean connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão realizada com sucesso");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Falha na conexão com o banco " + ex.getMessage());
            return false;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
