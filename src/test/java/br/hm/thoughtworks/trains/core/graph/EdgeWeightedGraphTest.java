package br.hm.thoughtworks.trains.core.graph;

import br.hm.thoughtworks.trains.core.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by helmutmigge on 04/02/2017.
 */
public class EdgeWeightedGraphTest {

    EdgeWeightedGraph graph;

    @Before
    public void setUp() throws ParseException {
        graph = new EdgeWeightedGraph();
        In in = new In("input.txt");
        String[] inputEdges = in.readAll().split(",");
        EdgeFormat edgeFormat = new EdgeFormat();
        for (String inputEdge : inputEdges) {
            Edge edge = Edge.parser(inputEdge.trim());
            graph.addEdge(edge);
        }
    }

    @Test
    public void addEdge() throws Exception {
        graph.addEdge(new Edge("D", "A", 13));
        Assert.assertTrue("Not found vertex DA13", graph.toString().contains("DA13"));
    }

    @Test
    public void incidentOnVertex() throws Exception {
        Vertex vertex = new Vertex("A");
        Stream<Edge> stream = graph.incidentOnVertex(vertex);
        String initalA = stream
                .filter(edge -> edge.either().getName().equals("A"))
                .map(edge -> edge.toString())
                .collect(Collectors.joining(","));
        Assert.assertEquals("AB5,AD5,AE7", initalA);
    }

    @Test
    public void degree() throws Exception {
        Vertex vertex = new Vertex("A");
        Assert.assertEquals(3, graph.degree(vertex));
    }

    @Test
    public void EdgeWeightedGraphtoString() throws Exception {
        String[] inputs = {"AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};
        String graphString = graph.toString();

        for (String input : inputs) {
            if (!graphString.contains(input)) {
                Assert.fail("graph not contains " + input);
            }
        }

    }

}