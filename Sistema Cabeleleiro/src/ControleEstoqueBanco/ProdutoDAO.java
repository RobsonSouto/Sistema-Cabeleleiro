package ControleEstoqueBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ControleEstoqueBean.Fornecedor;
import ControleEstoqueBean.Produto;
import ControleEstoqueBean.Venda;

public class ProdutoDAO {
	
	private Connection connection;

	public ProdutoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public long retornaAutoIncrement() {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SHOW TABLE STATUS LIKE 'produto';");
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
	
	public int inserir(Produto produto) {
		Integer valorRetorno = null;

		
			String sql = "INSERT INTO produto "
					+ "(nome_produto,valor_unitario,qntEstoque_produto,codigo_fornecedor_produto)"
					+ " VALUES (?,?,?,?);";
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			
			stmt.setString(1, produto.getNome());
			stmt.setFloat(2, produto.getPreco());
			stmt.setInt(3, produto.getQntEstoque());
			stmt.setInt(4, produto.getFornecedor().getCodigo());

			
			stmt.execute();

			
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				valorRetorno = resultSet.getInt(1);
				produto.setCodigo(valorRetorno);
			}
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
		return valorRetorno;
	}
	
	public int consultarCodigoFornecedor(String string) {
		try {
			Produto produto = new Produto();
			int codigoFornecedor = 0;
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT codigo_fornecedor FROM fornecedor WHERE nome_fornecedor = '" + string + "';");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {	
				produto.getFornecedor().setCodigo(rs.getInt("codigo_fornecedor"));
				codigoFornecedor = produto.getFornecedor().getCodigo();
			}
			rs.close();
			stmt.close();
			return codigoFornecedor;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public String consultarNomeFornecedor(int codigoFornecedor) {
		try {
			Produto produto = new Produto();
			String nomeFornecedor = null;
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT nome_fornecedor FROM fornecedor WHERE codigo_fornecedor = " + codigoFornecedor + ";");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {				
				
				produto.getFornecedor().setNome(rs.getString("nome_fornecedor"));
				nomeFornecedor = produto.getFornecedor().getNome();
			}
			rs.close();
			stmt.close();
			return nomeFornecedor;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	public List<Produto> consultar() {
		try {
			int codigoFornecedor = 0;
			List<Produto> listaProduto = new ArrayList<Produto>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM produto;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("codigo_produto"));
				produto.setNome(rs.getString("nome_produto"));
				produto.setPreco(rs.getFloat("valor_unitario"));
				produto.setQntEstoque(rs.getInt("qntEstoque_produto"));
				
				produto.getFornecedor().setCodigo(rs.getInt("codigo_fornecedor_produto"));
				codigoFornecedor = produto.getFornecedor().getCodigo();
				produto.getFornecedor().setNome(consultarNomeFornecedor(codigoFornecedor));

				
				listaProduto.add(produto);
			}
			rs.close();
			stmt.close();
			return listaProduto;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public ArrayList<String> comboFornecedor() {
		try {
			ArrayList<String> listaFornecedor = new ArrayList<String>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT nome_fornecedor FROM fornecedor ORDER BY nome_fornecedor DESC;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				listaFornecedor.add(rs.getString("nome_fornecedor"));
			}
			rs.close();
			stmt.close();
			return listaFornecedor;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	public List<String> pesquisar(String pesquisa) {
		List<String> listaProduto = new ArrayList<String>();
		int codigoFornecedor = 0;
		try{
			int intPesquisa = Integer.parseInt(pesquisa); 
			try{
				
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM produto WHERE codigo_produto = " + intPesquisa + ";");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Produto produto = new Produto();

					produto.setCodigo(rs.getInt("codigo_produto"));
					String codigoProduto = Integer.toString(produto.getCodigo()); 
					
					produto.setNome(rs.getString("nome_produto"));
					String nomeProduto = produto.getNome();

					produto.setPreco(rs.getFloat("valor_unitario"));
					String precoProduto = Float.toString(produto.getPreco());

					produto.setQntEstoque(rs.getInt("qntEstoque_produto"));
					String qntEstoqueProduto = Integer.toString(produto.getQntEstoque());
					
					produto.getFornecedor().setCodigo(rs.getInt("codigo_fornecedor_produto"));
					codigoFornecedor = produto.getFornecedor().getCodigo();
					produto.getFornecedor().setNome(consultarNomeFornecedor(codigoFornecedor));
					String fornecedorProduto = produto.getFornecedor().getNome();

					listaProduto.add(codigoProduto);
					listaProduto.add(nomeProduto);
					listaProduto.add(precoProduto);
					listaProduto.add(qntEstoqueProduto);
					listaProduto.add(fornecedorProduto);
				}
				rs.close();
				stmt.close();
				return listaProduto;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		} catch (java.lang.NumberFormatException numberFormatException){
			
			try{
				PreparedStatement stmt = this.connection
						.prepareStatement("SELECT * FROM produto WHERE nome_produto LIKE '"	+ pesquisa + "%';");
				ResultSet rs = stmt.executeQuery();
	
				while (rs.next()) {
					Produto produto = new Produto();

					produto.setCodigo(rs.getInt("codigo_produto"));
					String codigoProduto = Integer.toString(produto.getCodigo()); 
					
					produto.setNome(rs.getString("nome_produto"));
					String nomeProduto = produto.getNome();

					produto.setPreco(rs.getFloat("valor_unitario"));
					String precoProduto = Float.toString(produto.getPreco());

					produto.setQntEstoque(rs.getInt("qntEstoque_produto"));
					String qntEstoqueProduto = Integer.toString(produto.getQntEstoque());
					
					produto.getFornecedor().setCodigo(rs.getInt("codigo_fornecedor_produto"));
					codigoFornecedor = produto.getFornecedor().getCodigo();
					produto.getFornecedor().setNome(consultarNomeFornecedor(codigoFornecedor));
					String fornecedorProduto = produto.getFornecedor().getNome();

					listaProduto.add(codigoProduto);
					listaProduto.add(nomeProduto);
					listaProduto.add(precoProduto);
					listaProduto.add(qntEstoqueProduto);
					listaProduto.add(fornecedorProduto);
				}
				rs.close();
				stmt.close();
				return listaProduto;
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		}	
	}

	public void atualizar(Produto produto) {
		try {
			String sql1 = "UPDATE produto SET nome_produto = '" + produto.getNome() + "' WHERE codigo_produto = " + produto.getCodigo() + ";";
			String sql2 = "UPDATE produto SET valor_unitario = '" + produto.getPreco() + "' WHERE codigo_produto = " + produto.getCodigo() + ";";
			String sql3 = "UPDATE produto SET fornecedor_produto = '" + produto.getFornecedor() + "' WHERE codigo_produto = " + produto.getCodigo() + ";";
			String sql4 = "UPDATE produto SET qntEstoque_produto = '" + produto.getQntEstoque() + "' WHERE codigo_produto = " + produto.getCodigo() + ";";
		
			
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			PreparedStatement stmt3 = connection.prepareStatement(sql3);
			PreparedStatement stmt4 = connection.prepareStatement(sql4);

		
			stmt1.execute();
			stmt2.execute();
			stmt3.execute();
			stmt4.execute();
			
			stmt1.close();
			stmt2.close();
			stmt3.close();
			stmt4.close();
			
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}

	}

	public void excluir(Produto produto) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM produto WHERE codigo_produto = " + produto.getCodigo() + ";");

			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
}