package com.br.banknine.conta;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		ContaPoupanca luis = new ContaPoupanca();
		ContaPoupanca lais = new ContaPoupanca();
		ContaPoupanca roberta = new ContaPoupanca();
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Entre com o número conta");
		String numeroConta = sc.nextLine();
					
		luis.setAgencia(01);
		luis.setDiaDeposito(5);
		luis.setNumeroConta(numeroConta);
		
		lais.setAgencia(01);
		lais.setDiaDeposito(20);
		lais.setNumeroConta("003");
		
		roberta.setDiaDeposito(15);
		roberta.setNumeroConta("004");
		
		System.out.println("Minha conta é " + luis.getNumeroConta());
		System.out.println("Minha conta é " + lais.getNumeroConta());
		System.out.println("Minha conta é " + roberta.getNumeroConta());
		
	}

}
