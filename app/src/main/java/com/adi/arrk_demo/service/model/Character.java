package com.adi.arrk_demo.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Character implements Parcelable {
    private String name;
    private String height;
    private String mass;
    @SerializedName("created")
    private String createdDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public Character(Parcel in) {
        readFromParcel(in);
    }

    public Character() {

    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(height);
        parcel.writeString(mass);
        parcel.writeString(createdDate);
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {

        @Override
        public Character createFromParcel(Parcel source) {
            return new Character(source);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    private void readFromParcel(Parcel in) {
        setName(in.readString());
        setHeight(in.readString());
        setMass(in.readString());
        setCreatedDate(in.readString());

    }
}
