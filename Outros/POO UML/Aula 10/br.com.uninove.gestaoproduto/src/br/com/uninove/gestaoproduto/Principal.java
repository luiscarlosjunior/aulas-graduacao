package br.com.uninove.gestaoproduto;
import javax.swing.*;

public class Principal {

	public static void main(String[] args) {
		
		String descricao;
		
		descricao = JOptionPane
				.showInputDialog
				("Informe a descricao do produto");
		
		Produto produto1 = new Produto(descricao, 1.99);
		Produto produto2 = new Produto("Geleia", 8.99);
			
		ImprimeValores(produto1);
		ImprimeValores(produto2);
		
		// ImprimeValores(produto3.nome, produto3.preco);
	}

	private static void ImprimeValores(Produto produto) {
		System.out.println(produto.getNome());
		System.out.println(produto.getPreco());
	}
	
	private static void ImprimeValores(String nome, double preco) {
		System.out.println(nome);
		System.out.println(preco);
	}
	
	private static void produtoSemOO() {
		// Produto 1
		String nome = "Bolacha";
		double preco = 1.99;
		
		String nome2 = "Arroz";
		double preco2 = 21.99;
		
		String nome3 = "Feijão";
		double preco3 = 6.99;
		
		String nome4 = "Sorvete";
		double preco4 = 19.99;
		
		String nome5 = "Pão";
		double preco5 = 11.99;
		
		System.out.println(nome);
	}

}
