package com.zorina.lk.zorina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zorina.lk.zorina.model.SingleImge;
import com.zorina.lk.zorina.model.Test;

import java.util.ArrayList;

public class SingleProductViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_product_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList<SingleImge> singleImgeList = new ArrayList<>();
        singleImgeList.add(new SingleImge(R.drawable.img));
        singleImgeList.add(new SingleImge(R.drawable.img));
        singleImgeList.add(new SingleImge(R.drawable.img));


        RecyclerView sigleProductRecycle = findViewById(R.id.sigleProductRecycle);

        LinearLayoutManager lm = new LinearLayoutManager(SingleProductViewActivity.this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        sigleProductRecycle.setLayoutManager(lm);

        singleImgeAdapter cartadp = new singleImgeAdapter(singleImgeList);
        sigleProductRecycle.setAdapter(cartadp);
    }
}

class SingleImgeViewHolder extends RecyclerView.ViewHolder {

    public ImageView singleimge;

    public SingleImgeViewHolder(@NonNull View itemView) {
        super(itemView);
        singleimge = itemView.findViewById(R.id.singleImageView);

    }
}

class singleImgeAdapter extends RecyclerView.Adapter<SingleImgeViewHolder> {

    public ArrayList<SingleImge> singleImgeArrayList;

    public singleImgeAdapter(ArrayList<SingleImge> singleImgeArrayList) {
        this.singleImgeArrayList = singleImgeArrayList;
    }


    @NonNull
    @Override
    public SingleImgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View singleImgeItemview = layoutInflater.inflate(R.layout.single_product_view_item, parent, false);

        SingleImgeViewHolder viewHolder = new SingleImgeViewHolder(singleImgeItemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SingleImgeViewHolder holder, int position) {
        SingleImge singleImgeObj = singleImgeArrayList.get(position);
        holder.singleimge.setImageResource(singleImgeObj.getImageId());
    }

    @Override
    public int getItemCount() {
        return this.singleImgeArrayList.size();
    }
}