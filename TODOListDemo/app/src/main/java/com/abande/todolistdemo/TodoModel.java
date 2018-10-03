package com.abande.todolistdemo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity
public class TodoModel {

    public TodoModel(int id, int userID, String description, boolean status) {
        this.id = id;
        this.userID = userID;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return status;
    }

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    public int id;
    @SerializedName("userId")
    public int userID;
    @SerializedName("title")
    private String description;
    @SerializedName("completed")
    private boolean status;


}