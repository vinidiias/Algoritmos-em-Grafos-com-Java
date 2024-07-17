import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

    private boolean orientado;
    private int numVertices;
    private List<ArestaKruskal> arestas;
    private List<ArestaKruskal> arvoreGeradoraMinima;
    private int pesoTotal;

    // Classe para representar uma aresta
    static class ArestaKruskal implements Comparable<ArestaKruskal> {
        int inicio, fim;
        int peso;
        String cor;

        public ArestaKruskal(int inicio, int fim, int peso) {
            this.inicio = inicio;
            this.fim = fim;
            this.peso = peso;
            this. cor = "preto";
        }

        @Override
        public int compareTo(ArestaKruskal aresta) {
            return this.peso - aresta.peso;
        }
    }

    // Descrição: Adiciona o peso de uma aresta ao peso total da árvore geradora mínima.
    // Entrada: Peso da aresta como inteiro (int peso).
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O peso total da árvore geradora mínima é incrementado pelo valor fornecido.
    public void somaPeso(int peso){
        this.pesoTotal += peso;
    }

    // Descrição: Retorna o peso total acumulado da árvore geradora mínima.
    // Entrada: Nenhuma.
    // Retorno: Peso total como inteiro.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public int getPeso(){
        return this.pesoTotal;
    }

    // Descrição: Define a lista de arestas que compõem a árvore geradora mínima.
    // Entrada: Lista de arestas (List<ArestaKruskal> arvMin).
    // Retorno: Nenhum.
    // Pré-condição: A lista de arestas deve estar corretamente formada com base no algoritmo de Kruskal.
    // Pós-condição: A árvore geradora mínima é atualizada com a lista de arestas fornecida.
    public void setArvoreMin(List<ArestaKruskal> arvMin){
        this.arvoreGeradoraMinima = arvMin;
    }

    // Descrição: Retorna a lista de arestas que compõem a árvore geradora mínima.
    // Entrada: Nenhuma.
    // Retorno: Lista de arestas (List<ArestaKruskal>).
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public List<ArestaKruskal> getArvMin() {
        return this.arvoreGeradoraMinima;
    }

    // Descrição: Inicializa uma nova instância do algoritmo de Kruskal, preparando as listas para armazenar as arestas e a árvore geradora mínima.
    // Entrada: Nenhuma.
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: A instância está pronta para ter arestas adicionadas e processar a árvore geradora mínima.

    public KruskalAlgorithm(){
        this.arestas = new ArrayList<>();
        this.pesoTotal = 0;
        this.arvoreGeradoraMinima = new ArrayList<>();
    }

    // Função para encontrar a árvore geradora mínima usando Kruskal
    // Descrição: Executa o algoritmo de Kruskal para encontrar a árvore geradora mínima do grafo.
    // Entrada: Nenhuma.
    // Retorno: Lista de arestas que compõem a árvore geradora mínima (List<ArestaKruskal>).
    // Pré-condição: As arestas devem ser adicionadas ao grafo antes da execução.
    // Pós-condição: A árvore geradora mínima é formada e pode ser acessada ou modificada posteriormente.
    public List<ArestaKruskal> kruskall() {
        List<ArestaKruskal> arvoreGeradoraMinima = new ArrayList<>();

        // Ordena as arestas em ordem crescente de peso
        Collections.sort(this.arestas);

        // Array para armazenar o conjunto de cada vértice
        int[] conjuntos = new int[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            conjuntos[i] = i;
        }

        // Percorre as arestas em ordem crescente de peso
        for (ArestaKruskal aresta : this.arestas) {
            int conjuntoInicio = encontraConjunto(conjuntos, aresta.inicio);
            int conjuntoFim = encontraConjunto(conjuntos, aresta.fim);
            if (conjuntoInicio != conjuntoFim) {
                arvoreGeradoraMinima.add(aresta);
                uniao(conjuntos, conjuntoInicio, conjuntoFim);
            }
        }

        return arvoreGeradoraMinima;
    }

    // Descrição: Verifica se o grafo é conexo utilizando o algoritmo de Kruskal. O método determina se todos os vértices estão conectados em um único conjunto após tentar unir todas as arestas usando a estrutura de união e busca.
    // Entrada: Nenhuma.
    // Retorno: Booleano que indica se o grafo é conexo (true) ou não (false).
    // Pré-condição: As arestas do grafo devem estar adicionadas à lista de arestas e o número de vértices deve ser definido. O método assume que a lista de arestas e os vértices estão corretamente inicializados.
    // Pós-condição: Retorna true se todos os vértices estiverem conectados em um único conjunto, indicando que o grafo é conexo. Retorna false se algum vértice estiver em um conjunto separado, indicando que o grafo não é conexo.
    public boolean isGrafoConexo() {
        // Array para armazenar o conjunto de cada vértice após o processo de Kruskal
        int[] conjuntos = new int[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            conjuntos[i] = i;
        }

        // Processa todas as arestas como no Kruskal para formar a árvore geradora mínima
        Collections.sort(this.arestas);
        for (ArestaKruskal aresta : this.arestas) {
            int conjuntoInicio = encontraConjunto(conjuntos, aresta.inicio);
            int conjuntoFim = encontraConjunto(conjuntos, aresta.fim);
            if (conjuntoInicio != conjuntoFim) {
                uniao(conjuntos, conjuntoInicio, conjuntoFim);
            }
        }

        // Verifica se todos os vértices estão no mesmo conjunto
        int conjuntoInicial = encontraConjunto(conjuntos, 0);
        for (int i = 1; i < this.numVertices; i++) {
            if (encontraConjunto(conjuntos, i) != conjuntoInicial) {
                return false; // O grafo não é conexo
            }
        }

        return true; // O grafo é conexo
    }

    public boolean isOrientado(){
        return this.orientado;
    }
    // Função para encontrar o conjunto de um vértice
    // Descrição: Encontra o conjunto ao qual um vértice pertence, utilizando compressão de caminho para otimizar futuras buscas.
    // Entrada: Array de conjuntos (int[] conjuntos) e o vértice cujo conjunto é procurado (int v).
    // Retorno: O índice do conjunto ao qual o vértice pertence.
    // Pré-condição: O array de conjuntos deve estar inicializado e o vértice deve existir no grafo.
    // Pós-condição: O conjunto do vértice é potencialmente alterado devido à compressão de caminho, otimizando buscas futuras.
    public static int encontraConjunto(int[] conjuntos, int v) {
        if (conjuntos[v] != v) {
            conjuntos[v] = encontraConjunto(conjuntos, conjuntos[v]); // Compressão de caminho
        }
        return conjuntos[v];
    }
    public boolean generateDotFileKruskal(ColorPallets colors) {
        String filePath = "kruskal_agm.dot";
        try (FileWriter dotFile = new FileWriter(filePath)) {
            dotFile.write("graph G {\n");
            dotFile.write("  rankdir=LR;\n");
            dotFile.write("layout = neato;\n\n");
            dotFile.write("node [shape=circle];\n\n");

            int i = 0;
            for(ArestaKruskal aresta : this.arvoreGeradoraMinima){
                dotFile.write("    " + aresta.inicio + " -- " + aresta.fim +   " [color=" + colors.getColor(i)  + "];\n");
                i++;
            }

            // Marca as arestas na AGM com cor diferente
            for (ArestaKruskal aresta : this.arestas) {
                String color = "gray"; // cor padrão para arestas não incluídas na AGM
                if (this.arvoreGeradoraMinima.contains(aresta)) {
                    color = "black"; // cor para arestas na AGM
                }
                dotFile.write("  " + aresta.inicio + " -- " + aresta.fim +
                        " [label=\"" + aresta.peso + "\""  + "];\n");
            }

            dotFile.write("}\n");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo DOT: " + e.getMessage());
            return false;
        }
    }
    public void generateGraphImage() {
        String graphvizPath = "\"C:\\Program Files\\Graphviz\\bin\\dot\"";
        String command = graphvizPath + " -Tpng kruskal_agm.dot -o graphKruskal.png";

        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.err.println("Erro ao gerar a imagem do grafo: " + e.getMessage());
        }
    }
    public void processarArquivoKruskal(String caminhoDoArquivo){
        try(BufferedReader reader = new BufferedReader(new FileReader(caminhoDoArquivo))){
            String linha;
            this.arestas = new ArrayList<>();
            while((linha = reader.readLine()) != null){
                if(linha.startsWith("orientado=")){
                    this.orientado = linha.split("=")[1].trim().equalsIgnoreCase("sim");
                }else if(linha.startsWith("V=")){
                    this.numVertices = java.lang.Integer.parseInt(linha.split("=")[1].trim());
                }else if(linha.startsWith("(")){
                    processarAresta(linha);
                }
            }
        }catch (IOException e){
            System.out.println("Erro ao ler o arquivo:");
            e.printStackTrace();
        }
    }
    private void processarAresta(String linha) {
        // Suponha que a linha esteja no formato "(0,1):11"
        String[] partes = linha.split(":");
        String vertices = partes[0]; // "(0,1)"
        int peso = java.lang.Integer.parseInt(partes[1]); // "11"

        // Extrair os vértices individuais
        String[] verticesPartes = vertices.replace("(", "").replace(")", "").split(",");
        int origem = java.lang.Integer.parseInt(verticesPartes[0]); // "0"
        int destino = java.lang.Integer.parseInt(verticesPartes[1]); // "1"

        this.arestas.add(new ArestaKruskal(origem,  destino, peso));
        if(!this.orientado){
            arestas.add(new ArestaKruskal(destino, origem, peso));
            //System.out.printf("Aresta de %d para %d com peso %d%n", destino, origem, peso);
        }

        // Implemente como armazenar essa aresta, por exemplo, adicionando a uma lista ou a uma matriz de adjacência
        //System.out.printf("Aresta de %d para %d com peso %d%n", origem, destino, peso);
    }


    // Função para unir dois conjuntos
    // Descrição: Une dois conjuntos de vértices em um só, parte do processo de formação da árvore geradora mínima no algoritmo de Kruskal.
    // Entrada: Array de conjuntos (int[] conjuntos), conjunto de origem (int conjuntoInicio), conjunto de destino (int conjuntoFim).
    // Retorno: Nenhum.
    // Pré-condição: Ambos os conjuntos devem existir.
    // Pós-condição: Os conjuntos são unidos, indicando que seus vértices estão conectados na árvore geradora mínima.
    public static void uniao(int[] conjuntos, int conjunto1, int conjunto2) {
        conjuntos[conjunto1] = conjunto2;
    }

    /*public static void main(String[] args) {
        int V = 7; // Número de vértices

        // Lista de arestas do grafo
        List<Aresta> arestas = new ArrayList<>();
        arestas.add(new Aresta(0, 2, 8));
        arestas.add(new Aresta(0, 3, 5));
        arestas.add(new Aresta(0, 5, 11));
        arestas.add(new Aresta(1, 3, 2));
        arestas.add(new Aresta(1, 4, 18));
        arestas.add(new Aresta(2, 3, 7));
        arestas.add(new Aresta(2, 5, 4));
        arestas.add(new Aresta(2, 4, 3));
        arestas.add(new Aresta(4, 5, -1));
        arestas.add(new Aresta(4, 6, 5));
        arestas.add(new Aresta(5, 6, 17));

        // Chamando o algoritmo de Kruskal para encontrar a árvore geradora mínima
        List<Aresta> arvoreGeradoraMinima = kruskall(arestas, V);

        // Calculando o peso total da árvore geradora mínima
        int pesoTotal = 0;
        for (Aresta aresta : arvoreGeradoraMinima) {
            pesoTotal += aresta.peso;
        }

        // Imprimindo o resultado
        System.out.println("Peso total: " + pesoTotal);
        System.out.print("Arestas: ");
        for (Aresta aresta : arvoreGeradoraMinima) {
            System.out.print("(" + aresta.inicio + "," + aresta.fim + ") ");
        }
    }*/
}
