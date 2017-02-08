package br.hm.thoughtworks.trains.core.graph;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formata objetos {@link CriteriaDegree} em String.
 * Tambem é possivel transformar String no formato padrão para um objeto DirectedEdge
 * O formato padrão da string deve seguir duas expressões:
 * [A-Z-a-z]->[A-Z-a-z]=[0-9]+
 * [A-Z-a-z]->[A-Z-a-z]<=[0-9]+
 * Created by helmutmigge on 04/02/2017.
 */
public class CriteriaDegreeFormat extends Format {

    private static final Pattern DEFAULT_EQUAL_PATTERN = Pattern.compile("([a-zA-Z])->([a-zA-Z])=(\\d+)");
    private static final Pattern DEFAULT_MAX_PATTERN = Pattern.compile("([a-zA-Z])->([a-zA-Z])<=(\\d+)");

    private static final Pattern[] PATTERNS;

    static {


        PATTERNS =new Pattern[]{DEFAULT_EQUAL_PATTERN,DEFAULT_MAX_PATTERN};
    }

    /**
     * Formata o objeto CriteriaDegree em uma String;
     * @param obj um instancia de CriteriaDegree
     * @param toAppendTo Onde o texto deve ser adiconado
     * @param pos Identifica um campo no texto formatado
     * @return A mesma instancia de {@code toAppendTo} com o texto adicionado
     * @throws IllegalArgumentException CAso a instancia do {@code obj} não seja CriteriaDegree
     */

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (obj instanceof CriteriaDegree)
            return format((CriteriaDegree) obj, toAppendTo);
        else
            throw new IllegalArgumentException("Cannot format give Object as a CriteriaDegree");
    }


    /**
     * Formata o objeto CriteriaDegree em uma String;
     * @param criteriaDegree um instancia de CriteriaDegree
     * @param toAppendTo Onde o texto deve ser adiconado
     * @return A mesma instancia de {@code toAppendTo} com o texto adicionado
     */
    private StringBuffer format(CriteriaDegree criteriaDegree, StringBuffer toAppendTo) {
        toAppendTo.append(criteriaDegree.getFrom());
        toAppendTo.append("->");
        toAppendTo.append(criteriaDegree.getTo());
        if (criteriaDegree.isOnlyEqualDegree()){
            toAppendTo.append("=");
        }else{
            toAppendTo.append("<");
        }
        toAppendTo.append(criteriaDegree.getMaxDegree());

        return toAppendTo;
    }
    /**
     * Monta um CriteriaDegree atraves que  String com os formatos padrões
     * @param source String nos formatos padrões
     * @param pos Identifica um campo no texto formatado
     * @return uma instancia de CriteriaDegree
     */

    @Override
    public Object parseObject(String source, ParsePosition pos) {

        for (Pattern pattern : PATTERNS){
            Matcher matcher = pattern.matcher(source);
            if (matcher.matches()){
                boolean equalDegree = pattern == DEFAULT_EQUAL_PATTERN;
                String initialVertexName = matcher.group(1);
                String endVertexName = matcher.group(2);
                Integer degree = new Integer(matcher.group(3));
                pos.setIndex(3);
                return new CriteriaDegree(initialVertexName, endVertexName, degree,equalDegree);
            }
        }
        return null;

    }
}
