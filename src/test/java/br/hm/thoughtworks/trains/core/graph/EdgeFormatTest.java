package br.hm.thoughtworks.trains.core.graph;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by helmutmigge on 04/02/2017.
 */
public class EdgeFormatTest {

    @Test
    public void format() throws Exception {
        Edge edge = new Edge("A","B",5);
        Assert.assertEquals("AB5",new EdgeFormat().format(edge));
    }

    @Test
    public void parseObject() throws Exception {
        String  inputEdge ="AB5";
        Edge edge = (Edge) new EdgeFormat().parseObject(inputEdge);
        Vertex initialVertex = edge.either();
        Assert.assertEquals("A",initialVertex.getName());
        Assert.assertEquals("B",edge.other(initialVertex).getName());
        Assert.assertEquals(5,edge.getWeight());
    }

}
