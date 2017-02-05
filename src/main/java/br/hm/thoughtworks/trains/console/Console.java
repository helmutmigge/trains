package br.hm.thoughtworks.trains.console;

import br.hm.thoughtworks.trains.core.In;
import br.hm.thoughtworks.trains.core.graph.EdgeWeightedDigraph;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by helmutmigge on 05/02/2017.
 */
public class Console {


    private final EdgeWeightedDigraph digraph;

    private final List<CommandHandler> commandHandlers = new ArrayList<>();

    public Console(EdgeWeightedDigraph digraph) {
        this.digraph = digraph;
        commandHandlers.add(new ExitCommandHandler());
        commandHandlers.add(new DistanceRouteCommandHandler(digraph));
    }


    public void run() {
        displayMenu();
        displayGraph();
        waitCommand();
        System.out.println("Bye!!!");
    }

    private void displayGraph(){
        System.out.println("data in graph:\n\t"+digraph.toString());
        System.out.println("-----------------------------------------------");
    }

    private void waitCommand() {
        In in = new In();
        boolean exit=false;
        while (! exit && in.hasNextLine()) {

            String line = in.readLine();
            try {
                for (CommandHandler commandHandler : commandHandlers) {
                    if (commandHandler.supported(line)) {
                        String result = commandHandler.execute(line);
                        if (result != null) {
                            System.out.println("Result:" + result + "\n");
                        } else {
                            exit =true;
                            break;
                        }
                    }
                }
            } catch (ParseException e) {
                System.out.println("---input is not correct---.\ntry again");
            } catch (NoSuchElementException e) {
                System.out.println("NO SUCH ROUTE.Check case sensitive.\ntry again\n");
            }
        }
    }

    private void displayMenu() {
        System.out.println("menu press:");
        System.out.println("\t distance of the route (Ex:A-B-C)");
        System.out.println("\t shortest route (Ex:A->B)");
        System.out.println("\t Blanck line for exit");
        System.out.println("-----------------------------------------------");
    }

}
