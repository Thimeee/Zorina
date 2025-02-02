package com.zorina.lk.zorina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ArrayList<Test> cartList = new ArrayList<>();
        cartList.add(new Test("5000", "blckLongDress", "Xl", "1","1"));
        cartList.add(new Test("3000", "blckLongDress", "l", "1","1"));
        cartList.add(new Test("2000", "blckLongDress", "4Xl", "1","1"));
        cartList.add(new Test("5000", "blckLongDress", "3Xl", "1","1"));
        cartList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));


        RecyclerView cartRecycle = findViewById(R.id.cartRecyclerView);

        LinearLayoutManager lm = new LinearLayoutManager(CartActivity.this);
        cartRecycle.setLayoutManager(lm);

      cartAdapter cartadp = new cartAdapter(cartList);
      cartRecycle.setAdapter(cartadp);
    }
}

class cartViewHolder extends RecyclerView.ViewHolder {

    public TextView cartPrice;
    public TextView cartTitle;
    public TextView cartQty;
    public TextView cartSize;
//    public ImageView cartImage;


    public cartViewHolder(@NonNull View itemView) {
        super(itemView);
        cartPrice = itemView.findViewById(R.id.cartItemPrice);
        cartTitle = itemView.findViewById(R.id.cartItemTitle);
        cartQty = itemView.findViewById(R.id.cartItemQty);
        cartSize = itemView.findViewById(R.id.cartItemSize);
//        cartImage =itemView.findViewById(R.id.cartImageView);
    }
}

class cartAdapter extends RecyclerView.Adapter<cartViewHolder> {

    public ArrayList<Test> cartArrayList;

    public cartAdapter(ArrayList<Test> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }


    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View cartItemview = layoutInflater.inflate(R.layout.cart_items, parent, false);

        cartViewHolder viewHolder = new cartViewHolder(cartItemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull cartViewHolder holder, int position) {
        Test cartObj = cartArrayList.get(position);
        holder.cartPrice.setText(cartObj.getPrice());
        holder.cartTitle.setText(cartObj.getTitle());
        holder.cartQty.setText(cartObj.getQty());
        holder.cartSize.setText(cartObj.getSize());

    }

    @Override
    public int getItemCount() {
        return this.cartArrayList.size();
    }
}
