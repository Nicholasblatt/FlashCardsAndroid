package com.example.assign3flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class specificCard extends AppCompatActivity {


    private final String POSITION_KEY = "position";
    private final String TABLE_KEY = "table";

    DBHelper myCards;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.assign3flashcards";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_card);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String currentTable = mPreferences.getString(TABLE_KEY, "table1");
        int currentId = mPreferences.getInt(POSITION_KEY, 1);
        currentId++;
        myCards = new DBHelper(this, currentTable);
        ArrayList<String> frontAndBack = myCards.viewById(currentId);

        TextView frontText = findViewById(R.id.front);
        TextView backText = findViewById(R.id.back);

        frontText.setText(frontAndBack.get(0));
        backText.setText(frontAndBack.get(1));
    }
}
