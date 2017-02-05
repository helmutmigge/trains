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
public class EdgeWeightedDigraphTest {

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
    public void addEdge() throws Exception {
        graph.addEdge(new DirectedEdge("D", "A", 13));
        Assert.assertTrue("Not found vertex DA13", graph.toString().contains("DA13"));
    }

    @Test
    public void adjacencyForVertex() throws Exception {
        Vertex vertex = new Vertex("A");
        Stream<DirectedEdge> stream = graph.adjacencyForVertex(vertex);
        String initalA = stream
                .filter(edge -> edge.from().getName().equals("A"))
                .map(DirectedEdge::toString)
                .collect(Collectors.joining(","));
        Assert.assertEquals("AB5,AD5,AE7", initalA);
    }

    @Test
    public void outdegree() throws Exception {
        Vertex vertex = new Vertex("A");
        Assert.assertEquals(3, graph.outdegree(vertex));
    }

    @Test
    public void indegree() throws Exception {
        Vertex vertex = new Vertex("A");
        Assert.assertEquals(0, graph.indegree(vertex));
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