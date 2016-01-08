package ControleEstoqueGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import ControleEstoqueBanco.ProdutoDAO;
import ControleEstoqueBean.Produto;

public class JanCadProduto extends JFrame {
	ProdutoDAO dao = new ProdutoDAO(); 
	private JTextField textPesquisar;
	private JTextField textCodigo;
	private JTextField textNome;
	private JTextField textPreco;
	private JTextField textQntEstoque;
	private JComboBox<Object> comboFornecedor;
	private JButton buttonPesquisar;
	private JButton buttonNovo;
	private JButton buttonAlterar;
	private JButton buttonExcluir;
	private JButton buttonAtualizar;
	private JButton buttonSalvar;
	private JButton buttonFechar;
	private JLabel labelCodigo;
	private JLabel labelNome;
	private JLabel labelPreco;
	private JLabel labelFornecedor;
	private JLabel labelQntEstoque;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem subNovo;
	private JMenuItem subFechar;
	private JPanel panelPesquisarProduto;
	private JPanel panelDadosCadastrais;
	private JPanel panelBotoes;
	private JScrollPane scrollPaneInformacaoes;
	private JTable tableInformacaoes;

	public JanCadProduto() {
		super("Cadastro - Produto");
		Componentes();
		setLocationRelativeTo(null);
		this.setResizable(false);

		preencherJTable();
		Invisivel();
		
		this.comboFornecedor.removeAllItems(); 
		this.comboFornecedor.addItem("");
		ArrayList<String> fornecedor = this.dao.comboFornecedor();  
		Iterator<String> i = fornecedor.iterator();
		  
		while(i.hasNext()) {  
			this.comboFornecedor.addItem(String.valueOf(i.next()));  
		}
	}
	
	public void Invisivel() {
		this.textNome.setEditable(false);
		this.textPreco.setEditable(false);
		this.comboFornecedor.setEnabled(false);
		this.textQntEstoque.setEditable(false);
	}

	public void Visivel() {
		this.textNome.setEditable(true);
		this.textPreco.setEditable(true);
		this.comboFornecedor.setEnabled(true);
		this.textQntEstoque.setEditable(true);
	}

	public void preencherJTable() {
		this.tableInformacaoes.getColumnModel().getColumn(0).setPreferredWidth(5);
		this.tableInformacaoes.getColumnModel().getColumn(1).setPreferredWidth(100);
		this.tableInformacaoes.getColumnModel().getColumn(2).setPreferredWidth(100);
		this.tableInformacaoes.getColumnModel().getColumn(3).setPreferredWidth(15);
		this.tableInformacaoes.getColumnModel().getColumn(4).setPreferredWidth(15);
		
		DefaultTableModel tabela = (DefaultTableModel)this.tableInformacaoes.getModel();
		tabela.setNumRows(0);
		
		List<Produto> listaProduto = this.dao.consultar();
		
		for (Produto  produto: listaProduto) {
			tabela.addRow(new Object[] { produto.getCodigo(), produto.getNome(), produto.getFornecedor().getNome(), produto.getPreco(), produto.getQntEstoque() });
		}
	}
	
	private void Componentes() {
		this.textPesquisar = new JTextField();
		this.textCodigo = new JTextField();
		this.textNome = new JTextField();
		this.textPreco = new JTextField();
		this.textQntEstoque = new JTextField();
		this.buttonPesquisar = new JButton();
		this.comboFornecedor = new JComboBox<>();
		this.buttonNovo = new JButton();
		this.buttonAlterar = new JButton();
		this.buttonExcluir = new JButton();
		this.buttonAtualizar = new JButton();
		this.buttonSalvar = new JButton();
		this.buttonFechar = new JButton();
		this.labelCodigo = new JLabel();
		this.labelNome = new JLabel();
		this.labelPreco = new JLabel();
		this.labelFornecedor = new JLabel();
		this.labelQntEstoque = new JLabel();
		this.menuBar = new JMenuBar();
		this.menuArquivo = new JMenu();
		this.subNovo = new JMenuItem();
		this.subFechar = new JMenuItem();
		this.panelPesquisarProduto = new JPanel();
		this.panelDadosCadastrais = new JPanel();
		this.panelBotoes = new JPanel();
		this.scrollPaneInformacaoes = new JScrollPane();
		scrollPaneInformacaoes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.tableInformacaoes = new JTable();
		tableInformacaoes.setColumnSelectionAllowed(true);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	    this.panelPesquisarProduto.setBorder(BorderFactory.createTitledBorder("Pesquisar Produtos"));
	    
	    this.textPesquisar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadProduto.this.textPesquisarActionPerformed(evt);
	    	}
	    });
	    this.buttonPesquisar.setBackground(new Color(255, 255, 255));
	    this.buttonPesquisar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/localizar.gif")));
	    this.buttonPesquisar.setText("Pesquisar");
	    this.buttonPesquisar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadProduto.this.buttonPesquisarActionPerformed(evt);
	    	}
	    });
	    GroupLayout layoutPesquisarProduto = new GroupLayout(this.panelPesquisarProduto);
	    layoutPesquisarProduto.setHorizontalGroup(
	    	layoutPesquisarProduto.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(layoutPesquisarProduto.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(textPesquisar, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
	    			.addGap(18)
	    			.addComponent(buttonPesquisar, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    layoutPesquisarProduto.setVerticalGroup(
	    	layoutPesquisarProduto.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutPesquisarProduto.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutPesquisarProduto.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(textPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(buttonPesquisar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    this.panelPesquisarProduto.setLayout(layoutPesquisarProduto);
		
		
		this.panelDadosCadastrais.setBorder(BorderFactory.createTitledBorder("Dados Cadastrais"));
		
		
		this.labelCodigo.setText("Código:");
		this.textCodigo.setFont(new Font("Tahoma", 1, 11));
		this.textCodigo.setEnabled(false);
				
		
		this.labelNome.setText("Nome:");
		this.textNome.setFont(new Font("Tahoma", 1, 11));
		
		
		this.labelPreco.setText("Preço:");
		this.textPreco.setFont(new Font("Tahoma", 1, 11));
		
		
		this.labelFornecedor.setText("Fornecedor:");
		this.comboFornecedor.setFont(new Font("Tahoma", 1, 11));
		
		
		this.labelQntEstoque.setText("Quantidade em estoque:");
		this.textQntEstoque.setFont(new Font("Tahoma", 1, 11));

	    GroupLayout layoutDadosCadastrais = new GroupLayout(this.panelDadosCadastrais);
	    layoutDadosCadastrais.setHorizontalGroup(
	    	layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addComponent(labelCodigo, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(textCodigo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(labelNome, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(textNome, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING, false)
	    						.addComponent(labelQntEstoque, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    						.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    							.addGap(6)
	    							.addComponent(labelPreco, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(textPreco, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
	    					.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    						.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    							.addGap(18)
	    							.addComponent(labelFornecedor, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(comboFornecedor, 0, 355, Short.MAX_VALUE))
	    						.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    							.addGap(6)
	    							.addComponent(textQntEstoque, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))))
	    			.addContainerGap())
	    );
	    layoutDadosCadastrais.setVerticalGroup(
	    	layoutDadosCadastrais.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addComponent(labelNome)
	    					.addPreferredGap(ComponentPlacement.RELATED))
	    				.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    					.addComponent(labelCodigo)
	    					.addComponent(textCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addComponent(textNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addGap(21)
	    					.addComponent(labelPreco))
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addGap(18)
	    					.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    						.addComponent(comboFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    						.addComponent(textPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addGap(21)
	    					.addComponent(labelFornecedor)))
	    			.addGap(0, 0, Short.MAX_VALUE)
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addGap(21)
	    					.addComponent(labelQntEstoque))
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addGap(18)
	    					.addComponent(textQntEstoque, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    			.addContainerGap())
	    );
	    this.panelDadosCadastrais.setLayout(layoutDadosCadastrais);

	   
	    this.panelBotoes.setBorder(BorderFactory.createCompoundBorder());	    
	    
	    
	    this.buttonNovo.setBackground(new Color(255, 255, 255));
	    this.buttonNovo.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/novo_registro.gif")));
	    this.buttonNovo.setText("Novo");
	    this.buttonNovo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadProduto.this.buttonNovoActionPerformed(evt);
	    	}
	    });
	    this.buttonAlterar.setBackground(new Color(255, 255, 255));
	    this.buttonAlterar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/alterar_registro.gif")));
	    this.buttonAlterar.setText("Alterar");
	    this.buttonAlterar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadProduto.this.buttonAlterarActionPerformed(evt);
	    	}
	    });
	    
	    this.buttonExcluir.setBackground(new Color(255, 255, 255));
	    this.buttonExcluir.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/excluir.png")));
	    this.buttonExcluir.setText("Excluir");
	    this.buttonExcluir.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadProduto.this.buttonExcluirActionPerformed(evt);
	    	}
	    });
	    
	    this.buttonAtualizar.setBackground(new Color(255, 255, 255));
	    this.buttonAtualizar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/atualizar.png")));
	    this.buttonAtualizar.setText("Atualizar");
	    this.buttonAtualizar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadProduto.this.buttonAtualizarActionPerformed(evt);
	    	}
	    });
	    this.buttonSalvar.setBackground(new Color(255, 255, 255));
	    this.buttonSalvar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/gravar_registro.gif")));
	    this.buttonSalvar.setText("Salvar");
	    this.buttonSalvar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadProduto.this.buttonSalvarActionPerformed(evt);
	    	}
	    });
	    this.buttonFechar.setBackground(new Color(255, 255, 255));
	    this.buttonFechar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/sair.gif")));
	    this.buttonFechar.setText("Fechar");
	    this.buttonFechar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadProduto.this.buttonFecharActionPerformed(evt);
	    	}
	    });
	    
	    GroupLayout layoutBotoes = new GroupLayout(this.panelBotoes);
	    layoutBotoes.setHorizontalGroup(
	    	layoutBotoes.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutBotoes.createSequentialGroup()
	    			.addComponent(buttonNovo, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(buttonAlterar, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(buttonExcluir, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(buttonAtualizar, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(buttonSalvar, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(buttonFechar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
	    );
	    layoutBotoes.setVerticalGroup(
	    	layoutBotoes.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutBotoes.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutBotoes.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(buttonNovo)
	    				.addComponent(buttonFechar)
	    				.addComponent(buttonAlterar)
	    				.addComponent(buttonSalvar)
	    				.addComponent(buttonAtualizar)
	    				.addComponent(buttonExcluir, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
	    			.addGap(0, 0, Short.MAX_VALUE))
	    );
	    this.panelBotoes.setLayout(layoutBotoes);

	    
	    this.tableInformacaoes.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null  }, { null, null, null, null  }, { null, null, null, null  }, { null, null, null, null  } }, new String[] { "Código", "Nome", "Fornecedor", "Preço", "Quantidade" }));
	    
	    this.tableInformacaoes.setFocusable(false);
	    this.scrollPaneInformacaoes.setViewportView(this.tableInformacaoes);
	    
	    	    
	    this.menuArquivo.setText("Arquivo");

	    this.subNovo.setText("Novo");
	    this.subNovo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadProduto.this.subNovoActionPerformed(evt);
	    	}
	    });
	    this.menuArquivo.add(this.subNovo);

	    this.subFechar.setText("Fechar");
	    this.subFechar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadProduto.this.subFecharActionPerformed(evt);
	    	}
	    });
	    this.menuArquivo.add(this.subFechar);

	    this.menuBar.add(this.menuArquivo);

	    setJMenuBar(this.menuBar);
	    
	   
	    GroupLayout layout = new GroupLayout(getContentPane());
	    layout.setHorizontalGroup(
	    	layout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	    				.addComponent(panelDadosCadastrais, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addComponent(scrollPaneInformacaoes, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
	    				.addComponent(panelPesquisarProduto, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
	    				.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    layout.setVerticalGroup(
	    	layout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(panelPesquisarProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addGap(15)
	    			.addComponent(panelDadosCadastrais, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(scrollPaneInformacaoes, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap())
	    );
	    getContentPane().setLayout(layout);

	    pack();
	}
	
	
	private void textPesquisarActionPerformed(ActionEvent evt) {
		buttonPesquisarActionPerformed(evt);
	}
	
	
	private void buttonPesquisarActionPerformed(ActionEvent evt) {		
		if (!(this.textPesquisar.getText().equals(""))){
			
			this.dao.pesquisar(this.textPesquisar.getText());
			
			try{
				List<String> listaProduto = this.dao.pesquisar(this.textPesquisar.getText());
					this.textCodigo.setText(listaProduto.get(0));
					this.textNome.setText(listaProduto.get(1));
					this.textPreco.setText(listaProduto.get(2));
					this.comboFornecedor.setSelectedItem(listaProduto.get(3));
					this.textQntEstoque.setText(listaProduto.get(4));
					
					this.buttonAtualizar.setEnabled(false);
					this.buttonSalvar.setEnabled(false);
			} catch (java.lang.IndexOutOfBoundsException indexOutOfBoundsException){
				JOptionPane.showMessageDialog(null, "O produto " + this.textPesquisar.getText() + " não foi encontrado");
			} 
		}
	}
	
	
	private void buttonNovoActionPerformed(ActionEvent evt) {
		this.buttonSalvar.setEnabled(true);
		this.buttonExcluir.setEnabled(false);
		this.buttonAtualizar.setEnabled(false);
		this.buttonAlterar.setEnabled(false);
		
		Visivel();
		this.textCodigo.setText(Long.toString(this.dao.retornaAutoIncrement()));
		this.textNome.setText("");
	    this.textPreco.setText("");
	    this.comboFornecedor.setSelectedItem("");
	    this.textQntEstoque.setText("");
	}
	

	private void buttonAlterarActionPerformed(ActionEvent evt) {		
		if ((this.textCodigo.getText().equals("") || this.textNome.getText().equals("")) || (this.textPreco.getText().equals("")) || (this.comboFornecedor.getSelectedItem().equals("")) || (this.textQntEstoque.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Nenhum produto selecionado!");
		} else{
			Visivel();
			this.buttonAtualizar.setEnabled(true);
			this.buttonExcluir.setEnabled(false);
		}
	}
	
	
	private void buttonExcluirActionPerformed(ActionEvent evt) {		
		if ((this.textCodigo.getText().equals("") || this.textNome.getText().equals("")) || (this.textPreco.getText().equals("")) || (this.comboFornecedor.getSelectedItem().equals("")) || (this.textQntEstoque.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Nenhum produto selecionado!");
		} else{
			Visivel();
			
			Produto produto = new Produto();
	        produto.setCodigo(Integer.parseInt((this.textCodigo.getText())));
	        
			this.dao.excluir(produto);
			
			JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
	        preencherJTable();
	        
	        this.textCodigo.setText("");
			this.textNome.setText("");
		    this.textPreco.setText("");
		    this.comboFornecedor.setSelectedItem("");
		    this.textQntEstoque.setText("");

		    Invisivel(); 
		    
		    this.buttonSalvar.setEnabled(true);
		}
	}
	
	
	private void buttonAtualizarActionPerformed(ActionEvent evt) {		
	    if ((this.textNome.getText().equals("")) || (this.textPreco.getText().equals("")) || (this.comboFornecedor.getSelectedItem().equals("")) || (this.textQntEstoque.getText().equals(""))) {
	        JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
	    } else {
	        Produto produto = new Produto();
	        produto.setCodigo(Integer.parseInt((this.textCodigo.getText())));
	        produto.setNome(this.textNome.getText());
	        produto.setPreco(Float.parseFloat(this.textPreco.getText()));
	        produto.getFornecedor().setCodigo(this.dao.consultarCodigoFornecedor((String)this.comboFornecedor.getSelectedItem()));
	        produto.setQntEstoque(Integer.parseInt((this.textQntEstoque.getText())));
	        
	        
	        this.dao.atualizar(produto);
	        
	        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
	        preencherJTable();
	        
		    this.textCodigo.setText("");
			this.textNome.setText("");
		    this.textPreco.setText("");
		    this.comboFornecedor.setSelectedItem("");
		    this.textQntEstoque.setText("");

		    Invisivel();
		    
		    this.buttonSalvar.setEnabled(true);
			this.buttonExcluir.setEnabled(true);
	    }
	}
	
	
	private void buttonSalvarActionPerformed(ActionEvent evt) {
	    if ((this.textNome.getText().equals("")) || (this.textPreco.getText().equals("")) || (this.comboFornecedor.getSelectedItem().equals("")) || (this.textQntEstoque.getText().equals(""))) {
	        JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
	    } else {
	    	  
	        Produto produto = new Produto();
	        produto.setCodigo(Integer.parseInt((this.textCodigo.getText())));
	        produto.setNome(this.textNome.getText());
	        produto.setPreco(Float.parseFloat(this.textPreco.getText()));
	        produto.getFornecedor().setCodigo(this.dao.consultarCodigoFornecedor((String)this.comboFornecedor.getSelectedItem()));
	        produto.setQntEstoque(Integer.parseInt((this.textQntEstoque.getText())));
	        
	        
	        this.dao.inserir(produto);
	        
	        JOptionPane.showMessageDialog(null, "O cadastro do produto:\n" + produto.getCodigo() + " - " + this.textNome.getText() + "\nFoi realizado com sucesso!");
	        preencherJTable();
	        
	        this.textCodigo.setText("");
			this.textNome.setText("");
		    this.textPreco.setText("");
		    this.comboFornecedor.setSelectedItem("");
		    this.textQntEstoque.setText("");
		    
		    this.buttonExcluir.setEnabled(true);
			this.buttonAtualizar.setEnabled(true);
			this.buttonAlterar.setEnabled(true);
			
			Invisivel();
	    }
	}
	
	
	private void buttonFecharActionPerformed(ActionEvent evt) {
	    dispose();
	    JanPrincipal janP = new JanPrincipal();
	    janP.setVisible(true);
	}
	
	
	private void subNovoActionPerformed(ActionEvent evt) {
		buttonNovoActionPerformed(evt);
	}
	
	
	private void subFecharActionPerformed(ActionEvent evt) {
	    dispose();
	    JanPrincipal janP = new JanPrincipal();
	    janP.setVisible(true);
	}
}
