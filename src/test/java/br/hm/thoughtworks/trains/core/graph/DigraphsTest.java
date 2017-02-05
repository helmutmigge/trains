package br.hm.thoughtworks.trains.core.graph;

import br.hm.thoughtworks.trains.core.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 * Classe responsavel por testar o peso(distancia) entre o caminhamento de vertices
 * <p>
 * Created by helmutmigge on 05/02/2017.
 */
public class DigraphsTest {

    private EdgeWeightedDigraph graph;

    @Before
    public void setUp() throws ParseException {
        graph = new EdgeWeightedDigraph();
        In in = new In("input.txt");
        String[] inputEdges = in.readAll().split(",");
        for (String inputEdge : inputEdges) {
            DirectedEdge directedEdge = DirectedEdge.parser(inputEdge.trim());
            graph.addEdge(directedEdge);
        }
    }

    /**
     * The distance of the route A-B-C.
     *
     * @throws ParseException caso string não esteja formatada corretamente {@link VertexPathFormat}
     */
    @Test
    public void routerABC() throws ParseException, NoSuchElementException {
        Assert.assertEquals(9, Digraphs.weightedPath(graph, "A-B-C"));
    }

    /**
     * The distance of the route A-D.
     *
     * @throws ParseException caso string não esteja formatada corretamente {@link VertexPathFormat}
     */
    @Test
    public void routerAD() throws ParseException, NoSuchElementException {
        Assert.assertEquals(5, Digraphs.weightedPath(graph, "A-D"));
    }

    /**
     * The distance of the route A-D-C
     *
     * @throws ParseException caso string não esteja formatada corretamente {@link VertexPathFormat}
     */
    @Test
    public void routerADC() throws ParseException, NoSuchElementException {
        Assert.assertEquals(13, Digraphs.weightedPath(graph, "A-D-C"));
    }

    /**
     * The distance of the route A-E-B-C-D.
     *
     * @throws ParseException caso string não esteja formatada corretamente {@link VertexPathFormat}
     */
    @Test
    public void routerAEBCD() throws ParseException, NoSuchElementException {
        Assert.assertEquals(22, Digraphs.weightedPath(graph, "A-E-B-C-D"));
    }

    /**
     * The distance of the route A-E-D.
     *
     * @throws ParseException caso string não esteja formatada corretamente {@link VertexPathFormat}
     */
    @Test(expected = NoSuchElementException.class)
    public void routerAED() throws ParseException, NoSuchElementException {
        Digraphs.weightedPath(graph, "A-E-D");
    }

}