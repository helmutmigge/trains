package br.hm.thoughtworks.trains.core.graph;

import java.text.ParseException;

/**
 * Created by helmutmigge on 04/02/2017.
 */
public class Edge implements Comparable<Edge> {

    private final int weight;
    private final Vertex initialVertex;
    private final Vertex endVertex;


    /**
     * Inicializa a aresta entre os vertices {@code initialVertexName} and {@code endVertexName} para um dado {@code weight}.
     *
     * @param initialVertexName nome do vertice inicial
     * @param endVertexName     nome do vertice final
     * @param weight        peso dessa aresta
     */
    public Edge(String initialVertexName, String endVertexName, int weight) {
        this.initialVertex = new Vertex(initialVertexName);
        this.endVertex = new Vertex(endVertexName);
        this.weight = weight;
    }


    /**
     * Inicializa a aresta entre os vertices {@code initialVertex} and {@code endVertex} para um dado {@code weight}.
     *
     * @param initialVertex vertice inicial
     * @param endVertex     vertice final
     * @param weight        peso dessa aresta
     * @throws NullPointerException SE {@code initialVertex} ou {@code endVertex} for nulo
     */
    public Edge(Vertex initialVertex, Vertex endVertex, int weight) {
        if (initialVertex == null || endVertex == null){
            throw new NullPointerException("vertex is null");
        }

        if (initialVertex.equals(endVertex)){
            throw new IllegalArgumentException("Vertex can not be the same");
        }
        this.initialVertex = initialVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    /**
     * @return o ponto inicial desse aresta.
     */
    public Vertex either() {
        return initialVertex;
    }

    /**
     * Verifica que (@code o) é igual e essa aresta. true se {@code getWeight}
     * ,{@code endVertex},{@code initialVertex} deve forem igual
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (getWeight() != edge.getWeight()) return false;
        if (!initialVertex.equals(edge.initialVertex)) return false;
        return endVertex.equals(edge.endVertex);
    }

    @Override
    public int hashCode() {
        int result = getWeight();
        result = 31 * result + initialVertex.hashCode();
        result = 31 * result + endVertex.hashCode();
        return result;
    }

    /**
     * Compara duas arestas pelo peso.
     */
    @Override
    public int compareTo(Edge that) {
        return Integer.compare(this.getWeight(), that.getWeight());
    }

    /**
     * Returns o outro vertice essa aresta diferente do vertice informado.
     *
     * @param vertex Um vertice dessa aresta
     * @return o outro vertice dessa aresta
     * @throws NullPointerException     Se {@code vertex} for nulo
     * @throws IllegalArgumentException Se o vertice não pertence a essa aresta
     */
    public Vertex other(Vertex vertex) {
        if (vertex.equals(initialVertex)) {
            return endVertex;
        } else if (vertex.equals(endVertex)) {
            return initialVertex;
        }
        throw new IllegalArgumentException("Illegal endpoint");
    }


    /**
     * @return o peso dessa aresta
     */
    public int getWeight() {
        return weight;
    }

    /**
     *
     * @return um representação em string de uma aresta
     */
    @Override
    public String toString() {
        return new EdgeFormat().format(this);
    }

    /**
     * Monta uma instancia de Edge atraves que um String com o formato padrão.
     * @see EdgeFormat
     * @param source String no formato padrão
     * @return Um nova instancia de Edge configurada pela string no foramato padrão
     * @throws ParseException Caso {@code source} não esteja no formato padrão
     */
    public static Edge parser(String source) throws ParseException{
        return (Edge) new EdgeFormat().parseObject(source);
    }
}
