package com.example.jaceriehl.keeperproductivity;

import android.os.Parcel;
import android.os.Parcelable;


public class Tasks implements Parcelable{
    private String name;
    public boolean TAG = false;
    public boolean isHabit = false;
    public int habitStreak = 0;

    public Tasks(String name){
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        boolean habitArray[] = {isHabit, TAG};
        parcel.writeBooleanArray(habitArray);
        parcel.writeInt(habitStreak);
    }

    public static final Parcelable.Creator<Tasks> CREATOR = new Parcelable.Creator<Tasks>(){
        public Tasks createFromParcel(Parcel in){
            return new Tasks(in);
        }
        public Tasks[] newArray(int size){
            return new Tasks[size];
        }
    };
    public void setName(String s){
        this.name = s;
    }
    public String getName(){
        return name;
    }

    private Tasks(Parcel in) {
        name = in.readString();
        boolean habitArray[] = new boolean[2];
        in.readBooleanArray(habitArray);
        TAG = habitArray[1];
        isHabit = habitArray[0];
        habitStreak = in.readInt();
    }
}
