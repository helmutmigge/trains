package br.hm.thoughtworks.trains.core.graph;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.regex.Pattern;

/**
 * Formata objetos {@link VertexPath} em String. Tambem é possivel transformar String no
 * formato padrão para um objeto {@link VertexPath}
 * O formato padrão da string deve seguir o seguinte exemplo A-B-E-D
 * Created by helmutmigge on 05/02/2017.
 */
public class VertexPathFormat  extends Format{

    private static final Pattern DEFAULT_VERTEX_PATTERN = Pattern.compile("\\A[A-Za-z]\\z");

    /**
     * Formata o objeto {@link VertexPath} em uma String;
     * @param vertexPath um instancia de VertexPath
     * @param toAppendTo Onde o texto deve ser adiconado
     * @return A mesma instancia de {@code toAppendTo} com o texto adicionado
     */
    private StringBuffer format(VertexPath vertexPath, StringBuffer toAppendTo){
        vertexPath.path().forEach(vertex ->{
                if (toAppendTo.length() != 0) {
                    toAppendTo.append('-');
                }
                toAppendTo.append(vertex.getName());
        });
        return toAppendTo;
    }

    /**
     * Formata o objeto {@link VertexPath} em uma String;
     * @param obj um instancia de VertexPath
     * @param toAppendTo Onde o texto deve ser adiconado
     * @param pos Identifica um campo no texto formatado
     * @return A mesma instancia de {@code toAppendTo} com o texto adicionado
     * @throws IllegalArgumentException Caso {@code obj} não seja uma instancia de VertexPath
     */
    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (obj instanceof VertexPath)
            return format((VertexPath) obj, toAppendTo);
        else
            throw new IllegalArgumentException("Cannot format give Object as a VertexPath");
    }

    /**
     * Monta um caminho de vertice(rota){@link VertexPath} atraves que um String com o formato padrão
     * @param source String no formato padrão
     * @param pos Identifica um campo no texto formatado
     * @return uma instancia de {@link VertexPath}
     */
    @Override
    public Object parseObject(String source, ParsePosition pos) {
        String[] vertexNames = source.split("-");
        for (int i = 0 ; i < vertexNames.length; i++){
            String vertexName = vertexNames[i];
            if (! DEFAULT_VERTEX_PATTERN.matcher(vertexName).matches()){
                pos.setIndex(0);
                pos.setErrorIndex(i);
                break;
            }
            pos.setIndex(i);
        }
        return new VertexPath(vertexNames);
    }
}
