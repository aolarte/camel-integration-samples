package com.javaprocess.examples.integration.camel;

import com.javaprocess.examples.integration.main.Main;

/**
 * Created by andres.olarte on 9/4/15.
 */
public class ApplicationConfig {

    public boolean isDirect() {
        return Main.argList.contains("direct");
    }

    public boolean isClient() {
        return Main.argList.contains("client");
    }

    public boolean isServer() { return Main.argList.contains("server"); }
}
