package com.javaprocess.examples.integration.pojos;

import java.io.Serializable;

/**
 * Created by andres.olarte on 5/4/15.
 */
public class Order implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
