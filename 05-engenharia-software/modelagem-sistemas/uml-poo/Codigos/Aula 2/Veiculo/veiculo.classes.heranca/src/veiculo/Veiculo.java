package veiculo;

public abstract class Veiculo {
	public String nome;
	public float velocidade;
	public float velocidadeMaxima;
	
	public Veiculo () {}
	
	// Construtor
	public Veiculo(float velocidade)
	{
		this.velocidade = velocidade;
	}

	public Veiculo(float velocidade, float velocidadeMaxima)
	{
		this.velocidade = velocidade;
		setVelocidadeMaxima(velocidadeMaxima);
	}
	
	// Resgatar
	public float getVelocidade() {
		return velocidade;
	}

	private void setVelocidadeMaxima(float velocidadeMaxima) {
		if (velocidadeMaxima <= 220) {
			this.velocidadeMaxima = velocidadeMaxima ;			
		}
	}
	
	public void acelera() throws InterruptedException
	{
		if (velocidade<160) {
			velocidade++;
			if (velocidade % 10 == 0) {
				Thread.sleep(1000);
				System.out.println(velocidade);				
			}
		}
	}
	
	public void frea() throws InterruptedException
	{
		if(velocidade>0) {
			velocidade -= 10;
			if (velocidade % 10 == 0) {
				Thread.sleep(1000);
				System.out.println(velocidade);				
			}
		}
	}
	
}
