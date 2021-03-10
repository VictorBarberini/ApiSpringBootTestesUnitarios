package br.com.compasso.TrabalhoSpringBoot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.compasso.TrabalhoSpringBoot.modelo.Estoque;
import br.com.compasso.TrabalhoSpringBoot.modelo.Produto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProdutoRepositoryTest {
	
	@Autowired
	private ProdutoRepository produtoRepository;
   
    @Test
    public void deveriaAdicionarProdutos() throws Exception {
         Produto produto = new Produto("Produto Teste", BigDecimal.valueOf(150.70), new Estoque(85));
//         Estoque estoque = new Estoque();
//         estoque.setQuantidade(85);
//         produto.setEstoque(estoque);
//         produto.setDescricao("Produto Teste");
//         produto.setValor(BigDecimal.valueOf(150.70));
         produtoRepository.save(produto);

         assertEquals(produto.getEstoque().getQuantidade(), 85);
         assertEquals(produto.getDescricao(), "Produto Teste");
         assertEquals(produto.getValor(), BigDecimal.valueOf(150.70));
         
         
         Produto produto2 = new Produto("Produto Teste2", BigDecimal.valueOf(1200.50), new Estoque(35));
//         Estoque estoque2 = new Estoque();
//         estoque2.setQuantidade(35);
//         produto2.setEstoque(estoque2);
//         produto2.setDescricao("Produto Teste2");
//         produto2.setValor(BigDecimal.valueOf(1200.50));
         produtoRepository.save(produto2);
         
         assertEquals(produto2.getEstoque().getQuantidade(), 35);
         assertEquals(produto2.getDescricao(), "Produto Teste2");
         assertEquals(produto2.getValor(), BigDecimal.valueOf(1200.50));
    }
}
