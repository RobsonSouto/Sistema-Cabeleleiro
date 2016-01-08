package ControleEstoqueGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import ControleEstoqueBanco.VendaDAO;
import ControleEstoqueBean.Venda;

public class JanRelFinanceiro extends JFrame {
	MaskFormatter maskData;
	Venda venda = new Venda();
	VendaDAO dao = new VendaDAO();
	String inicioData = null;
	String tipo = "todas";
	private JTextField textData;
	private JTextField textCliente;
	private JButton buttonPesquisar;
	private JButton buttonFechar;
	private JLabel labelData;
	private JLabel labelCliente;
	private JLabel labelTotalVenda;
	private JPanel panelListaVendas;
	private JPanel panelConsultaVenda;
	private JPanel panelTotalVendas;
	private JScrollPane scrollPaneInformacaoes;
	private JTable tableInformacaoes;

	public JanRelFinanceiro() {
		super("Relatório Financeiro");
		Componentes();
		setLocationRelativeTo(null);
		this.setResizable(false);

		preencherJTable(tipo);

	}

	public void limpaTabela() {
		DefaultTableModel tabela = (DefaultTableModel) this.tableInformacaoes
				.getModel();
		if (tabela.getRowCount() > 0) {
			for (int i = tabela.getRowCount() - 1; i >= 0; i--) {
				tabela.removeRow(i);
			}
		}
	}

	public void preencherJTable(String tipo) {
		this.tableInformacaoes.getColumnModel().getColumn(0)
				.setPreferredWidth(50);
		this.tableInformacaoes.getColumnModel().getColumn(1)
				.setPreferredWidth(150);
		this.tableInformacaoes.getColumnModel().getColumn(2)
				.setPreferredWidth(50);

		DefaultTableModel tabela = (DefaultTableModel) this.tableInformacaoes
				.getModel();
		tabela.setNumRows(0);
		
		float totalVenda = 0;

		
		if (this.tipo.equals("dataCliente")) {
			List<Venda> listaVenda = this.dao.consultarVendas(this.venda);

			for (Venda venda : listaVenda) {
				tabela.addRow(new Object[] { venda.getData(),
						venda.getCliente().getNome(), venda.getTotalVenda() });
				totalVenda = totalVenda + venda.getTotalVenda();
			}
			
			this.labelTotalVenda.setText("R$ " + totalVenda);
		}

		
		if (this.tipo.equals("todas")) {
			List<Venda> listaVenda = this.dao.consultarTodasVendas();

			for (Venda venda : listaVenda) {
				tabela.addRow(new Object[] { venda.getData(),
						venda.getCliente().getNome(), venda.getTotalVenda() });
				totalVenda = totalVenda + venda.getTotalVenda();
			}
			
			this.labelTotalVenda.setText("R$ " + totalVenda);
		}

		
		if (this.tipo.equals("data")) {
			List<Venda> listaVenda = this.dao.consultaVendaData(this.venda);

			for (Venda venda : listaVenda) {
				tabela.addRow(new Object[] { venda.getData(),
						venda.getCliente().getNome(), venda.getTotalVenda() });
				totalVenda = totalVenda + venda.getTotalVenda();
			}
			
			this.labelTotalVenda.setText("R$ " + totalVenda);
		}
		
		
		if (this.tipo.equals("cliente")) {
			List<Venda> listaVenda = this.dao.consultaVendaCliente(this.venda);

			for (Venda venda : listaVenda) {
				tabela.addRow(new Object[] { venda.getData(),
						venda.getCliente().getNome(), venda.getTotalVenda() });
				totalVenda = totalVenda + venda.getTotalVenda();
			}
			
			this.labelTotalVenda.setText("R$ " + totalVenda);
		}
	}

	private void Componentes() {
		try {
			this.maskData = new MaskFormatter("##/##/####");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Nao foi possivel inserir a mascara");
		}
		this.textData = new JFormattedTextField(this.maskData);
		this.textCliente = new JTextField();
		this.buttonPesquisar = new JButton();
		this.buttonFechar = new JButton();
		this.labelData = new JLabel();
		this.labelCliente = new JLabel();
		this.labelTotalVenda = new JLabel();
		this.panelListaVendas = new JPanel();
		this.panelConsultaVenda = new JPanel();
		this.panelTotalVendas = new JPanel();
		this.scrollPaneInformacaoes = new JScrollPane();
		scrollPaneInformacaoes
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.tableInformacaoes = new JTable();
		tableInformacaoes.setColumnSelectionAllowed(true);
		this.inicioData = this.textData.getText();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		
		this.panelListaVendas.setBorder(BorderFactory
				.createTitledBorder("Itens da Venda"));

		
		this.labelData.setFont(new Font("Tahoma", 1, 11));
		this.labelData.setText("Data:");
		this.textData.setFont(new Font("Tahoma", 1, 11));

		
		this.labelCliente.setFont(new Font("Tahoma", 1, 11));
		this.labelCliente.setText("Cliente:");

		
		this.buttonPesquisar.setBackground(new Color(255, 255, 255));
		this.buttonPesquisar.setIcon(new ImageIcon(getClass().getResource(
				"/ControleEstoqueGUI/localizar.gif")));
		this.buttonPesquisar.setFont(new Font("Tahoma", 1, 11));
		this.buttonPesquisar.setText("Pesquisar");
		this.buttonPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JanRelFinanceiro.this.buttonPesquisarActionPerformed(evt);
			}
		});

		GroupLayout gl_panelListaVendas = new GroupLayout(panelListaVendas);
		gl_panelListaVendas
				.setHorizontalGroup(gl_panelListaVendas
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelListaVendas
										.createSequentialGroup()
										.addGap(24)
										.addGroup(
												gl_panelListaVendas
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addGroup(
																gl_panelListaVendas
																		.createSequentialGroup()
																		.addComponent(
																				scrollPaneInformacaoes,
																				GroupLayout.PREFERRED_SIZE,
																				449,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())
														.addGroup(
																gl_panelListaVendas
																		.createSequentialGroup()
																		.addComponent(
																				panelTotalVendas,
																				GroupLayout.PREFERRED_SIZE,
																				241,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				buttonFechar)
																		.addGap(23)))));
		gl_panelListaVendas
				.setVerticalGroup(gl_panelListaVendas
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelListaVendas
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(scrollPaneInformacaoes,
												GroupLayout.PREFERRED_SIZE,
												269, GroupLayout.PREFERRED_SIZE)
										.addGroup(
												gl_panelListaVendas
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panelListaVendas
																		.createSequentialGroup()
																		.addGap(34)
																		.addComponent(
																				buttonFechar,
																				GroupLayout.PREFERRED_SIZE,
																				41,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panelListaVendas
																		.createSequentialGroup()
																		.addGap(18)
																		.addComponent(
																				panelTotalVendas,
																				GroupLayout.PREFERRED_SIZE,
																				69,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		panelListaVendas.setLayout(gl_panelListaVendas);

		
		this.panelConsultaVenda.setBorder(BorderFactory
				.createTitledBorder("Listagem Venda"));

		
		this.buttonFechar.setBackground(new Color(255, 255, 255));
		this.buttonFechar.setIcon(new ImageIcon(getClass().getResource(
				"/ControleEstoqueGUI/fechar.gif")));
		this.buttonFechar.setText("Fechar");
		this.buttonFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JanRelFinanceiro.this.buttonFecharActionPerformed(evt);
			}
		});

		
		this.panelTotalVendas.setBorder(BorderFactory
				.createTitledBorder("Total Venda"));
		this.labelTotalVenda.setFont(new Font("Tahoma", Font.BOLD, 25));
		panelTotalVendas.add(labelTotalVenda);

		GroupLayout gl_panelConsultaVenda = new GroupLayout(
				this.panelConsultaVenda);
		gl_panelConsultaVenda
				.setHorizontalGroup(gl_panelConsultaVenda
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelConsultaVenda
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panelConsultaVenda
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(
																panelListaVendas,
																GroupLayout.PREFERRED_SIZE,
																508,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_panelConsultaVenda
																		.createSequentialGroup()
																		.addComponent(
																				labelData,
																				GroupLayout.PREFERRED_SIZE,
																				31,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				textData,
																				GroupLayout.PREFERRED_SIZE,
																				95,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				labelCliente,
																				GroupLayout.PREFERRED_SIZE,
																				45,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				textCliente)
																		.addGap(18)
																		.addComponent(
																				buttonPesquisar,
																				GroupLayout.PREFERRED_SIZE,
																				144,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)))
										.addGap(10)));
		gl_panelConsultaVenda
				.setVerticalGroup(gl_panelConsultaVenda
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelConsultaVenda
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panelConsultaVenda
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(labelData)
														.addComponent(
																textData,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																labelCliente)
														.addComponent(
																textCliente,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																buttonPesquisar,
																GroupLayout.PREFERRED_SIZE,
																33,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(panelListaVendas,
												GroupLayout.PREFERRED_SIZE,
												401, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		this.panelConsultaVenda.setLayout(gl_panelConsultaVenda);

		// ---------- TABLE INFORMAÇÕES ----------- //
		this.tableInformacaoes.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null }, { null, null, null },
				{ null, null, null } }, new String[] { "Data", "Cliente",
				"Valor" }));

		this.tableInformacaoes.setFocusable(false);
		this.scrollPaneInformacaoes.setViewportView(this.tableInformacaoes);

		
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(panelConsultaVenda,
										GroupLayout.PREFERRED_SIZE, 540,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(19, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(panelConsultaVenda,
										GroupLayout.PREFERRED_SIZE, 485,
										Short.MAX_VALUE).addContainerGap()));
		getContentPane().setLayout(layout);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 531) / 2, (screenSize.height - 545) / 2,
				567, 545);
	}

	
	private void buttonPesquisarActionPerformed(ActionEvent evt) {
		this.venda.setData(this.textData.getText());
		this.venda.getCliente().setCodigo(
				this.dao.consultarCodigoCliente(this.textCliente.getText()));

		
		if (!(this.textData.getText().equals(this.inicioData))
				&& (!(this.textCliente.getText().equals("")))) {
			this.tipo = "dataCliente";

		}

		
		if ((this.textData.getText().equals(this.inicioData))
				&& ((this.textCliente.getText().equals("")))) {
			this.tipo = "todas";
		}

		
		if (!(this.textData.getText().equals(this.inicioData))
				&& (this.textCliente.getText().equals(""))) {

			this.tipo = "data";
		}

		
		if (!(this.textCliente.getText().equals(""))
				&& (this.textData.getText().equals(this.inicioData))) {
			this.tipo = "cliente";
		}

		preencherJTable(this.tipo);

	}

	
	private void buttonFecharActionPerformed(ActionEvent evt) {
		dispose();
		JanPrincipal janP = new JanPrincipal();
		janP.setVisible(true);
	}
}