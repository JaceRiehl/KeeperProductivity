package com.example.jaceriehl.keeperproductivity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Lists implements Parcelable {
    private String listName;
    public Lists(String name){
        this.listName = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(listName);
    }

    public static final Parcelable.Creator<Lists> CREATOR = new Parcelable.Creator<Lists>(){
        public Lists createFromParcel(Parcel in){
            return new Lists(in);
        }
        public Lists[] newArray(int size){
            return new Lists[size];
        }
    };
    public void setName(String s){
        this.listName = s;
    }
    public String getName(){
        return listName;
    }

    private Lists(Parcel in) {
        listName = in.readString();
    }

}
