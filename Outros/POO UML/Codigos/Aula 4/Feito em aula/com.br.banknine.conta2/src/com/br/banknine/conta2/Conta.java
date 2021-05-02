package com.br.banknine.conta2;

public class Conta {
	
	private int agencia;
	private String numeroConta;
	private float saldo;
	
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	
	public int getAgencia() {
		return agencia;
	}
	
	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public void depositar(float deposito) {
		saldo = deposito;
	}
	public float saldo() {
		return saldo;
	}
}
