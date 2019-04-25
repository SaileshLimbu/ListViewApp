package com.example.listviewapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddWordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etWord, etMeaning;
    private Button btnAddWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnAddWord = findViewById(R.id.btnAddWord);

        btnAddWord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        saveWord();
    }

    private void saveWord(){
        try {
            PrintStream printStream = new PrintStream(openFileOutput("words.txt", MODE_PRIVATE | MODE_APPEND));
            printStream.println(etWord.getText().toString() + "->" + etMeaning.getText().toString());
            printStream.close();
            Toast.makeText(this, "Saved to " + getFilesDir(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Log.d("Dictionary App", e.getMessage());
            e.printStackTrace();
        }
    }
}
