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

        // Criar e exibir a tela principal
        java.awt.EventQueue.invokeLater(() -> {
            new cadastroVIEW().setVisible(true);
        });
    }
}

