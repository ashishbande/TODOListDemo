package com.abande.todolistdemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class TodoListViewModel extends AndroidViewModel {

    private List<TodoModel> todoListData;
    private LiveData<List<TodoModel>> todoList;


    public TodoListViewModel(Application application) {
        super(application);
        todoListData = new ArrayList<>();
        todoList = new MutableLiveData<>();
        ((MutableLiveData<List<TodoModel>>) todoList).setValue(todoListData);
   }


    public LiveData<List<TodoModel>> getTodoList() {
        return todoList;
    }

    public void addItem(TodoModel todoModel){
        if(todoModel != null) {
            todoListData.add(todoModel);
        }
    }

}

