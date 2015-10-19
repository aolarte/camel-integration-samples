package com.javaprocess.examples.integration.camel;

import com.javaprocess.examples.integration.interfaces.IOrderService;
import com.javaprocess.examples.integration.pojos.Order;

import javax.inject.Inject;

/**
 * Created by andres.olarte on 7/30/15.
 */
public class Application {

    @Inject
    IOrderService service;

    public void run() {
        System.out.println("Running Application");
        Order order = new Order();
        System.out.println(service.placeOrder(order).getThreadHandler());

    }
}
