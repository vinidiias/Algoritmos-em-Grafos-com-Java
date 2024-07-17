public class Aresta<Integer> {
    private Double valor;
    private Vertice<Integer> inicio;
    private Vertice<Integer> fim;

    // Descrição: Constrói uma nova aresta com um valor específico, vértice de início e vértice de fim.
    // Entrada: Valor da aresta como Double (valor), vértice de início (Vertice<Integer> inicio), e vértice de fim (Vertice<Integer> fim).
    // Retorno: Nenhum.
    // Pré-condição: Os vértices de início e fim devem ser inicializados antes de serem passados para o construtor.
    // Pós-condição: Uma nova aresta é criada ligando os dois vértices com o valor especificado.
    public Aresta(Double valor, Vertice<Integer> inicio, Vertice<Integer> fim){
        this.valor = valor;
        this.inicio = inicio;
        this.fim = fim;
    }

    // Descrição: Define ou atualiza o valor da aresta.
    // Entrada: O novo valor da aresta como Double (valor).
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O valor da aresta é atualizado para o novo valor fornecido.
    public Double getValor() {
        return valor;
    }

    // Descrição: Define ou atualiza o valor da aresta.
    // Entrada: O novo valor da aresta como Double (valor).
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O valor da aresta é atualizado para o novo valor fornecido.
    public void setValor(Double valor) {
        this.valor = valor;
    }

    // Descrição: Retorna o vértice de início da aresta.
    // Entrada: Nenhuma.
    // Retorno: O vértice de início da aresta como Vertice<Integer>.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public Vertice<Integer> getInicio() {
        return inicio;
    }

    // Descrição: Define ou atualiza o vértice de início da aresta.
    // Entrada: O vértice que será o novo início da aresta (Vertice<Integer> inicio).
    // Retorno: Nenhum.
    // Pré-condição: O vértice passado deve estar inicializado.
    // Pós-condição: O vértice de início da aresta é atualizado para o novo vértice fornecido.
    public void setInicio(Vertice<Integer> inicio) {
        this.inicio = inicio;
    }

    // Descrição: Retorna o vértice de fim da aresta.
    // Entrada: Nenhuma.
    // Retorno: O vértice de fim da aresta como Vertice<Integer>.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public Vertice<Integer> getFim() {
        return fim;
    }

    // Descrição: Define ou atualiza o vértice de fim da aresta.
    // Entrada: O vértice que será o novo fim da aresta (Vertice<Integer> fim).
    // Retorno: Nenhum.
    // Pré-condição: O vértice passado deve estar inicializado.
    // Pós-condição: O vértice de fim da aresta é atualizado para o novo vértice fornecido.
    public void setFim(Vertice<Integer> fim) {
        this.fim = fim;
    }


}
