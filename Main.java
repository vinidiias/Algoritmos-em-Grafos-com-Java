import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.lang.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Grafo<Integer> grafo = new Grafo<Integer>();
        Grafo<Integer> grafoOriginal = new Grafo<Integer>();
        KruskalAlgorithm grafoKruskalOriginal = new KruskalAlgorithm();
        KruskalAlgorithm grafoKruskal = new KruskalAlgorithm();
        ColorPallets colors = new ColorPallets();

        Scanner scanner = new Scanner(System.in);
        int op;
        String linha;
            System.out.println(" 1 -> Carregar Grafo");
            op = scanner.nextInt();
            scanner.nextLine();
            if(op == 1){
                System.out.println("Digite o nome do arquivo");
                linha = scanner.nextLine();
                scanner.nextLine();
                grafo.processarArquivo("C:/Users/Vinii/OneDrive/Documents/3ANO/PAA/AlgoritmosEmGrafos/src/" + linha);
                grafoOriginal.processarArquivo("C:/Users/Vinii/OneDrive/Documents/3ANO/PAA/AlgoritmosEmGrafos/src/" + linha);
                grafoKruskalOriginal.processarArquivoKruskal("C:/Users/Vinii/OneDrive/Documents/3ANO/PAA/AlgoritmosEmGrafos/src/" + linha);
                grafoKruskal.processarArquivoKruskal("C:/Users/Vinii/OneDrive/Documents/3ANO/PAA/AlgoritmosEmGrafos/src/" + linha);
                do {
                    System.out.println("1 -> Desenhar Grafo");
                    System.out.println("2 -> Busca em profundidade");
                    System.out.println("3 -> Busca em Largura");
                    System.out.println("4 -> Bellman-Ford");
                    System.out.println("5 -> Kruskal");
                    System.out.println("6 -> Componentes conexas");
                    System.out.println("7 -> Componentes fortementes conexas");
                    System.out.println("8 -> Sair");
                    op = scanner.nextInt();
                    scanner.nextLine();
                    if(op == 1){
                        grafoOriginal.generateDotFile();
                        grafoOriginal.generateGraphImage();
                    }else if(op == 2){
                        grafo.DFS();
                    }else if(op == 3){
                        System.out.println("Indice:");
                        int n = scanner.nextInt();
                        grafo.buscaEmLargura(n);
                    }else if(op == 4){
                        if(grafo.getOrientado()){
                            System.out.println("Indice:");
                            int n = scanner.nextInt();
                            grafo.BellmanFord(n);
                            grafo = grafoOriginal;
                        }else System.out.println("grafo nao orientado!");
                    }else if(op == 5){
                        if(grafoKruskal.isGrafoConexo() && !grafoKruskal.isOrientado()) {
                            grafoKruskal.setArvoreMin(grafoKruskal.kruskall());
                            for (KruskalAlgorithm.ArestaKruskal aresta : grafoKruskal.getArvMin()) {
                                grafoKruskal.somaPeso(aresta.peso);
                            }
                            // Imprimindo o resultado
                            System.out.println("Peso total: " + grafoKruskal.getPeso());
                            System.out.print("Arestas: ");
                            for (KruskalAlgorithm.ArestaKruskal aresta : grafoKruskal.getArvMin()) {
                                System.out.print("(" + aresta.inicio + "," + aresta.fim + ") ");
                            }
                            System.out.println();
                            grafoKruskal.generateDotFileKruskal(colors);
                            grafoKruskal.generateGraphImage();
                            grafoKruskal = grafoKruskalOriginal;
                        }else System.out.println("Grafo Orientado ou nao conexo");
                    }else if(op == 6){
                        if(!grafo.getOrientado()){
                            grafo.ComponentesConexas();
                            grafo = grafoOriginal;
                        }else System.out.println("Grafo orientado!");
                    }else if(op == 7){
                        if(grafo.getOrientado()){
                            grafo = grafo.ComponentesFortementeConexas(colors);
                            grafo.generateDotFileOut();
                            grafo.generateGraphImageOut();
                            grafo = grafoOriginal;
                        }else System.out.println("Grafo nao orientado!");
                    }
                }while(op != 8);
            }



        /*grafoKruskal.processarArquivoKruskal("C:/Users/Vinii/OneDrive/Documents/3ANO/PAA/AlgoritmosEmGrafos/src/input_2.txt");
        System.out.println();
        grafoKruskal.setArvoreMin(grafoKruskal.kruskall());

        for (KruskalAlgorithm.ArestaKruskal aresta : grafoKruskal.getArvMin()) {
            grafoKruskal.somaPeso(aresta.peso);
        }

        // Imprimindo o resultado
        System.out.println("Peso total: " + grafoKruskal.getPeso());
        System.out.print("Arestas: ");
        for (KruskalAlgorithm.ArestaKruskal aresta : grafoKruskal.getArvMin()) {
            System.out.print("(" + aresta.inicio + "," + aresta.fim + ") ");
        }
        grafoKruskal.generateDotFileKruskal(colors);
        grafoKruskal.generateGraphImage();



        grafo.processarArquivo("C:/Users/Vinii/OneDrive/Documents/3ANO/PAA/AlgoritmosEmGrafos/src/input_1.txt");
        System.out.println();
        grafo.generateDotFile();
        grafo.generateGraphImage();
        grafoCompFortConexas = grafo.ComponentesFortementeConexas(colors);
        grafoCompFortConexas.generateDotFileOut(7);
        grafoCompFortConexas.generateGraphImageOut();
        //grafo.BellmanFord(1);
        Grafo<Integer> grafoOrienteado = new Grafo<Integer>();
        Grafo<Integer> grafo2NaoOrientado = new Grafo<Integer>();
        Grafo<Integer> grafo2Orientado = new Grafo<Integer>();
        Grafo<Integer> kruskal = new Grafo<Integer>();

        for(int i = 0; i < 7; i++)
            grafo.adicionarVertice(i);

        grafo.adicionarAresta(5.0, 0, 3);
        grafo.adicionarAresta(8.0, 0, 2);
        grafo.adicionarAresta(11.0, 0, 5);
        grafo.adicionarAresta(2.0, 1, 3);
        grafo.adicionarAresta(18.0, 1, 4);
        grafo.adicionarAresta(8.0, 2, 0);
        grafo.adicionarAresta(7.0, 2, 3);
        grafo.adicionarAresta(3.0, 2, 4);
        grafo.adicionarAresta(4.0, 2, 5);
        grafo.adicionarAresta(5.0, 3, 0);
        grafo.adicionarAresta(2.0, 3, 1);
        grafo.adicionarAresta(7.0, 3, 2);
        grafo.adicionarAresta(18.0, 4, 1);
        grafo.adicionarAresta(3.0, 4, 2);
        grafo.adicionarAresta(-1.0, 4, 5);
        grafo.adicionarAresta(5.0, 4, 6);
        grafo.adicionarAresta(11.0, 5, 0);
        grafo.adicionarAresta(4.0, 5, 2);
        grafo.adicionarAresta(-1.0, 5, 4);
        grafo.adicionarAresta(17.0, 5, 6);
        grafo.adicionarAresta(5.0, 6, 4);
        grafo.adicionarAresta(17.0, 6, 5);
        
        grafo.buscaEmLargura();
        grafo.DFS();
        /////////////////////////////
        for(int i = 0; i < 5; i++)
            grafoOrienteado.adicionarVertice(i);

        grafoOrienteado.adicionarAresta(11.0, 0, 1);
        grafoOrienteado.adicionarAresta(-4.0, 0, 2);
        grafoOrienteado.adicionarAresta(-5.0, 1, 3);
        grafoOrienteado.adicionarAresta(7.0, 2, 4);
        grafoOrienteado.adicionarAresta(2.0, 3, 0);
        grafoOrienteado.adicionarAresta(8.0, 3, 2);
        grafoOrienteado.adicionarAresta(19.0, 4, 2);

        grafoOrienteado.BellmanFord(1);
        System.out.println();

        /////////////////////////////////////////////////

        for(int i = 0; i < 12; i++)
            grafo2NaoOrientado.adicionarVertice(i);

        grafo2NaoOrientado.adicionarAresta(6.0, 0, 3);
        grafo2NaoOrientado.adicionarAresta(3.0, 0, 4);
        grafo2NaoOrientado.adicionarAresta(8.0, 0, 1);
        grafo2NaoOrientado.adicionarAresta(8.0, 1, 0);
        grafo2NaoOrientado.adicionarAresta(0.0, 1, 4);
        grafo2NaoOrientado.adicionarAresta(11.0, 1, 2);
        grafo2NaoOrientado.adicionarAresta(11.0, 2, 1);
        grafo2NaoOrientado.adicionarAresta(3.0, 2, 7);
        grafo2NaoOrientado.adicionarAresta(6.0, 3, 0);
        grafo2NaoOrientado.adicionarAresta(1.0, 3, 4);
        grafo2NaoOrientado.adicionarAresta(17.0, 3, 5);
        grafo2NaoOrientado.adicionarAresta(5.0, 3, 6);
        grafo2NaoOrientado.adicionarAresta(2.0, 3, 7);
        grafo2NaoOrientado.adicionarAresta(3.0, 4, 0);
        grafo2NaoOrientado.adicionarAresta(0.0, 4, 1);
        grafo2NaoOrientado.adicionarAresta(1.0, 4, 3);
        grafo2NaoOrientado.adicionarAresta(17.0, 5, 3);
        grafo2NaoOrientado.adicionarAresta(5.0, 5, 7);
        grafo2NaoOrientado.adicionarAresta(5.0, 6, 3);
        grafo2NaoOrientado.adicionarAresta(3.0, 7, 2);
        grafo2NaoOrientado.adicionarAresta(2.0, 7, 3);
        grafo2NaoOrientado.adicionarAresta(5.0, 7, 5);
        grafo2NaoOrientado.adicionarAresta(7.0, 8, 9);
        grafo2NaoOrientado.adicionarAresta(2.0, 8, 10);
        grafo2NaoOrientado.adicionarAresta(7.0, 9, 8);
        grafo2NaoOrientado.adicionarAresta(1.0, 9, 10);
        grafo2NaoOrientado.adicionarAresta(2.0, 10, 8);
        grafo2NaoOrientado.adicionarAresta(1.0, 10, 9);
        grafo2NaoOrientado.adicionarAresta(3.0, 10, 11);
        grafo2NaoOrientado.adicionarAresta(3.0, 11, 10);

        grafo2NaoOrientado.ComponentesConexas();
        System.out.println();

        ///////////////////////

        for(int i = 0; i < 5; i++)
            grafo2Orientado.adicionarVertice(i);

        grafo2Orientado.adicionarAresta(11.0, 0, 1);
        grafo2Orientado.adicionarAresta(-4.0, 0, 2);
        grafo2Orientado.adicionarAresta(-5.0, 1, 3);
        grafo2Orientado.adicionarAresta(7.0, 2, 4);
        grafo2Orientado.adicionarAresta(2.0, 3, 0);
        grafo2Orientado.adicionarAresta(8.0, 3, 2);
        grafo2Orientado.adicionarAresta(19.0, 4, 2);

        /*System.out.println("Arestas do grafo nao transposto");
        grafo2Orientado.imprimeArestas();

        grafo2Orientado = grafoOrienteado.grafoTransposto();
        System.out.println("\nArestas do grafo transposto");
        grafo2Orientado.imprimeArestas();
        //grafo2Orientado.buscaEmLargura();
        System.out.println();

        grafo2Orientado.ComponentesFortementeConexas();
        System.out.println();
        ///////////////////////////////////////////

        for(int i = 0; i < 7; i++){
            kruskal.adicionarVertice(i);
        }
        kruskal.adicionarAresta(5.0, 0, 3);
        kruskal.adicionarAresta(8.0, 0, 2);
        kruskal.adicionarAresta(11.0, 0, 5);
        kruskal.adicionarAresta(2.0, 1, 3);
        kruskal.adicionarAresta(18.0, 1, 4);
        kruskal.adicionarAresta(8.0, 2, 0);
        kruskal.adicionarAresta(7.0, 2, 3);
        kruskal.adicionarAresta(3.0, 2, 4);
        kruskal.adicionarAresta(4.0, 2, 5);
        kruskal.adicionarAresta(5.0, 3, 0);
        kruskal.adicionarAresta(2.0, 3, 1);
        kruskal.adicionarAresta(7.0, 3, 2);
        kruskal.adicionarAresta(18.0, 4, 1);
        kruskal.adicionarAresta(3.0, 4, 2);
        kruskal.adicionarAresta(-1.0, 4, 5);
        kruskal.adicionarAresta(5.0, 4, 6);
        kruskal.adicionarAresta(11.0, 5, 0);
        kruskal.adicionarAresta(4.0, 5, 2);
        kruskal.adicionarAresta(-1.0, 5, 4);
        kruskal.adicionarAresta(17.0, 5, 6);
        kruskal.adicionarAresta(5.0, 6, 4);
        kruskal.adicionarAresta(17.0, 6, 5);*/
    }

}