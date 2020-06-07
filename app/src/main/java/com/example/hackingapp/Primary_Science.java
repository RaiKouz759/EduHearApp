package com.example.hackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Primary_Science extends AppCompatActivity implements View.OnClickListener {

    //String[] chapters = {"Chapter 1", "Chapter 2", "Chapter 3"};

    private TextView textViewChapter1;
    private TextView textViewChapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary__science);

        textViewChapter1 = findViewById(R.id.textViewChapter1);
        textViewChapter2 = findViewById(R.id.textViewChapter2);

        /*ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.ListView, chapters);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);*/

        textViewChapter1.setOnClickListener(this);
        textViewChapter2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == textViewChapter1) startActivity(new Intent(this, Player.class));
        if (view == textViewChapter2) startActivity(new Intent(this, Player2.class));
    }
}
