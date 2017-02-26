package com.example.cutit_000.github_user_search.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cutit_000 on 14.02.2017.
 */

public class User implements Parcelable {
    int Id;
    String Username;
    String AvatarlUrl;
    Repository[] Repositories;

    /**
     * Ctor.
     */
    public User() {
        this.Id = 0;
        this.Username = null;
        this.AvatarlUrl = null;
    }

    public User(Parcel in) {
        Id = in.readInt();
        Username = in.readString();
        AvatarlUrl = in.readString();
        Repositories = in.createTypedArray(Repository.CREATOR);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setData(int id, String username, String avatarlUrl) {
        this.Id = id;
        this.Username = username;
        this.AvatarlUrl = avatarlUrl;
    }

    public void setRepositories(Repository[] repositories) {
        this.Repositories = repositories;
    }

    public void printData() {
        System.out.println(this.Id);
        System.out.println(this.Username);
        System.out.println(this.AvatarlUrl);
        for (int i = 0; i < Repositories.length; i++) {
            System.out.println((Repositories[i]).getName());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Username);
        dest.writeString(AvatarlUrl);
        dest.writeTypedArray(Repositories, flags);
    }

    public ArrayList getRepositoryList() {
        ArrayList<HashMap<String, String>> RepositoryList;
        RepositoryList = new ArrayList<>();
        for (int i = 0; i < Repositories.length; i++) {
            HashMap<String, String> repository = new HashMap<>();
            repository.put("name", Repositories[i].getName());
            System.out.print(Repositories[i].getName());
            RepositoryList.add(repository);
        }
        return RepositoryList;
    }

    public Repository getSpecifiedRepository(int i) {
        return Repositories[i];
    }

    public List<Repository> getRepositories() {
        List<Repository> repoList = new ArrayList<Repository>(Arrays.asList(this.Repositories));
        return repoList;
    }

}

