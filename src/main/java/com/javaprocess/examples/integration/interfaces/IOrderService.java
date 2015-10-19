package com.javaprocess.examples.integration.interfaces;

import com.javaprocess.examples.integration.pojos.Order;
import com.javaprocess.examples.integration.pojos.OrderConfirmation;

public interface IOrderService {
    OrderConfirmation placeOrder(Order order);
}
