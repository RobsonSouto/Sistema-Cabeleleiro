package ControleEstoqueBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ControleEstoqueBean.Funcionario;

public class FuncionarioDAO {
	
	private Connection connection;

	public FuncionarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public long retornaAutoIncrement() {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SHOW TABLE STATUS LIKE 'funcionario';");
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
	
	public int inserir(Funcionario funcionario) {
		Integer valorRetorno = null;

		
			String sql = "INSERT INTO funcionario "
					+ "(nome_funcionario,telefone_funcionario,endereco_funcionario,funcao_funcionario,setor_funcionario)"
					+ " VALUES (?,?,?,?,?);";
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getTelefone());
			stmt.setString(3, funcionario.getEndereco());
			stmt.setString(4, funcionario.getFuncao());
			stmt.setString(5, funcionario.getSetor());
			

			
			stmt.execute();

			
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				valorRetorno = resultSet.getInt(1);
				funcionario.setCodigo(valorRetorno);
			}
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
		return valorRetorno;
	}

	public List<Funcionario> consultar() {
		try {
			List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM funcionario;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Funcionario funcionario = new Funcionario();
				funcionario.setCodigo(rs.getInt("codigo_funcionario"));
				funcionario.setNome(rs.getString("nome_funcionario"));
				funcionario.setTelefone(rs.getString("telefone_funcionario"));
				funcionario.setEndereco(rs.getString("endereco_funcionario"));
				funcionario.setFuncao(rs.getString("funcao_funcionario"));
				funcionario.setSetor(rs.getString("setor_funcionario"));

				
				listaFuncionario.add(funcionario);
			}
			rs.close();
			stmt.close();
			return listaFuncionario;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	public List<String> pesquisar(String pesquisa) {
		List<String> listaFuncionario = new ArrayList<String>();
			
		try{
			int intPesquisa = Integer.parseInt(pesquisa); 
			try{
				
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM funcionario WHERE codigo_funcionario = " + intPesquisa + ";");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Funcionario funcionario = new Funcionario();

					funcionario.setCodigo(rs.getInt("codigo_funcionario"));
					String codigoFuncionario = Integer.toString(funcionario.getCodigo()); 
					
					funcionario.setNome(rs.getString("nome_funcionario"));
					String nomeFuncionario = funcionario.getNome();

					funcionario.setTelefone(rs.getString("telefone_funcionario"));
					String telefoneFuncionario = funcionario.getTelefone();

					funcionario.setEndereco(rs.getString("endereco_funcionario"));
					String enderecoFuncionario = funcionario.getEndereco();
					
					funcionario.setFuncao(rs.getString("funcao_funcionario"));
					String funcaoFuncionario = funcionario.getFuncao();
					
					funcionario.setSetor(rs.getString("setor_funcionario"));
					String setorFuncionario = funcionario.getSetor();

					listaFuncionario.add(codigoFuncionario);
					listaFuncionario.add(nomeFuncionario);
					listaFuncionario.add(telefoneFuncionario);
					listaFuncionario.add(enderecoFuncionario);
					listaFuncionario.add(funcaoFuncionario);
					listaFuncionario.add(setorFuncionario);
				}
				rs.close();
				stmt.close();
				return listaFuncionario;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		} catch (java.lang.NumberFormatException numberFormatException){
			
			try{
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM funcionario WHERE nome_funcionario LIKE '"	+ pesquisa + "%';");
				ResultSet rs = stmt.executeQuery();
	
				while (rs.next()) {
					Funcionario funcionario = new Funcionario();

					funcionario.setCodigo(rs.getInt("codigo_funcionario"));
					String codigoFuncionario = Integer.toString(funcionario.getCodigo()); 
					
					funcionario.setNome(rs.getString("nome_funcionario"));
					String nomeFuncionario = funcionario.getNome();

					funcionario.setTelefone(rs.getString("telefone_funcionario"));
					String telefoneFuncionario = funcionario.getTelefone();

					funcionario.setEndereco(rs.getString("endereco_funcionario"));
					String enderecoFuncionario = funcionario.getEndereco();
					
					funcionario.setFuncao(rs.getString("funcao_funcionario"));
					String funcaoFuncionario = funcionario.getFuncao();
					
					funcionario.setSetor(rs.getString("setor_funcionario"));
					String setorFuncionario = funcionario.getSetor();

					listaFuncionario.add(codigoFuncionario);
					listaFuncionario.add(nomeFuncionario);
					listaFuncionario.add(telefoneFuncionario);
					listaFuncionario.add(enderecoFuncionario);
					listaFuncionario.add(funcaoFuncionario);
					listaFuncionario.add(setorFuncionario);
				}
				rs.close();
				stmt.close();
				return listaFuncionario;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		}	
	}

	public void atualizar(Funcionario funcionario) {
		try {
			String sql1 = "UPDATE funcionario SET nome_funcionario = '" + funcionario.getNome() + "' WHERE codigo_funcionario = " + funcionario.getCodigo() + ";";
			String sql2 = "UPDATE funcionario SET telefone_funcionario = '" + funcionario.getTelefone() + "' WHERE codigo_funcionario = " + funcionario.getCodigo() + ";";
			String sql3 = "UPDATE funcionario SET endereco_funcionario = '" + funcionario.getEndereco() + "' WHERE codigo_funcionario = " + funcionario.getCodigo() + ";";
			String sql4 = "UPDATE funcionario SET funcao_funcionario = '" + funcionario.getFuncao() + "' WHERE codigo_funcionario = " + funcionario.getCodigo() + ";";
			String sql5 = "UPDATE funcionario SET setor_funcionario = '" + funcionario.getSetor() + "' WHERE codigo_funcionario = " + funcionario.getCodigo() + ";";
		
			
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			PreparedStatement stmt3 = connection.prepareStatement(sql3);
			PreparedStatement stmt4 = connection.prepareStatement(sql3);
			PreparedStatement stmt5 = connection.prepareStatement(sql3);

			
			stmt1.execute();
			stmt2.execute();
			stmt3.execute();
			stmt4.execute();
			stmt5.execute();
			
			stmt1.close();
			stmt2.close();
			stmt3.close();
			stmt4.close();
			stmt5.close();
			
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}

	}

	public void excluir(Funcionario funcionario) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM funcionario WHERE codigo_funcionario = " + funcionario.getCodigo() + ";");

			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
}