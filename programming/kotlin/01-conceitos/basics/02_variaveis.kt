// Variáveis em Kotlin 

// Variáveis são conteineres para armazenar algum dado
// Para cirar uma variável, pode ser usado val ou var, 
// e sinal de igual (=) para atribuir valor

fun main() {
    // exemploVarVal()
    exemploVar()         
}

fun exemploVarVal() {
    var nome = "Roberto"
    val anoNascimento = 1990
    
    println(nome)          
    println(anoNascimento)
}

fun exemploVar() {
    var idade = 27
    var cep = "00665530"

    println(idade)
    println(cep)

    idade = 30
    cep = "0000001"

    println(idade)
    println(cep)
}