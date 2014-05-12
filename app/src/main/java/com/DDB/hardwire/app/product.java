package com.DDB.hardwire.app;

/**
 * Created by MarK on 09-May-14.
 */
public class product {

    String productName, productDescription, listId;
    int productPrice, id;

    public product(String listId, int id, String productName, String productDescription, int productPrice) {
        this.listId = listId;
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getListId() {
        return listId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductPrice(){
        return productPrice;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
