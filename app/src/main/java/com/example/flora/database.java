package com.example.flora;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class database extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Plant> plantDatabase = new ArrayList<Plant>(); // reads from the database and stores it in an array
    private ArrayList<String> lines = new ArrayList<>();  // Data from Database
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        recyclerView = findViewById(R.id.databaseRecyclerView);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("Plant Database.txt")));
            while (br.ready()) {
                lines.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Organizes database into Object Array
        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(":");
            plantDatabase.add(new Plant(split[0], split[1], split[2]));
        }

        for (int i = 0; i < 10; i++) {
            Log.d(TAG, plantDatabase.get(i).getPlantName());
        }
        setAdapter();
    }

    private void setAdapter(){
        databaseAdapter adapter = new databaseAdapter(plantDatabase);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
