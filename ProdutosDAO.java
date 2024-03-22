import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    
    
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultSet;
    
    public ArrayList<ProdutosDTO> listarProdutos() throws SQLException {
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
        
        try {
            conn = new conectaDAO().connectDB(); // Estabelece a conexão com o banco de dados
            String query = "SELECT * FROM produtos"; // Consulta SQL para selecionar todos os produtos
            
            prep = conn.prepareStatement(query);
            resultSet = prep.executeQuery();
            
            while (resultSet.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getInt("valor"));
                produto.setStatus(resultSet.getString("status"));
                
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar produtos: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (prep != null) {
                prep.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        return produtos;
    }

    public void cadastrarProduto(ProdutosDTO produto) throws SQLException {
        try {
            conn = new conectaDAO().connectDB(); // Estabelece a conexão com o banco de dados
            String query = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)"; // Consulta SQL para inserir um novo produto
            
            prep = conn.prepareStatement(query);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate(); // Executa a inserção no banco de dados
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar produto: " + e.getMessage());
        } finally {
            if (prep != null) {
                prep.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
