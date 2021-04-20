package veiculo;

public class Veiculo {
	public String nome;
	private float velocidade;
	
	public float getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(float velocidade) {
		this.velocidade = velocidade;
	}
	
	public void acelera() throws InterruptedException
	{
		if (velocidade<160) {
			velocidade++;
			if (velocidade % 5 == 0) {
				Thread.sleep(2000);
				System.out.println(velocidade);				
			}
		}
	}
	
	public void frea() throws InterruptedException
	{
		if(velocidade>0) {
			Thread.sleep(2000);
			velocidade -= 10;
		}
	}
	
}
