package br.hm.thoughtworks.trains.core.graph;

/**
 * Representa um criteria a ser usando em uma pesquisa em um digrafo {@link CriteriaDegree}
 *
 * Created by helmutmigge on 07/02/2017.
 */
public class CriteriaDegree {


    private final Vertex from;
    private final Vertex to;
    private final int maxDegree;
    private final boolean onlyEqualDegree;

    /**
     * Inicializa  com o vertice de saida {@code from} para um possivel vertice de chegada {@code to}.
     * A pesquisa caminhará no digrafo ate a grau menor um igual ao {@code maxDegree}.
     *

     * @param fromNameVertex nome vertice de saida para criteria
     * @param toNameVertex nome do vertice de entrada para criteria
     * @param maxDegree Grau maximo atingido na pesquisa
     * @param onlyEqualDegree Caso o {@code onlyEqualDegree} for true somente os vertices que estejam no mesmo grau
     * que o {@code maxDegree} será considerados
     */
    public CriteriaDegree(String fromNameVertex, String toNameVertex, int maxDegree, boolean onlyEqualDegree){
        this(new Vertex(fromNameVertex),new Vertex(toNameVertex),maxDegree,onlyEqualDegree);
    }


    /**
     * Inicializa  com o vertice de saida {@code from} para um possivel vertice de chegada {@code to}.
     * A pesquisa caminhará no digrafo ate a grau menor um igual ao {@code maxDegree}.
     *

     * @param from vertice de saida para criteria
     * @param to vertice de entrada para criteria
     * @param maxDegree Grau maximo atingido na pesquisa
     * @param onlyEqualDegree Caso o {@code onlyEqualDegree} for true somente os vertices que estejam no mesmo grau
     * que o {@code maxDegree} será considerados
     */
    public CriteriaDegree(Vertex from, Vertex to, int maxDegree, boolean onlyEqualDegree){
        this.from = from;
        this.to = to;
        this.maxDegree = maxDegree;
        this.onlyEqualDegree = onlyEqualDegree;
    }

    public Vertex getFrom() {
        return from;
    }

    public boolean degreeSupport(int degree, Vertex testTo){
        return ((onlyEqualDegree && degree == maxDegree)
                || (!onlyEqualDegree && degree < maxDegree))
                && to.equals(testTo);
    }

    public Vertex getTo() {
        return to;
    }

    public int getMaxDegree() {
        return maxDegree;
    }

    public boolean isOnlyEqualDegree() {
        return onlyEqualDegree;
    }

}
