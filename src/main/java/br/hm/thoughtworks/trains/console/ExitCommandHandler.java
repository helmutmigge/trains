package br.hm.thoughtworks.trains.console;

import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 * Created by helmutmigge on 05/02/2017.
 */
public class ExitCommandHandler implements CommandHandler {
    @Override
    public boolean supported(String line) {
        return line == null || line.isEmpty();
    }

    @Override
    public String execute(String line) throws ParseException, NoSuchElementException {
        return null;
    }
}
