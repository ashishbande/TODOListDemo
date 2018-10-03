package com.abande.todolistdemo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TodoDAO {

    @Query("select * from TodoModel")
    LiveData<List<TodoModel>> getAllTodo();

    @Query("select * from TodoModel where id = :id")
    TodoModel getItemById(String id);

    @Insert(onConflict = REPLACE)
    void addTodo(TodoModel borrowModel);

    @Delete
    void deleteTodo(TodoModel borrowModel);

}
