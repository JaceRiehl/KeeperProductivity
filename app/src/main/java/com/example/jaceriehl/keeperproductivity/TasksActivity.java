package com.example.jaceriehl.keeperproductivity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
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
        taskView = findViewById(R.id.taskRecycler);
        taskView.setHasFixedSize(true);
        taskList = new ArrayList<>();
        //taskList.add(0, new Tasks("Finish App Demos"));

        mLayoutManager = new GridLayoutManager(mContext,1);
        taskView.setLayoutManager(mLayoutManager);
        mAdapter = new TasksAdapter(mContext,taskList);
        taskView.setAdapter(mAdapter);

        ActionBar action = getSupportActionBar();
        action.setHomeButtonEnabled(true);
        action.setDisplayHomeAsUpEnabled(true);

        returnIntent = getIntent();
        Lists listEl = returnIntent.getParcelableExtra("listObject");
        action.setTitle(listEl.getName());
        DividerItemDecoration itemDecorator = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.recycler_spacer));
        taskView.addItemDecoration(itemDecorator);



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
                String result = data.getStringExtra("taskName");
                int ht = data.getIntExtra("taskHabit",0);
                Tasks newTaskToBeAdded = new Tasks(result);
                if(ht == 1) newTaskToBeAdded.isHabit = true;
                else newTaskToBeAdded.isHabit = false;
                taskList.add(newTaskToBeAdded);
                mAdapter.notifyItemInserted(position);
                taskView.scrollToPosition(position);

                Toast.makeText(mContext,"Added : " + result,Toast.LENGTH_SHORT).show();

                position += 1;
            }

        }
    }

}
