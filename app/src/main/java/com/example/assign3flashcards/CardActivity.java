package com.example.assign3flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {
    private DBHelper myCards;
    public ArrayList<String> cardList;
    public String currentTable = "table1";
    public static final String CURRENTTAB = "com.example.android.assign3flashcards.extra.CURRENTTAB";
    public Intent intent;



    ListView myList;

    private final String TABLE_KEY = "table";
    private final String POSITION_KEY = "position";
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.assign3flashcards";
    //Bundle bundle = getIntent().getExtras();
    //String currentTable = bundle.getString("tableName");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        currentTable = mPreferences.getString(TABLE_KEY, "table1");
        final SharedPreferences.Editor preferencesEditor = mPreferences.edit();


        setContentView(R.layout.activity_card);
        myCards = new DBHelper(this, currentTable);
        myCards.makeTable(currentTable);


        cardList = new ArrayList<>();
        cardList = myCards.viewCards();

        cardListAdapter adapter = new cardListAdapter(this, cardList);
        myList = findViewById(R.id.card_list);
        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                preferencesEditor.putInt(POSITION_KEY, position);
                preferencesEditor.apply();
                Intent intent = new Intent(getBaseContext(), specificCard.class);
                startActivity(intent);

            }
        });
    }

    public void goToCardMaker(View view) {
        intent = new Intent(this, CardMaker.class);
        startActivity(intent);
    }

    public void backToCards(View view) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clearAll(View view) {
        myCards.emptyTable(currentTable);
        Intent i = new Intent(this, CardActivity.class);
        finish();
        overridePendingTransition(0, 0);
        startActivity(i);
        overridePendingTransition(0, 0);
    }
}
