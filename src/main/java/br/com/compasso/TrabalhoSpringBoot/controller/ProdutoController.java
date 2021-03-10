package br.com.compasso.TrabalhoSpringBoot.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.TrabalhoSpringBoot.controller.dto.ProdutoDto;
import br.com.compasso.TrabalhoSpringBoot.controller.form.AtualizarProdutoForm;
import br.com.compasso.TrabalhoSpringBoot.controller.form.ProdutoForm;
import br.com.compasso.TrabalhoSpringBoot.modelo.Produto;
import br.com.compasso.TrabalhoSpringBoot.repository.EstoqueRepository;
import br.com.compasso.TrabalhoSpringBoot.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;
	
	//Lista Produtos
	@GetMapping
	public List<ProdutoDto> lista() {
		List<Produto> produtos = produtoRepository.findAll();
		return ProdutoDto.converter(produtos);
	}
	
	//Cadastra Produtos
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
		Produto produto = form.converter(estoqueRepository);
		produtoRepository.save(produto);
		
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}
	
	//Atualiza Produtos
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarProdutoForm form) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			Produto produto = form.atualizar(id, produtoRepository, estoqueRepository);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}
		return ResponseEntity.notFound().build();
	}

	//Exclui Produtos
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> remover(@PathVariable Long id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
