package br.hm.thoughtworks.trains.core;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by helmutmigge on 04/02/2017.
 */
public class InTest {

    private static final String CONTENT_INPUT = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    @Test
    public void readLine() throws Exception {
        In in = new In("input.txt");
        if(in.hasNextLine()){
            Assert.assertEquals(CONTENT_INPUT,in.readLine() );

        }
    }

    @Test
    public void readAll() throws Exception {
        In in = new In("input.txt");
        Assert.assertEquals(CONTENT_INPUT,in.readAll() );
    }

}