package br.hm.thoughtworks.trains.core.graph;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formata objetos DirectedEdge em String. Tambem é possivel transformar String no formato padrão para um objeto DirectedEdge
 * O formato padrão da string deve seguir a expressão [A-Z-a-z][A-Z-a-z]\d
 * Created by helmutmigge on 04/02/2017.
 */
public class DirectedEdgeFormat extends Format {

    private static final Pattern DEFAULT_PATTERN = Pattern.compile("\\A([a-zA-Z])([a-zA-Z])(\\d)+\\z");

    /**
     * Formata o objeto DirectedEdge em uma String;
     * @param obj um instancia de DirectedEdge
     * @param toAppendTo Onde o texto deve ser adiconado
     * @param pos Identifica um campo no texto formatado
     * @return A mesma instancia de {@code toAppendTo} com o texto adicionado
     * @throws IllegalArgumentException CAso a instancia do {@code obj} não seja DirectedEdge
     */
    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (obj instanceof DirectedEdge)
            return format((DirectedEdge) obj, toAppendTo);
        else
            throw new IllegalArgumentException("Cannot format give Object as a DirectedEdge");

    }

    /**
     * Formata o objeto DirectedEdge em uma String;
     * @param directedEdge um instancia de DirectedEdge
     * @param toAppendTo Onde o texto deve ser adiconado
     * @return A mesma instancia de {@code toAppendTo} com o texto adicionado
     */
    private StringBuffer format(DirectedEdge directedEdge, StringBuffer toAppendTo) {
        Vertex from = directedEdge.from();
        Vertex to = directedEdge.to();
        long weight = directedEdge.getWeight();

        toAppendTo.append(from);
        toAppendTo.append(to);
        toAppendTo.append(Long.toString(weight));
        return toAppendTo;
    }

    /**
     * Monta uma Aresta(DirectedEdge) atraves que um String com o formato padrão
     * @param source String no formato padrão
     * @param pos Identifica um campo no texto formatado
     * @return uma instancia de DirectedEdge
     */
    @Override
    public Object parseObject(String source, ParsePosition pos) {
        Matcher matcher = DEFAULT_PATTERN.matcher(source);
        DirectedEdge result = null;
        if (matcher.matches()) {
            String initialVertexName = matcher.group(1);
            String endVertexName = matcher.group(2);
            Integer weight = new Integer(matcher.group(3));
            pos.setIndex(3);
            result = new DirectedEdge(initialVertexName, endVertexName, weight);
        }
        return result;
    }

}

