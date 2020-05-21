package com.example.assign3flashcards;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;

import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CardMaker extends Activity {
    private DBHelper cardDB;
    TextView front ;
    TextView back;
    public String currentTable;
    int id_To_Update = 0;


    private final String TABLE_KEY = "table";
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.assign3flashcards";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_maker);
        front = (TextView) findViewById(R.id.editFront);
        back = (TextView) findViewById(R.id.editBack);


        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        currentTable = mPreferences.getString(TABLE_KEY, "table1");


        // pass through intents when adding to the table you're on


        cardDB = new DBHelper(this, currentTable);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                Cursor rs = cardDB.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String f = rs.getString(rs.getColumnIndex(DBHelper.CARD_FRONT));
                String b = rs.getString(rs.getColumnIndex(DBHelper.CARD_BACK));

                if (!rs.isClosed())  {
                    rs.close();
                }
                Button saver = (Button)findViewById(R.id.save);
                saver.setVisibility(View.INVISIBLE);

                front.setText((CharSequence)f);
                front.setFocusable(false);
                front.setClickable(false);

                back.setText((CharSequence)b);
                back.setFocusable(false);
                back.setClickable(false);

            }
        }
    }

    public void run(View view) {
                if(cardDB.addCard(front.getText().toString(), back.getText().toString(),
                       currentTable)){
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getBaseContext(), CardActivity.class);
                startActivity(intent);
    }
}
