package jogoForca

// SECÇÃO 1: DICIONÁRIO
fun prepararDicionario(): MutableMap<String, List<String>> {
    val dicionario = mutableMapOf<String, List<String>>()

    dicionario["Filmes"]    = listOf("Titanic", "007 Skyfall", "The Godfather", "The Dark Knight", "Fight Club", "The Matrix", "Jurassic Park", "Home Alone")
    dicionario["Desportos"] = listOf("Futebol", "Basquetebol", "Voleibol", "Andebol", "Hóquei", "Futsal", "Golf", "Xadrez", "Basebol", "Criquete")
    dicionario["Animais"]   = listOf("Leão", "Cabra", "Cão", "Gato", "Corvo", "Hamster", "Coelho", "Tartaruga", "Polvo", "Lobo", "Raposa", "Macaco", "Cavalo", "Pato")
    dicionario["Comida"]    = listOf("Francesinha", "Bacalhau", "Frango", "Arroz", "Queijo", "Massa", "Bolo", "Feijão", "Puré", "Lasanha", "Sopa", "Kebab")
    dicionario["Cores"]     = listOf("Roxo", "Preto", "Azul", "Vermelho", "Castanho", "Amarelo", "Verde", "Laranja", "Cinzento", "Bege", "Dourado", "Violeta")
    dicionario["Jogos"]     = listOf("Minecraft", "Fifa", "Assassin's Creed", "CSGO", "League of Legends", "Valorant", "Elden Ring", "Call of Duty", "GTA")

    return dicionario
}

// SECÇÃO 2: SORTEIO
fun sortearDesafio(dicionario: Map<String, List<String>>): Pair<String, String> {
    val temaSorteado = dicionario.keys.random()
    val listaDeFrases = dicionario[temaSorteado]!!
    val fraseSorteada = listaDeFrases.random()

    return Pair(temaSorteado, fraseSorteada)
}

// SECÇÃO 3: SOMBRA (parte visual)
fun gerarSombra(fraseReal: String): MutableList<Char> {
    val sombra = mutableListOf<Char>()
    for (letra in fraseReal) {
        if (letra == ' ') {
            sombra.add(' ')
        } else {
            sombra.add('-')
        }
    }
    return sombra
}

// SECÇÃO 4: CONVERSÃO (parte técnica)
fun converterParaLista(frase: String): MutableList<Char> {
    return frase.toMutableList()
}

// SECÇÃO 5: JOGO
fun executarJogo(fraseReal: MutableList<Char>, sombra: MutableList<Char>) {
    var vidas = 10
    val letrasUsadas = mutableListOf<Char>()

    while (vidas > 0 && sombra.contains('-')) {
        println("\nDesafio: ${sombra.joinToString("")}")
        println("Vidas: $vidas | Letras já usadas: $letrasUsadas")
        print("Escreve uma letra: ")

        val input = readln().uppercase()

        if (input.isNotEmpty()) {
            val letra = input[0]

            // Verifica se a letra já foi usada antes de processar
            if (!letrasUsadas.contains(letra)) {
                letrasUsadas.add(letra)

                // 1. Verifica se a letra existe na frase real
                var encontrou = false
                for (caracter in fraseReal) {
                    if (caracter.uppercaseChar() == letra) {
                        encontrou = true
                        break
                    }
                }

                // 2. Feedback e revelação na sombra
                if (encontrou) {
                    for (posicao in fraseReal.indices) {
                        if (fraseReal[posicao].uppercaseChar() == letra) {
                            sombra[posicao] = fraseReal[posicao]
                        }
                    }
                    println(" Parabéns filhinho! A letra '$letra' existe na palavra!")
                } else {
                    vidas--
                    println("Ai ai ai, essa letra não existe! Vidas restantes: $vidas")
                }
            } else {
                println(" Já usaste essa letra! Tenta outra.")
            }
        }
    }

    // Resultado Final
    println()
    if (!sombra.contains('-')) {
        println("GANHASTE! É bem campeão/ã!")
    } else {
        println("PERDESTE! Mais sorte para a próxima e goodbye que eu me goodfico.")
    }
    println("A palavra era: ${fraseReal.joinToString("")}")
    println()
}

fun main() {
    println("==============================")
    println("       JOGO DA FORCA          ")
    println("==============================")

    val aqueleDicionario = prepararDicionario()
    val (tema, frase) = sortearDesafio(aqueleDicionario)
    
    println("\nO tema é: $tema")

    val sombra = gerarSombra(frase)
    val fraseComoLista = converterParaLista(frase)

    executarJogo(fraseComoLista, sombra)
}
