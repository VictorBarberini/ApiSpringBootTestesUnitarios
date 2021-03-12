package br.com.compasso.TrabalhoSpringBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.TrabalhoSpringBoot.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByDescricao(String descricao);
}
