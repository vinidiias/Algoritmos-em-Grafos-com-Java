public class ColorPallets {
    private String[] colors;

    // Descrição: Inicializa o objeto ColorPallets e popula o array de cores com um conjunto pré-definido de cores.
    // Entrada: Nenhuma.
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O array de cores é inicializado com valores de cores pré-definidos.
    public ColorPallets(){
        colors = new String[9];
        colors[0] = "red";
        colors[1] = "yellow";
        colors[2] = "green";
        colors[3] = "gray";
        colors[4] = "blue";
        colors[5] = "pink";
        colors[6] = "purple";
        colors[7] = "brown";
        colors[8] = "orange";
    }

    // Descrição: Recupera uma cor do array de cores com base no índice fornecido.
    // Entrada: Índice inteiro representando a posição no array de cores.
    // Retorno: A string de cor na posição especificada do array de cores.
    // Pré-condição: O índice deve estar dentro dos limites do array de cores (0 a 8).
    // Pós-condição: Nenhuma.
    public String getColor(int indice){
        return colors[indice];
    }
}
