package br.hm.thoughtworks.trains.core.graph;

import br.hm.thoughtworks.trains.core.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * Created by helmutmigge on 05/02/2017.
 */
public class DijkstraTest {

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

    @Test
    public void weightedPathAToC() throws Exception {
        Assert.assertEquals(9,new Dijkstra(graph,new Vertex("A")).weightedPathTo(new Vertex("C")));
    }

    @Test
    public void weightedPathBToB() throws Exception {
        Assert.assertEquals(9,new Dijkstra(graph,new Vertex("B")).weightedPathTo(new Vertex("B")));
    }

    @Test
    public void weightedPathCToB() throws Exception {
        Assert.assertEquals(5,new Dijkstra(graph,new Vertex("C")).weightedPathTo(new Vertex("B")));
    }

}