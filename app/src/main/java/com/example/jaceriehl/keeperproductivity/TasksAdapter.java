package com.example.jaceriehl.keeperproductivity;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    private List<Tasks> mDataSet;
    private Context mContext;
    private Random mRandom = new Random();
    private boolean TAG = false;
    public List<Tasks> habitsClicked = new ArrayList<>();

    public TasksAdapter(Context context, List<Tasks> list) {
        mDataSet = list;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView mShowHabit;
        public ImageButton mRemoveButton;
        public Button mRemoveTask;
        public LinearLayout mRelativeLayout;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.taskName);
            mShowHabit = (TextView) v.findViewById(R.id.showHabitTV);
            mRemoveTask = (Button) v.findViewById(R.id.removeTask);
            mRemoveButton = (ImageButton) v.findViewById(R.id.taskButton);
            mRelativeLayout = (LinearLayout) v.findViewById(R.id.taskLayout);
        }
    }

    @Override
    public TasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.tasks_view, parent, false);
        TasksAdapter.ViewHolder vh = new TasksAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final TasksAdapter.ViewHolder holder, final int position) {
        holder.mTextView.setText((String) mDataSet.get(position).getName());
        //final int pos = position;
        if(mDataSet.get(position).isHabit)
            holder.mShowHabit.setText(Integer.toString(mDataSet.get(position).habitStreak));

        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mDataSet.get(position).isHabit) {

                    if (mDataSet.get(position).TAG) {
                        holder.mRemoveButton.setImageResource(R.drawable.checked_checkbox);
                    } else {
                        holder.mRemoveButton.setImageResource(R.drawable.unchecked_checkbox);
                    }
                    mDataSet.get(position).TAG ^= true;
                }
                else {
                    mDataSet.get(position).habitStreak = mDataSet.get(position).habitStreak + 1;
                    // Show the removed item label
                    Toast.makeText(mContext,"Habit Streak : " + mDataSet.get(position).habitStreak,Toast.LENGTH_SHORT).show();
                    habitsClicked.add(mDataSet.get(position));
                    mDataSet.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,mDataSet.size());


                }
            }
        });

        holder.mRemoveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSet.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mDataSet.size());
            }
        });

        // Set a click listener for TextView
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String animal = mDataSet.get(position).getName();
                Toast.makeText(mContext, animal, Toast.LENGTH_SHORT).show();

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


    }
    @Override
    public int getItemCount(){
        return mDataSet.size();
    }
};
