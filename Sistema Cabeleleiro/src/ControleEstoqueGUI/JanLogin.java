package ControleEstoqueGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import ControleEstoqueBanco.LoginDAO;
import ControleEstoqueBean.Login;

public class JanLogin extends JFrame {

	private JButton buttonEntrar;
	private JLabel labelUsuario;
	private JLabel labelSenha;
	private JTextField textUsuario;
	private JPasswordField passwordSenha;

	public JanLogin() {
		super("Login");
		Componentes();
		setLocationRelativeTo(null);
		this.setResizable(false);
	}

	private void Componentes() {
		this.labelUsuario = new JLabel();
		this.labelSenha = new JLabel();
		this.textUsuario = new JTextField();
		this.passwordSenha = new JPasswordField();
		this.buttonEntrar = new JButton();

		setDefaultCloseOperation(3);

		this.labelUsuario.setText("Usuário:");
		this.labelSenha.setText("Senha:");

		this.buttonEntrar.setText("Entrar");
		this.buttonEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JanLogin.this.buttonLoginActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(34)
																.addGroup(
																		layout.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						labelUsuario,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						labelSenha))
																.addGap(28)
																.addGroup(
																		layout.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						textUsuario,
																						GroupLayout.DEFAULT_SIZE,
																						187,
																						Short.MAX_VALUE)
																				.addComponent(
																						passwordSenha)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(126)
																.addComponent(
																		buttonEntrar,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGap(100)))
								.addGap(30)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(33)
								.addGroup(
										layout.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(labelUsuario)
												.addComponent(textUsuario))
								.addGap(18)
								.addGroup(
										layout.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(labelSenha)
												.addComponent(passwordSenha))
								.addGap(30)
								.addComponent(buttonEntrar,
										GroupLayout.DEFAULT_SIZE, 23,
										Short.MAX_VALUE).addGap(24)));
		getContentPane().setLayout(layout);

		getRootPane().setDefaultButton(buttonEntrar);
		pack();
	}

	private void buttonLoginActionPerformed(ActionEvent evt) {

		if ((this.textUsuario.getText().equals("")) || (this.passwordSenha.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
		} else{
			
			Login login = new Login();
			login.setUsuario(this.textUsuario.getText());
			login.setSenha(this.passwordSenha.getText());

			LoginDAO dao = new LoginDAO();
			if (dao.login(login) == true) {
				JanPrincipal janela = new JanPrincipal();
				janela.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou senha inválido");
				this.passwordSenha.setText("");
				this.textUsuario.setText("");
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		JanLogin jl = new JanLogin();
		jl.setVisible(true);
	}
}
