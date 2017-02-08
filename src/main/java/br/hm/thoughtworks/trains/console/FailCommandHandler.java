package br.hm.thoughtworks.trains.console;

import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 * Created by helmutmigge on 07/02/2017.
 */
public class FailCommandHandler  implements CommandHandler {

    @Override
    public boolean supported(String line) {
        return true;
    }

    @Override
    public String execute(String line) throws ParseException, NoSuchElementException {
        return "---input is not correct \""+line+"\"---.\ntry again";
    }
}
