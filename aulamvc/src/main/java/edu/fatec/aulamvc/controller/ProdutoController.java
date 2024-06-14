package edu.fatec.aulamvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.fatec.aulamvc.business.ProdutoBusiness;
import edu.fatec.aulamvc.model.Produto;
import edu.fatec.aulamvc.repository.ProdutoRepository;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoBusiness produtoBusiness;
	
	@PostMapping(path = "/produtos")
	public ResponseEntity<?> cadastrar(@RequestBody Produto produto) {
		
		try {
			Produto produtoCadastrado = 
					this.produtoBusiness.salvarProduto(produto);
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(produtoCadastrado);

		} catch(Exception e) {
			e.printStackTrace();
			
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		
	}
	
	@GetMapping(path = "/produtos")
	public List<Produto> consultarProduto() {
		
		return this.produtoBusiness.listarProdutos();
	}
}
