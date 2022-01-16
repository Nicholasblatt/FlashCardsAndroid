package com.example.assign3flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;





public class MainActivity extends AppCompatActivity {

    ListView list;

    private final String TABLE_KEY = "table";

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.assign3flashcards";



    String[] maintitle ={
            "Flash Card Set 1","Flash Card Set 2",
            "Flash Card Set 3","Flash Card Set 4",
            "Flash Card Set 5",
    };

    Integer[] imgid={
            R.drawable.flashcardseticon,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_list_adapter adapter=new my_list_adapter(this, maintitle, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        final SharedPreferences.Editor preferencesEditor = mPreferences.edit();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                if(position == 0) {
                    preferencesEditor.putString(TABLE_KEY, "table1");
                    preferencesEditor.apply();
                    Intent intent = new Intent(getBaseContext(), CardActivity.class);
                    startActivity(intent);

                }

                else if(position == 1) {

                    preferencesEditor.putString(TABLE_KEY, "table2");
                    preferencesEditor.apply();

                    Intent intent = new Intent(getBaseContext(), CardActivity.class);
                    startActivity(intent);

                }

                else if(position == 2) {


                    preferencesEditor.putString(TABLE_KEY, "table3");
                    preferencesEditor.apply();

                    Intent intent = new Intent(getBaseContext(), CardActivity.class);
                    startActivity(intent);

                }
                else if(position == 3) {


                    preferencesEditor.putString(TABLE_KEY, "table4");
                    preferencesEditor.apply();

                    Intent intent = new Intent(getBaseContext(), CardActivity.class);
                    startActivity(intent);
                }
                else if(position == 4) {

                    preferencesEditor.putString(TABLE_KEY, "table5");
                    preferencesEditor.apply();

                    Intent intent = new Intent(getBaseContext(), CardActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}

