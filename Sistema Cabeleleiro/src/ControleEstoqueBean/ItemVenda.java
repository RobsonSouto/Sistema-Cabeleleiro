package ControleEstoqueBean;

public class ItemVenda {
	private int quantidade;
	private float preco;
	private Produto produto;
	private Servico servico;
	private Venda codigoVenda;

	public ItemVenda() {
		this.servico = new Servico();
		this.produto = new Produto();
		this.codigoVenda = new Venda();
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(Venda codigoVenda) {
		this.codigoVenda = codigoVenda;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}
