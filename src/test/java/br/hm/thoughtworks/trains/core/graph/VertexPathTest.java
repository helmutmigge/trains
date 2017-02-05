package br.hm.thoughtworks.trains.core.graph;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * Created by helmutmigge on 05/02/2017.
 */
public class VertexPathTest {


    @Test
    public void createVerticesPath(){
        String[] vertexNames = {"A","B","C"};
        VertexPath path = new VertexPath(vertexNames);
        Assert.assertEquals(3,path.path().count());
    }


}