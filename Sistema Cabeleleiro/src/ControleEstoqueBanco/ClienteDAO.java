package ControleEstoqueBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ControleEstoqueBean.Cliente;

public class ClienteDAO {
	
	private Connection connection;

	public ClienteDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public long retornaAutoIncrement() {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SHOW TABLE STATUS LIKE 'cliente';");
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
	
	public int inserir(Cliente cliente) {
		Integer valorRetorno = null;

		
			String sql = "INSERT INTO cliente "
					+ "(nome_cliente,telefone_cliente,endereco_cliente)"
					+ " VALUES (?,?,?);";
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEndereco());

			
			stmt.execute();

			
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				valorRetorno = resultSet.getInt(1);
				cliente.setCodigo(valorRetorno);
			}
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
		return valorRetorno;
	}

	public List<Cliente> consultar() {
		try {
			List<Cliente> listaCliente = new ArrayList<Cliente>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM cliente;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getInt("codigo_cliente"));
				cliente.setNome(rs.getString("nome_cliente"));
				cliente.setTelefone(rs.getString("telefone_cliente"));
				cliente.setEndereco(rs.getString("endereco_cliente"));

				
				listaCliente.add(cliente);
			}
			rs.close();
			stmt.close();
			return listaCliente;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	public List<String> pesquisar(String pesquisa) {
		List<String> listaCliente = new ArrayList<String>();
			
		try{
			int intPesquisa = Integer.parseInt(pesquisa); 
			try{
				
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM cliente WHERE codigo_cliente = " + intPesquisa + ";");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();

					cliente.setCodigo(rs.getInt("codigo_cliente"));
					String codigoCliente = Integer.toString(cliente.getCodigo()); // CONVERTE PARA STRING
					
					cliente.setNome(rs.getString("nome_cliente"));
					String nomeCliente = cliente.getNome();

					cliente.setTelefone(rs.getString("telefone_cliente"));
					String telefoneCliente = cliente.getTelefone();

					cliente.setEndereco(rs.getString("endereco_cliente"));
					String enderecoCliente = cliente.getEndereco();

					listaCliente.add(codigoCliente);
					listaCliente.add(nomeCliente);
					listaCliente.add(telefoneCliente);
					listaCliente.add(enderecoCliente);
				}
				rs.close();
				stmt.close();
				return listaCliente;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		} catch (java.lang.NumberFormatException numberFormatException){
			
			try{
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM cliente WHERE nome_cliente LIKE '"	+ pesquisa + "%';");
				ResultSet rs = stmt.executeQuery();
	
				while (rs.next()) {
					Cliente cliente = new Cliente();
	
					cliente.setCodigo(rs.getInt("codigo_cliente"));
					String codigoCliente = Integer.toString(cliente.getCodigo()); // CONVERTE PARA STRING
					
					cliente.setNome(rs.getString("nome_cliente"));
					String nomeCliente = cliente.getNome();
	
					cliente.setTelefone(rs.getString("telefone_cliente"));
					String telefoneCliente = cliente.getTelefone();
	
					cliente.setEndereco(rs.getString("endereco_cliente"));
					String enderecoCliente = cliente.getEndereco();
	
					listaCliente.add(codigoCliente);
					listaCliente.add(nomeCliente);
					listaCliente.add(telefoneCliente);
					listaCliente.add(enderecoCliente);
				}
				rs.close();
				stmt.close();
				return listaCliente;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		}	
	}

	public void atualizar(Cliente cliente) {
		try {
			String sql1 = "UPDATE cliente SET nome_cliente = '" + cliente.getNome() + "' WHERE codigo_cliente = " + cliente.getCodigo() + ";";
			String sql2 = "UPDATE cliente SET telefone_cliente = '" + cliente.getTelefone() + "' WHERE codigo_cliente = " + cliente.getCodigo() + ";";
			String sql3 = "UPDATE cliente SET endereco_cliente = '" + cliente.getEndereco() + "' WHERE codigo_cliente = " + cliente.getCodigo() + ";";
		
			
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

	public void excluir(Cliente cliente) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM cliente WHERE codigo_cliente = " + cliente.getCodigo() + ";");

			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
}