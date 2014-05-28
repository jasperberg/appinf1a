package com.DDB.hardwire.app;

/**
 * Created by MarK on 22-May-14.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ProductDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public ProductDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addProduct(Product Product) {
        String sql = "INSERT INTO PRODUCTS (listid, _id, name, description, price) VALUES ('"
                + Product.getListId() + "','"
                + Product.getId() + "','"
                + Product.getProductName() + "','"
                + Product.getProductDescription() + "','"
                + Product.getProductPrice() + "')";
        database.execSQL(sql);
    }

    public void deleteProduct(Product product) {
        long id = product.getId();
        System.out.println("Product deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_PRODUCTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCTS,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product Product = cursorToProduct(cursor);
            products.add(Product);
            cursor.moveToNext();
        }
        cursor.close();
        return products;
    }

    private Product cursorToProduct(Cursor cursor) {
        String listid = cursor.getString(0);
        int id = cursor.getInt(1);
        String name = cursor.getString(2);
        String description = cursor.getString(3);
        int price = cursor.getInt(4);
        Product Product = new Product(listid, id, name, description, price);
        return Product;
    }
}