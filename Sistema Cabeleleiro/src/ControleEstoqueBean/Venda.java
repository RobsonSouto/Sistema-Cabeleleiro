package ControleEstoqueBean;

import java.util.ArrayList;

public class Venda {
	private int codigo;
	private String data;
	private ArrayList<ItemVenda> itensVenda;
	private Cliente cliente;
	private float totalVenda;

	public Venda() {
		this.itensVenda = new ArrayList<ItemVenda>();
		this.cliente = new Cliente();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ArrayList<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(ArrayList<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getValorTotal() {
		float retorno = 0;
		for (int i = 0; i < this.itensVenda.size(); i++) {
			float valor = (this.itensVenda.get(i).getQuantidade() * this.itensVenda
					.get(i).getPreco());
			retorno = retorno + valor;
		}
		return retorno;
	}

	public float getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(float totalVenda) {
		this.totalVenda = totalVenda;
	}

}
