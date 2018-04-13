package com.example.jaceriehl.keeperproductivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;


public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder>{
    private List<Lists> mDataSet;
    private Context mContext;
    private Random mRandom = new Random();

    public ListsAdapter(Context context, List<Lists> list){
        mDataSet = list;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public Button mRemoveButton;
        public LinearLayout mRelativeLayout;
        public ViewHolder(View v){
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv);
            mRemoveButton = (Button) v.findViewById(R.id.removeList);
            mRelativeLayout = (LinearLayout) v.findViewById(R.id.rl);
        }
    }

    @Override
    public ListsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_view,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.mTextView.setText((String)mDataSet.get(position).getName());

        // Set a random color for TextView background
        holder.mTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.mainWhite));

        // Set a text color for TextView
        holder.mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));

        // Set a click listener for TextView
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String animal = mDataSet.get(position).getName();
                Toast.makeText(mContext, animal, Toast.LENGTH_SHORT).show();
                Intent showList = new Intent(mContext,TasksActivity.class);
                //showList.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Lists parce = mDataSet.get(position);
                showList.putExtra("listObject", (Parcelable) parce);
                //showList.addFlags(FLAG_ACTIVITY_NEW_TASK);
                //sowList.setAction(Intent.ACTION_GET_CONTENT);
                //showList.setClass(mContext,TasksBoard.class);
                 mContext.startActivity(showList);

                }


//            @Override
//            protected void onActivityResult(int requestCode, int resultCode, Intent data){
//            ListsAdapter.super.onActivityResult(requestCode,resultCode,data);
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


        // Set a click listener for item remove button
        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                String itemLabel = mDataSet.get(position).getName();

                // Remove the item on remove/button click
                mDataSet.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mDataSet.size());
                Toast.makeText(mContext,"Removed : " + itemLabel,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount(){
        return mDataSet.size();
    }




}