package com.example.jaceriehl.keeperproductivity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

/**
 * Created by jaceriehl on 2018-04-04.
 */

public class TasksAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Tasks> mDataSet;
    private Context mContext;
    private Random mRandom = new Random();

    public TasksAdapter(Context context, List<Tasks> list) {
        mDataSet = list;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageButton mRemoveButton;
        public RelativeLayout mRelativeLayout;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv);
//            mRemoveButton = (ImageButton) v.findViewById(R.id.ib_remove);
            mRelativeLayout = (RelativeLayout) v.findViewById(R.id.rl);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_view, parent, false);
        MyAdapter.ViewHolder vh = new MyAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
        holder.mTextView.setText((String) mDataSet.get(position).getName());

        // Set a random color for TextView background
        holder.mTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.mainWhite));

        // Set a text color for TextView
        holder.mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));

        // Set a gradient background for RelativeLayout
//        holder.mRelativeLayout.setBackground(getGradientDrawable());


        // Set a click listener for TextView
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String animal = mDataSet.get(position).getName();
                Toast.makeText(mContext, animal, Toast.LENGTH_SHORT).show();


            }


//            @Override
//            protected void onActivityResult(int requestCode, int resultCode, Intent data){
//            MyAdapter.super.onActivityResult(requestCode,resultCode,data);
//                if(requestCode == 0)
//                {
//                    if(resultCode == RESULT_OK)
//                    {
////                    final Button newList = findViewById(R.id.newList);
////                    //newList.setText("HI");
////
////
////                    String result = data.getStringExtra("boardName");
////                    boardList.add(position,"" + result);
////                    mAdapter.notifyItemInserted(position);
////                    listView.scrollToPosition(position);
////                    Lists newListToBeAdded = new Lists(result);
////                    Toast.makeText(mContext,"Added : " + result,Toast.LENGTH_SHORT).show();
////
////                    position += 1;
////                }
//
//                    }
//                }
        });


    }
    @Override
    public int getItemCount(){
        return mDataSet.size();
    }
};
