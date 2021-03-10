package br.com.compasso.TrabalhoSpringBoot.controller.form;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import br.com.compasso.TrabalhoSpringBoot.modelo.Estoque;
import br.com.compasso.TrabalhoSpringBoot.modelo.Produto;
import br.com.compasso.TrabalhoSpringBoot.repository.EstoqueRepository;

public class ProdutoForm {

	@NotNull @NotEmpty
	private String descricao;
	@NotNull  @DecimalMin("0.00")
	private BigDecimal valor;
	@NotNull @Min(0)
	private Integer quantidade;
	
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto converter(EstoqueRepository estoqueRepository) {
		Estoque estoque = new Estoque(this.quantidade);
		return new Produto(descricao, valor, estoque);
	}
}
