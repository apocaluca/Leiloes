import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ConsultaVendasView extends JFrame {
    private JTable tabelaVendas;

    public ConsultaVendasView(ArrayList<ProdutosDTO> produtosVendidos) {
        initComponents(produtosVendidos);
    }

    private void initComponents(ArrayList<ProdutosDTO> produtosVendidos) {
        tabelaVendas = new JTable();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Valor");
        model.addColumn("Status");

        for (ProdutosDTO produto : produtosVendidos) {
            model.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    produto.getValor(),
                    produto.getStatus()
            });
        }

        tabelaVendas.setModel(model);

        JScrollPane scrollPane = new JScrollPane(tabelaVendas);
        getContentPane().add(scrollPane);
        
        setTitle("Consulta de Vendas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
