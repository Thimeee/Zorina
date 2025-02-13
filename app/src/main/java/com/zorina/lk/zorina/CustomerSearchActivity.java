package com.zorina.lk.zorina;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zorina.lk.zorina.model.Product;
import com.zorina.lk.zorina.model.ProductResponse;
import com.zorina.lk.zorina.model.Test;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CustomerSearchActivity extends AppCompatActivity {
    ArrayList<Product>  AllProductList;
    RecyclerView SearchRecycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       TextView backButton =findViewById(R.id.CustomerSearch_Back);
       backButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               finish();
           }
       });
         SearchRecycle = findViewById(R.id.SearchRecycleView);

        SearchRecycle.setLayoutManager(new GridLayoutManager(this,2));
        getAllProduct();

        EditText searchText = findViewById(R.id.SerchProductTextCustomer);
        ImageView searchTextbu = findViewById(R.id.searchButtonProductCustomer);
        searchTextbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("App", searchText.getText().toString());
                if(searchText.getText().toString().isEmpty()){
                    getAllProduct();
                }else {
                    ArrayList<Product> searchProductList = new ArrayList<>();
                    for (Product product : AllProductList) {
                        if (product.getProductName().contains(searchText.getText().toString())) {
                            searchProductList.add(product);
                        }
                    }

                    searchAdapter searchadp = new searchAdapter(searchProductList);
                    SearchRecycle.setAdapter(searchadp);

                }
            }
        });

    }
    public  void getAllProduct(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://tick-crucial-gently.ngrok-free.app/zorina/getAllActiveProduct")
                        .build();

                try {
                    Response response = httpClient.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        throw new RuntimeException("Unexpected response: " + response);
                    }

                    String respText = response.body().string();
                    Log.i("App 15", respText);

                    // Parse JSON response
                    Gson gson = new Gson();
                    ProductResponse productResponse = gson.fromJson(respText, ProductResponse.class);
                    AllProductList = productResponse.getProductList();
                    Log.i("App 15", String.valueOf(AllProductList) );

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (AllProductList != null) {

                                searchAdapter searchadp = new searchAdapter(AllProductList);
                                SearchRecycle.setAdapter(searchadp);

                            }
                        }
                    });




                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

class searchViewHolder extends RecyclerView.ViewHolder {

    public TextView SearchPrice;
    public TextView searchTitle;

    public ImageView serchproductImage;


    public searchViewHolder(@NonNull View itemView) {
        super(itemView);
        SearchPrice = itemView.findViewById(R.id.SearchItemPrice);
        searchTitle = itemView.findViewById(R.id.SearchItemTitle);

        serchproductImage =itemView.findViewById(R.id.SearchImageView);
    }
}

class searchAdapter extends RecyclerView.Adapter<searchViewHolder> {

    public ArrayList<Product> searchArrayList;

    public searchAdapter(ArrayList<Product> searchArrayList) {
        this.searchArrayList = searchArrayList;
    }


    @NonNull
    @Override
    public searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View searchItemview = layoutInflater.inflate(R.layout.search_items, parent, false);

        searchViewHolder viewHolder = new searchViewHolder(searchItemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull searchViewHolder holder, int position) {
        Product searchItemObj = searchArrayList.get(position);
        holder.SearchPrice.setText(String.valueOf(searchItemObj.getProductPrice()));
        holder.searchTitle.setText(searchItemObj.getProductName());
        Glide.with(holder.serchproductImage.getContext())
                .load("https://tick-crucial-gently.ngrok-free.app/zorina/productimage/product"+searchItemObj.getId()+"_temp_image1.jpg")
                .into(holder.serchproductImage);

    }

    @Override
    public int getItemCount() {
        return this.searchArrayList.size();
    }
}