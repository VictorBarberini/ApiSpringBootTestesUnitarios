package br.com.compasso.TrabalhoSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.TrabalhoSpringBoot.modelo.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

	Estoque findByQuantidade(Integer quantidade);
}
