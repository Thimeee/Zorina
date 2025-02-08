package com.zorina.lk.zorina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zorina.lk.zorina.model.Test;

import java.util.ArrayList;

public class CoustomerOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_coustomer_orders);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList<Test> ordersList = new ArrayList<>();
        ordersList.add(new Test("Ord1234", "2023.11.12", "Delivery", "1","1"));
        ordersList.add(new Test("Ord2345", "2024.11.12", "Delivery", "1","1"));
        ordersList.add(new Test("Ord2345", "2026.10.12", "Delivery", "1","1"));
        ordersList.add(new Test("Ord5455", "2023.07.12", "Pending", "1","1"));
        ordersList.add(new Test("Ord2435", "2023.05.12", "prapera Order", "1","1"));


        RecyclerView OrdersRecycle = findViewById(R.id.OrdersRecyclerView);

        LinearLayoutManager lm = new LinearLayoutManager(CoustomerOrdersActivity.this);
        OrdersRecycle.setLayoutManager(lm);

        orderAdapter cartadp = new orderAdapter(ordersList);
        OrdersRecycle.setAdapter(cartadp);
    }
}

class ordersViewHolder extends RecyclerView.ViewHolder {

    public TextView orderId;
    public TextView orderDate;
    public TextView orderStatus;
    public Button orderTrackButton;
//    public ImageView cartImage;


    public ordersViewHolder(@NonNull View itemView) {
        super(itemView);
        orderId = itemView.findViewById(R.id.orderIdCus);
        orderDate = itemView.findViewById(R.id.orderDateCus);
        orderStatus = itemView.findViewById(R.id.orderStstusCus);
//        orderTrackButton = itemView.findViewById(R.id.orderTrackButtonCus);
//        cartImage =itemView.findViewById(R.id.cartImageView);
    }
}

class orderAdapter extends RecyclerView.Adapter<ordersViewHolder> {

    public ArrayList<Test> orderArrayList;

    public orderAdapter(ArrayList<Test> orderArrayList) {
        this.orderArrayList = orderArrayList;
    }


    @NonNull
    @Override
    public ordersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View orderItemview = layoutInflater.inflate(R.layout.orders_item, parent, false);

        ordersViewHolder viewHolder = new ordersViewHolder(orderItemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ordersViewHolder holder, int position) {
        Test orderObj = orderArrayList.get(position);
        holder.orderId.setText(orderObj.getPrice());
        holder.orderDate.setText(orderObj.getTitle());
        holder.orderStatus.setText(orderObj.getSize());
//        holder.orderTrackButton.setText(orderObj.getSize());

    }

    @Override
    public int getItemCount() {
        return this.orderArrayList.size();
    }
}