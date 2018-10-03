package com.abande.todolistdemo;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TodoActivity extends AppCompatActivity implements View.OnLongClickListener{

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    TodoListViewModel todoListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        // Fetch the TO-do
        getTodoServer();


        //RecyclerView Formalities
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<TodoModel>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        todoListViewModel = ViewModelProviders.of(this)
                .get(TodoListViewModel.class);

        todoListViewModel.getTodoList().observe(this, new Observer<List<TodoModel>>() {
            @Override
            public void onChanged(@Nullable List<TodoModel> todoModels) {
                recyclerViewAdapter.addItems(todoModels);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });


    }

    private void getTodoServer() {
        //Retrofit builders
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        TodoApiEndpointInterface api = retrofit.create(TodoApiEndpointInterface.class);


        Call<List<TodoModel>> listCall = api.getAllTodo();
        listCall.enqueue(new Callback<List<TodoModel>>() {
            @Override
            public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                // Response Added to DB
                if (response.body() != null) {
                     todoListViewModel.addItems(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TodoModel>> call, Throwable t) {
                Log.d("All Failed ", "Nicely");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onLongClick(View view) {
        TodoModel borrowModel = (TodoModel) view.getTag();
        todoListViewModel.deleteItem(borrowModel);
        return true;
    }


}
