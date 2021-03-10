package br.com.compasso.TrabalhoSpringBoot.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.TrabalhoSpringBoot.modelo.Produto;

public class ProdutoDto {

	private Long id;
	private String descricao;
	private BigDecimal valor;
	private Integer quantidade;
	
	public ProdutoDto(Produto produto){
		this.id = produto.getId();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.quantidade = produto.getEstoque().getQuantidade();
	}
	
	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public static List<ProdutoDto> converter(List<Produto> produtos){
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
}
