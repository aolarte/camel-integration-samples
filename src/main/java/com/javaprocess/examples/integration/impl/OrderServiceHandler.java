package com.javaprocess.examples.integration.impl;

import com.javaprocess.examples.integration.pojos.Order;
import com.javaprocess.examples.integration.pojos.OrderConfirmation;

import javax.inject.Named;

/**
 * Created by andres.olarte on 5/10/15.
 */
@Named("orderServiceHandler")
public class OrderServiceHandler {


    public OrderConfirmation processOrder(Order order) {
        long threadId = Thread.currentThread().getId();
        System.out.println("Got order with id " + order.getId() + " on thread: " + threadId);
        OrderConfirmation ret = new OrderConfirmation();

        ret.setThreadHandler(threadId);
        return ret;
    }
}
