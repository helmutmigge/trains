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

    /**
     * Representado em formato texto dessa classe {@link VertexPath}
     * @return em formato texto dessa classe
     */
    @Override
    public String toString() {
        return new VertexPathFormat().format(this);
    }

    /**
     * instancia um {@link VertexPath} apartir de uma string com o formato padrão exemplo: A-B-C
     * @param source texto com formato padrão
     * @return instancia um {@link VertexPath} apartir de uma string com o formato padrão
     * @throws ParseException Caso {@code source} não esteja no formato padrão
     */
    public static VertexPath parseVertexPath(String source) throws ParseException {
        return (VertexPath) new VertexPathFormat().parseObject(source);
    }
}
