package com.example.steven.yo.model;

import android.os.Parcelable;
import android.os.Parcel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Education implements Parcelable {
    public String id;
    public String school;
    public String major;
    public Date startDate;
    public Date endDate;
    public List<String> courses;

    public Education() {
        id = UUID.randomUUID().toString();
    }

    protected Education(Parcel in) {
        id = in.readString();
        school = in.readString();
        major = in.readString();
        courses = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(school);
        dest.writeString(major);
        dest.writeStringList(courses);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Education> CREATOR = new Creator<Education>() {
        @Override
        public Education createFromParcel(Parcel in) {
            return new Education(in);
        }

        @Override
        public Education[] newArray(int size) {
            return new Education[size];
        }
    };
}
