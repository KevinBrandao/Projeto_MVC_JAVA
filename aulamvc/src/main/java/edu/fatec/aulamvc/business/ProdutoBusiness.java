package edu.fatec.aulamvc.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fatec.aulamvc.model.Produto;
import edu.fatec.aulamvc.repository.ProdutoRepository;

@Service
public class ProdutoBusiness {

	private static final Double VALOR_LIMINTE = 10000.0;
	private static final Integer QUANTIDADE_LIMITE = 20;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto salvarProduto(Produto produto) throws Exception {
		
		if(produto.getValorUnitario() > VALOR_LIMINTE
				&& produto.getQuantidade() > QUANTIDADE_LIMITE) {
			
			throw new Exception("Valor limite para a quantidade");
		}
		
		return this.produtoRepository.salvar(produto);
		
	}
	
	public List<Produto> listarProdutos() {
		return this.produtoRepository.getProdutos();
	}
}
