package com.example.jaceriehl.keeperproductivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class TasksActivity extends AppCompatActivity {
    protected Intent returnIntent;
    private RecyclerView taskView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    LinearLayout mRelativeLayout;
    private int position = 1;
    ArrayList<Tasks> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        mContext = getApplicationContext();
        taskView = (RecyclerView) findViewById(R.id.taskRecycler);
        taskView.setHasFixedSize(true);

        final Lists[] taskEls = {
                new Lists("Test")
        };

        // Intilize an array list from array
        taskList = new ArrayList<Tasks>();
        taskList.add(0, new Tasks("Test"));

        mLayoutManager = new GridLayoutManager(mContext,1);
        taskView.setLayoutManager(mLayoutManager);
        mAdapter = new TasksAdapter(mContext,taskList);
        taskView.setAdapter(mAdapter);

        ActionBar action = getSupportActionBar();
        action.setHomeButtonEnabled(true);
        action.setDisplayHomeAsUpEnabled(true);

        //action.setTitle("Title");
        //getSupportActionBar().setTitle("Hello world App");
        returnIntent = getIntent();
        Lists listEl = (Lists)returnIntent.getParcelableExtra("listObject");
        action.setTitle(listEl.getName());
        taskView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tasks_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                returnIntent = getIntent();
                finish();
                return true;
            case R.id.action_add:
                final Intent listSettingsIntent = new Intent(mContext, NewTaskActivity.class);
                startActivityForResult(listSettingsIntent, 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        TasksActivity.super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 0)
        {
            if(resultCode == RESULT_OK)
            {
                final Button newList = findViewById(R.id.newList);
                //newList.setText("HI");


                String result = data.getStringExtra("taskName");
                Tasks newTaskToBeAdded = new Tasks(result);
                taskList.add(newTaskToBeAdded);
                mAdapter.notifyItemInserted(position);
                taskView.scrollToPosition(position);

                Toast.makeText(mContext,"Added : " + result,Toast.LENGTH_SHORT).show();

                position += 1;
            }

        }
    }

}
