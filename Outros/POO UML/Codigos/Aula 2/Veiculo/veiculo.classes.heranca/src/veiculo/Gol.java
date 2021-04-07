package veiculo;

public class Gol extends Veiculo {
	
	private final float velocidadeMaximaCarro = 180;
	
	public Gol(float velocidade, float velocidadeMaxima) {
		this.velocidade = velocidade;
		
		if (velocidadeMaxima <= velocidadeMaximaCarro) {
			this.velocidadeMaxima = velocidadeMaxima;
		}
		else
		{
			this.velocidadeMaxima = velocidadeMaximaCarro;
		}
		/*
		this.velocidadeMaxima = 
				velocidadeMaxima <= velocidadeMaximaCarro 
				? velocidadeMaxima : velocidadeMaximaCarro;
		*/
	}
	
	// Polimorfismo
	public void acelera() throws InterruptedException
	{
		if (velocidade<velocidadeMaxima) {
			velocidade++;
			if (velocidade % 10 == 0) {
				Thread.sleep(1000);
				System.out.println(velocidade);				
			}
		}
	}
}
