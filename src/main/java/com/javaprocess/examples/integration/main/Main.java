package com.javaprocess.examples.integration.main;

import com.javaprocess.examples.integration.impl.App;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andres.olarte on 5/4/15.
 */
public class Main {

    public static List<String> argList;

    public static void main(String... args) {
        argList = Arrays.asList(args);
        boolean clientMode = false;
        if (argList.contains("direct")) {
            clientMode = true;
        }
        if (argList.contains("client")) {
            clientMode = true;
        }


        Weld weld = new Weld();
        WeldContainer container = weld.initialize();


        if (clientMode) {
            System.out.println("Running in client mode");
            App app = container.instance().select(App.class).get();
            app.run();

        } else {
            System.out.println("Running in server mode");
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        weld.shutdown();


    }

}
