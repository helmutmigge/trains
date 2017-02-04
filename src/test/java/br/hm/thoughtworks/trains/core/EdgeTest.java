package br.hm.thoughtworks.trains.core;

import br.hm.thoughtworks.trains.core.graph.Edge;
import br.hm.thoughtworks.trains.core.graph.Vertex;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by helmutmigge on 04/02/2017.
 */
public class EdgeTest {

    Vertex vertexA = new Vertex("A");
    Vertex vertexB = new Vertex("B");
    Edge edge = new Edge("A", "B", 5);


    @Test
    public void createEdge() throws Exception {
        Assert.assertEquals("AB5", edge.toString());
    }

    @Test
    public void other() throws Exception {
        Vertex otherVertex = edge.other(vertexA);
        Assert.assertEquals(vertexB, otherVertex);
    }
}