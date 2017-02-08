package br.hm.thoughtworks.trains.core.graph;

/**
 * Created by helmutmigge on 07/02/2017.
 */
public class DepthCountDegree {

    public long getCount() {
        return count;
    }

    private long count;

    public DepthCountDegree(EdgeWeightedDigraph digraph, CriteriaDegree criteriaDegree) {


        if (criteriaDegree.getFrom().equals(criteriaDegree.getTo())) {
            digraph.adjacencyForVertex(criteriaDegree.getFrom()).forEach(
                    adj -> {
                        CriteriaDegree newCriteriaDegree = new CriteriaDegree(
                                adj.to()
                                , criteriaDegree.getTo()
                                , criteriaDegree.getMaxDegree()
                                , criteriaDegree.isOnlyEqualDegree());

                        dfs(digraph, criteriaDegree, criteriaDegree.getFrom(), 1);
                    }

            );
        }else {
            dfs(digraph, criteriaDegree, criteriaDegree.getFrom(), 0);
        }
    }


    private void dfs(EdgeWeightedDigraph digraph, CriteriaDegree criteriaDegree, Vertex source, int currentDegree) {
        if (currentDegree <= criteriaDegree.getMaxDegree()) {
            if (criteriaDegree.degreeSupport(currentDegree,source)){
                count++;
            }
            digraph.adjacencyForVertex(source).forEach(
                    directedEdge -> {
                        dfs(digraph,criteriaDegree,directedEdge.to(),currentDegree + 1);
                    }

            );
        }
    }
}
