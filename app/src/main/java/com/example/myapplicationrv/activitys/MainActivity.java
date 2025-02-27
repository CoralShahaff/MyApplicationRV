package com.example.myapplicationrv.activitys;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationrv.R;
import com.example.myapplicationrv.adapters.CustomeAdapter;
import com.example.myapplicationrv.classes.myData;
import com.example.myapplicationrv.models.Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Data> arr;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter customeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.rvcon);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arr = new ArrayList<Data>();

        for (int i = 0; i < myData.nameArray.length; i++) {
            arr.add(new Data(
                    myData.nameArray[i],
                    myData.descriptionArray[i],
                    myData.drawableArray[i],
                    myData.id_[i]
            ));
        }

        customeAdapter = new CustomeAdapter(arr);
        recyclerView.setAdapter(customeAdapter);

        // SearchView functionality
        SearchView searchView = findViewById(R.id.search_View);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // You can handle submit here if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the RecyclerView items based on the text entered
                customeAdapter.filter(newText);
                return true;
            }
        });
    } }