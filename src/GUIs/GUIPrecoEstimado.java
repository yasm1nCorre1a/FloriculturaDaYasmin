package GUIs;
import CodigoGerado.GUIListagemPrecoEstimado;
import DAOs.DAOPrecoEstimado;
import Entidades.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Image;
import javax.swing.JTextField;
import tools.*;
import DAOs.*;
public class GUIPrecoEstimado extends JFrame {
ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
JButton btnCreate = new JButton(iconeCreate);
JButton btnRetrieve = new JButton(iconeRetrieve);
JButton btnUpdate = new JButton(iconeUpdate);
JButton btnDelete = new JButton(iconeDelete);
JButton btnSave = new JButton(iconeSave);
JButton btnCancel = new JButton(iconeCancel);
JButton btnList = new JButton(iconeListar);
private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
private Date data1;
private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
private JPanel pnCentro = new JPanel(new GridLayout(2, 2));
private JPanel pnSul = new JPanel(new GridLayout(1, 1));
private JLabel lbFloresIdFlor = new JLabel("FloresIdFlor");
private JTextField tfFloresIdFlor = new JTextField(10);
private JLabel lbData = new JLabel("Data");
private JTextField tfData = new JTextField(10);
private JButton btEscolha1 = new JButton("Escolha");
private JPanel pnData = new JPanel(new GridLayout(1, 2));
private JLabel lbPreco = new JLabel("Preco");
private JTextField tfPreco = new JTextField(10);
ScrollPane scroll = new ScrollPane();
JTextArea jTextArea = new JTextArea();
JPanel aviso = new JPanel();
JLabel labelAviso = new JLabel("");
String qualAcao = "";//variavel para facilitar insert e update
DAOPrecoEstimado daoPrecoEstimado = new DAOPrecoEstimado();
PrecoEstimado precoEstimado;
private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();
public GUIPrecoEstimado() {
setSize(900, 400);
setDefaultCloseOperation(DISPOSE_ON_CLOSE);
setTitle("CRUD - PrecoEstimado");
Container cp = getContentPane();cp = getContentPane();
btnCreate.setToolTipText("Inserir novo registro");
btnRetrieve.setToolTipText("Pesquisar por chave");
btnUpdate.setToolTipText("Alterar");
btnDelete.setToolTipText("Excluir");
btnList.setToolTipText("Listar todos");
btnSave.setToolTipText("Salvar");
btnCancel.setToolTipText("Cancelar");cp.setLayout(new BorderLayout());
cp.add(pnNorte, BorderLayout.NORTH);
cp.add(pnCentro, BorderLayout.CENTER);
cp.add(pnSul, BorderLayout.SOUTH);pnNorte.add(lbFloresIdFlor);
pnNorte.add(tfFloresIdFlor);
pnNorte.add(btnRetrieve);
pnNorte.add(btnCreate);
pnNorte.add(btnUpdate);
pnNorte.add(btnDelete);
pnNorte.add(btnSave);
pnNorte.add(btnList);
btnCancel.setVisible(false);
btnDelete.setVisible(false);
btnCreate.setVisible(false);
btnSave.setVisible(false);
btnUpdate.setVisible(false);
pnCentro.add(lbData);
pnCentro.add(pnData);
pnData.add(btEscolha1);
pnData.add(tfData);
pnCentro.add(lbPreco);
pnCentro.add(tfPreco);
pnSul.setBackground(Color.red);
scroll.add(jTextArea);
pnSul.add(scroll);
btEscolha1.setEnabled(false);
tfData.setEditable(false);
tfPreco.setEditable(false);
btnRetrieve.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
try {
tfFloresIdFlor.setBackground(Color.white);
jTextArea.setText("");
precoEstimado = new PrecoEstimado();
int identificador = Integer.valueOf(tfFloresIdFlor.getText());
precoEstimado.setFloresIdFlor(identificador);
precoEstimado = daoPrecoEstimado.obter(precoEstimado.getFloresIdFlor());
if (precoEstimado == null) {
pnNorte.setBackground(Color.red);
tfData.setText("");
tfPreco.setText("");
btnCreate.setVisible(true);
} else {
pnNorte.setBackground(Color.green);
tfData.setText(sdf.format(precoEstimado.getData()));
tfPreco.setText(String.valueOf(precoEstimado.getPreco()));
btnUpdate.setVisible(true);
btnDelete.setVisible(true);
btnCreate.setVisible(false);
}
tfFloresIdFlor.setEditable(false);
tfData.setEditable(false);
tfPreco.setEditable(false);
tfFloresIdFlor.selectAll();
} catch (Exception erro) {
pnNorte.setBackground(Color.yellow);
tfFloresIdFlor.requestFocus();
tfFloresIdFlor.setBackground(Color.red);
jTextArea.setText("Erro... \n");
jTextArea.append(erro.getMessage());
}
}
});
btnCreate.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
tfFloresIdFlor.setEditable(false);
tfData.requestFocus();
btnCreate.setVisible(false);
btnSave.setVisible(true);
qualAcao = "incluir";
precoEstimado = new PrecoEstimado();
btEscolha1.setEnabled(true);
tfPreco.setEditable(true);
tfFloresIdFlor.setEditable(false);
}
});
btnSave.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
try {
jTextArea.setText("");
precoEstimado = new PrecoEstimado();
precoEstimado.setFloresIdFlor(Integer.valueOf(tfFloresIdFlor.getText()));
sdf.setLenient(false);
data1 = sdf.parse(tfData.getText());
try {
precoEstimado.setData(sdf.parse(tfData.getText()));} catch (ParseException ex) {
Logger.getLogger(GUIPrecoEstimado.class
.getName()).log(Level.SEVERE, null, ex);
}
precoEstimado.setPreco(Double.valueOf(tfPreco.getText()));
if (qualAcao.equals("incluir")) {
daoPrecoEstimado.inserir(precoEstimado);
} else {
daoPrecoEstimado.atualizar(precoEstimado);
}
tfFloresIdFlor.setEditable(true);
tfFloresIdFlor.requestFocus();
tfData.setText("");
tfPreco.setText("");
btnSave.setVisible(false)
;pnNorte.setBackground(Color.green);
btEscolha1.setEnabled(false);
tfPreco.setEditable(false);
} catch (Exception erro){
jTextArea.append("Erro............");
tfFloresIdFlor.setEditable(true);
pnNorte.setBackground(Color.red); 
}
}
});
btnUpdate.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
btnUpdate.setVisible(false);
btnDelete.setVisible(false);
tfData.requestFocus();
btnSave.setVisible(true);
qualAcao = "editar";
btEscolha1.setEnabled(true);
tfPreco.setEditable(true);
}
});
btnDelete.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
"Confirma a exclusão do registro <ID = " + precoEstimado.getFloresIdFlor() + ">?", "Confirm",
JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
daoPrecoEstimado.remover(precoEstimado);
tfFloresIdFlor.requestFocus();
tfData.setText("");
tfPreco.setText("");
tfFloresIdFlor.setEditable(true);
btnUpdate.setVisible(false);
btnDelete.setVisible(false);
}
}
});
btnList.addActionListener(new ActionListener() {
@Override
 public void actionPerformed(ActionEvent e) {
GUIListagemPrecoEstimado guiListagem = new GUIListagemPrecoEstimado(daoPrecoEstimado.list());
}
});
setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
addWindowListener(new WindowAdapter() {
@Override
public void windowClosing(WindowEvent e) {
System.exit(0);
}
});
btEscolha1.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
try {
jTextArea.setText("");
DateChooser dc1 = new DateChooser((JFrame) null, "Escolha uma data", 683, 0);
data1 = dc1.select();
tfData.setText(sdf.format(data1));
} catch (Exception ex) {
jTextArea.setText("Escolha uma data\n");
}}
});
tfFloresIdFlor.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
List<String> listaAuxiliar = daoPrecoEstimado.listInOrderNomeStrings("id");
if (listaAuxiliar.size() > 0) {
String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
if (!selectedItem.equals("")) {
String[] aux = selectedItem.split("-");
tfFloresIdFlor.setText(aux[0]);
btnRetrieve.doClick();

} else {
tfFloresIdFlor.requestFocus();tfFloresIdFlor.selectAll();}
}
}
});
CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
setVisible(true);
}
public static void main(String[] args) {
GUIPrecoEstimado guiPrecoEstimado = new GUIPrecoEstimado();
}
}
