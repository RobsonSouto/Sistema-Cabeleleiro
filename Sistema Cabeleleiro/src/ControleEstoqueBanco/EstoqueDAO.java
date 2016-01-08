package ControleEstoqueBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ControleEstoqueBean.Estoque;
import ControleEstoqueBean.Produto;
import ControleEstoqueGUI.JanLogin;

public class EstoqueDAO extends JanLogin{

	private Connection connection;

	public EstoqueDAO() {
		this.connection = new ConnectionFactory().getConnection();
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
	
	public void modifica(Estoque estoque) {
		try {
			String sql = "UPDATE produto SET qntEstoque_produto = '" + estoque.getQntEstoque() + "' WHERE nome_produto = '" + estoque.getNomeProduto() + "';";
		
			
			PreparedStatement stmt = connection.prepareStatement(sql);

		
			stmt.execute();
			
			stmt.close();
			
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public int atualizaTextQntEstoque(Estoque estoque){
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT qntEstoque_produto FROM produto WHERE nome_produto = '" + estoque.getNomeProduto() + "';");
			ResultSet rs = stmt.executeQuery();
			int qntEstoque = 0;

			while (rs.next()) {
				qntEstoque = rs.getInt("qntEstoque_produto");
			}
			rs.close();
			stmt.close();
			return qntEstoque;
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
		
	}
}

