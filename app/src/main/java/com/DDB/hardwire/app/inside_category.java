package com.DDB.hardwire.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.DDB.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Inside_category extends Activity implements Serializable {

    private static String _category;
    List<Product> Products = new ArrayList<Product>();
    ListView productListView;
    private List<Product> productCategory = new ArrayList<Product>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_category);
        setTitle(_category);
        productListView = (ListView) findViewById(R.id.listView);
        addProduct();
        populateList();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(Inside_category.this, ProductView.class);
                int productid = productCategory.get(position).getId();
                intent.putExtra("Product", productid);
                intent.putExtra("data", new DataWrapper(productCategory));
                intent.putExtra("method", "None");
                startActivity(intent);
            }
        });
    }

    public List<Product> getProductCategory(){
        return productCategory;
    }

    public static void setCategory(String category){
        _category = category;
    }

    public void addProduct(){
        //Products.add(new Product("MB Socket AM3+",1, "ASRock 960GM-VGS3 FX", "De 960GM-VGS3 FX van ASRock ondersteunt één AMD Socket AM3+ processor samen met maximaal 16 GB aan geheugen. Verder is er een onboard ATI Radeon HD3000 grafische kaart aanwezig, is er één PCIe x16 2.0 slot aanwezig en kunnen er SATA apparaten worden aangesloten.", 45));
        //Products.add(new Product("Socket 2011",2, "Intel Core i7 - 3820", "De Intel® Core™ i7-3820 CPU, codenaam \"Sandy Bridge E\", beschikt over vier verwerkingseenheden en is geschikt om op een moederbord met Socket 2011 te plaatsen. De processor beschikt over 4x 256 KB Level 2 en 10240 KB Level 3 cache en werkt op een snelheid van 3600 MHz met een HyperTransport bus van 4800 MT/s.", 200));
        //Products.add(new Product("AMD",3, "ASUS EAH5450", "Bereid je voor op een geweldige high-definition Multimedia ervaring met de ATI Radeon HD5400 serie. Met behulp van ATI-Stream-technologie versnellen zelfs de meest veeleisende applicaties en doen deze meer dan ooit met uw PC. Tevens ondersteunt hij volledig Microsoft DirectX 11.De EAH5450 SILENT/DI/1GD3(LP) van Asus is gebaseerd op de ATI Radeon HD5450 Chip en beschikt over 1024 MB GDDR3 Geheugen dat via een 64 bit brede interface aangesproken wordt. De GPU heeft een kloksnelheid van 650 MHz en het geheugen een snelheid van 900 MHz.", 430));
        //Products.add(new Product("Nvidia",4, "ASUS EN210", "De EN210 SILENT/DI/1GD3/V2(LP) van Asus is gebaseerd op de NVIDIA GeForce 210 Chip en beschikt over 1024 MB GDDR3 Geheugen dat via een 64 bit brede interface aangesproken wordt. De GPU heeft een kloksnelheid van 589 MHz en het geheugen een snelheid van 1200 MHz.", 200));
        //Products.add(new Product("DDR2",5, "Corsair 1 GB DDR2-533", "Deze geheugenmodule heeft een capaciteit van 1024 MB en is geschikt voor systemen met een DDR2 geheugenbus van 533 MHz.", 430));
        GetItems GI = new GetItems();
        Products = GI.getProductLister();
        int listLength = Products.size();
        for(int i = 0; i < listLength; i++){
            if(Products.get(i).getListId().equals(_category)){
                productCategory.add(Products.get(i));
            }
        }
    }

    private void populateList(){
        ArrayAdapter<Product> adapter = new ProductListAdapter();
        productListView.setAdapter(adapter);
    }


    public String getCategory(){
        return _category;
    }

    private class ProductListAdapter extends ArrayAdapter<Product>{
        public ProductListAdapter(){
            super (Inside_category.this, R.layout.product, productCategory);
        }

        @Override
        public long getItemId(int position){
            // return the id(), or whatever you use to access the id on your Product object
            return productCategory.get(position).getId();
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.product, parent, false);

            Product prod = productCategory.get(position);

            TextView name = (TextView) view.findViewById(R.id.productTitle);
            name.setText(prod.getProductName());
            TextView price = (TextView) view.findViewById(R.id.productPrice);
            price.setText("€"+prod.getProductPrice());

            return view;
        }
    }
}
