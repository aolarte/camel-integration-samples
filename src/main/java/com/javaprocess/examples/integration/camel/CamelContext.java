package com.javaprocess.examples.integration.camel;

import com.javaprocess.examples.integration.cdi.Eager;
import com.javaprocess.examples.integration.interfaces.IOrderService;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.ProxyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.component.jms.JmsComponent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

/**
 * Created by andres.olarte on 7/30/15.
 */


@Eager
@ApplicationScoped
public class CamelContext {


    @Inject
    CdiCamelContext camelCtx;

    @Inject
    ConnectionFactory connectionFactory;

    @Inject
    ApplicationConfig applicationConfig;


    RouteBuilder directCamelRouteRoute = new RouteBuilder() {
        @Override
        public void configure() throws Exception {
            from("direct:order").to("bean:orderServiceHandler");

        }
    };

    RouteBuilder jmsClientCamelRoute = new RouteBuilder() {
        @Override
        public void configure() throws Exception {
            from("direct:order").to( "jms:queue:order", "bean:claimCheckOut").setExchangePattern(ExchangePattern.InOut);
        }
    };

    RouteBuilder jmsServerCamelRoute = new RouteBuilder() {
        @Override
        public void configure() throws Exception {
            from("jms:queue:order?concurrentConsumers=5").to( "bean:orderServiceHandler", "bean:claimCheckIn");
        }
    };


    @PostConstruct
    public void load() throws Exception {


        if (applicationConfig.isClient() || applicationConfig.isServer()) {
            camelCtx.addComponent("jms",
                    JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        }

        // Add the proper camel routes, as per the arguments passed in the command line
        if (applicationConfig.isDirect()) {
            camelCtx.addRoutes(directCamelRouteRoute);
        }

        if (applicationConfig.isClient()) {
            camelCtx.addRoutes(jmsClientCamelRoute);
        }

        if (applicationConfig.isServer()) {
            camelCtx.addRoutes(jmsServerCamelRoute);
        }

        // Start Camel Context
        camelCtx.start();


    }

    @Produces
    public IOrderService createService() throws Exception {
        IOrderService service = new ProxyBuilder(camelCtx).endpoint("direct:order").build(IOrderService.class);
        return service;
    }

    @PreDestroy
    public void shutdown() throws Exception {
        System.out.println("Stop Camel");
        camelCtx.shutdown();
    }
}
