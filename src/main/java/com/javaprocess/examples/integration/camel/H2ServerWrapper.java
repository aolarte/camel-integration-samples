package com.javaprocess.examples.integration.camel;

import org.h2.tools.Server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;

/**
 * User: andres
 * Date: 10/22/2015
 * Time: 1:34 AM
 */
@Singleton
public class H2ServerWrapper {

    @Inject
    ApplicationConfig config;

    Server server;

    @PostConstruct
    public void init() {
        if (config.isServer() && server == null) {
            try {
                server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8043");
                server.start();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @PreDestroy
    public void shutdown() throws Exception {
        if (server != null) {
            System.out.println("Stop H2");
            server.stop();
        }
    }
}
