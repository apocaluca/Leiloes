import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class ClassPrincipal {

    public static void main(String[] args) {
        // Configurar a aparência do Swing
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cadastroVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Fazer a conexão com o banco de dados
        Connection conn = (Connection) conectaDAO.connectDB();
        
        // Verificar se a conexão foi bem-sucedida antes de abrir a tela principal
        if (conn != null) {
            // Tela principal
            java.awt.EventQueue.invokeLater(() -> {
                new cadastroVIEW().setVisible(true);
            });
        } else {
            // Exibir mensagem de erro e encerrar o programa se a conexão falhar
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados.");
            System.exit(1);
        }
    }
}
