/**
 * Classe que representa uma forma geométrica que pode ser clonada
 * 
 * Demonstra clonagem simples (shallow copy) onde apenas os
 * atributos primitivos são copiados.
 * 
 * @author Aulas Graduação
 */
public abstract class Forma implements Prototipo {
    
    protected String cor;
    protected int x;
    protected int y;
    
    public Forma() {
        this.cor = "Preto";
        this.x = 0;
        this.y = 0;
    }
    
    public Forma(Forma forma) {
        this.cor = forma.cor;
        this.x = forma.x;
        this.y = forma.y;
    }
    
    @Override
    public abstract Prototipo clonar();
    
    @Override
    public abstract void exibirInfo();
    
    // Getters e Setters
    public void setCor(String cor) { this.cor = cor; }
    public void setPosicao(int x, int y) { this.x = x; this.y = y; }
    public String getCor() { return cor; }
    public int getX() { return x; }
    public int getY() { return y; }
}
