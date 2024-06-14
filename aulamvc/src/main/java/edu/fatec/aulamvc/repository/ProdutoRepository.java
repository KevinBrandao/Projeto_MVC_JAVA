package edu.fatec.aulamvc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.fatec.aulamvc.model.Produto;

@Repository
public class ProdutoRepository {

	private static List<Produto> listaProdutos = new ArrayList<Produto>();
	
	public Produto salvar(Produto produto) {
		Integer codigo = (int) (Math.random() * 1000);
		produto.setCodigo(codigo);
		
		//salva o produto na memoria
		listaProdutos.add(produto);
		
		return produto;
	}
	
	public List<Produto> getProdutos() {
		return listaProdutos;
	}
}
