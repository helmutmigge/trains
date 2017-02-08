package br.hm.thoughtworks.trains.core.graph;

import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 * Classe que contem varios metodos de manipulação de um {@link EdgeWeightedDigraph}
 * (como peso de um Path, e peso entre dois vertices)
 * Created by helmutmigge on 05/02/2017.
 */
public class Digraphs {

    /**
     * Retorna o peso do {@code path} ({@link VertexPath} em formato de string) no digrafo {@code digraph}
     * @param digraph a ser pesquisado
     * @param path formatado como string usado o formato padrão {@link VertexPathFormat}
     * @return o peso de um {@code path} {@link VertexPath} em formato de string
     * @throws ParseException Caso {@code path} não esteja no formato padrão
     * @throws NoSuchElementException Caso o path informado não exista no digrafo {@code digraph}
     */
    public static long weightedPath(EdgeWeightedDigraph digraph, String path) throws ParseException, NoSuchElementException {
        VertexPath vertexPath = VertexPath.parseVertexPath(path);
        return new WeightedVertexPaths(digraph, vertexPath).weight();
    }

    /**
     * Retorna o peso do {@code vertexPath} no digrafo {@code digraph}
     * @param digraph a ser pesquisado
     * @param vertexPath Path com caminho vertice a vertice de um digrafo
     * @return o peso do {@code vertexPath} no digrafo {@code digraph}
     * @throws NoSuchElementException Caso o path informado não exista no digrafo {@code digraph}
     */
    public static long weightedPath(EdgeWeightedDigraph digraph,VertexPath vertexPath) throws  NoSuchElementException {
        return new WeightedVertexPaths(digraph, vertexPath).weight();
    }

    /**
     * Retorna o menor caminho (peso) entre dois vertices {@code from} e {@code to}
     * de um digrafo {@code digraph}
     * @param digraph a ser pesquisado
     * @param from vertice de saida
     * @param to vertice de entrada
     * @return o menor caminho (peso) entre dois vertices {@code from} e {@code to}
     * @throws NoSuchElementException Não existe um caminho entre o vertice {@code from} e {@code to}
     */
    public static long weightedShortestPath(EdgeWeightedDigraph digraph, Vertex from, Vertex to) throws NoSuchElementException{
        return new Dijkstra(digraph,from).weightedPathTo(to);
    }


    /**
     * Retorna a quantidade de ocorrencia que atendam ao criterio {@code criteriaDegree} no digrafo {@code digraph}
     * @param digraph a se presquisado
     * @param criteriaDegree criteria para pesquisa {@link CriteriaDegree}
     * @return a quantidade de ocorrencia que atendam ao criterio {@code criteriaDegree} no digrafo {@code digraph}.
     */
    public static long countDegree(EdgeWeightedDigraph digraph, CriteriaDegree criteriaDegree){
        return new DepthCountDegree(digraph,criteriaDegree).getCount();
    }
}
