package com.javaprocess.examples.integration.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Header;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: andres
 * Date: 10/21/2015
 * Time: 7:48 PM
 */
@Named("claimCheckOut")
public class ClaimCheckOut {

    @Inject
    private DataStore dataStore;

    public void checkOut(Exchange exchange, @Header("claimCheck") String claimCheck) {
        exchange.getIn().setBody(dataStore.get(claimCheck));
        // remove the message data from the data store
        dataStore.remove(claimCheck);
        // remove the claim check header
        exchange.getIn().removeHeader("claimCheck");
    }
}
