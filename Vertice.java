import java.util.ArrayList;
import java.lang.String;

public class Vertice<Integer> {
    private int dado;
    private ArrayList<Aresta<Integer>> arestasIn;
    private ArrayList<Aresta<Integer>> arestasOut;
    private String estado;
    private int preced;
    private int d;
    private int f;
    private String colort;

    // Descrição: Inicializa um novo vértice com o dado especificado.
    // Entrada: Inteiro representando o dado do vértice (int dado).
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: Um vértice é criado com listas de arestas de entrada e saída vazias, estado inicial como "branco", e predecessor como -1.
    public Vertice(int dado){
        this.dado = dado;
        this.arestasIn = new ArrayList<Aresta<Integer>>();
        this.arestasOut = new ArrayList<Aresta<Integer>>();
        this.estado = "branco";
        this.preced = -1;
        this.colort = "branco";
    }

    // Descrição: Define a cor do vértice.
    // Entrada: String representando a cor (String cor).
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: A cor do vértice é atualizada para o valor fornecido.
    public void setColor(String cor){
        this.colort = cor;
    }

    // Descrição: Retorna a cor atual do vértice.
    // Entrada: Nenhuma.
    // Retorno: String representando a cor do vértice.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public String getColor(){
        return this.colort;
    }

    // Descrição: Retorna o tempo de descoberta do vértice no contexto de uma busca em profundidade.
    // Entrada: Nenhuma.
    // Retorno: Inteiro representando o tempo de descoberta.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public int getD() {
        return d;
    }

    // Descrição: Define o tempo de descoberta do vértice.
    // Entrada: Inteiro representando o tempo de descoberta (int d).
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O tempo de descoberta do vértice é atualizado para o valor fornecido.
    public void setD(int d) {
        this.d = d;
    }

    // Descrição: Retorna o tempo de finalização do vértice no contexto de uma busca em profundidade.
    // Entrada: Nenhuma.
    // Retorno: Inteiro representando o tempo de finalização.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public int getF() {
        return f;
    }

    // Descrição: Define o tempo de finalização do vértice.
    // Entrada: Inteiro representando o tempo de finalização (int f).
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O tempo de finalização do vértice é atualizado para o valor fornecido.
    public void setF(int f) {
        this.f = f;
    }

    // Descrição: Retorna o predecessor do vértice em uma busca de grafo.
    // Entrada: Nenhuma.
    // Retorno: Inteiro representando o dado do predecessor.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public int getPreced() {
        return preced;
    }

    // Descrição: Define o predecessor do vértice.
    // Entrada: Inteiro representando o dado do predecessor (int preced).
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O predecessor do vértice é atualizado para o valor fornecido.
    public void setPreced(int preced) {
        this.preced = preced;
    }

    // Descrição: Retorna o estado atual do vértice.
    // Entrada: Nenhuma.
    // Retorno: String representando o estado do vértice ("branco", "cinza", ou "preto").
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public String getEstado() {
        return estado;
    }

    // Descrição: Define o estado do vértice.
    // Entrada: String representando o novo estado (String estado).
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O estado do vértice é atualizado para o valor fornecido.
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Descrição: Retorna a lista de arestas que entram no vértice.
    // Entrada: Nenhuma.
    // Retorno: ArrayList de Aresta<Integer> representando as arestas de entrada.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public ArrayList<Aresta<Integer>> getArestasIn() {
        return arestasIn;
    }

    // Descrição: Define a lista de arestas que entram no vértice.
    // Entrada: ArrayList de Aresta<Integer> contendo as arestas de entrada.
    // Retorno: Nenhum.
    // Pré-condição: A lista fornecida deve ser válida.
    // Pós-condição: A lista de arestas de entrada do vértice é atualizada.
    public void setArestasIn(ArrayList<Aresta<Integer>> arestasIn) {
        this.arestasIn = arestasIn;
    }

    // Descrição: Retorna a lista de arestas que saem do vértice.
    // Entrada: Nenhuma.
    // Retorno: ArrayList de Aresta<Integer> representando as arestas de saída.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public ArrayList<Aresta<Integer>> getArestasOut() {
        return arestasOut;
    }

    // Descrição: Define a lista de arestas que saem do vértice.
    // Entrada: ArrayList de Aresta<Integer> contendo as arestas de saída.
    // Retorno: Nenhum.
    // Pré-condição: A lista fornecida deve ser válida.
    // Pós-condição: A lista de arestas de saída do vértice é atualizada.
    public void setArestasOut(ArrayList<Aresta<Integer>> arestasOut) {
        this.arestasOut = arestasOut;
    }

    // Descrição: Retorna o dado armazenado no vértice.
    // Entrada: Nenhuma.
    // Retorno: Inteiro representando o dado do vértice.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    //condição: O dado do vértice é atualizado para o valor fornecido.
    public int getDado() {
        return dado;
    }

    // Descrição: Define o dado do vértice.
    // Entrada: Inteiro representando o novo dado (int dado).
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O dado do vértice é atualizado para o valor fornecido.
    public void setDado(int dado) {
        this.dado = dado;
    }

    // Descrição: Adiciona uma aresta à lista de arestas de entrada ou saída do vértice, respectivamente.
    // Entrada: Aresta<Integer> a ser adicionada.
    // Retorno: Nenhum.
    // Pré-condição: A aresta deve estar corretamente inicializada.
    // Pós-condição: A aresta é adicionada à lista de entrada ou saída do vértice, dependendo do método.
    public void adicionarArestaIn(Aresta<Integer> aresta){
        this.arestasIn.add(aresta);
    }

    // Descrição: Adiciona uma aresta à lista de arestas de entrada ou saída do vértice, respectivamente.
    // Entrada: Aresta<Integer> a ser adicionada.
    // Retorno: Nenhum.
    // Pré-condição: A aresta deve estar corretamente inicializada.
    // Pós-condição: A aresta é adicionada à lista de entrada ou saída do vértice, dependendo do método.
    public void adicionarArestaOut(Aresta<Integer> aresta){
        this.arestasOut.add(aresta);
    }

}
