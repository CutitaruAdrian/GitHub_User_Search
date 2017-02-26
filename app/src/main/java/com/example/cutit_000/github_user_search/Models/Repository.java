package com.example.cutit_000.github_user_search.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cutit_000 on 14.02.2017.
 */

public class Repository implements Parcelable {
    int Id;
    String Name;
    String User;
    String Description;

    public Repository(int id, String name, String user, String description) {
        this.Id = id;
        this.Name = name;
        this.User = user;
        this.Description = description;
    }

    public Repository(Parcel in) {
        Id = in.readInt();
        Name = in.readString();
        User = in.readString();
        Description = in.readString();
    }

    public static final Parcelable.Creator<Repository> CREATOR
            = new Parcelable.Creator<Repository>() {
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };


    public String getName() {
        return this.Name;
    }

    public String getUser() {
        return this.User;
    }

    public String getDescription() {
        return this.Description;
    }

    public Integer getId() {
        return this.Id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Name);
        dest.writeString(User);
        dest.writeString(Description);
    }
}
