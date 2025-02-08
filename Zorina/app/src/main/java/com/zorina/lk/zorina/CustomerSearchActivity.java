package com.zorina.lk.zorina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.zorina.lk.zorina.model.Test;

import java.util.ArrayList;

public class CustomerSearchActivity extends AppCompatActivity {

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

        ArrayList<Test> searchList = new ArrayList<>();
        searchList.add(new Test("5000", "blckLongDress", "Xl", "1","1"));
        searchList.add(new Test("3000", "blckLongDress", "l", "1","1"));
        searchList.add(new Test("2000", "blckLongDress", "4Xl", "1","1"));
        searchList.add(new Test("5000", "blckLongDress", "3Xl", "1","1"));
        searchList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));
        searchList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));
        searchList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));
        searchList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));
        searchList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));
        searchList.add(new Test("6000", "blckLongDress", "2Xl", "1","1"));


        RecyclerView SearchRecycle = findViewById(R.id.SearchRecycleView);

        SearchRecycle.setLayoutManager(new GridLayoutManager(this,2));

        searchAdapter searchadp = new searchAdapter(searchList);
        SearchRecycle.setAdapter(searchadp);
    }
}

class searchViewHolder extends RecyclerView.ViewHolder {

    public TextView SearchPrice;
    public TextView searchTitle;

//    public ImageView cartImage;


    public searchViewHolder(@NonNull View itemView) {
        super(itemView);
        SearchPrice = itemView.findViewById(R.id.SearchItemPrice);
        searchTitle = itemView.findViewById(R.id.SearchItemTitle);

//        cartImage =itemView.findViewById(R.id.SearchImageView);
    }
}

class searchAdapter extends RecyclerView.Adapter<searchViewHolder> {

    public ArrayList<Test> searchArrayList;

    public searchAdapter(ArrayList<Test> searchArrayList) {
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
        Test searchItemObj = searchArrayList.get(position);
        holder.SearchPrice.setText(searchItemObj.getPrice());
        holder.searchTitle.setText(searchItemObj.getTitle());

    }

    @Override
    public int getItemCount() {
        return this.searchArrayList.size();
    }
}