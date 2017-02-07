package br.hm.thoughtworks.trains.core.graph;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Representa uma solução que soma todos os peso de um @{@link VertexPath} em um digrafo
 * {@link EdgeWeightedDigraph}
 * Created by helmutmigge on 05/02/2017.
 */
public class WeightedPaths {

    private long weight;

    public WeightedPaths(EdgeWeightedDigraph digraph, VertexPath vertexPath) {
        dfs(digraph, vertexPath);
    }

    /**
     * Obtem o peso do  vertice de sai de {@code from} e vai para {@code to}
     * com digrafo informado
     *
     * @param digraph digrafo a ser pesquisado
     * @param from    vertice de saida
     * @param to      vertice de entrada
     * @return peso do do vertice de sai de {@code from} e vai para {@code to}
     * @throws NoSuchElementException Caso não seja encontrado um aresta que tenha
     *                                um vertice de sai de {@code from} e vai para {@code to}
     */
    protected long weight(EdgeWeightedDigraph digraph, Vertex from, Vertex to) throws NoSuchElementException {

        Optional<DirectedEdge> directedEdgeOptional = digraph.adjacencyForVertex(from)
                .filter(directedEdge -> directedEdge.to().equals(to)).findFirst();
        return directedEdgeOptional.get().getWeight();

    }


    private void dfs(EdgeWeightedDigraph digraph, VertexPath vertexPath) throws NoSuchElementException {
        Queue<Vertex> vertexQueue = new LinkedList(vertexPath.path().collect(Collectors.toList()));
        Vertex from = vertexQueue.poll();
        Vertex to = vertexQueue.poll();
        while (from != null && to != null) {
            this.weight += weight(digraph, from, to);
            from = to;
            to = vertexQueue.poll();
        }


    }

    public long weight() {
        return this.weight;
    }
}
