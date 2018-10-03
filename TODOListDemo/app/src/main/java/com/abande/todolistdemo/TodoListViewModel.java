package com.abande.todolistdemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import android.os.AsyncTask;
import java.util.List;

public class TodoListViewModel extends AndroidViewModel {

    private LiveData<List<TodoModel>> todoList;

    private TodoDataBase todoDataBase;

    public TodoListViewModel(Application application) {
        super(application);
        todoDataBase = TodoDataBase.getDatabase(this.getApplication());
        todoList = todoDataBase.todoModel().getAllTodo();
   }


    public LiveData<List<TodoModel>> getTodoList() {
        return todoList;
    }


    public void deleteItem(TodoModel todoModel) {
        new deleteAsyncTask(todoDataBase).execute(todoModel);
    }

    public void addItems(List<TodoModel> todoModels) {
        new addAllTodoAsyncTask(todoDataBase).execute(todoModels);
    }


    private static class deleteAsyncTask extends AsyncTask<TodoModel, Void, Void> {

        private TodoDataBase db;

        deleteAsyncTask(TodoDataBase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final TodoModel... params) {
            db.todoModel().deleteTodo(params[0]);
            return null;
        }

    }


    private static class addAllTodoAsyncTask extends AsyncTask<List<TodoModel>, Void, Void> {

        private TodoDataBase db;

        addAllTodoAsyncTask(TodoDataBase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final List<TodoModel>... params) {
            if(params[0] != null){
                for (TodoModel model : params[0]){
                    db.todoModel().addTodo(model);
                }

            }

            return null;
        }

    }

}

