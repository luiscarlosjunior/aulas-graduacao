package br.com.banknine.principal;

import br.com.banknine.pessoa.*;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Funcionario func1 = new Funcionario();
		
		func1.setNome("José da Silva Oliveira");
		func1.setRg("4455221166");
		func1.setCartao("132465");
		
		func1.ExibirInformacoes();		
	}

}
