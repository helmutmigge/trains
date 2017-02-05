package br.hm.thoughtworks.trains;

import br.hm.thoughtworks.trains.console.Console;
import br.hm.thoughtworks.trains.core.In;
import br.hm.thoughtworks.trains.core.graph.DirectedEdge;
import br.hm.thoughtworks.trains.core.graph.EdgeWeightedDigraph;

import java.text.ParseException;

/**
 * Created by helmutmigge on 05/02/2017.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Load data in input.txt");
        try {
            EdgeWeightedDigraph digraph = loaddata();
            new Console(digraph).run();
        } catch (ParseException e) {
            System.err.print("corrupt data in input.txt");
        }
    }

    public static EdgeWeightedDigraph loaddata() throws ParseException {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph();
        In in = new In("input.txt");
        String[] inputEdges = in.readAll().split(",");
        for (String inputEdge : inputEdges) {
            DirectedEdge directedEdge = DirectedEdge.parser(inputEdge.trim());
            digraph.addEdge(directedEdge);
        }
        return digraph;
    }
}
