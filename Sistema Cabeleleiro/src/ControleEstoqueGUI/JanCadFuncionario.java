package ControleEstoqueGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;

import ControleEstoqueBanco.FuncionarioDAO;
import ControleEstoqueBean.Funcionario;

public class JanCadFuncionario extends JFrame {
	String inicioTelefone = null;
	MaskFormatter maskTelefone;
	private JTextField textPesquisar;
	private JTextField textCodigo;
	private JTextField textNome;
	private JTextField textTelefone;
	private JTextField textEndereco;
	private JTextField textFuncao;
	private JTextField textSetor;
	private JButton buttonPesquisar;
	private JButton buttonNovo;
	private JButton buttonAlterar;
	private JButton buttonExcluir;
	private JButton buttonAtualizar;
	private JButton buttonSalvar;
	private JButton buttonFechar;
	private JLabel labelCodigo;
	private JLabel labelNome;
	private JLabel labelTelefone;
	private JLabel labelEndereco;
	private JLabel labelFuncao;
	private JLabel labelSetor;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem subNovo;
	private JMenuItem subFechar;
	private JPanel panelPesquisarFuncionario;
	private JPanel panelDadosCadastrais;
	private JPanel panelBotoes;
	private JScrollPane scrollPaneInformacaoes;
	private JTable tableInformacaoes;

	public JanCadFuncionario() {
		super("Cadastro - Funcionario");
		Componentes();
		setLocationRelativeTo(null);
		this.setResizable(false);

		preencherJTable();
		Invisivel();
	}
	
	public void Invisivel() {
		this.textNome.setEditable(false);
		this.textTelefone.setEditable(false);
		this.textEndereco.setEditable(false);
		this.textFuncao.setEditable(false);
		this.textSetor.setEditable(false);
		
	}

	public void Visivel() {
		this.textNome.setEditable(true);
		this.textTelefone.setEditable(true);
		this.textEndereco.setEditable(true);
		this.textFuncao.setEditable(true);
		this.textSetor.setEditable(true);
	}

	public void preencherJTable() {
		this.tableInformacaoes.getColumnModel().getColumn(0).setPreferredWidth(5);
		this.tableInformacaoes.getColumnModel().getColumn(1).setPreferredWidth(50);
		this.tableInformacaoes.getColumnModel().getColumn(2).setPreferredWidth(100);
		this.tableInformacaoes.getColumnModel().getColumn(3).setPreferredWidth(15);
		this.tableInformacaoes.getColumnModel().getColumn(4).setPreferredWidth(30);
		this.tableInformacaoes.getColumnModel().getColumn(5).setPreferredWidth(30);
		
		DefaultTableModel tabela = (DefaultTableModel)this.tableInformacaoes.getModel();
		tabela.setNumRows(0);
		
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> listaFuncionario = dao.consultar();
		
		for (Funcionario  funcionario: listaFuncionario) {
			tabela.addRow(new Object[] { funcionario.getCodigo(), funcionario.getNome(), funcionario.getEndereco(), funcionario.getTelefone(), funcionario.getFuncao(), funcionario.getSetor() });
		}
	}
	
	private void Componentes() {
		try {
		      this.maskTelefone = new MaskFormatter("(##)####-####");
		} catch (Exception e) {
		      JOptionPane.showMessageDialog(null, "Nao foi possivel inserir a mascara");
		}
		this.textPesquisar = new JTextField();
		this.textCodigo = new JTextField();
		this.textNome = new JTextField();
		this.textTelefone = new JFormattedTextField(this.maskTelefone);
		this.textEndereco = new JTextField();
		this.textFuncao = new JTextField();
		this.textSetor = new JTextField();
		this.buttonPesquisar = new JButton();
		this.buttonNovo = new JButton();
		this.buttonAlterar = new JButton();
		this.buttonExcluir = new JButton();
		this.buttonAtualizar = new JButton();
		this.buttonSalvar = new JButton();
		this.buttonFechar = new JButton();
		this.labelCodigo = new JLabel();
		this.labelNome = new JLabel();
		this.labelTelefone = new JLabel();
		this.labelEndereco = new JLabel();
		this.labelFuncao = new JLabel();
		this.labelSetor = new JLabel();
		this.menuBar = new JMenuBar();
		this.menuArquivo = new JMenu();
		this.subNovo = new JMenuItem();
		this.subFechar = new JMenuItem();
		this.panelPesquisarFuncionario = new JPanel();
		this.panelDadosCadastrais = new JPanel();
		this.panelBotoes = new JPanel();
		this.scrollPaneInformacaoes = new JScrollPane();
		scrollPaneInformacaoes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.tableInformacaoes = new JTable();
		tableInformacaoes.setColumnSelectionAllowed(true);
		this.inicioTelefone = this.textTelefone.getText();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	    this.panelPesquisarFuncionario.setBorder(BorderFactory.createTitledBorder("Pesquisar Funcionarios"));
	    
	    this.textPesquisar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadFuncionario.this.textPesquisarActionPerformed(evt);
	    	}
	    });
	    this.buttonPesquisar.setBackground(new Color(255, 255, 255));
	    this.buttonPesquisar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/localizar.gif")));
	    this.buttonPesquisar.setText("Pesquisar");
	    this.buttonPesquisar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadFuncionario.this.buttonPesquisarActionPerformed(evt);
	    	}
	    });
	    GroupLayout layoutPesquisarFuncionario = new GroupLayout(this.panelPesquisarFuncionario);
	    layoutPesquisarFuncionario.setHorizontalGroup(
	    	layoutPesquisarFuncionario.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(layoutPesquisarFuncionario.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(textPesquisar, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
	    			.addGap(18)
	    			.addComponent(buttonPesquisar, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    layoutPesquisarFuncionario.setVerticalGroup(
	    	layoutPesquisarFuncionario.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutPesquisarFuncionario.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutPesquisarFuncionario.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(textPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(buttonPesquisar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    this.panelPesquisarFuncionario.setLayout(layoutPesquisarFuncionario);
		
		
		this.panelDadosCadastrais.setBorder(BorderFactory.createTitledBorder("Dados Cadastrais"));

		
		this.labelCodigo.setText("Código:");
		this.textCodigo.setFont(new Font("Tahoma", 1, 11));
		this.textCodigo.setEnabled(false);
		
		
		this.labelNome.setText("Nome:");
		this.textNome.setFont(new Font("Tahoma", 1, 11));
				
		
		this.labelTelefone.setText("Telefone:");
		this.textTelefone.setFont(new Font("Tahoma", 1, 11));
				
		
		this.labelEndereco.setText("Endereço:");
		this.textEndereco.setFont(new Font("Tahoma", 1, 11));
		
		
		this.labelFuncao.setText("Função:");
		this.textFuncao.setFont(new Font("Tahoma", 1, 11));
		
		
		this.labelSetor.setText("Setor:");
		this.textSetor.setFont(new Font("Tahoma", 1, 11));

	    GroupLayout layoutDadosCadastrais = new GroupLayout(this.panelDadosCadastrais);
	    layoutDadosCadastrais.setHorizontalGroup(
	    	layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    						.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    							.addComponent(labelTelefone, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(textTelefone, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
	    							.addGap(18))
	    						.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    							.addComponent(labelCodigo)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(textCodigo, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
	    							.addGap(18)
	    							.addComponent(labelNome)
	    							.addGap(10)))
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.TRAILING)
	    						.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    							.addComponent(labelEndereco, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(textEndereco, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
	    						.addComponent(textNome, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)))
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addComponent(labelFuncao, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(textFuncao, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
	    					.addGap(24)
	    					.addComponent(labelSetor, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(textSetor, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
	    			.addContainerGap())
	    );
	    layoutDadosCadastrais.setVerticalGroup(
	    	layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(labelCodigo)
	    				.addComponent(textCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(textNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(labelNome))
	    			.addGap(18)
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(labelTelefone)
	    				.addComponent(textEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(textTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(labelEndereco))
	    			.addGap(18)
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    				.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.BASELINE)
	    					.addComponent(textSetor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addComponent(labelSetor)
	    					.addComponent(labelFuncao))
	    				.addComponent(textFuncao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(5))
	    );
	    this.panelDadosCadastrais.setLayout(layoutDadosCadastrais);

	    
	    this.panelBotoes.setBorder(BorderFactory.createCompoundBorder());	    
	    
	    
	    this.buttonNovo.setBackground(new Color(255, 255, 255));
	    this.buttonNovo.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/novo_registro.gif")));
	    this.buttonNovo.setText("Novo");
	    this.buttonNovo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadFuncionario.this.buttonNovoActionPerformed(evt);
	    	}
	    });
	    this.buttonAlterar.setBackground(new Color(255, 255, 255));
	    this.buttonAlterar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/alterar_registro.gif")));
	    this.buttonAlterar.setText("Alterar");
	    this.buttonAlterar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadFuncionario.this.buttonAlterarActionPerformed(evt);
	    	}
	    });
	    
	    this.buttonExcluir.setBackground(new Color(255, 255, 255));
	    this.buttonExcluir.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/excluir.png")));
	    this.buttonExcluir.setText("Excluir");
	    this.buttonExcluir.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadFuncionario.this.buttonExcluirActionPerformed(evt);
	    	}
	    });
	    
	    this.buttonAtualizar.setBackground(new Color(255, 255, 255));
	    this.buttonAtualizar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/atualizar.png")));
	    this.buttonAtualizar.setText("Atualizar");
	    this.buttonAtualizar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadFuncionario.this.buttonAtualizarActionPerformed(evt);
	    	}
	    });
	    this.buttonSalvar.setBackground(new Color(255, 255, 255));
	    this.buttonSalvar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/gravar_registro.gif")));
	    this.buttonSalvar.setText("Salvar");
	    this.buttonSalvar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadFuncionario.this.buttonSalvarActionPerformed(evt);
	    	}
	    });
	    this.buttonFechar.setBackground(new Color(255, 255, 255));
	    this.buttonFechar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/sair.gif")));
	    this.buttonFechar.setText("Fechar");
	    this.buttonFechar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadFuncionario.this.buttonFecharActionPerformed(evt);
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

	  
	    this.tableInformacaoes.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null  }, { null, null, null, null  }, { null, null, null, null  }, { null, null, null, null  }, { null, null, null, null  } }, new String[] { "Código", "Nome", "Endereço", "Telefone", "Função", "Setor" }));
	    
	    this.tableInformacaoes.setFocusable(false);
	    this.scrollPaneInformacaoes.setViewportView(this.tableInformacaoes);
	    
	    	    
	    this.menuArquivo.setText("Arquivo");

	    this.subNovo.setText("Novo");
	    this.subNovo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadFuncionario.this.subNovoActionPerformed(evt);
	    	}
	    });
	    this.menuArquivo.add(this.subNovo);

	    this.subFechar.setText("Fechar");
	    this.subFechar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadFuncionario.this.subFecharActionPerformed(evt);
	    	}
	    });
	    this.menuArquivo.add(this.subFechar);

	    this.menuBar.add(this.menuArquivo);

	    setJMenuBar(this.menuBar);
	    
	   
	    GroupLayout layout = new GroupLayout(getContentPane());
	    layout.setHorizontalGroup(
	    	layout.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	    				.addComponent(panelDadosCadastrais, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
	    				.addComponent(scrollPaneInformacaoes, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
	    				.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
	    				.addComponent(panelPesquisarFuncionario, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    layout.setVerticalGroup(
	    	layout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(panelPesquisarFuncionario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addGap(15)
	    			.addComponent(panelDadosCadastrais, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(scrollPaneInformacaoes, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
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
			
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.pesquisar(this.textPesquisar.getText());
			
			try{
				List<String> listaFuncionario = dao.pesquisar(this.textPesquisar.getText());
					this.textCodigo.setText(listaFuncionario.get(0));
					this.textNome.setText(listaFuncionario.get(1));
					this.textTelefone.setText(listaFuncionario.get(2));
					this.textEndereco.setText(listaFuncionario.get(3));
					this.textFuncao.setText(listaFuncionario.get(4));
					this.textSetor.setText(listaFuncionario.get(5));
					
					this.buttonAtualizar.setEnabled(false);
					this.buttonSalvar.setEnabled(false);
			} catch (java.lang.IndexOutOfBoundsException indexOutOfBoundsException){
				JOptionPane.showMessageDialog(null, "O funcionario " + this.textPesquisar.getText() + " não foi encontrado");
			} 
		}
	}
	
	
	private void buttonNovoActionPerformed(ActionEvent evt) {
		this.buttonSalvar.setEnabled(true);
		this.buttonExcluir.setEnabled(false);
		this.buttonAtualizar.setEnabled(false);
		this.buttonAlterar.setEnabled(false);
		
		Visivel();
		FuncionarioDAO dao = new FuncionarioDAO();
		this.textCodigo.setText(Long.toString(dao.retornaAutoIncrement()));
		this.textNome.setText("");
	    this.textTelefone.setText("");
	    this.textEndereco.setText("");
	    this.textFuncao.setText("");
	    this.textSetor.setText("");
	}
	
	
	private void buttonAlterarActionPerformed(ActionEvent evt) {
		if ((this.textCodigo.getText().equals("") || this.textNome.getText().equals("")) || (this.textTelefone.getText().equals(this.inicioTelefone)) || (this.textEndereco.getText().equals("")) || (this.textFuncao.getText().equals("")) || (this.textSetor.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Nenhum funcionario selecionado!");
		} else{
			Visivel();
			this.buttonAtualizar.setEnabled(true);
			this.buttonExcluir.setEnabled(false);
		}
	}
	
	
	private void buttonExcluirActionPerformed(ActionEvent evt) {
		if ((this.textCodigo.getText().equals("") || this.textNome.getText().equals("")) || (this.textTelefone.getText().equals(this.inicioTelefone)) || (this.textEndereco.getText().equals("")) || (this.textFuncao.getText().equals("")) || (this.textSetor.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Nenhum funcionario selecionado!");
		} else{
			Visivel();
			
			Funcionario funcionario = new Funcionario();
	        funcionario.setCodigo(Integer.parseInt((this.textCodigo.getText())));
	        
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.excluir(funcionario);
			
			JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
	        preencherJTable();
	        
	        this.textCodigo.setText("");
			this.textNome.setText("");
		    this.textTelefone.setText("");
		    this.textEndereco.setText("");
		    this.textFuncao.setText("");
		    this.textSetor.setText("");
		    
		    Invisivel();
		    
		    this.buttonSalvar.setEnabled(true);
		}
	}
	
	
	private void buttonAtualizarActionPerformed(ActionEvent evt) {
	    if ((this.textNome.getText().equals("")) || (this.textTelefone.getText().equals(this.inicioTelefone)) || (this.textEndereco.getText().equals("")) || (this.textFuncao.getText().equals("")) || (this.textSetor.getText().equals(""))) {
	        JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
	    } else {
	    	  
	        Funcionario funcionario = new Funcionario();
	        funcionario.setCodigo(Integer.parseInt((this.textCodigo.getText())));
	        funcionario.setNome(this.textNome.getText());
	        funcionario.setTelefone(this.textTelefone.getText());
	        funcionario.setEndereco(this.textEndereco.getText());
	        funcionario.setFuncao(this.textFuncao.getText());
	        funcionario.setSetor(this.textSetor.getText());	        
	        
	        
	        FuncionarioDAO dao = new FuncionarioDAO();
	        dao.atualizar(funcionario);
	        
	        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
	        preencherJTable();
	        
		    this.textCodigo.setText("");
			this.textNome.setText("");
		    this.textTelefone.setText("");
		    this.textEndereco.setText("");
		    this.textFuncao.setText("");
		    this.textSetor.setText("");	

		    Invisivel();
		    
		    this.buttonSalvar.setEnabled(true);
			this.buttonExcluir.setEnabled(true);
	    }
	}
	
	
	private void buttonSalvarActionPerformed(ActionEvent evt) {
	    if ((this.textNome.getText().equals("")) || (this.textTelefone.getText().equals(this.inicioTelefone)) || (this.textEndereco.getText().equals("")) || (this.textFuncao.getText().equals("")) || (this.textSetor.getText().equals(""))) {
	        JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
	    } else {
	    	  
	        Funcionario funcionario = new Funcionario();
	        funcionario.setNome(this.textNome.getText());
	        funcionario.setTelefone(this.textTelefone.getText());
	        funcionario.setEndereco(this.textEndereco.getText());
	        funcionario.setFuncao(this.textFuncao.getText());
	        funcionario.setSetor(this.textSetor.getText());
	        
	       
	        FuncionarioDAO dao = new FuncionarioDAO();
	        dao.inserir(funcionario);
	        
	        JOptionPane.showMessageDialog(null, "O cadastro do funcionario:\n" + funcionario.getCodigo() + " - " + this.textNome.getText() + "\nFoi realizado com sucesso!");
	        preencherJTable();
	        
	        this.textCodigo.setText("");
			this.textNome.setText("");
		    this.textTelefone.setText("");
		    this.textEndereco.setText("");
		    this.textFuncao.setText("");
		    this.textSetor.setText("");
		    
			this.buttonExcluir.setEnabled(true);
			this.buttonAtualizar.setEnabled(true);
			this.buttonAlterar.setEnabled(true);
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
