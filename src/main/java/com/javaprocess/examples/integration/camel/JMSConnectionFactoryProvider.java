package com.javaprocess.examples.integration.camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.ConnectionFactory;

/**
 * Created by andres.olarte on 8/4/15.
 */
@Singleton
public class JMSConnectionFactoryProvider {

    @Inject
    ApplicationConfig applicationConfig;

    String connectionString = "tcp://localhost:61616";

    BrokerService broker;

    @PostConstruct
    public void init() throws Exception {
        if (applicationConfig.isServer()) {
            broker = new BrokerService();
            broker.addConnector(connectionString);
            broker.setPersistent(false);
            broker.start();
        }
    }

    @Produces
    public ConnectionFactory createConnectionFactory() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                connectionString);
        return connectionFactory;

    }

    @PreDestroy
    public void shutdown() throws Exception {
        if (broker != null) {
            System.out.println("Stop ActiveMQ");
            broker.stop();
        }
    }


}
