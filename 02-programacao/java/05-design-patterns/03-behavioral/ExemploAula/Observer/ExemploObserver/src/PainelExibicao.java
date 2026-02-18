class PainelExibicao implements Observer {
    @Override
    public void atualizar(int temperatura) {
        System.out.println("Painel de Exibição: Temperatura atual: " + temperatura + "°C");
    }
     
}