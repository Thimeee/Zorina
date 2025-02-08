package com.zorina.lk.zorina.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zorina.lk.zorina.R;
import com.zorina.lk.zorina.model.Test;

import java.util.ArrayList;

public class CoustomerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<Test> AllCustomerList = new ArrayList<>();
        AllCustomerList.add(new Test("0785475839", "Thimira sathsara", "Xl", "1","active"));
        AllCustomerList.add(new Test("0874766438", "ufg ugdugf", "l", "1","active"));
        AllCustomerList.add(new Test("0943878968", "blckLongDress sdvsv", "4Xl", "1","active"));
        AllCustomerList.add(new Test("0858786564", "blckLongDress", "3Xl", "1","active"));
        AllCustomerList.add(new Test("7648934986", "blckLongDress", "2Xl", "1","active"));
        AllCustomerList.add(new Test("3894676437", "blckLongDress", "2Xl", "1","Deactive"));
        AllCustomerList.add(new Test("0934078962", "blckLongDress", "2Xl", "1","active"));
        AllCustomerList.add(new Test("3409y97864", "blckLongDress", "2Xl", "1","Deactive"));
        AllCustomerList.add(new Test("3434343444", "blckLongDress", "2Xl", "1","active"));


        View view = inflater.inflate(R.layout.fragment_coustomer, container, false);

        RecyclerView AllCostomerListRecyclerView = view.findViewById(R.id.Recycle_view_admin_allCustomer);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        AllCostomerListRecyclerView.setLayoutManager(linearLayoutManager);

        AllCustomerAdapter alCusdp = new AllCustomerAdapter(AllCustomerList);
        AllCostomerListRecyclerView.setAdapter(alCusdp);
        return view;
    }
}
class AllCustomerViewHolder extends RecyclerView.ViewHolder {

    public TextView CustomerName;
    public TextView CustomerMobile;
    public TextView CustomerStstus;
//    public TextView CustomerSwich;


    public AllCustomerViewHolder(@NonNull View itemView) {
        super(itemView);
        CustomerName   = itemView.findViewById(R.id.admin_cusName);
        CustomerMobile = itemView.findViewById(R.id.Admin_cusMobile);
        CustomerStstus = itemView.findViewById(R.id.admin_cusStatus);
//        CustomerSwich = itemView.findViewById(R.id.admin_cusSwich);
    }
}

class AllCustomerAdapter extends RecyclerView.Adapter<AllCustomerViewHolder> {

    public ArrayList<Test> allCustomerArrayList;

    public AllCustomerAdapter(ArrayList<Test> allProductArrayList) {
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
        Test allProductObj = allCustomerArrayList.get(position);
        holder.CustomerName.setText(allProductObj.getTitle());
        holder.CustomerMobile.setText(allProductObj.getPrice());
        holder.CustomerStstus.setText(allProductObj.getQty());
//        holder.allProductSwich.setText(cartObj.getSize());

    }

    @Override
    public int getItemCount() {
        return this.allCustomerArrayList.size();
    }
}
