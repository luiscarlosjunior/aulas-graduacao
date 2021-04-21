package com.br.banknine.conta;

public class ContaPoupanca extends Conta {
	
	private int diaDeposito;	
	
	public float verSaldo() {
		return saldo();
	}

	public int getDiaDeposito() {
		return diaDeposito;
	}
	public void setDiaDeposito(int diaDeposito) {
		this.diaDeposito = diaDeposito;
	}
}
