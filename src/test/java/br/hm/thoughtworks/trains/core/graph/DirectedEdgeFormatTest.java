package br.hm.thoughtworks.trains.core.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by helmutmigge on 04/02/2017.
 */
public class DirectedEdgeFormatTest {

    @Test
    public void format() throws Exception {
        DirectedEdge directedEdge = new DirectedEdge("A","B",5);
        Assert.assertEquals("AB5",new DirectedEdgeFormat().format(directedEdge));
    }

    @Test
    public void parseObject() throws Exception {
        String  inputEdge ="AB5";
        DirectedEdge directedEdge = (DirectedEdge) new DirectedEdgeFormat().parseObject(inputEdge);

        Assert.assertEquals("A",directedEdge.from().getName());
        Assert.assertEquals("B", directedEdge.to().getName());
        Assert.assertEquals(5, directedEdge.getWeight());
    }

}
