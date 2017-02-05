package br.hm.thoughtworks.trains.core.graph;

import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 * Created by helmutmigge on 05/02/2017.
 */
public class Digraphs {

    public static long weightedPath(EdgeWeightedDigraph digraph, String path) throws ParseException, NoSuchElementException {
        VertexPath vertexPath = VertexPath.parseVertexPath(path);
        return new WeightedPaths(digraph, vertexPath).weight();
    }

    public static long weightedShortestPath(EdgeWeightedDigraph digraph, Vertex from, Vertex to) throws NoSuchElementException{
        return new Dijkstra(digraph,from).weightedPathTo(to);
    }
}
