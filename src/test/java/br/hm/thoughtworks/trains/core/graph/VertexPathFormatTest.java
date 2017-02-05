package br.hm.thoughtworks.trains.core.graph;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * Created by helmutmigge on 05/02/2017.
 */
public class VertexPathFormatTest {
    @Test
    public void format() throws Exception {
        String[] vertexNames = {"A","B","C"};
        VertexPath path = new VertexPath(vertexNames);
        Assert.assertEquals("A-B-C",new VertexPathFormat().format(path));

    }

    @Test
    public void parseObject() throws Exception {
        VertexPath path =(VertexPath) new VertexPathFormat().parseObject("A-B-C");
        Assert.assertEquals(3,path.path().count());
    }

    @Test(expected = ParseException.class)
    public void parseObjectFail() throws ParseException {
        VertexPath path =(VertexPath) new VertexPathFormat().parseObject("AQ-B-C");
        Assert.assertEquals(3,path.path().count());
    }

}