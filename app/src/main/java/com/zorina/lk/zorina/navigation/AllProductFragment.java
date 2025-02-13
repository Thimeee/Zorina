package com.zorina.lk.zorina.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zorina.lk.zorina.R;
import com.zorina.lk.zorina.model.Product;
import com.zorina.lk.zorina.model.ProductResponse;
import com.zorina.lk.zorina.model.Test;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class AllProductFragment extends Fragment {
    ArrayList<Product>  AllProductList;
    RecyclerView AllProductlistRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_product, container, false);

         AllProductlistRecyclerView = view.findViewById(R.id.Recycle_view_allProduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        AllProductlistRecyclerView.setLayoutManager(linearLayoutManager);

        getAllProduct();

        EditText searchText = view.findViewById(R.id.allProductSerchAdmin);
        ImageView searchTextbu = view.findViewById(R.id.searchIconAllProductadmin);
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
                    AllProductAdapter allProductdp = new AllProductAdapter(searchProductList);
                    AllProductlistRecyclerView.setAdapter(allProductdp);
                }
            }
        });


        return view;
    }

    public  void getAllProduct(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://tick-crucial-gently.ngrok-free.app/zorina/getAllProduct")
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

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (AllProductList != null) {
                                AllProductAdapter allProductdp = new AllProductAdapter(AllProductList);
                                AllProductlistRecyclerView.setAdapter(allProductdp);
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



class AllProductViewHolder extends RecyclerView.ViewHolder {

    public TextView allProductTitle;
    public TextView allProductPrice;
    public TextView allProductStatus;
    public Switch allProductSwich;
    public ImageView allProductImage;


    public AllProductViewHolder(@NonNull View itemView) {
        super(itemView);
        allProductTitle   = itemView.findViewById(R.id.allProductTitle);
        allProductPrice = itemView.findViewById(R.id.allProductPrice);
        allProductStatus = itemView.findViewById(R.id.allProductStstus);
        allProductSwich = itemView.findViewById(R.id.allProductSwich);
        allProductImage =itemView.findViewById(R.id.allImageView);
    }
}

class AllProductAdapter extends RecyclerView.Adapter<AllProductViewHolder> {

    public ArrayList<Product> allProductArrayList;

    public AllProductAdapter(ArrayList<Product> allProductArrayList) {
        this.allProductArrayList = allProductArrayList;
    }


    @NonNull
    @Override
    public AllProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View allProductItemview = layoutInflater.inflate(R.layout.all_product_product_item, parent, false);

        AllProductViewHolder viewHolder = new AllProductViewHolder(allProductItemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductViewHolder holder, int position) {
        Product allProductObj = allProductArrayList.get(position);
        holder.allProductTitle.setText(allProductObj.getProductName());
        holder.allProductPrice.setText(String.valueOf(allProductObj.getProductPrice()));
        if(allProductObj.getProductStatus() == 1){
            holder.allProductStatus.setText("Available");
            holder.allProductStatus.setTextColor(0xFF008000);

        }else {
            holder.allProductStatus.setText("Not Available");
            holder.allProductStatus.setTextColor(0xFFFF0000);

        }
        Glide.with(holder.allProductImage.getContext())
                .load("https://tick-crucial-gently.ngrok-free.app/zorina/productimage/product"+allProductObj.getId()+"_temp_image1.jpg")
                .into(holder.allProductImage);
        if(allProductObj.getProductStatus() == 1) {
            holder.allProductSwich.setChecked(true);
        }else {
            holder.allProductSwich.setChecked(false);
        }
        holder.allProductSwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.allProductSwich.isChecked()) {
                    allProductObj.setProductStatus(0);
                    holder.allProductStatus.setText("Not Available");
                    holder.allProductStatus.setTextColor(0xFFFF0000);
                } else {
                    allProductObj.setProductStatus(1);
                    holder.allProductStatus.setText("Available");
                    holder.allProductStatus.setTextColor(0xFF008000);
                }

                Gson  gson= new Gson();

                JsonObject requestObj=new JsonObject();
                requestObj.add("product",gson.toJsonTree(allProductObj));

                String requsetJson=gson.toJson(requestObj);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient httpClient = new OkHttpClient();
                        MediaType Json = MediaType.parse("application/json; charset=utf-8");
                        RequestBody requsetbody = RequestBody.create(requsetJson, Json);
                        Request request = new Request.Builder()
                                .url("https://tick-crucial-gently.ngrok-free.app/zorina/updateProduct")
                                .post(requsetbody)
                                .build();

                        try {
                            Response response = httpClient.newCall(request).execute();
                            if (!response.isSuccessful()) {
                                throw new RuntimeException("Unexpected response: " + response);
                            }

                            String respText = response.body().string();
                            Log.i("App 15", respText);

                            // Parse JSON response
//                            Gson gson = new Gson();
//                            ProductResponse productResponse = gson.fromJson(respText, ProductResponse.class);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();



            }
        });


    }

    @Override
    public int getItemCount() {
        return this.allProductArrayList.size();
    }
}
