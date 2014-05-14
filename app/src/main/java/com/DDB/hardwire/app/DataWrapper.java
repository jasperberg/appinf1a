package com.DDB.hardwire.app;

/**
 * Created by MarK on 13-May-14.
 */

import java.io.Serializable;
import java.util.List;

public class DataWrapper implements Serializable {

    private List<product> products;

    public DataWrapper(List<product> data) {
        this.products = data;
    }

    public List<product> getProductCategory() {
        return products;
    }

}