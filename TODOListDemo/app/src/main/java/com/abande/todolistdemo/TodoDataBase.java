package com.abande.todolistdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {TodoModel.class}, version = 1)
public abstract class TodoDataBase extends RoomDatabase {

    private static TodoDataBase INSTANCE;

    public static TodoDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), TodoDataBase.class, "borrow_db")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract TodoDAO todoModel();

}
