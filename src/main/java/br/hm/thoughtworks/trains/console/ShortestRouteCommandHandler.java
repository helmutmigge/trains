package br.hm.thoughtworks.trains.console;

import br.hm.thoughtworks.trains.core.graph.Digraphs;
import br.hm.thoughtworks.trains.core.graph.EdgeWeightedDigraph;
import br.hm.thoughtworks.trains.core.graph.Vertex;

import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by helmutmigge on 05/02/2017.
 */
public class ShortestRouteCommandHandler implements CommandHandler {
    private final static Pattern DEFAULT_PATTERN = Pattern.compile("\\A([A-Za-z]{1})->([A-Za-z]{1})\\z");
    private final EdgeWeightedDigraph digraph;

    private Matcher matcherCache;

    public ShortestRouteCommandHandler(EdgeWeightedDigraph digraph){
        this.digraph = digraph;
    }

    @Override
    public boolean supported(String line) {
        matcherCache = DEFAULT_PATTERN.matcher(line);
        return matcherCache.matches();

    }

    @Override
    public String execute(String line) throws ParseException, NoSuchElementException {
        if (matcherCache == null){
            matcherCache = DEFAULT_PATTERN.matcher(line);
        }

        String fromString = matcherCache.group(1);
        String toString = matcherCache.group(2);
        return Digraphs.weightedShortestPath(digraph, new Vertex(fromString), new Vertex(toString)) +"";
    }
}
