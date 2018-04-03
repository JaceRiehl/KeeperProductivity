package com.example.jaceriehl.keeperproductivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    RelativeLayout mRelativeLayout;
    private List<String> boardList;
    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        listView = (RecyclerView) findViewById(R.id.displayLists);
        listView.setHasFixedSize(true);

        final Lists[] listEls = {

        };

        // Intilize an array list from array
        boardList = new ArrayList(Arrays.asList(listEls));

        mLayoutManager = new GridLayoutManager(mContext,1);
        listView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(mContext,boardList);
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
                boardList.add(position,"" + result);
                mAdapter.notifyItemInserted(position);
                listView.scrollToPosition(position);
                Lists newListToBeAdded = new Lists(result);
                Toast.makeText(mContext,"Added : " + result,Toast.LENGTH_SHORT).show();

                position += 1;
            }

        }
    }

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
