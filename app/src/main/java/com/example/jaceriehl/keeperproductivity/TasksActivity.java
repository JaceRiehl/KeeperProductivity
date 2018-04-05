package com.example.jaceriehl.keeperproductivity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.List;

public class TasksActivity extends AppCompatActivity {
    protected Intent returnIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ActionBar action = getSupportActionBar();
        action.setHomeButtonEnabled(true);
        action.setDisplayHomeAsUpEnabled(true);
        //action.setTitle("Title");
        //getSupportActionBar().setTitle("Hello world App");
        returnIntent = getIntent();
        Lists listEl = (Lists)returnIntent.getParcelableExtra("listObject");
        //listEl.setName("Jace");
        action.setTitle(listEl.getName());



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                returnIntent = getIntent();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
