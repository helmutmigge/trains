package br.hm.thoughtworks.trains.console;

import br.hm.thoughtworks.trains.core.graph.*;

import java.text.ParseException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.WeakHashMap;

/**
 * Created by helmutmigge on 07/02/2017.
 */
public class CountTripsCommandHandler implements CommandHandler {
    private EdgeWeightedDigraph digraph;
    private Map<String,CriteriaDegree> cache = new WeakHashMap<>();

    public CountTripsCommandHandler(EdgeWeightedDigraph digraph){
        this.digraph= digraph;
    }

    @Override
    public boolean supported(String line) {
        try {
            cache.put(line,format(line));
            return true;
        }catch (ParseException e){
            return false;
        }

    }

    private CriteriaDegree format(String line) throws ParseException {
        return (CriteriaDegree)new CriteriaDegreeFormat().parseObject(line);
    }

    @Override
    public String execute(String line) throws ParseException, NoSuchElementException {
        CriteriaDegree criteriaDegree = cache.get(line);
        if (criteriaDegree == null){
            criteriaDegree = format(line);
        }
        cache.clear();
        return Digraphs.countDegree(digraph,criteriaDegree) + "";
    }
}
