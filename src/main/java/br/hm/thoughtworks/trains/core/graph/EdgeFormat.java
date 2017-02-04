package br.hm.thoughtworks.trains.core.graph;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formata objetos Edge em String. Tambem é possivel transformar String no formato padrão para um objeto Edge
 * O formato padrão da string deve seguir a expressão [A-D-a-d]{1}[A-D-a-d]{1}\d
 * Created by helmutmigge on 04/02/2017.
 */
public class EdgeFormat extends Format {

    private static final Pattern DEFAULT_PATTERN = Pattern.compile("([a-zA-Z]{1})([a-zA-Z]{1})(\\d)");

    /**
     * Formata o objeto Edge em uma String;
     * @param obj um instancia de Edge
     * @param toAppendTo Onde o texto deve ser adiconado
     * @param pos Identifica um campo no texto formatado
     * @return A mesma instancia de {@code toAppendTo} com o texto adicionado
     * @throws IllegalArgumentException CAso a instancia do {@code obj} não seja Edge
     */
    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (obj instanceof Edge)
            return format((Edge) obj, toAppendTo, pos);
        else
            throw new IllegalArgumentException("Cannot format give Object as a Edge");

    }

    /**
     * Formata o objeto Edge em uma String;
     * @param edge um instancia de Edge
     * @param toAppendTo Onde o texto deve ser adiconado
     * @param fieldPosition Identifica um campo no texto formatado
     * @return A mesma instancia de {@code toAppendTo} com o texto adicionado
     */
    public StringBuffer format(Edge edge, StringBuffer toAppendTo,
                               FieldPosition fieldPosition) {
        Vertex initialVertex = edge.either();
        Vertex endVertex = edge.other(initialVertex);
        int weight = edge.getWeight();

        toAppendTo.append(initialVertex);
        toAppendTo.append(endVertex);
        toAppendTo.append(Integer.toString(weight));
        return toAppendTo;
    }

    /**
     * Monta uma Aresta(Edge) atraves que um String com o formato padrão
     * @param source String no formato padrão
     * @param pos Identifica um campo no texto formatado
     * @return uma instancia de Edge
     */
    @Override
    public Object parseObject(String source, ParsePosition pos) {
        Matcher matcher = DEFAULT_PATTERN.matcher(source);
        Edge result = null;
        if (matcher.matches()) {
            String initialVertexName = matcher.group(1);
            String endVertexName = matcher.group(2);
            Integer weight = new Integer(matcher.group(3));
            pos.setIndex(3);
            result = new Edge(initialVertexName, endVertexName, weight);
        }
        return result;
    }

}

