package GUIs;
import Entidades.PrecoEstimado;
import tools.*;import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
public class GUIListagemPrecoEstimado extends JDialog {
JPanel painelTa = new JPanel();
JScrollPane scroll = new JScrollPane();
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
public GUIListagemPrecoEstimado(List<PrecoEstimado> texto) {
setTitle("Listagem de PrecoEstimado");
setSize(600, 300);//tamanho da janela
setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memÃ³ria a classe
setLayout(new BorderLayout());//informa qual gerenciador de layout serÃ¡ usado
setBackground(Color.CYAN);//cor do fundo da janela
setModal(true);
Container cp = getContentPane();//container principal, para adicionar nele os outros componentes
JToolBar toolBar = new JToolBar();

String[] colunas = new String[]{"FloresIdFlor", "Data", "Preco", };
String[][] dados = new String[0][3];
DefaultTableModel model = new DefaultTableModel(dados, colunas);
JTable tabela = new JTable(model);
scroll.setViewportView(tabela);
for (int i = 0; i < texto.size(); i++) {
String[] linha = new String[]{String.valueOf(texto.get(i).getFloresIdFlor()), sdf.format(texto.get(i).getData()), String.valueOf(texto.get(i).getPreco()), };
model.addRow(linha);
}
painelTa.add(scroll);
cp.add(toolBar, BorderLayout.NORTH);
cp.add(scroll, BorderLayout.CENTER);
CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
setVisible(true);
}
}
