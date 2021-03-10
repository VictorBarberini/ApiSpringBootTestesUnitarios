package br.com.compasso.TrabalhoSpringBoot.controller.form;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import com.sun.istack.NotNull;
import br.com.compasso.TrabalhoSpringBoot.modelo.Estoque;
import br.com.compasso.TrabalhoSpringBoot.modelo.Produto;
import br.com.compasso.TrabalhoSpringBoot.repository.EstoqueRepository;
import br.com.compasso.TrabalhoSpringBoot.repository.ProdutoRepository;

public class AtualizarProdutoForm {

	@NotNull @NotEmpty
	private String descricao;
	@NotNull @DecimalMin("0.00")
	private BigDecimal valor;
	@NotNull @Min(0)
	private Integer quantidade;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Produto atualizar(Long id, ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository) {
		Produto produto = produtoRepository.getOne(id);
		Estoque estoque = estoqueRepository.getOne(id);
		estoque.setQuantidade(this.quantidade);
		produto.setDescricao(this.descricao);
		produto.setEstoque(estoque);
		produto.setValor(this.valor);
		return produto;
	}
}
