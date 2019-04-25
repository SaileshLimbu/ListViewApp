package com.example.listviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private Button btnAddMoreWords;
    private Map<String, String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionary = new HashMap<>();
        readFile();

        listView = findViewById(R.id.listView);
        btnAddMoreWords = findViewById(R.id.btnAddMoreWords);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<String>(dictionary.keySet()));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word = parent.getItemAtPosition(position).toString();
                String meaning = dictionary.get(word);
                Toast.makeText(MainActivity.this, meaning , Toast.LENGTH_SHORT).show();
            }
        });

        btnAddMoreWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddWordActivity.class));
            }
        });
    }

    private void readFile(){
        try {
            FileInputStream fis = openFileInput("words.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line=br.readLine()) != null){
                String[] parts = line.split(("->"));
                dictionary.put(parts[0], parts[1]);
            }
            fis.close();
            isr.close();
            br.close();
        } catch (FileNotFoundException e) {
            Log.d("Dictionary App", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("Dictionary App", e.getMessage());
            e.printStackTrace();
        }
    }
}