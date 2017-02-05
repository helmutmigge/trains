package br.hm.thoughtworks.trains.core.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *  Classe que repesenta um digrafo com aresta com peso, onde cada aresta
 *  tem o tipo {@link DirectedEdge}.
 *  Suporta dua opareçõa principais:
 *   - adicionar uma aresta direcionada para o digrafo;
 *   - iterar todos as arestas que incidem diretamente usando o vertice de inicio.
 * Created by helmutmigge on 04/02/2017.
 */
public class EdgeWeightedDigraph {

    // map de arestas adijacentes a um vertice
    private Map<Vertex,Collection<DirectedEdge>> adjacency = new HashMap<>();
    //map com o grau de um vertice
    private Map<Vertex,Integer> indegree = new HashMap<>();

    /**
     * Adiciona uma aresta unidirecional {@code e} para esse grafo.
     *
     * @param  e aresta
     */
    public void addEdge(DirectedEdge e) {
        Vertex from = e.from();
        Vertex to = e.to();
        registerEdgeToVertex(from, e);
        registerEdgeToVertex(to, null);

        Integer in = indegree.get(to);
        if (in == null){
            in = 0;
        }else{
            in = in +1;
        }
        indegree.put(to,in) ;
    }

    /**
     * Returns as arestas que incidem diretamente no vertice {@code vertex}.
     *
     * @param  vertex the vertex
     * @return as arestas que incidem diretamente no vertice  {@code vertex}
     */
    public Stream<DirectedEdge> adjacencyForVertex(Vertex vertex) {
        Collection<DirectedEdge> directedEdges = adjacency.get(vertex);
        if (directedEdges == null){
            directedEdges = Collections.EMPTY_LIST;
        }
        return directedEdges.stream();
    }


    /**
     * Returns numero de arestas que sai do vertice diretamente {@code vertex}.
     *
     * @param  vertex  vertice
     * @return grau do que o vertice inicia  {@code vertex}
     */
    public int outdegree(Vertex vertex) {

        Collection<DirectedEdge> directedEdges = adjacency.get(vertex);
        if (directedEdges != null) {
            return directedEdges.size();
        }
        return 0;
    }


    /**
     * retorna o numero de arestas que entra no verice {@code vertex}.
     * @param  vertex vertice
     * @return numero de arestas que entra no verice {@code vertex}
     */
    public int indegree(Vertex vertex) {
        Integer in =indegree.get(vertex);
        return in == null? 0: in;
    }


    /**
     * Retorna todas as arestas dentro desse grafo.
     *
     * @return todas as arestas dentro desse grafo
     */
    public Stream<DirectedEdge> edges() {
        List<DirectedEdge> list = new LinkedList<DirectedEdge>();
        for (Vertex vertex: adjacency.keySet()) {
            for (DirectedEdge e : adjacency.get(vertex)) {
                list.add(e);
            }
        }
        return list.stream();
    }

    /**
     * Retona uma representação em stirng do grafo.
     *
     *
     * @return uma representação em stirng do grafo.
     */
    public String toString() {
        return edges()
                .map(edge -> edge.toString())
                .collect(Collectors.joining(", "));
    }

    /*
        Registra um aresta direta ao vertice.
        Caso a aresta seja {@code null} será registrado o vertice sem colocar aresta
     */
    private void registerEdgeToVertex(Vertex vertex, DirectedEdge directedEdge) {
        Collection<DirectedEdge> directedEdges = this.adjacency.get(vertex);
        if (directedEdges == null){
            directedEdges = new LinkedList<DirectedEdge>();
            this.adjacency.put(vertex, directedEdges);
        }
        if (directedEdge != null) {
            directedEdges.add(directedEdge);
        }
    }
}
