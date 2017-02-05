package br.hm.thoughtworks.trains.core.graph;

import java.text.ParseException;

/**
 *  Representa uma aresta com peso para um digrafo
 *  {@link EdgeWeightedDigraph}. Cada aresta consist em 2 vertices e um peso.
 * Created by helmutmigge on 04/02/2017.
 */
public class DirectedEdge implements Comparable<DirectedEdge> {

    private final int weight;
    private final Vertex from;
    private final Vertex to;


    /**
     * Inicializa a aresta entre os vertices {@code initialVertexName} and {@code endVertexName} para um dado {@code weight}.
     *
     * @param fromVertexName nome do vertice inicial
     * @param toVertexName     nome do vertice final
     * @param weight        peso dessa aresta
     */
    public DirectedEdge(String fromVertexName, String toVertexName, int weight) {
        this.from = new Vertex(fromVertexName);
        this.to = new Vertex(toVertexName);
        this.weight = weight;
    }


    /**
     * Inicializa a aresta entre os vertices {@code from} and {@code to} para um dado {@code weight}.
     *
     * @param from vertice inicial
     * @param to     vertice final
     * @param weight        peso dessa aresta
     * @throws NullPointerException SE {@code from} ou {@code to} for nulo
     */
    public DirectedEdge(Vertex from, Vertex to, int weight) {
        if (from == null || to == null){
            throw new NullPointerException("vertex is null");
        }

        if (from.equals(to)){
            throw new IllegalArgumentException("Vertex can not be the same");
        }
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * @return o ponto inicial desse aresta.
     */
    public Vertex from() {
        return from;
    }

    /**
     * @return o ponto final desse aresta.
     */
    public Vertex to() {
        return to;
    }

    /**
     * Verifica que (@code o) é igual e essa aresta. true se {@code getWeight}
     * ,{@code to},{@code from} deve forem igual
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectedEdge directedEdge = (DirectedEdge) o;

        if (getWeight() != directedEdge.getWeight()) return false;
        if (!from.equals(directedEdge.from)) return false;
        return to.equals(directedEdge.to);
    }

    @Override
    public int hashCode() {
        int result = getWeight();
        result = 31 * result + from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    /**
     * Compara duas arestas pelo peso.
     */
    @Override
    public int compareTo(DirectedEdge that) {
        return Integer.compare(this.getWeight(), that.getWeight());
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
        return new DirectedEdgeFormat().format(this);
    }

    /**
     * Monta uma instancia de DirectedEdge atraves que um String com o formato padrão.
     * @see DirectedEdgeFormat
     * @param source String no formato padrão
     * @return Um nova instancia de DirectedEdge configurada pela string no foramato padrão
     * @throws ParseException Caso {@code source} não esteja no formato padrão
     */
    public static DirectedEdge parser(String source) throws ParseException{
        return (DirectedEdge) new DirectedEdgeFormat().parseObject(source);
    }
}
