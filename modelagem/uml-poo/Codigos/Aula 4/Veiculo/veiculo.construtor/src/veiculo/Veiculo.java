package veiculo;

public class Veiculo {
	public String nome;
	private float velocidade;
	
	public Veiculo () {}
	
	// Construtor
	public Veiculo(float velocidade)
	{
		this.velocidade = velocidade;
	}
	
	// Resgatar
	public float getVelocidade() {
		return velocidade;
	}
	// Definir
	private void setVelocidade(float velocidade) {
		this.velocidade = velocidade;
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
