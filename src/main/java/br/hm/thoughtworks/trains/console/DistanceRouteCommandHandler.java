package br.hm.thoughtworks.trains.console;

import br.hm.thoughtworks.trains.core.graph.Digraphs;
import br.hm.thoughtworks.trains.core.graph.EdgeWeightedDigraph;
import br.hm.thoughtworks.trains.core.graph.VertexPath;

import java.text.ParseException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.WeakHashMap;

/**
 * Created by helmutmigge on 05/02/2017.
 */
class DistanceRouteCommandHandler implements CommandHandler{

    private EdgeWeightedDigraph digraph;
    private Map<String,VertexPath> cache = new WeakHashMap<>();

    public DistanceRouteCommandHandler(EdgeWeightedDigraph digraph){
        this.digraph= digraph;
    }

    @Override
    public boolean supported(String line) {
        try {
            cache.put(line,VertexPath.parseVertexPath(line)) ;
            return true;
        }catch (ParseException e){
            return false;
        }

    }

    @Override
    public String execute(String line) throws ParseException, NoSuchElementException {
        VertexPath vertexPath = cache.get(line);
        if (vertexPath == null){
            vertexPath = VertexPath.parseVertexPath(line);
        }
        cache.clear();
        return Digraphs.weightedPath(digraph,vertexPath)+"";

    }
}