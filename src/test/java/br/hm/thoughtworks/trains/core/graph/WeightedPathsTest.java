package br.hm.thoughtworks.trains.core.graph;

import br.hm.thoughtworks.trains.core.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by helmutmigge on 06/02/2017.
 */
public class WeightedPathsTest {

    private EdgeWeightedDigraph graph;

    @Before
    public void setUp() throws ParseException, IOException {
        graph = new EdgeWeightedDigraph();
        try(In in = new In("input.txt")) {
            String[] inputEdges = in.readAll().split(",");
            for (String inputEdge : inputEdges) {
                DirectedEdge directedEdge = DirectedEdge.parser(inputEdge.trim());
                graph.addEdge(directedEdge);
            }
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void weightNotExistePath() throws Exception {
        new WeightedPaths(graph,VertexPath.parseVertexPath("E-A")).weight();
    }

    @Test
    public void weightAEB() throws Exception {
        Assert.assertEquals(10,new WeightedPaths(graph,VertexPath.parseVertexPath("A-E-B")).weight());
    }


}