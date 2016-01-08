package ControleEstoqueBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ControleEstoqueBean.Fornecedor;

public class FornecedorDAO {
	
	private Connection connection;

	public FornecedorDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public long retornaAutoIncrement() {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SHOW TABLE STATUS LIKE 'fornecedor';");
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
	
	public int inserir(Fornecedor fornecedor) {
		Integer valorRetorno = null;

		
			String sql = "INSERT INTO fornecedor "
					+ "(nome_fornecedor,telefone_fornecedor,endereco_fornecedor)"
					+ " VALUES (?,?,?);";
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getTelefone());
			stmt.setString(3, fornecedor.getEndereco());

			
			stmt.execute();

			
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				valorRetorno = resultSet.getInt(1);
				fornecedor.setCodigo(valorRetorno);
			}
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
		return valorRetorno;
	}

	public List<Fornecedor> consultar() {
		try {
			List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM fornecedor;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setCodigo(rs.getInt("codigo_fornecedor"));
				fornecedor.setNome(rs.getString("nome_fornecedor"));
				fornecedor.setTelefone(rs.getString("telefone_fornecedor"));
				fornecedor.setEndereco(rs.getString("endereco_fornecedor"));

				
				listaFornecedor.add(fornecedor);
			}
			rs.close();
			stmt.close();
			return listaFornecedor;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	public List<String> pesquisar(String pesquisa) {
		List<String> listaFornecedor = new ArrayList<String>();
			
		try{
			int intPesquisa = Integer.parseInt(pesquisa); 
			try{
				
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM fornecedor WHERE codigo_fornecedor = " + intPesquisa + ";");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Fornecedor fornecedor = new Fornecedor();

					fornecedor.setCodigo(rs.getInt("codigo_fornecedor"));
					String codigoFornecedor = Integer.toString(fornecedor.getCodigo()); // CONVERTE PARA STRING
					
					fornecedor.setNome(rs.getString("nome_fornecedor"));
					String nomeFornecedor = fornecedor.getNome();

					fornecedor.setTelefone(rs.getString("telefone_fornecedor"));
					String telefoneFornecedor = fornecedor.getTelefone();

					fornecedor.setEndereco(rs.getString("endereco_fornecedor"));
					String enderecoFornecedor = fornecedor.getEndereco();

					listaFornecedor.add(codigoFornecedor);
					listaFornecedor.add(nomeFornecedor);
					listaFornecedor.add(telefoneFornecedor);
					listaFornecedor.add(enderecoFornecedor);
				}
				rs.close();
				stmt.close();
				return listaFornecedor;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		} catch (java.lang.NumberFormatException numberFormatException){
			
			try{
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM fornecedor WHERE nome_fornecedor LIKE '"	+ pesquisa + "%';");
				ResultSet rs = stmt.executeQuery();
	
				while (rs.next()) {
					Fornecedor fornecedor = new Fornecedor();
	
					fornecedor.setCodigo(rs.getInt("codigo_fornecedor"));
					String codigoFornecedor = Integer.toString(fornecedor.getCodigo());
					
					fornecedor.setNome(rs.getString("nome_fornecedor"));
					String nomeFornecedor = fornecedor.getNome();
	
					fornecedor.setTelefone(rs.getString("telefone_fornecedor"));
					String telefoneFornecedor = fornecedor.getTelefone();
	
					fornecedor.setEndereco(rs.getString("endereco_fornecedor"));
					String enderecoFornecedor = fornecedor.getEndereco();
	
					listaFornecedor.add(codigoFornecedor);
					listaFornecedor.add(nomeFornecedor);
					listaFornecedor.add(telefoneFornecedor);
					listaFornecedor.add(enderecoFornecedor);
				}
				rs.close();
				stmt.close();
				return listaFornecedor;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		}	
	}

	public void atualizar(Fornecedor fornecedor) {
		try {
			String sql1 = "UPDATE fornecedor SET nome_fornecedor = '" + fornecedor.getNome() + "' WHERE codigo_fornecedor = " + fornecedor.getCodigo() + ";";
			String sql2 = "UPDATE fornecedor SET telefone_fornecedor = '" + fornecedor.getTelefone() + "' WHERE codigo_fornecedor = " + fornecedor.getCodigo() + ";";
			String sql3 = "UPDATE fornecedor SET endereco_fornecedor = '" + fornecedor.getEndereco() + "' WHERE codigo_fornecedor = " + fornecedor.getCodigo() + ";";
		
			
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			PreparedStatement stmt3 = connection.prepareStatement(sql3);

			
			stmt1.execute();
			stmt2.execute();
			stmt3.execute();
			
			stmt1.close();
			stmt2.close();
			stmt3.close();
			
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}

	}

	public void excluir(Fornecedor fornecedor) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM fornecedor WHERE codigo_fornecedor = " + fornecedor.getCodigo() + ";");

			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
}