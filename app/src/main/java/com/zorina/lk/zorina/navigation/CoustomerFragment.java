package com.zorina.lk.zorina.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zorina.lk.zorina.R;
import com.zorina.lk.zorina.model.Product;
import com.zorina.lk.zorina.model.ProductResponse;
import com.zorina.lk.zorina.model.Test;
import com.zorina.lk.zorina.model.User;
import com.zorina.lk.zorina.model.UserResponse;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CoustomerFragment extends Fragment {
    ArrayList<User> AllUser;
    RecyclerView AllCostomerListRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_coustomer, container, false);

        AllCostomerListRecyclerView = view.findViewById(R.id.Recycle_view_admin_allCustomer);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        AllCostomerListRecyclerView.setLayoutManager(linearLayoutManager);

        getAllUser();


        EditText searchText = view.findViewById(R.id.searchInputUserforAdmin);
        ImageView searchTextbu = view.findViewById(R.id.searchIconUserforAdmin);
        searchTextbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("App", searchText.getText().toString());
                if(searchText.getText().toString().isEmpty()){
                    getAllUser();
                }else {
                    ArrayList<User> searchUserList = new ArrayList<>();
                    for (User user : AllUser) {
                        if (user.getFullName().contains(searchText.getText().toString())) {
                            searchUserList.add(user);
                        }
                    }

                    AllCustomerAdapter alCusdp = new AllCustomerAdapter(searchUserList);
                    AllCostomerListRecyclerView.setAdapter(alCusdp);

                }
            }
        });


        return view;
    }

    private void getAllUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://tick-crucial-gently.ngrok-free.app/zorina/getAllUser")
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
                    UserResponse productResponse = gson.fromJson(respText, UserResponse.class);
                    AllUser = productResponse.getUserList();
                    Log.i("App 15", String.valueOf(AllUser));

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (AllUser != null) {
                                AllCustomerAdapter alCusdp = new AllCustomerAdapter(AllUser);
                                AllCostomerListRecyclerView.setAdapter(alCusdp);
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
class AllCustomerViewHolder extends RecyclerView.ViewHolder {

    public TextView CustomerName;
    public TextView CustomerMobile;
    public TextView CustomerStstus;
    public Switch CustomerSwich;


    public AllCustomerViewHolder(@NonNull View itemView) {
        super(itemView);
        CustomerName   = itemView.findViewById(R.id.admin_cusName);
        CustomerMobile = itemView.findViewById(R.id.Admin_cusMobile);
        CustomerStstus = itemView.findViewById(R.id.admin_cusStatus);
        CustomerSwich = itemView.findViewById(R.id.admin_cusSwich);
    }
}

class AllCustomerAdapter extends RecyclerView.Adapter<AllCustomerViewHolder> {

    public ArrayList<User> allCustomerArrayList;

    public AllCustomerAdapter(ArrayList<User> allProductArrayList) {
        this.allCustomerArrayList = allProductArrayList;
    }


    @NonNull
    @Override
    public AllCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View allCustomerItemview = layoutInflater.inflate(R.layout.all_customer_admin_items, parent, false);

        AllCustomerViewHolder viewHolder = new AllCustomerViewHolder(allCustomerItemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllCustomerViewHolder holder, int position) {
        User allUserObj = allCustomerArrayList.get(position);
        holder.CustomerName.setText(allUserObj.getFullName());
        holder.CustomerMobile.setText(allUserObj.getMobile());
        if(allUserObj.getUserStatus() == 1){
            holder.CustomerStstus.setText("Active");
            holder.CustomerStstus.setTextColor(0xFF008000);

        }else {
            holder.CustomerStstus.setText("Block");
            holder.CustomerStstus.setTextColor(0xFFFF0000);

        }
        if(allUserObj.getUserStatus() == 1) {
            holder.CustomerSwich.setChecked(true);
        }else {
            holder.CustomerSwich.setChecked(false);
        }

        holder.CustomerSwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.CustomerSwich.isChecked()) {
                    allUserObj.setUserStatus(0);
                    holder.CustomerStstus.setText("Not Available");
                    holder.CustomerStstus.setTextColor(0xFFFF0000);
                } else {
                    allUserObj.setUserStatus(1);
                    holder.CustomerStstus.setText("Available");
                    holder.CustomerStstus.setTextColor(0xFF008000);
                }

                Gson  gson= new Gson();

                JsonObject requestObj=new JsonObject();
                requestObj.add("user",gson.toJsonTree(allUserObj));

                String requsetJson=gson.toJson(requestObj);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient httpClient = new OkHttpClient();
                        MediaType Json = MediaType.parse("application/json; charset=utf-8");
                        RequestBody requsetbody = RequestBody.create(requsetJson, Json);
                        Request request = new Request.Builder()
                                .url("https://tick-crucial-gently.ngrok-free.app/zorina/updateUser")
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
        return this.allCustomerArrayList.size();
    }
}
