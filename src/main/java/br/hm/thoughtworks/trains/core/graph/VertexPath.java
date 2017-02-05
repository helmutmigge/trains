package br.hm.thoughtworks.trains.core.graph;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Representa um caminho (rota) que deve ser precorido.
 * Created by helmutmigge on 05/02/2017.
 */
public class VertexPath {

    private final List<Vertex> path;


    /**
     * Inicializa com o nomo dos vertices, ponto de uma rota.
     */
    public VertexPath(String... vertexNames) {
        Vertex[] vertices = new Vertex[vertexNames.length];
        for (int i = 0; i < vertexNames.length; i++) {
            vertices[i] = new Vertex(vertexNames[i]);
        }
        this.path = Arrays.asList(vertices);
    }


    /**
     * Inicializa com vertices ponto de uma rota.
     */
    public VertexPath(Vertex... path) {
        this.path = Arrays.asList(path);
    }

    /**
     * @return o caminho
     */
    public Stream<Vertex> path() {
        return path.stream();
    }


    public static VertexPath parseVertexPath(String source) throws ParseException {
        return (VertexPath) new VertexPathFormat().parseObject(source);
    }
}
