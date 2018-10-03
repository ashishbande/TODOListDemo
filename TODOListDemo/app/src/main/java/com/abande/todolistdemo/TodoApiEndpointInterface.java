package com.abande.todolistdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TodoApiEndpointInterface {

    @GET("/todos/")
    Call<List<TodoModel>> getAllTodo();

}
