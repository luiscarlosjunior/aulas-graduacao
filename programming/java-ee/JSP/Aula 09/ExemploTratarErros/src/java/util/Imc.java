package util;

public class Imc {
    
    private float peso;
    private float altura;
    
    private float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    private float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
    
    public float retornarIMC() throws Exception {
        
        float resultado = 0.0f;
        
        try {
            resultado = getPeso() / (getAltura()* getAltura());
        } catch (Exception e) {
           throw new Exception();
        }
        return resultado;
    }
}
