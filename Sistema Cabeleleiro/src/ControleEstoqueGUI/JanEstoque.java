package ControleEstoqueGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import ControleEstoqueBanco.EstoqueDAO;
import ControleEstoqueBean.Estoque;

public class JanEstoque extends JFrame {

	private JLabel labelProduto;
	private JLabel labelQntEstoque;
	private JComboBox comboProduto;
	private JTextField textQntEstoque;
	private JButton buttonModificar;
	private JButton buttonFechar;

	public JanEstoque() {
		super("Modificar Estoque");
		Componentes();
		setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.textQntEstoque.setEditable(false);
		
		EstoqueDAO dao = new EstoqueDAO();
		this.comboProduto.removeAllItems(); 
		this.comboProduto.addItem("");
		ArrayList<String> produto = dao.comboProduto();
		Iterator<String> i = produto.iterator();
		  
		while(i.hasNext()) {  
			this.comboProduto.addItem(String.valueOf(i.next()));  
		}
		
	}

	private void Componentes() {
		this.labelProduto = new JLabel();
		this.labelQntEstoque = new JLabel();
		this.comboProduto = new JComboBox();
		this.textQntEstoque = new JTextField();
		this.buttonModificar = new JButton();
		this.buttonFechar = new JButton();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		

		this.labelProduto.setText("Selecione o Produto:");
		this.labelQntEstoque.setText("Quantidade em Estoque:");
		
		this.comboProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JanEstoque.this.comboProdutoActionPerformed(evt);
			}
		});

		this.buttonModificar.setText("Modificar");
		this.buttonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JanEstoque.this.buttonModificarActionPerformed(evt);
			}
		});
		this.buttonFechar.setText("Fechar");
		this.buttonFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JanEstoque.this.buttonFecharActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.labelProduto).addComponent(this.comboProduto, -2, 151, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 75, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.textQntEstoque).addComponent(this.labelQntEstoque, -1, -1, 32767)).addContainerGap(45, 32767)).addGroup(layout.createSequentialGroup().addGap(39, 39, 39).addComponent(this.buttonModificar).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 150, 32767).addComponent(this.buttonFechar).addGap(71, 71, 71)));

	    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(45, 45, 45).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelProduto).addComponent(this.labelQntEstoque)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.comboProduto, -2, -1, -2).addComponent(this.textQntEstoque, -2, -1, -2)).addGap(87, 87, 87).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.buttonModificar).addComponent(this.buttonFechar)).addContainerGap(36, 32767)));

		pack();

	}

	
	private void comboProdutoActionPerformed(ActionEvent evt) {
		String nomeProduto = (String) this.comboProduto.getSelectedItem();
		if (!(nomeProduto.equals(""))) {
			this.textQntEstoque.setEditable(true);
			Estoque estoque = new Estoque();
			estoque.setNomeProduto((String) this.comboProduto.getSelectedItem());
	        
	        
	        EstoqueDAO dao = new EstoqueDAO();
	        this.textQntEstoque.setText(Integer.toString(dao.atualizaTextQntEstoque(estoque)));
		} else {
			this.textQntEstoque.setText("");
		}
	}

	private void buttonModificarActionPerformed(ActionEvent evt) {
		Estoque estoque = new Estoque();
		estoque.setNomeProduto((String) this.comboProduto.getSelectedItem());
		estoque.setQntEstoque(Integer.parseInt((this.textQntEstoque.getText())));
        
        
        EstoqueDAO dao = new EstoqueDAO();
        dao.modifica(estoque);
        
        JOptionPane.showMessageDialog(null, "Modificado com sucesso!");
	}

	private void buttonFecharActionPerformed(ActionEvent evt) {
		dispose();
		JanPrincipal janP = new JanPrincipal();
	    janP.setVisible(true);
	}
}
