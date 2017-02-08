package br.hm.thoughtworks.trains.core.graph;

import br.hm.thoughtworks.trains.core.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * Created by helmutmigge on 07/02/2017.
 */
public class DepthCountDegreeTest {


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
     * : The number of trips starting at C and ending at C with a maximum of 3 stops. In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
     */
    @Test
    public void getCountMaxDegreeCtoC() {
        Vertex vertexC =new Vertex("C");
        CriteriaDegree criteriaDegree = new CriteriaDegree(vertexC,vertexC,3,false);
        Assert.assertEquals(2,new DepthCountDegree(graph,criteriaDegree).getCount());
    }

    /**
     * The number of trips starting at A and ending at C with exactly 4 stops. In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
     */
    @Test
    public void getCountEqualDegreeAtoCwithExactly4Stops()  {
        Vertex vertexA =new Vertex("A");
        Vertex vertexC =new Vertex("C");
        CriteriaDegree criteriaDegree = new CriteriaDegree(vertexA,vertexC,4,true);
        Assert.assertEquals(3,new DepthCountDegree(graph,criteriaDegree).getCount());
    }

}