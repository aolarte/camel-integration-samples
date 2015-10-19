package com.javaprocess.examples.integration.pojos;

import java.io.Serializable;

/**
 * Created by andres.olarte on 5/4/15.
 */
public class OrderConfirmation implements Serializable {
    long threadHandler;

    public long getThreadHandler() {
        return threadHandler;
    }

    public void setThreadHandler(long threadHandler) {
        this.threadHandler = threadHandler;
    }
}
