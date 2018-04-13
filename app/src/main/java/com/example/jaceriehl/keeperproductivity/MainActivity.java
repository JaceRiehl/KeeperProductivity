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

public class MainActivity extends AppCompatActivity implements NewListOverlay.EnterListNameListener{
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

        // Intilize an array list from array
        boardList = new ArrayList<Lists>();
        boardList.add(0, new Lists("Wendy\'s List"));

        mLayoutManager = new GridLayoutManager(mContext,1);
        listView.setLayoutManager(mLayoutManager);
        mAdapter = new ListsAdapter(mContext,boardList);
        listView.setAdapter(mAdapter);

        final Button newList = findViewById(R.id.newList);
        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent listSettingsIntent = new Intent(view.getContext(), NewListActivity.class);
                    //startActivityForResult(listSettingsIntent, 0);
                NewListOverlay overlayPopup = new NewListOverlay();
                overlayPopup.show(getSupportFragmentManager(),"Enter List Name");
                }
        });

    }



    @Override
    public void passText(String name) {
        String result = name;
        Lists newListToBeAdded = new Lists(result);
        boardList.add(newListToBeAdded);
        mAdapter.notifyItemInserted(position);
        listView.scrollToPosition(position);

        Toast.makeText(mContext,"Added : " + result,Toast.LENGTH_SHORT).show();

        position += 1;
    }


}
