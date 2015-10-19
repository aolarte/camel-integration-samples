package com.javaprocess.examples.integration.camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

/**
 * Created by andres.olarte on 8/4/15.
 */
@ApplicationScoped
public class JMSConnectionFactoryProvider {

    String connectionString="tcp://localhost:61616";

    BrokerService broker;

    @Inject
    public JMSConnectionFactoryProvider(ApplicationConfig applicationConfig) throws Exception {
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


}
