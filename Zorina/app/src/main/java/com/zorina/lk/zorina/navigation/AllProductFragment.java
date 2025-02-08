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


public class AllProductFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ArrayList<Test> AllProductList = new ArrayList<>();
        AllProductList.add(new Test("5000", "blckLongDress", "Xl", "1","1"));
        AllProductList.add(new Test("3000", "blckLongDress", "l", "1","1"));
        AllProductList.add(new Test("2000", "blckLongDress", "4Xl", "1","1"));
        AllProductList.add(new Test("5000", "blckLongDress", "3Xl", "1","1"));
        AllProductList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));
        AllProductList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));
        AllProductList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));
        AllProductList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));
        AllProductList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));


        View view = inflater.inflate(R.layout.fragment_all_product, container, false);

        RecyclerView AllProductlistRecyclerView = view.findViewById(R.id.Recycle_view_allProduct);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        AllProductlistRecyclerView.setLayoutManager(linearLayoutManager);

        AllProductAdapter allProductdp = new AllProductAdapter(AllProductList);
        AllProductlistRecyclerView.setAdapter(allProductdp);
        return view;
    }
}
class AllProductViewHolder extends RecyclerView.ViewHolder {

    public TextView allProductTitle;
    public TextView allProductPrice;
    public TextView allProductStatus;
//    public TextView allProductSwich;
//    public ImageView cartImage;


    public AllProductViewHolder(@NonNull View itemView) {
        super(itemView);
        allProductTitle   = itemView.findViewById(R.id.allProductTitle);
        allProductPrice = itemView.findViewById(R.id.allProductPrice);
        allProductStatus = itemView.findViewById(R.id.allProductStstus);
//        allProductSwich = itemView.findViewById(R.id.allProductSwich);
//        allProductImage =itemView.findViewById(R.id.allImageView);
    }
}

class AllProductAdapter extends RecyclerView.Adapter<AllProductViewHolder> {

    public ArrayList<Test> allProductArrayList;

    public AllProductAdapter(ArrayList<Test> allProductArrayList) {
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
        Test allProductObj = allProductArrayList.get(position);
        holder.allProductTitle.setText(allProductObj.getTitle());
        holder.allProductPrice.setText(allProductObj.getPrice());
        holder.allProductStatus.setText(allProductObj.getQty());
//        holder.allProductSwich.setText(cartObj.getSize());

    }

    @Override
    public int getItemCount() {
        return this.allProductArrayList.size();
    }
}
