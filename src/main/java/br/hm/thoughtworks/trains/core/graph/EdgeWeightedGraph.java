package br.hm.thoughtworks.trains.core.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by helmutmigge on 04/02/2017.
 */
public class EdgeWeightedGraph {

    private Map<Vertex,Collection<Edge>> adjacent = new HashMap<>();


    /**
     * Adiciona uma aresta unidirecional {@code e} para esse grafo.
     *
     * @param  e aresta
     */
    public void addEdge(Edge e) {
        Vertex initVertex = e.either();
        Vertex endVertex = e.other(initVertex);
        registerEdgeToVertex(initVertex, e);
        registerEdgeToVertex(endVertex, null);
    }

    /**
     * Returns as arestas que incidem diretamente no vertice {@code vertex}.
     *
     * @param  vertex the vertex
     * @return as arestas que incidem diretamente no vertice  {@code vertex}
     */
    public Stream<Edge> incidentOnVertex(Vertex vertex) {
        Collection<Edge> edges = adjacent.get(vertex);
        if (edges == null){
            edges = Collections.EMPTY_LIST;
        }
        return edges.stream();
    }


    /**
     * Returns grau do vertice {@code vertex}.
     *
     * @param  vertex  vertice
     * @return grau do vertice {@code vertex}
     */
    public int degree(Vertex vertex) {

        Collection<Edge> edges = adjacent.get(vertex);
        if (edges != null) {
            return edges.size();
        }
        return 0;
    }


    /**
     * Retorna todas as arestas dentro desse grafo.
     *
     * @return todas as arestas dentro desse grafo
     */
    public Stream<Edge> edges() {
        List<Edge> list = new LinkedList<Edge>();
        for (Vertex vertex: adjacent.keySet()) {
            for (Edge e : adjacent.get(vertex)) {
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
    private void registerEdgeToVertex(Vertex vertex, Edge edge) {
        Collection<Edge> edges = this.adjacent.get(vertex);
        if (edges == null){
            edges = new LinkedList<Edge>();
            this.adjacent.put(vertex,edges);
        }
        if (edge != null) {
            edges.add(edge);
        }
    }
}
