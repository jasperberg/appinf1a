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
import android.widget.Toast;

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
        int lastid = getLastProductId();
        lastid ++;
        String sql = "INSERT INTO PRODUCTS (pk, listid, _id, name, description, price) VALUES ('"
                + lastid + "','"
                + Product.getListId() + "','"
                + Product.getId() + "','"
                + Product.getProductName() + "','"
                + Product.getProductDescription() + "','"
                + Product.getProductPrice() + "')";
        database.execSQL(sql);
    }

    public void changeBuildName(String name) {
        String delsql = "DELETE FROM BUILDNAME";
        database.execSQL(delsql);
        String sql = "INSERT INTO BUILDNAME (name) VALUES ('"
                + name + "')";
        database.execSQL(sql);
    }

    public String getBuildName(){
        String buildname;
        String count = "SELECT count(*) FROM BUILDNAME";
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0) {
            Cursor cursor = database.rawQuery("SELECT * FROM BUILDNAME", null);
            cursor.moveToFirst();
            String value = cursorToString(cursor);
            cursor.close();
            buildname = value;
        }
        else{
            buildname = "Mijn Computer";
        }
        mcursor.close();
        return buildname;
    }

    public void deleteProduct(int pkid) {
        int id = pkid;
        System.out.println("Product deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_PRODUCTS, MySQLiteHelper.COLUMN_PK
                + " = " + id, null);
    }

    public void deleteBuild(){
        String prodSql = "DELETE FROM PRODUCTS";
        String nameSql = "DELETE FROM BUILDNAME";
        database.execSQL(prodSql);
        database.execSQL(nameSql);
    }

    public int getLastProductId(){
        int lastid;
        String count = "SELECT count(*) FROM PRODUCTS";
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0) {
            String query = "SELECT pk FROM PRODUCTS ORDER BY pk DESC LIMIT 1;";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastid = cursor.getInt(0);
        }
        else{
            lastid = 0;
        }
        return lastid;
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
        int pkid = cursor.getInt(0);
        String listid = cursor.getString(1);
        int id = cursor.getInt(2);
        String name = cursor.getString(3);
        String description = cursor.getString(4);
        double price = cursor.getDouble(5);
        Product Product = new Product(pkid, listid, id, name, description, price);
        return Product;
    }

    private String cursorToString(Cursor cursor) {
        String name = cursor.getString(0);
        return name;
    }
}