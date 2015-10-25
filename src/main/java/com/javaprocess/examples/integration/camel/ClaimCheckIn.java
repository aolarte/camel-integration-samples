package com.javaprocess.examples.integration.camel;

import org.apache.camel.Body;
import org.apache.camel.Exchange;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

/**
 * User: andres
 * Date: 10/21/2015
 * Time: 7:47 PM
 */
@Named("claimCheckIn")
public class ClaimCheckIn {

    @Inject
    private DataStore dataStore;

    public void checkIn(Exchange exchange, @Body Object body) {
        String id = UUID.randomUUID().toString();
        // store the message in the data store
        dataStore.put(id, body);
        // add the claim check as a header
        exchange.getIn().setHeader("claimCheck", id);

        // remove the body from the message
        exchange.getIn().setBody(null);
    }
}
