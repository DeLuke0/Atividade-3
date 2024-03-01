import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    public static ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public static boolean adicionar(ProdutosDTO p){
        try{
        if(p.getNome().isBlank() || p.getNome().isEmpty()){
            JOptionPane.showMessageDialog(null, "Cadastre o nome do produto corretamente");
        } else if(p.getStatus().isBlank() || p.getStatus().isEmpty()){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o status do produto");
        } else if(p.getValor() < 0){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o valor do produto");
        } else{
            listagem.add(p);
            JOptionPane.showMessageDialog(null, "O produto foi cadastrado com sucesso!");
        return true;}}
        catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Usuario não pode ser cadastrado, Tente Novamente");
        }
    return false;}
    
    public static ArrayList<ProdutosDTO> listarProdutos(){
        return listagem;
    }      
}

