package br.hm.thoughtworks.trains.core.graph;

import br.hm.thoughtworks.trains.core.graph.DirectedEdge;
import br.hm.thoughtworks.trains.core.graph.Vertex;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by helmutmigge on 04/02/2017.
 */
public class DirectedEdgeTest {

    Vertex vertexA = new Vertex("A");
    Vertex vertexB = new Vertex("B");
    DirectedEdge directedEdge = new DirectedEdge("A", "B", 5);


    @Test
    public void createEdge() throws Exception {
        Assert.assertEquals("AB5", directedEdge.toString());
    }

    @Test
    public void to() throws Exception {
        Vertex to = directedEdge.to();
        Assert.assertEquals(vertexB, to);
    }
}