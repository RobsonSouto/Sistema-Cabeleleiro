package ControleEstoqueBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ControleEstoqueBean.Cliente;
import ControleEstoqueBean.Login;
import ControleEstoqueGUI.JanLogin;

public class LoginDAO extends JanLogin{

	private Connection connection;

	public LoginDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public boolean login(Login login){
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT nome_usuario, senha_usuario FROM usuario;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				String usuario = rs.getString("nome_usuario");
				String senha = rs.getString("senha_usuario");
				
				if(login.getUsuario().equals(usuario) && login.getSenha().equals(senha)){  
					return true;  
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
		}
		return false;		
	}
}

