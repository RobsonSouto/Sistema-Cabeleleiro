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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import ControleEstoqueBanco.LoginDAO;
import ControleEstoqueBanco.UsuarioDAO;
import ControleEstoqueBean.Usuario;

public class JanCadUsuario extends JFrame {
	private JTextField textPesquisar;
	private JTextField textCodigo;
	private JTextField textNome;
	private JPasswordField passwordSenha;
	private JPasswordField passwordRepetirSenha;
	private JButton buttonPesquisar;
	private JButton buttonNovo;
	private JButton buttonAlterar;
	private JButton buttonExcluir;
	private JButton buttonAtualizar;
	private JButton buttonSalvar;
	private JButton buttonFechar;
	private JLabel labelCodigo;
	private JLabel labelNome;
	private JLabel labelSenha;
	private JLabel labelRepetirSenha;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem subNovo;
	private JMenuItem subFechar;
	private JPanel panelPesquisarUsuario;
	private JPanel panelDadosCadastrais;
	private JPanel panelBotoes;
	private JScrollPane scrollPaneInformacaoes;
	private JTable tableInformacaoes;

	public JanCadUsuario() {
		super("Cadastro - Usuário");
		Componentes();
		setLocationRelativeTo(null);
		this.setResizable(false);

		preencherJTable();
		Invisivel();
	}
	
	public void Invisivel() {
		this.textNome.setEditable(false);
		this.passwordSenha.setEditable(false);
		this.passwordRepetirSenha.setEditable(false);
	}

	public void Visivel() {
		this.textNome.setEditable(true);
		this.passwordSenha.setEditable(true);
		this.passwordRepetirSenha.setEditable(true);
	}

	public void preencherJTable() {
		this.tableInformacaoes.getColumnModel().getColumn(0).setPreferredWidth(5);
		this.tableInformacaoes.getColumnModel().getColumn(1).setPreferredWidth(150);
		this.tableInformacaoes.getColumnModel().getColumn(2).setPreferredWidth(10);
		
		DefaultTableModel tabela = (DefaultTableModel)this.tableInformacaoes.getModel();
		tabela.setNumRows(0);
		
		UsuarioDAO dao = new UsuarioDAO();
		LoginDAO daoLogin = new LoginDAO();
		List<Usuario> listaUsuario = dao.consultar();
		
		for (Usuario  usuario: listaUsuario) {
			tabela.addRow(new Object[] { usuario.getCodigo(), usuario.getNome(), usuario.getSenha() });
		}
	}
	
	private void Componentes() {
		this.textPesquisar = new JTextField();
		this.textCodigo = new JTextField();
		this.textNome = new JTextField();
		this.passwordSenha = new JPasswordField();
		this.passwordRepetirSenha = new JPasswordField();
		this.buttonPesquisar = new JButton();
		this.buttonNovo = new JButton();
		this.buttonAlterar = new JButton();
		this.buttonExcluir = new JButton();
		this.buttonAtualizar = new JButton();
		this.buttonSalvar = new JButton();
		this.buttonFechar = new JButton();
		this.labelCodigo = new JLabel();
		this.labelNome = new JLabel();
		this.labelSenha = new JLabel();
		this.labelRepetirSenha = new JLabel();
		this.menuBar = new JMenuBar();
		this.menuArquivo = new JMenu();
		this.subNovo = new JMenuItem();
		this.subFechar = new JMenuItem();
		this.panelPesquisarUsuario = new JPanel();
		this.panelDadosCadastrais = new JPanel();
		this.panelBotoes = new JPanel();
		this.scrollPaneInformacaoes = new JScrollPane();
		scrollPaneInformacaoes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.tableInformacaoes = new JTable();
		tableInformacaoes.setColumnSelectionAllowed(true);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	    this.panelPesquisarUsuario.setBorder(BorderFactory.createTitledBorder("Pesquisar Usuários"));
	    
	    this.textPesquisar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadUsuario.this.textPesquisarActionPerformed(evt);
	    	}
	    });
	    this.buttonPesquisar.setBackground(new Color(255, 255, 255));
	    this.buttonPesquisar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/localizar.gif")));
	    this.buttonPesquisar.setText("Pesquisar");
	    this.buttonPesquisar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadUsuario.this.buttonPesquisarActionPerformed(evt);
	    	}
	    });
	    GroupLayout layoutPesquisarUsuario = new GroupLayout(this.panelPesquisarUsuario);
	    layoutPesquisarUsuario.setHorizontalGroup(
	    	layoutPesquisarUsuario.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(layoutPesquisarUsuario.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(textPesquisar, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
	    			.addGap(18)
	    			.addComponent(buttonPesquisar, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    layoutPesquisarUsuario.setVerticalGroup(
	    	layoutPesquisarUsuario.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutPesquisarUsuario.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutPesquisarUsuario.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(textPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(buttonPesquisar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    this.panelPesquisarUsuario.setLayout(layoutPesquisarUsuario);
		
		
		this.panelDadosCadastrais.setBorder(BorderFactory.createTitledBorder("Dados Cadastrais"));

		
		this.labelCodigo.setText("Código:");
		this.textCodigo.setFont(new Font("Tahoma", 1, 11));
		this.textCodigo.setEnabled(false);
		
		
		this.labelNome.setText("Nome:");
		this.textNome.setFont(new Font("Tahoma", 1, 11));
				
		
		this.labelSenha.setText("Senha:");
		this.passwordSenha.setFont(new Font("Tahoma", 1, 11));
		
	
		this.labelRepetirSenha.setText("Repetir senha:");
		this.passwordRepetirSenha.setFont(new Font("Tahoma", 1, 11));
	    
	    JLabel labelRepetirSenha = new JLabel();
	    labelRepetirSenha.setText("Repetir senha:");

	    GroupLayout layoutDadosCadastrais = new GroupLayout(this.panelDadosCadastrais);
	    layoutDadosCadastrais.setHorizontalGroup(
	    	layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING, false)
	    				.addComponent(labelSenha, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addComponent(labelCodigo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addComponent(textCodigo, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
	    					.addGap(18)
	    					.addComponent(labelNome))
	    				.addComponent(passwordSenha, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
	    			.addGap(10)
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addComponent(labelRepetirSenha)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(passwordRepetirSenha, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(textNome, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
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
	    				.addComponent(labelSenha)
	    				.addComponent(passwordSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(passwordRepetirSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(labelRepetirSenha)))
	    );
	    this.panelDadosCadastrais.setLayout(layoutDadosCadastrais);

	    
	    this.panelBotoes.setBorder(BorderFactory.createCompoundBorder());	    
	    
	   
	    this.buttonNovo.setBackground(new Color(255, 255, 255));
	    this.buttonNovo.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/novo_registro.gif")));
	    this.buttonNovo.setText("Novo");
	    this.buttonNovo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadUsuario.this.buttonNovoActionPerformed(evt);
	    	}
	    });
	    this.buttonAlterar.setBackground(new Color(255, 255, 255));
	    this.buttonAlterar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/alterar_registro.gif")));
	    this.buttonAlterar.setText("Alterar");
	    this.buttonAlterar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadUsuario.this.buttonAlterarActionPerformed(evt);
	    	}
	    });
	    
	    this.buttonExcluir.setBackground(new Color(255, 255, 255));
	    this.buttonExcluir.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/excluir.png")));
	    this.buttonExcluir.setText("Excluir");
	    this.buttonExcluir.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadUsuario.this.buttonExcluirActionPerformed(evt);
	    	}
	    });
	    
	    this.buttonAtualizar.setBackground(new Color(255, 255, 255));
	    this.buttonAtualizar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/atualizar.png")));
	    this.buttonAtualizar.setText("Atualizar");
	    this.buttonAtualizar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadUsuario.this.buttonAtualizarActionPerformed(evt);
	    	}
	    });
	    this.buttonSalvar.setBackground(new Color(255, 255, 255));
	    this.buttonSalvar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/gravar_registro.gif")));
	    this.buttonSalvar.setText("Salvar");
	    this.buttonSalvar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadUsuario.this.buttonSalvarActionPerformed(evt);
	    	}
	    });
	    this.buttonFechar.setBackground(new Color(255, 255, 255));
	    this.buttonFechar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/sair.gif")));
	    this.buttonFechar.setText("Fechar");
	    this.buttonFechar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadUsuario.this.buttonFecharActionPerformed(evt);
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

	   
	    this.tableInformacaoes.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null  }, { null, null, null, null  } }, new String[] { "Código", "Nome", "Senha"}));
	    
	    this.tableInformacaoes.setFocusable(false);
	    this.scrollPaneInformacaoes.setViewportView(this.tableInformacaoes);
	    
	  	    
	    this.menuArquivo.setText("Arquivo");

	    this.subNovo.setText("Novo");
	    this.subNovo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadUsuario.this.subNovoActionPerformed(evt);
	    	}
	    });
	    this.menuArquivo.add(this.subNovo);

	    this.subFechar.setText("Fechar");
	    this.subFechar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadUsuario.this.subFecharActionPerformed(evt);
	    	}
	    });
	    this.menuArquivo.add(this.subFechar);

	    this.menuBar.add(this.menuArquivo);

	    setJMenuBar(this.menuBar);
	    
	    
	    GroupLayout layout = new GroupLayout(getContentPane());
	    layout.setHorizontalGroup(
	    	layout.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(scrollPaneInformacaoes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
	    				.addComponent(panelDadosCadastrais, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
	    				.addComponent(panelPesquisarUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
	    				.addComponent(panelBotoes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    layout.setVerticalGroup(
	    	layout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(panelPesquisarUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addGap(15)
	    			.addComponent(panelDadosCadastrais, GroupLayout.PREFERRED_SIZE, 106, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
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
			
			UsuarioDAO dao = new UsuarioDAO();
			dao.pesquisar(this.textPesquisar.getText());
			
			try{
				List<String> listaUsuario = dao.pesquisar(this.textPesquisar.getText());
					this.textCodigo.setText(listaUsuario.get(0));
					this.textNome.setText(listaUsuario.get(1));
					this.passwordSenha.setText(listaUsuario.get(2));
					
					this.buttonAtualizar.setEnabled(false);
					this.buttonSalvar.setEnabled(false);
			} catch (java.lang.IndexOutOfBoundsException indexOutOfBoundsException){
				JOptionPane.showMessageDialog(null, "O usuario " + this.textPesquisar.getText() + " não foi encontrado");
			} 
		}
	}
	
	
	private void buttonNovoActionPerformed(ActionEvent evt) {
		this.buttonSalvar.setEnabled(true);
		this.buttonExcluir.setEnabled(false);
		this.buttonAtualizar.setEnabled(false);
		this.buttonAlterar.setEnabled(false);
		
		Visivel();
		UsuarioDAO dao = new UsuarioDAO();
		this.textCodigo.setText(Long.toString(dao.retornaAutoIncrement()));
		this.textNome.setText("");
	    this.passwordSenha.setText("");
	    this.passwordRepetirSenha.setText("");
	}
	
	
	private void buttonAlterarActionPerformed(ActionEvent evt) {
		if ((this.textCodigo.getText().equals("") || this.textNome.getText().equals("")) || (this.passwordSenha.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Nenhum usuario selecionado!");
		} else{
			Visivel();
			this.buttonAtualizar.setEnabled(true);
			this.buttonExcluir.setEnabled(false);
		}
	}
	
	
	private void buttonExcluirActionPerformed(ActionEvent evt) {
		if ((this.textCodigo.getText().equals("") || this.textNome.getText().equals("")) || (this.passwordSenha.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Nenhum usuario selecionado!");
		} else{
			Visivel();
			
			Usuario usuario = new Usuario();
	        usuario.setCodigo(Integer.parseInt((this.textCodigo.getText())));
	        
			UsuarioDAO dao = new UsuarioDAO();
			dao.excluir(usuario);
			
			JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
	        preencherJTable();
	        
			this.textCodigo.setText("");
			this.textNome.setText("");
		    this.passwordSenha.setText("");
		    this.passwordRepetirSenha.setText("");

		    Invisivel();
		    
			this.buttonSalvar.setEnabled(true);
		}
	}
	
	
	private void buttonAtualizarActionPerformed(ActionEvent evt) {
		Usuario usuario = new Usuario();
        usuario.setNome(this.textNome.getText());
        
        UsuarioDAO dao = new UsuarioDAO();
        if (dao.nomeUsuario(usuario)){
        	JOptionPane.showMessageDialog(null, "Usuário já cadastrado!");
        	this.textCodigo.setText("");
			this.textNome.setText("");
		    this.passwordSenha.setText("");
		    this.passwordRepetirSenha.setText("");
        } else {
        	if (this.passwordSenha.getText().equals(this.passwordRepetirSenha.getText())) {				
			    if ((this.textNome.getText().equals("")) || (this.passwordSenha.getText().equals(""))) {
			        JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
			    } else {
			    	  
			        usuario.setCodigo(Integer.parseInt((this.textCodigo.getText())));
			        usuario.setNome(this.textNome.getText());
			        usuario.setSenha(this.passwordSenha.getText());
			        
			        
			        dao.atualizar(usuario);
			        
			        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
			        preencherJTable();
			        
				    this.textCodigo.setText("");
					this.textNome.setText("");
				    this.passwordSenha.setText("");
				    this.passwordRepetirSenha.setText("");
			
				    Invisivel();
				    
				    this.buttonSalvar.setEnabled(true);
					this.buttonExcluir.setEnabled(true);
			    }
        	} else {
        		JOptionPane.showMessageDialog(null, "Senhas incompatíveis!");
        		this.passwordSenha.setText("");
			    this.passwordRepetirSenha.setText("");
        	}
        }
	}
	

	private void buttonSalvarActionPerformed(ActionEvent evt) {
		Usuario usuario = new Usuario();
        usuario.setNome(this.textNome.getText());
        
        UsuarioDAO dao = new UsuarioDAO();
        if (dao.nomeUsuario(usuario)){
        	JOptionPane.showMessageDialog(null, "Usuário já cadastrado!");
        	this.textCodigo.setText("");
			this.textNome.setText("");
		    this.passwordSenha.setText("");
		    this.passwordRepetirSenha.setText("");
        } else {
        	if (this.passwordSenha.getText().equals(this.passwordRepetirSenha.getText())) {
			    if ((this.textNome.getText().equals("")) || (this.passwordSenha.getText().equals(""))) {
			        JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
			    } else {
			    	  
			        usuario.setNome(this.textNome.getText());
			        usuario.setSenha(this.passwordSenha.getText());
			        
			       
			        dao.inserir(usuario);
			        
			        JOptionPane.showMessageDialog(null, "O cadastro do usuario:\n" + usuario.getCodigo() + " - " + this.textNome.getText() + "\nFoi realizado com sucesso!");
			        preencherJTable();
			        
				    this.textCodigo.setText("");
					this.textNome.setText("");
				    this.passwordSenha.setText("");
				    this.passwordRepetirSenha.setText("");
				    
					Invisivel();
				    
					this.buttonExcluir.setEnabled(true);
					this.buttonAtualizar.setEnabled(true);
					this.buttonAlterar.setEnabled(true);
			    }
        	} else {
        		JOptionPane.showMessageDialog(null, "Senhas incompatíveis!");
        		this.passwordSenha.setText("");
			    this.passwordRepetirSenha.setText("");
        	}
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
