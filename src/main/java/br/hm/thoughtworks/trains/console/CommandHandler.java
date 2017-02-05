package br.hm.thoughtworks.trains.console;

import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 * Created by helmutmigge on 05/02/2017.
 */
interface CommandHandler{
    boolean supported(String line);
    String execute(String line)throws ParseException,NoSuchElementException;
}