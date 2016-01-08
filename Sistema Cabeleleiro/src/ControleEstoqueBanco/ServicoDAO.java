package ControleEstoqueBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ControleEstoqueBean.Servico;

public class ServicoDAO {
	
	private Connection connection;

	public ServicoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public long retornaAutoIncrement() {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SHOW TABLE STATUS LIKE 'servico';");
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
	
	public int inserir(Servico servico) {
		Integer valorRetorno = null;

		
			String sql = "INSERT INTO servico "
					+ "(nome_servico,valor_servico)"
					+ " VALUES (?,?);";
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			
			stmt.setString(1, servico.getNome());
			stmt.setFloat(2, servico.getPreco());

			
			stmt.execute();

			
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				valorRetorno = resultSet.getInt(1);
				servico.setCodigo(valorRetorno);
			}
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
		return valorRetorno;
	}

	public List<Servico> consultar() {
		try {
			List<Servico> listaServico = new ArrayList<Servico>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM servico;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Servico servico = new Servico();
				servico.setCodigo(rs.getInt("codigo_servico"));
				servico.setNome(rs.getString("nome_servico"));
				servico.setPreco(rs.getFloat("valor_servico"));

				
				listaServico.add(servico);
			}
			rs.close();
			stmt.close();
			return listaServico;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	public List<String> pesquisar(String pesquisa) {
		List<String> listaServico = new ArrayList<String>();
			
		try{
			int intPesquisa = Integer.parseInt(pesquisa); 
			try{
				
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM servico WHERE codigo_servico = " + intPesquisa + ";");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Servico servico = new Servico();

					servico.setCodigo(rs.getInt("codigo_servico"));
					String codigoServico = Integer.toString(servico.getCodigo()); 
					
					servico.setNome(rs.getString("nome_servico"));
					String nomeServico = servico.getNome();

					servico.setPreco(rs.getFloat("valor_servico"));
					String precoServico = Float.toString(servico.getPreco());

					listaServico.add(codigoServico);
					listaServico.add(nomeServico);
					listaServico.add(precoServico);
				}
				rs.close();
				stmt.close();
				return listaServico;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		} catch (java.lang.NumberFormatException numberFormatException){
			
			try{
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM servico WHERE nome_servico LIKE '"	+ pesquisa + "%';");
				ResultSet rs = stmt.executeQuery();
	
				while (rs.next()) {
					Servico servico = new Servico();
	
					servico.setCodigo(rs.getInt("codigo_servico"));
					String codigoServico = Integer.toString(servico.getCodigo()); 
					
					servico.setNome(rs.getString("nome_servico"));
					String nomeServico = servico.getNome();
	
					servico.setPreco(rs.getFloat("valor_servico"));
					String precoServico = Float.toString(servico.getPreco());
	
	
					listaServico.add(codigoServico);
					listaServico.add(nomeServico);
					listaServico.add(precoServico);
				}
				rs.close();
				stmt.close();
				return listaServico;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		}	
	}

	public void atualizar(Servico servico) {
		try {
			String sql1 = "UPDATE servico SET nome_servico = '" + servico.getNome() + "' WHERE codigo_servico = " + servico.getCodigo() + ";";
			String sql2 = "UPDATE servico SET preco_servico = '" + servico.getPreco() + "' WHERE codigo_servico = " + servico.getCodigo() + ";";
		
			
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

	public void excluir(Servico servico) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM servico WHERE codigo_servico = " + servico.getCodigo() + ";");

			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
}