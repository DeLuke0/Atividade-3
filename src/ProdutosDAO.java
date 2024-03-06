import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


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
    
    public static boolean venderProduto(ProdutosDTO p){
        try{
            conectaDAO conector = new conectaDAO();
            conector.connectDB();
            Statement st = conector.conn.createStatement();
            String sql;
            sql = "select produtos.status from produtos where id = " + p.getId();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            switch (rs.getString(1)) {
                case "Vendido" -> {
                    JOptionPane.showMessageDialog(null, "Este produto ja foi vendido");
                    return true;
                }
                case "A Venda" -> {
                    sql = "update produtos set status = 'Vendido' where id = " + p.getId();
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
                    return true;
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Erro ao vender o produto");
                    return false;
                }
            }
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
    public static DefaultTableModel listarProdutosVendidos(){
        try{
        // Conexão com o banco de dados
        conectaDAO conector = new conectaDAO();
        conector.connectDB();
        Statement st = conector.conn.createStatement();
        // Declaração das variáveis
        String sql;
        int i = 0;
        String[] colunas = {"ID", "Nome do Produto", "Valor do Produto", "Status da Venda"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);
        // Laço de repetição
        for(i = 0; i < 1000; i++){
            sql = "select * from produtos where id = " + i + " and status = 'Vendido'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int valor = rs.getInt("valor");
                String status = rs.getString("status");
                // Laço de repetição responsável por atualizar as linhas da tabela
                String[] linha = {
                Integer.toString(id),
                nome,
                Integer.toString(valor),
                status
            };
            tabela.addRow(linha);
        }}
        // Comandos para a inserção dos dados na tabela
        return tabela;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}

