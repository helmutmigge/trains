package br.hm.thoughtworks.trains.core.graph;

import java.util.*;

/**
 * Atual no problema de caminho mais curtos de uma únicia fonte em um digrafo com peso.
 * Não pode haver pesos negativos
 * Created by helmutmigge on 05/02/2017.
 */
public class Dijkstra {


    private Map<Vertex, Long> distTo = new HashMap<>();
    private Map<Vertex, DirectedEdge> edgeTo = new HashMap<>();
    private PriorityQueue<VertexWeighted> priorityQueue = new PriorityQueue();
    private Vertex source;
    private EdgeWeightedDigraph digraph;
    /**
     * Inicia com o digrafo a ser pesquisado e o vertice de origem
     *
     * @param digraph a ser pesquisado
     * @param source  vertice de origem
     * @throws IllegalArgumentException Se existe no grafo algum peso menor de zero
     */
    public Dijkstra(EdgeWeightedDigraph digraph, Vertex source) {

        digraph.edges().forEach(
                directedEdge -> {
                    if (directedEdge.getWeight() < 0)
                        throw new IllegalArgumentException("edge " + directedEdge + " has negative weight");

                }
        );
        this.source = source;
        this.digraph=digraph;
        setDistTo(source, 0);

        priorityQueue.add(new VertexWeighted(source, 0));
        while (!priorityQueue.isEmpty()) {
            VertexWeighted vertexWeighted = priorityQueue.poll();
            digraph.adjacencyForVertex(vertexWeighted).forEach(
                    directedEdge -> {
                        relax(directedEdge);
                    }
            );
        }

    }

    private void relax(DirectedEdge directedEdge) {
        Vertex from = directedEdge.from();
        Vertex to = directedEdge.to();
        long tmpDistTo = getDistTo(to);
        long tmpDistFrom = getDistTo(from);

        if (tmpDistTo > tmpDistFrom + directedEdge.getWeight()) {
            setDistTo(to, tmpDistFrom + directedEdge.getWeight());
            edgeTo.put(to, directedEdge);
            if (!priorityQueue.contains(to))
                priorityQueue.add(new VertexWeighted(to, getDistTo(to)));
        }
    }


    /**
     * Returns o tamanho do caminho mais curto  entre {@code from} e {@code vertex}
     *
     * @param vertex the destination vertex
     * @return o tamanho do caminho mais curto  entre {@code from} e {@code vertex}
     * {@code Long.MAX_VALUE}  Se não encontrar um caminho;
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */

    public long getDistTo(Vertex vertex) {
        Long tmp = distTo.get(vertex);
        if (tmp == null) {
            tmp = Long.MAX_VALUE;
        }
        return tmp;
    }

    private void setDistTo(Vertex vertex, long dist) {
        this.distTo.put(vertex, dist);
    }


    public boolean hasPathTo(Vertex vertex) {
        return getDistTo(vertex) < Long.MAX_VALUE;
    }



    public long weightedPathTo(Vertex vertex) throws NoSuchElementException {

        if (!hasPathTo(vertex))
            throw new NoSuchElementException("not found vertex in path");

        if (vertex.equals(source)){

            return digraph.adjacencyForVertex(source)
                    .mapToLong(directedEdge ->
                         new Dijkstra(digraph,directedEdge.to()).weightedPathTo(directedEdge.from()) + directedEdge.getWeight()
                    ).min().getAsLong();

        }else {
            long result = 0;
            for (DirectedEdge e = edgeTo.get(vertex); e != null; e = edgeTo.get(e.from())) {
                result += e.getWeight();
            }
            return result;
        }
    }

    /**
     * Classe interna que representa um Vertex com peso
     */
    private class VertexWeighted extends Vertex implements Comparable<VertexWeighted> {

        long weight;

        public VertexWeighted(Vertex vertex, long weight) {
            super(vertex.getName());
            this.weight = weight;
        }

        @Override
        public int compareTo(VertexWeighted that) {
            return Long.compare(this.weight, that.weight);
        }
    }
}
