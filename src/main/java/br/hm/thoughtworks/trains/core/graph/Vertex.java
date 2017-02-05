package br.hm.thoughtworks.trains.core.graph;

/**
 * Reporesenta o vertice de um grafo. O nome do vertice é o que diferencia um vertice de outro.
 * Created by helmutmigge on 04/02/2017.
 */
public class Vertex {

    private String name;

    /**
     * Inicializa com o nome do vertice.
     * @param name do vertice
     *
     */
    public Vertex(String name) {
        if (name == null) {
            throw new NullPointerException("vertex name is null");
        }
        this.name = name;
    }

    /**
     * Retorna true caso esse objeto seja iqual ao {@code o}
     * @param o Objeto que se deseja comparar a igualdade
     * @return true caso esse objeto seja iqual ao {@code o}. false caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return getName() != null ? getName().equals(vertex.getName()) : vertex.getName() == null;
    }

    /**
     *
     * @return o hascode para vertice
     */
    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    /**
     *
     * @return um representação em string para o vertice
     */
    @Override
    public String toString() {
        return this.getName();
    }

    /**
     *
     * @return o nome do vertice
     */
    public String getName() {
        return name;
    }


}
