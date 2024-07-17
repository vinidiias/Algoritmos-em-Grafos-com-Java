import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.util.Stack;
import java.util.Collections;

public class Grafo<Integer>{
    private ArrayList<Aresta<Integer>> arestas;
    private ArrayList<Vertice<Integer>> vertices;
    private int timestamp;
    private int nroComp;
    private boolean orientado;
    private int numVertices;
    public Grafo(){
        this.vertices = new ArrayList<Vertice<Integer>>();
        this.arestas = new ArrayList<Aresta<Integer>>();
    }
    // Descrição: Retorna se o grafo é orientado ou não.
    // Entrada: Nenhuma.
    // Retorno: Booleano indicando se o grafo é orientado.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public boolean getOrientado(){
        return this.orientado;
    }
    // Descrição: Retorna o valor atual do timestamp usado em operações de DFS.
    // Entrada: Nenhuma.
    // Retorno: Inteiro representando o timestamp atual.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.

    public int getTimestamp() {
        return timestamp;
    }

    // Descrição: Retorna a lista de vértices do grafo.
    // Entrada: Nenhuma.
    // Retorno: ArrayList de vértices do grafo.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public ArrayList<Vertice<Integer>> getVertices() {
        return vertices;
    }
    public ArrayList<Aresta<Integer>> getArestas(){
        return arestas;
    }

    // Descrição: Define o valor do timestamp.
    // Entrada: Inteiro com o novo valor do timestamp.
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O timestamp do grafo é atualizado para o valor fornecido.
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    // Descrição: Adiciona um novo vértice ao grafo.
    // Entrada: Inteiro representando o dado do novo vértice.
    // Retorno: Nenhum.
    // Pré-condição: Nenhuma.
    // Pós-condição: O grafo terá um vértice adicional.
    public void adicionarVertice(int dado){
        Vertice<Integer> novoVertice = new Vertice<Integer>(dado);
        this.vertices.add(novoVertice);
    }
    // Descrição: Adiciona uma nova aresta ao grafo entre dois vértices especificados.
    // Entrada: Double representando o peso da aresta, dois inteiros indicando os vértices de início e fim.
    // Retorno: Nenhum.
    // Pré-condição: Os vértices de início e fim devem existir no grafo.
    // Pós-condição: O grafo terá uma aresta adicional conectando os dois vértices especificados.
    public void adicionarAresta(Double peso, int dadoInicio, int dadoFim){
        Vertice<Integer> inicio = this.getVertice(dadoInicio);
        Vertice<Integer> fim = this.getVertice(dadoFim);
        Aresta<Integer> aresta = new Aresta(peso, inicio, fim);
        inicio.adicionarArestaOut(aresta);
        fim.adicionarArestaIn(aresta);
        this.arestas.add(aresta);
    }
    // Descrição: Retorna o vértice correspondente ao dado especificado.
    // Entrada: Inteiro representando o dado do vértice.
    // Retorno: Vértice correspondente ao dado, ou null se não encontrado.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    public Vertice<Integer> getVertice(int dado) {
        Vertice<Integer> vertice = null;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getDado() == dado) {
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }

    // Descrição: Realiza uma busca em largura no grafo a partir do primeiro vértice.
    // Entrada: Nenhuma.
    // Retorno: Nenhum.
    // Pré-condição: O grafo deve conter pelo menos um vértice.
    // Pós-condição: Os vértices visitados durante a busca serão impressos na saída.
    public void buscaEmLargura(int src){
        System.out.println("Busca em Largura (Breadth-First-Search - BFS)");
        ArrayList<Vertice<Integer>> marcados = new ArrayList<Vertice<Integer>>();
        ArrayList<Vertice<Integer>> fila = new ArrayList<Vertice<Integer>>();
        Vertice<Integer> atual = this.vertices.get(src);
        marcados.add(atual);
        System.out.print(atual.getDado()+ "  ");
        fila.add(atual);

        while(fila.size() > 0){
            Vertice<Integer> visitado = fila.get(0);

            for(int i = 0; i < visitado.getArestasOut().size(); i++) {
                Vertice<Integer> prox = visitado.getArestasOut().get(i).getFim();
                if (!marcados.contains(prox)) { //se o vertice ainda n foi marcado
                    marcados.add(prox);
                    System.out.print(prox.getDado() + "  ");
                    fila.add(prox);
                }
            }
            fila.remove(0);
        }
        System.out.println("\n");
    }

    // Descrição: Realiza uma busca em profundidade no grafo.
    // Entrada: Nenhuma.
    // Retorno: Nenhum.
    // Pré-condição: O grafo deve conter pelo menos um vértice.
    // Pós-condição: Os vértices visitados durante a DFS serão impressos na saída.
    public void DFS(){
        int timestamp;
        for(int i = 0; i < this.vertices.size(); i++){
            this.vertices.get(i).setEstado("branco");
            this.vertices.get(i).setPreced(-1);
        }
        this.timestamp = 0;
        System.out.println("Busca por profundidade (Depth-First-Search) - DFS:");
        for(int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getEstado().equals("branco")){DFS_visit(this.vertices.get(i));}
        }
        System.out.println();
    }

    // Descrição: Executa uma visita recursiva do algoritmo de busca em profundidade (DFS) no vértice especificado, marcando cada vértice visitado, registrando tempos de descoberta e finalização, e seguindo através das arestas que levam a vértices ainda não visitados.
    // Entrada: Vértice a ser visitado (Vertice<Integer> v).
    // Retorno: Nenhum.
    // Pré-condição: O vértice passado como argumento deve ser inicializado e parte do grafo.
    // Pós-condição: O vértice v e possivelmente outros vértices acessíveis a partir de v serão marcados como visitados. O estado dos vértices pode ser alterado para cinza ou preto, e seus tempos de descoberta e finalização são atualizados.
    public void DFS_visit(Vertice<Integer> v){
        v.setEstado("cinza");
        this.timestamp += 1;
        v.setD(this.timestamp);
        System.out.print(v.getDado() + "  ");
        for(int i = 0; i < v.getArestasOut().size(); i++){
            if(v.getArestasOut().get(i).getFim().getEstado().equals("branco")){
                v.getArestasOut().get(i).getFim().setPreced(v.getDado());
                DFS_visit(v.getArestasOut().get(i).getFim());
            }
        }
        v.setEstado("preto");
        this.timestamp += 1;
        v.setF(this.timestamp);
    }

    // Descrição: Executa o algoritmo de Bellman-Ford para encontrar o caminho mínimo do vértice de origem especificado para todos os outros vértices.
    // Entrada: Inteiro representando o vértice de origem.
    // Retorno: Nenhum.
    // Pré-condição: O vértice de origem deve existir no grafo.
    // Pós-condição: Os menores caminhos do vértice de origem para todos os outros vértices serão impressos.
    public void BellmanFord(int src) {
        int V = vertices.size();    // número de vértices
        int E = arestas.size();     // número de arestas

        Double dist[] = new Double[V];
        int[] pred = new int[V]; // Array para armazenar os predecessores dos vértices

        // Inicialize todas as distâncias como infinito e os predecessores como -1
        for (int i = 0; i < V; ++i) {
            dist[i] = 99999.0;
            pred[i] = -1;
        }
        dist[src] = 0.0;

        // Executar o algoritmo Bellman-Ford
        for (int i = 3; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = arestas.get(j).getInicio().getDado();
                int v = arestas.get(j).getFim().getDado();
                Double w = arestas.get(j).getValor();
                if (dist[u] != 99999.0 && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pred[v] = u; // Atualiza o predecessor de v
                }
            }
        }

        // Imprimir os menores caminhos e seus predecessores
        System.out.println("\nAlgoritmo Bellman-Ford:\nOrigem: " + src);
        for (int i = 0; i < V; ++i) {
            System.out.print("Vertice " + i + " dist.: " + dist[i]);
            if (dist[i] != 99999.0) {
                System.out.print(", Caminho: ");
                printPath(pred, i);
            }
            System.out.println();
        }
    }

    // Método para imprimir o caminho de um vértice até o vértice de origem
    // Descrição: Imprime recursivamente o caminho do vértice de origem até o vértice de destino especificado usando a matriz de predecessores.
    // Entrada: Array de inteiros contendo os predecessores de cada vértice (int[] pred) e o índice do vértice de destino (int dest).
    // Retorno: Nenhum.
    // Pré-condição: A matriz de predecessores deve ser preenchida corretamente pelo algoritmo que identifica caminhos, como Bellman-Ford.
    // Pós-condição: O caminho do vértice de origem até o vértice destino é impresso na saída padrão, mostrado de forma inversa (da origem para o destino).
    public void printPath(int[] pred, int dest) {
        if (dest == -1) return; // Se não há predecessores, retorna
        printPath(pred, pred[dest]);
        System.out.print(dest + " ");
    }

    // Descrição: Identifica e imprime as componentes conexas do grafo, utilizando uma série de buscas DFS para explorar completamente cada componente.
    // Entrada: Nenhuma.
    // Retorno: Nenhum.
    // Pré-condição: O grafo deve ser não-direcionado.
    // Pós-condição: Todas as componentes conexas do grafo serão identificadas e impressas, cada componente listada com seus vértices.
    public void ComponentesConexas(){
        this.nroComp = 0;
        for(int i = 0; i < this.vertices.size(); i++)
            this.vertices.get(i).setEstado("branco");
        for(int i = 0; i < this.vertices.size(); i++){
            if(this.vertices.get(i).getEstado().equals("branco")){
                this.nroComp += 1;
                System.out.print("Componente conexo " + this.nroComp + ": ");
                DFS_cc(this.vertices.get(i));
                System.out.println();
                }
        }
    }

    // Descrição: Função auxiliar para a identificação de componentes conexas, realiza uma busca em profundidade marcando todos os vértices alcançáveis a partir do vértice dado.
    // Entrada: Vértice de onde a busca deve começar (Vertice<Integer> v).
    // Retorno: Nenhum.
    // Pré-condição: O vértice deve estar inicializado e parte do grafo.
    // Pós-condição: Todos os vértices alcançáveis a partir do vértice de entrada serão explorados e marcados, contribuindo para a identificação de uma componente conexa.
    public void DFS_cc(Vertice<Integer> v){
        v.setEstado("cinza");
        System.out.print(v.getDado() + " ");
        for(int i = 0; i < v.getArestasOut().size(); i++){
            if(v.getArestasOut().get(i).getFim().getEstado().equals("branco")){
                DFS_cc(v.getArestasOut().get(i).getFim());
            }
        }
    }

    // Descrição: Calcula as componentes fortemente conexas do grafo, utilizando a transposição do grafo e uma série de buscas em profundidade.
    // Entrada: Objeto ColorPallets para atribuição de cores (ColorPallets colors).
    // Retorno: Retorna um novo grafo que representa o grafo transposto com as componentes fortemente conexas marcadas.
    // Pré-condição: O grafo deve ser direcionado.
    // Pós-condição: As componentes fortemente conexas do grafo original são calculadas e o grafo transposto com estas componentes marcadas é retornado.
    public Grafo<Integer> ComponentesFortementeConexas(ColorPallets colors){
        Grafo<Integer> grafoTransposto = this.grafoTransposto();
        grafoTransposto.DFS_ordemDecrescente(colors);

        return grafoTransposto;
    }

    // Descrição: Cria uma versão transposta do grafo original, invertendo a direção de todas as arestas.
    // Entrada: Nenhuma.
    // Retorno: Retorna um novo grafo que é o transposto do grafo original.
    // Pré-condição: O grafo original deve ter vértices e arestas definidos.
    // Pós-condição: Um novo grafo com todas as arestas invertidas em relação ao original é criado e retornado.
    public Grafo<Integer> grafoTransposto() {
        Grafo<Integer> grafoTransposto = new Grafo<>();

        // Adiciona os mesmos vértices do grafo original
        for (Vertice<Integer> vertice : this.vertices) {
            grafoTransposto.adicionarVertice(vertice.getDado());
        }

        // Inverte a direção de todas as arestas
        for (int i = 0; i < this.arestas.size(); i++) {
            int inicio = arestas.get(i).getInicio().getDado();
            int fim = arestas.get(i).getFim().getDado();
            grafoTransposto.adicionarAresta(arestas.get(i).getValor(), fim, inicio);
        }
        grafoTransposto.orientado = this.orientado;
        return grafoTransposto;
    }

    // Descrição: Imprime todas as arestas de entrada e saída de cada vértice do grafo, listando as conexões de cada vértice.
    // Entrada: Nenhuma.
    // Retorno: Nenhum.
    // Pré-condição: O grafo deve ter vértices com are

    public void imprimeArestas(){
        for(int i = 0; i < this.vertices.size(); i++){
            for(int j = 0; j < this.vertices.get(i).getArestasIn().size(); j++){
                System.out.println("vertice: " + this.vertices.get(i).getDado() + " Arestas de entrada : " + this.vertices.get(i).getArestasIn().get(j).getInicio().getDado() + " - " + this.vertices.get(i).getArestasIn().get(j).getFim().getDado());
            }
            System.out.println();
            for(int j = 0; j < this.vertices.get(i).getArestasOut().size(); j++){
                System.out.println("vertice: " + this.vertices.get(i).getDado() + " Arestas de saida : " + this.vertices.get(i).getArestasOut().get(j).getInicio().getDado() + " - " + this.vertices.get(i).getArestasOut().get(j).getFim().getDado());
            }
            System.out.println();
        }
    }

    // Descrição: Executa uma busca em profundidade em ordem decrescente, utilizada para encontrar componentes fortemente conexos após transpor o grafo.
    // Entrada: Objeto ColorPallets para gerenciar a cor de cada vértice durante a visualização.
    // Retorno: Nenhum.
    // Pré-condição: A lista de vértices deve ser inicializada e o objeto ColorPallets deve ser passado corretamente.
    // Pós-condição: As componentes fortemente conexos são identificadas e impressas, cada componente com sua própria cor.
    public void DFS_ordemDecrescente(ColorPallets colors){
        Stack<Vertice<Integer>> pilha = new Stack<>();
        for (Vertice<Integer> vertice : this.vertices) {
            vertice.setEstado("branco");
        }
        this.timestamp = 0;
        int j = 1;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getEstado().equals("branco")) {
                System.out.print("Componente fortemente conexo " + j + ": ");
                this.vertices.get(i).setColor(colors.getColor(i));
                DFS_visit_ordemDecrescente(this.vertices.get(i), pilha, colors.getColor(i));
                System.out.println();
                j++;
            }
        }
        System.out.println();
    }

    // Descrição: Visita recursivamente os vértices em ordem decrescente, marcando-os e explorando os vértices adjacentes que ainda não foram visitados.
    // Entrada: Vértice atual (Vertice<Integer> v), pilha para armazenar a ordem de finalização dos vértices (Stack<Vertice<Integer>>), cor a ser aplicada ao vértice durante a visita (String cor).
    // Retorno: Nenhum.
    // Pré-condição: O vértice e a pilha devem estar inicializados; o estado do vértice deve ser "branco" antes da visita.
    // Pós-condição: O vértice é marcado como visitado ("preto"), seu tempo de descoberta e finalização são registrados, e ele é adicionado à pilha.
    public void DFS_visit_ordemDecrescente(Vertice<Integer> v, Stack<Vertice<Integer>> pilha, String cor) {
        v.setEstado("cinza");
        this.timestamp += 1;
        v.setD(this.timestamp);
        System.out.print(v.getDado() + "  ");
        for (Aresta<Integer> aresta : v.getArestasOut()) {
            if (aresta.getFim().getEstado().equals("branco")) {
                aresta.getFim().setPreced(v.getDado());
                DFS_visit_ordemDecrescente(aresta.getFim(), pilha, cor);
                this.vertices.get(aresta.getFim().getDado()).setColor(cor);
            }
        }
        v.setEstado("preto");
        this.timestamp += 1;
        v.setF(this.timestamp);
        pilha.push(v);
    }

    // Descrição: Lê dados de um arquivo para construir o grafo, interpretando linhas que definem se o grafo é orientado e as arestas entre vértices.
    // Entrada: Caminho do arquivo que contém os dados do grafo (String caminhoDoArquivo).
    // Retorno: Nenhum.
    // Pré-condição: O arquivo deve existir no caminho especificado e deve estar no formato correto esperado pelo método.
    // Pós-condição: O grafo é construído ou modificado com base nos dados lidos do arquivo.
    public void processarArquivo(String caminhoDoArquivo){
        try(BufferedReader reader = new BufferedReader(new FileReader(caminhoDoArquivo))){
            String linha;

            while((linha = reader.readLine()) != null){
                if(linha.startsWith("orientado=")){
                    this.orientado = linha.split("=")[1].trim().equalsIgnoreCase("sim");
                }else if(linha.startsWith("V=")){
                    this.numVertices = java.lang.Integer.parseInt(linha.split("=")[1].trim());
                    for(int i = 0; i < this.numVertices; i++){this.adicionarVertice(i);}
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

        this.adicionarAresta((double) peso, origem, destino);
        if(!this.orientado){
            this.adicionarAresta((double) peso, destino, origem);
            //System.out.printf("Aresta de %d para %d com peso %d%n", destino, origem, peso);
        }

        // Implemente como armazenar essa aresta, por exemplo, adicionando a uma lista ou a uma matriz de adjacência
        //System.out.printf("Aresta de %d para %d com peso %d%n", origem, destino, peso);
    }
    // Gera um arquivo DOT representando um grafo.
    // Descrição: Gera um arquivo no formato DOT para representar o grafo, que pode ser usado para visualização usando ferramentas como Graphviz.
    // Entrada: Nenhuma.
    // Retorno: Booleano que indica se o arquivo foi criado com sucesso.
    // Pré-condição: O grafo deve ter vértices e arestas definidos.
    // Pós-condição: Um arquivo DOT é criado representando o grafo.
    public boolean generateDotFile() {
        ArrayList<Aresta<Integer>> edges = this.getArestas();  // Assume que getEdges retorna um ArrayList
        String separator = this.orientado ? "->" : "--";
        String graphType = this.orientado? "digraph" : "graph";

        try (FileWriter dotFile = new FileWriter("graph.dot")) {
            dotFile.write(graphType + " G {\n");
            dotFile.write("rankdir=LR;\n");
            dotFile.write("ranksep=1.0;\n");
            dotFile.write("nodesep=1.0;\n");
            dotFile.write("layout = neato;\n\n");
            dotFile.write("node [shape=circle];\n\n");

            for (Aresta edge : edges) {
                int vertex1 = edge.getInicio().getDado();
                int vertex2 = edge.getFim().getDado();
                Double weight = edge.getValor();
                dotFile.write("    " + vertex1 + separator + vertex2 + " [label=\"" + weight + "\"];\n");
            }

            dotFile.write("}\n");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo DOT: " + e.getMessage());
            return false;
        }
    }

    // Gera a imagem do grafo usando Graphviz.
    // Descrição: Gera uma imagem PNG do grafo usando o Graphviz a partir do arquivo DOT criado.
    // Entrada: Nenhuma.
    // Retorno: Nenhum.
    // Pré-condição: Deve existir um arquivo DOT chamado 'graph.dot' no diretório especificado.
    // Pós-condição: Uma imagem PNG do grafo é gerada e salva no diretório especificado.
    public void generateGraphImage() {
        String graphvizPath = "\"C:\\Program Files\\Graphviz\\bin\\dot\"";
        String command = graphvizPath + " -Tpng graph.dot -o graph.png";

        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.err.println("Erro ao gerar a imagem do grafo: " + e.getMessage());
        }
    }

    // Descrição: Gera um arquivo DOT para a representação visual do grafo, potencialmente com atributos visuais específicos para cada vértice ou aresta.
    // Entrada: Nenhuma.
    // Retorno: Booleano que indica se o arquivo foi criado com sucesso.
    // Pré-condição: O grafo deve ter vértices e arestas definidos.
    // Pós-condição: Um arquivo DOT é criado com representações visuais personalizadas para os vértices e arestas.
    public boolean generateDotFileOut() {
        ArrayList<Aresta<Integer>> edges = this.getArestas();  // Assume que getEdges retorna um ArrayList
        ArrayList<Vertice<Integer>> vertices = this.getVertices();
        String separator = this.orientado ? "->" : "--";
        String graphType = this.orientado? "digraph" : "graph";

        try (FileWriter dotFile = new FileWriter("graphOut.dot")) {
            dotFile.write(graphType + " G {\n");
            dotFile.write("rankdir=LR;\n");
            dotFile.write("layout = neato;\n\n");
            dotFile.write("node [shape=circle];\n\n");


                for (int i = 0; i < this.vertices.size(); i++){
                    dotFile.write("    " + this.vertices.get(i).getDado() + " [color=" + this.vertices.get(i).getColor() + ", fillcolor=" + this.vertices.get(i).getColor() + ", style=filled];\n");
                }


            for (int i = 0; i < this.vertices.size(); i++) {
                for(int j = 0; j < this.vertices.get(i).getArestasOut().size(); j++){
                    int vertex1 = this.vertices.get(i).getArestasOut().get(j).getInicio().getDado();
                    int vertex2 = this.vertices.get(i).getArestasOut().get(j).getFim().getDado();
                    Double weight = this.vertices.get(i).getArestasOut().get(j).getValor();
                    dotFile.write("    " + vertex1 + separator + vertex2 + " [label=\"" + weight + "\"];\n");
                }

            }

            dotFile.write("}\n");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo DOT: " + e.getMessage());
            return false;
        }
    }

    // Descrição: Gera uma imagem PNG do grafo usando o Graphviz a partir do arquivo DOT criado pelo método `generateDotFileOut`.
    // Entrada: Nenhuma.
    // Retorno: Nenhum.
    // Pré-condição: Deve existir um arquivo DOT chamado 'graphOut.dot'
    public void generateGraphImageOut() {
        String graphvizPath = "\"C:\\Program Files\\Graphviz\\bin\\dot\"";
        String command = graphvizPath + " -Tpng graphOut.dot -o graphOut.png";

        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.err.println("Erro ao gerar a imagem do grafo: " + e.getMessage());
        }
    }
}
