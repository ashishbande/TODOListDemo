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


        //RecyclerView Formalities
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<TodoModel>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        todoListViewModel = ViewModelProviders.of(this)
                .get(TodoListViewModel.class);

        todoListViewModel.addItem(new TodoModel(1,102,"Test Element", true));

        todoListViewModel.getTodoList().observe(this, new Observer<List<TodoModel>>() {
            @Override
            public void onChanged(@Nullable List<TodoModel> todoModels) {
                recyclerViewAdapter.addItems(todoModels);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });


    }



    @Override
    public boolean onLongClick(View view) {
        return true;
    }


}
