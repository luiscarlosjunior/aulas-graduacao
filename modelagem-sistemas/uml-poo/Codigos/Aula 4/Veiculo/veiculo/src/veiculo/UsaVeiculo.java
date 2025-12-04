package veiculo;

import java.util.Iterator;

public class UsaVeiculo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Veiculo v1 = new Veiculo();
		v1.nome = "Gol";
		v1.setVelocidade(80);
		
		for (int i = 0; i < 25; i++) {
			v1.acelera();
		}
		
		System.out.println("Velocidade = " + v1.getVelocidade());
		
		v1.setVelocidade(10);
		for (int i = 0; i < 25; i++) {
			v1.frea();
		}
		
		System.out.println("Velocidade = " + v1.getVelocidade());
		
	}

}
