public class Diretor extends Handler {
    @Override
    public void handle(double valor) {
        if (valor <= 20000) {
            System.out.println("Diretor aprovou o valor de: " + valor);
        } else {
            System.out.println("Valor de: " + valor + " excede o limite de aprovação do Diretor. Encaminhar para o próximo nível.");
        }
    }
}