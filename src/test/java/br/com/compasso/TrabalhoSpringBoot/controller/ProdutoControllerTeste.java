package br.com.compasso.TrabalhoSpringBoot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.compasso.TrabalhoSpringBoot.modelo.Estoque;
import br.com.compasso.TrabalhoSpringBoot.modelo.Produto;
import br.com.compasso.TrabalhoSpringBoot.repository.EstoqueRepository;
import br.com.compasso.TrabalhoSpringBoot.repository.ProdutoRepository;
import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class ProdutoControllerTeste {

	@Autowired
	private ProdutoController produtoController;
	
	@MockBean
	private ProdutoRepository produtoRepository;
	
	@MockBean
	private EstoqueRepository estoqueRepository;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.produtoController);
	}
	
	@Test
	public void deveRetornarSucesso_AoBuscarProduto() {
		List<Produto> produtos = produto();
		Mockito.when(this.produtoRepository.findByDescricao("Produto Teste")).thenReturn(produtos);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/produtos")
		.then()
			.statusCode(HttpStatus.OK.value());
		
		Produto produto = produtos.get(0);
		assertEquals(produto.getEstoque().getQuantidade(), 85);
        assertEquals(produto.getDescricao(), "Produto Teste");
        assertEquals(produto.getValor(), BigDecimal.valueOf(150.70));
	}
	
	public List<Produto> produto() {
        Produto produto = new Produto("Produto Teste", BigDecimal.valueOf(150.70), new Estoque(85));
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        return produtos;
    }
}
