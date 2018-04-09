package com.example.jaceriehl.keeperproductivity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;

    LinearLayout mRelativeLayout;
    //private List<String> boardList;
    private int position = 1;
    ArrayList<Lists> boardList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        listView = (RecyclerView) findViewById(R.id.displayLists);
        listView.setHasFixedSize(true);
        ActionBar action = getSupportActionBar();

        action.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        action.setCustomView(R.layout.custom_main_act_bar);
        action.setTitle("Lists");
        DividerItemDecoration itemDecorator = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.list_recycler_spacer));
        listView.addItemDecoration(itemDecorator);
        final Lists[] listEls = {
            new Lists("Test")
        };

        // Intilize an array list from array
        boardList = new ArrayList<Lists>();
        boardList.add(0, new Lists("Test"));

        mLayoutManager = new GridLayoutManager(mContext,1);
        listView.setLayoutManager(mLayoutManager);
        mAdapter = new ListsAdapter(mContext,boardList);
        listView.setAdapter(mAdapter);

        final Button newList = findViewById(R.id.newList);
        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent listSettingsIntent = new Intent(view.getContext(), NewListActivity.class);
                    startActivityForResult(listSettingsIntent, 0);

                }
        });

    }

//    @Override
//    public void onContentChanged() {
//        super.onContentChanged();
//        boardList = (ListView) findViewById(R.id.lists);
//        View empty = findViewById(R.id.empty);
//        boardList.setEmptyView(empty);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        MainActivity.super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 0)
        {
            if(resultCode == RESULT_OK)
            {
                final Button newList = findViewById(R.id.newList);
                //newList.setText("HI");


                String result = data.getStringExtra("boardName");
                Lists newListToBeAdded = new Lists(result);
                boardList.add(newListToBeAdded);
                mAdapter.notifyItemInserted(position);
                listView.scrollToPosition(position);

                Toast.makeText(mContext,"Added : " + result,Toast.LENGTH_SHORT).show();

                position += 1;
            }

        }
    }

//    public void callOnListClicked(int position){
//        //mContext = getApplicationContext();
//        //String animal = boardList.get(position);
//        Toast.makeText(getApplicationContext(), "dfshjak", Toast.LENGTH_SHORT).show();
//        //Intent showList = new Intent(mContext,TasksActivity.class);
//        //showList.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //showList.putExtra("test", "This is a test value");
//        //showList.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        //sowList.setAction(Intent.ACTION_GET_CONTENT);
//        //showList.setClass(mContext,TasksBoard.class);
//       // mContext.startActivity(showList);
//    }

//    //@Override
//    public void onItemClick(View view, int position) {
//        //Context context=view.getContext();
//        Intent intent=new Intent();
//        switch (position){
//            case 0:
//                intent =  new Intent(mContext, TasksBoard.class);
//                mContext.startActivity(intent);
//                break;
//        }
//    }

}
