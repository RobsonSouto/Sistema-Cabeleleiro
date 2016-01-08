package ControleEstoqueBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ControleEstoqueBean.ItemVenda;
import ControleEstoqueBean.Produto;
import ControleEstoqueBean.Servico;
import ControleEstoqueBean.Venda;

public class VendaDAO {
	
	private Connection connection;

	public VendaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public long retornaAutoIncrement() {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SHOW TABLE STATUS LIKE 'venda';");
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
	
	public int inserir(Venda venda) {
		Integer valorRetorno = null;
		
			String sql = "INSERT INTO venda "
					+ "(codigo_venda,data_venda,total_venda,codigo_cliente_venda)"
					+ " VALUES (?,?,?,?);";
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			
			stmt.setInt(1, venda.getCodigo());
			stmt.setString(2, venda.getData());
			stmt.setFloat(3, venda.getValorTotal());
			stmt.setInt(4, venda.getCliente().getCodigo());

			
			stmt.execute();

			
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				valorRetorno = resultSet.getInt(1);
				venda.setCodigo(valorRetorno);
			}
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
		return valorRetorno;
	}
	
	public void faturar(Venda venda) {
		try {
			String sql = "UPDATE venda SET total_venda = " + venda.getValorTotal() + " WHERE codigo_venda = " + venda.getCodigo() + ";";
		
			
			PreparedStatement stmt = connection.prepareStatement(sql);

			
			stmt.execute();
			stmt.close();	
			
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public void adicionarLista(Venda venda, String tipoItemVenda) {
		if (tipoItemVenda.equals("produto")){	
				String sql = "INSERT INTO itensVenda "
						+ "(quantidade_itensVenda,preco_itensVenda,codigo_produto_itensVenda,codigo_venda_itensVenda)"
						+ " VALUES (?,?,?,?);";
			try {
				
				PreparedStatement stmt = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
	
				
				for (int i = 0; i < venda.getItensVenda().size(); i++) {
					stmt.setInt(1,venda.getItensVenda().get(i).getQuantidade());
					stmt.setFloat(2,venda.getItensVenda().get(i).getPreco());
					stmt.setInt(3,venda.getItensVenda().get(i).getProduto().getCodigo());
					stmt.setInt(4,venda.getItensVenda().get(i).getCodigoVenda().getCodigo());
				}
	
				
				stmt.execute();
				stmt.close();
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		} else {
				String sql = "INSERT INTO itensVenda "
						+ "(quantidade_itensVenda,preco_itensVenda,codigo_servico_itensVenda,codigo_venda_itensVenda)"
						+ " VALUES (?,?,?,?);";
			try {
				
				PreparedStatement stmt = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
	
				
				for (int i = 0; i < venda.getItensVenda().size(); i++) {
					stmt.setInt(1,venda.getItensVenda().get(i).getQuantidade());
					stmt.setFloat(2,venda.getItensVenda().get(i).getPreco());
					stmt.setInt(3,venda.getItensVenda().get(i).getServico().getCodigo());
					stmt.setInt(4,venda.getItensVenda().get(i).getCodigoVenda().getCodigo());
				}
	
				
				stmt.execute();
				stmt.close();
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		}
	}
	
	public int consultarCodigoCliente(String string) {
		try {
			Venda venda = new Venda();
			int codigoCliente = 0;
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT codigo_cliente FROM cliente WHERE nome_cliente = '" + string + "';");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {				
				
				venda.getCliente().setCodigo(rs.getInt("codigo_cliente"));
				codigoCliente = venda.getCliente().getCodigo();
			}
			rs.close();
			stmt.close();
			return codigoCliente;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public int consultarCodigoProduto(String string) {
		try {
			ItemVenda itemVenda = new ItemVenda();
			int codigoProduto = 0;
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT codigo_produto FROM produto WHERE nome_produto = '" + string + "';");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {				
				
				itemVenda.getProduto().setCodigo(rs.getInt("codigo_produto"));
				codigoProduto = itemVenda.getProduto().getCodigo();
			}
			rs.close();
			stmt.close();
			return codigoProduto;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public int consultarCodigoServico(String string) {
		try {
			ItemVenda itemVenda = new ItemVenda();
			int codigoServico = 0;
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT codigo_servico FROM servico WHERE nome_servico = '" + string + "';");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {				
				
				itemVenda.getServico().setCodigo(rs.getInt("codigo_servico"));
				codigoServico = itemVenda.getServico().getCodigo();
			}
			rs.close();
			stmt.close();
			return codigoServico;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	public float atualizaTextPrecoProduto(ItemVenda itemVenda) {
		try {
			float valorProduto = 0;
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT valor_unitario FROM produto WHERE nome_produto = '" + itemVenda.getProduto().getNome() + "';");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {				
				
				valorProduto = rs.getFloat("valor_unitario");
				
			}
			rs.close();
			stmt.close();
			return valorProduto;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public float atualizaTextPrecoServico(ItemVenda itemVenda) {
		try {
			float valorServico = 0;
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT preco_servico FROM servico WHERE nome_servico = '" + itemVenda.getServico().getNome() + "';");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {				
				
				valorServico = rs.getFloat("preco_servico");
			}
			rs.close();
			stmt.close();
			return valorServico;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public ArrayList<String> comboCliente() {
		try {
			ArrayList<String> listaCliente = new ArrayList<String>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT nome_cliente FROM cliente ORDER BY nome_cliente DESC;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				listaCliente.add(rs.getString("nome_cliente"));
			}
			rs.close();
			stmt.close();
			return listaCliente;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public ArrayList<String> comboProduto() {
		try {
			ArrayList<String> listaProduto = new ArrayList<String>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT nome_produto FROM produto ORDER BY nome_produto DESC;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				listaProduto.add(rs.getString("nome_produto"));
			}
			rs.close();
			stmt.close();
			return listaProduto;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public ArrayList<String> comboServico() {
		try {
			ArrayList<String> listaServico = new ArrayList<String>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT nome_servico FROM servico ORDER BY nome_servico DESC;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				listaServico.add(rs.getString("nome_servico"));
			}
			rs.close();
			stmt.close();
			return listaServico;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public void excluirVenda(int codigoVenda) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM venda WHERE codigo_venda = " + codigoVenda + ";");

			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public void excluirItensVenda(int codigoVenda) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM itensVenda WHERE codigo_venda_itensVenda = " + codigoVenda + ";");

			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	

	public List<Venda> consultarVendas(Venda Venda) {		    
		try {
			List<Venda> listaVenda = new ArrayList<Venda>();
			int codigoCliente = 0;
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM venda WHERE (data_venda = '" + Venda.getData() + "') AND (codigo_cliente_venda = " + Venda.getCliente().getCodigo() + ");");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Venda venda = new Venda();
				
				venda.setData(rs.getString("data_venda"));
				
				venda.getCliente().setCodigo(rs.getInt("codigo_cliente_venda"));
				codigoCliente = venda.getCliente().getCodigo();
				venda.getCliente().setNome(consultarNomeCliente(codigoCliente));
				
				venda.setTotalVenda(rs.getFloat("total_venda"));
				
				
				listaVenda.add(venda);
			}
			rs.close();
			stmt.close();
			return listaVenda;
			
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public List<Venda> consultarTodasVendas() {
		try {
			int codigoCliente = 0;
			List<Venda> listaVenda = new ArrayList<Venda>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM venda;");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Venda venda = new Venda();
				
				venda.setData(rs.getString("data_venda"));
				
				venda.getCliente().setCodigo(rs.getInt("codigo_cliente_venda"));
				codigoCliente = venda.getCliente().getCodigo();
				venda.getCliente().setNome(consultarNomeCliente(codigoCliente));
				
				venda.setTotalVenda(rs.getFloat("total_venda"));
				
				
				listaVenda.add(venda);
			}
			rs.close();
			stmt.close();
			return listaVenda;
			
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public List<Venda> consultaVendaData(Venda Venda) {
		try {
			int codigoCliente = 0;
			List<Venda> listaVenda = new ArrayList<Venda>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM venda WHERE data_venda = '" + Venda.getData() + "';");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Venda venda = new Venda();
				
				venda.setData(rs.getString("data_venda"));
				
				venda.getCliente().setCodigo(rs.getInt("codigo_cliente_venda"));
				codigoCliente = venda.getCliente().getCodigo();
				venda.getCliente().setNome(consultarNomeCliente(codigoCliente));
				
				venda.setTotalVenda(rs.getFloat("total_venda"));
				
				
				listaVenda.add(venda);
			}
			rs.close();
			stmt.close();
			return listaVenda;
			
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public List<Venda> consultaVendaCliente(Venda Venda) {
		try {
			int codigoCliente = 0;
			List<Venda> listaVenda = new ArrayList<Venda>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM venda WHERE codigo_cliente_venda = '" + Venda.getCliente().getCodigo() + "';");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Venda venda = new Venda();
				
				venda.setData(rs.getString("data_venda"));
				
				venda.getCliente().setCodigo(rs.getInt("codigo_cliente_venda"));
				codigoCliente = venda.getCliente().getCodigo();
				venda.getCliente().setNome(consultarNomeCliente(codigoCliente));
				
				venda.setTotalVenda(rs.getFloat("total_venda"));
				
				
				listaVenda.add(venda);
			}
			rs.close();
			stmt.close();
			return listaVenda;
			
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	
	public String consultarNomeCliente(int codigoCliente) {
		try {
			Venda venda = new Venda();
			String nomeCliente = null;
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT nome_cliente FROM cliente WHERE codigo_cliente = " + codigoCliente + ";");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {				
				
				venda.getCliente().setNome(rs.getString("nome_cliente"));
				nomeCliente = venda.getCliente().getNome();
			}
			rs.close();
			stmt.close();
			return nomeCliente;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
}