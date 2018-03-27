package com.example.jaceriehl.keeperproductivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button newList = findViewById(R.id.newList);
        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent listSettingsIntent = new Intent(view.getContext(), NewListActivity.class);
                    startActivityForResult(listSettingsIntent, 0);
                }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        MainActivity.super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 0)
        {
            if(resultCode == RESULT_OK)
            {
                final Button newList = findViewById(R.id.newList);
                newList.setText("HI");
            }
        }
    }

}
