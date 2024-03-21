import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class conectaDAO {

    public static Connection connectDB() {
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=9221&serverTimezone=UTC");
            System.out.println("Conexão bem-sucedida!");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver JDBC não encontrado.");
            e.printStackTrace();
        } catch (SQLException erro) {
            System.out.println("Erro: Não foi possível conectar ao banco de dados.");
            erro.printStackTrace();
        }
        
        return conn; // Retorna a conexão
    }
    
    
    
    // Método para inserir um produto no banco de dados
    public static void inserirProduto(String nome, int valor, String status) {
        Connection conn = connectDB();
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)");
            statement.setString(1, nome);
            statement.setInt(2, valor);
            statement.setString(3, status);
            statement.executeUpdate();
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto.");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão.");
                e.printStackTrace();
            }
        }
    }
    
    // Método para obter todos os produtos do banco de dados
    public static List<Produto> getAllProdutos() {
        List<Produto> produtos = new ArrayList<>();
        Connection conn = connectDB();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM produtos");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                int valor = resultSet.getInt("valor");
                String status = resultSet.getString("status");
                produtos.add(new Produto(id, nome, valor, status));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter produtos.");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão.");
                e.printStackTrace();
            }
        }
        return produtos;
    }
    
    // Classe modelo para representar um produto
    public static class Produto {
        private long id;
        private String nome;
        private int valor;
        private String status;
        
        public Produto(long id, String nome, int valor, String status) {
            this.id = id;
            this.nome = nome;
            this.valor = valor;
            this.status = status;
        }

        // Getters e setters
        // Implemente conforme necessário
    }
}
