package ControleEstoqueBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ControleEstoqueBean.Usuario;

public class UsuarioDAO {
	
	private Connection connection;

	public UsuarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public long retornaAutoIncrement() {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SHOW TABLE STATUS LIKE 'usuario';");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return rs.getLong("auto_increment");
			}

			rs.close();
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
		return -1;
	}
	
	public int inserir(Usuario usuario) {
		Integer valorRetorno = null;

		
			String sql = "INSERT INTO usuario "
					+ "(nome_usuario,senha_usuario)"
					+ " VALUES (?,?);";
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());

			
			stmt.execute();

			
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				valorRetorno = resultSet.getInt(1);
				usuario.setCodigo(valorRetorno);
			}
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
		return valorRetorno;
	}

	public boolean nomeUsuario(Usuario usuario){
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT nome_usuario FROM usuario;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				String nomeUsuario = rs.getString("nome_usuario");
				
				if(usuario.getNome().equals(nomeUsuario)){  
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
	
	public List<Usuario> consultar() {
		try {
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM usuario;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Usuario usuario = new Usuario();
				usuario.setCodigo(rs.getInt("codigo_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setSenha(rs.getString("senha_usuario"));

				
				listaUsuario.add(usuario);
			}
			rs.close();
			stmt.close();
			return listaUsuario;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	public List<String> pesquisar(String pesquisa) {
		List<String> listaUsuario = new ArrayList<String>();
			
		try{
			int intPesquisa = Integer.parseInt(pesquisa); 
			try{
				
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM usuario WHERE codigo_usuario = " + intPesquisa + ";");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Usuario usuario = new Usuario();

					usuario.setCodigo(rs.getInt("codigo_usuario"));
					String codigoUsuario = Integer.toString(usuario.getCodigo()); 
					
					usuario.setNome(rs.getString("nome_usuario"));
					String nomeUsuario = usuario.getNome();

					usuario.setSenha(rs.getString("senha_usuario"));
					String senhaUsuario = usuario.getSenha();

					listaUsuario.add(codigoUsuario);
					listaUsuario.add(nomeUsuario);
					listaUsuario.add(senhaUsuario);
				}
				rs.close();
				stmt.close();
				return listaUsuario;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		} catch (java.lang.NumberFormatException numberFormatException){
			
			try{
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM usuario WHERE nome_usuario LIKE '"	+ pesquisa + "%';");
				ResultSet rs = stmt.executeQuery();
	
				while (rs.next()) {
					Usuario usuario = new Usuario();
	
					usuario.setCodigo(rs.getInt("codigo_usuario"));
					String codigoUsuario = Integer.toString(usuario.getCodigo()); 
					
					usuario.setNome(rs.getString("nome_usuario"));
					String nomeUsuario = usuario.getNome();
	
					usuario.setSenha(rs.getString("senha_usuario"));
					String senhaUsuario = usuario.getSenha();
	
	
					listaUsuario.add(codigoUsuario);
					listaUsuario.add(nomeUsuario);
					listaUsuario.add(senhaUsuario);
				}
				rs.close();
				stmt.close();
				return listaUsuario;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		}	
	}

	public void atualizar(Usuario usuario) {
		try {
			String sql1 = "UPDATE usuario SET nome_usuario = '" + usuario.getNome() + "' WHERE codigo_usuario = " + usuario.getCodigo() + ";";
			String sql2 = "UPDATE usuario SET senha_usuario = '" + usuario.getSenha() + "' WHERE codigo_usuario = " + usuario.getCodigo() + ";";
		
			
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			PreparedStatement stmt2 = connection.prepareStatement(sql2);

			
			stmt1.execute();
			stmt2.execute();
			
			stmt1.close();
			stmt2.close();
			
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}

	}

	public void excluir(Usuario usuario) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM usuario WHERE codigo_usuario = " + usuario.getCodigo() + ";");

			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
}