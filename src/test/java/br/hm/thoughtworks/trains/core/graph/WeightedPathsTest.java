package br.hm.thoughtworks.trains.core.graph;

import br.hm.thoughtworks.trains.core.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by helmutmigge on 05/02/2017.
 */
public class WeightedPathsTest {

    EdgeWeightedDigraph graph;

    @Before
    public void setUp() throws ParseException {
        graph = new EdgeWeightedDigraph();
        In in = new In("input.txt");
        String[] inputEdges = in.readAll().split(",");
        DirectedEdgeFormat directedEdgeFormat = new DirectedEdgeFormat();
        for (String inputEdge : inputEdges) {
            DirectedEdge directedEdge = DirectedEdge.parser(inputEdge.trim());
            graph.addEdge(directedEdge);
        }
    }

    /**
     * The distance of the route A-B-C.
     * @throws Exception
     */
    @Test
    public void routerABC() throws Exception {
        Assert.assertEquals(9,new WeightedPaths(graph,VertexPath.parseVertexPath("A-B-C")).weight());
    }

    /**
     *  The distance of the route A-D.
     * @throws Exception
     */
    @Test
    public void routerAD() throws Exception {
        Assert.assertEquals(5,new WeightedPaths(graph,VertexPath.parseVertexPath("A-D")).weight());
    }

    /**
     * The distance of the route A-D-C
     * @throws Exception
     */
    @Test
    public void routerADC() throws Exception {
        Assert.assertEquals(13,new WeightedPaths(graph,VertexPath.parseVertexPath("A-D-C")).weight());
    }

    /**
     * The distance of the route A-E-B-C-D.
     * @throws Exception
     */
    @Test
    public void routerAEBCD() throws Exception {
        Assert.assertEquals(22,new WeightedPaths(graph,VertexPath.parseVertexPath("A-E-B-C-D")).weight());
    }

    /**
     * The distance of the route A-E-D.
     * @throws Exception
     */
    @Test(expected = NoSuchElementException.class)
    public void routerAED() throws Exception {
        new WeightedPaths(graph,VertexPath.parseVertexPath("A-E-D")).weight();
    }

}