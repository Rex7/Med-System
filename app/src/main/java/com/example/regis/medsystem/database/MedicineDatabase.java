package com.example.regis.medsystem.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Medicine.class}, version = 1)
public abstract class MedicineDatabase extends RoomDatabase {
    private static MedicineDatabase Instance;

    public abstract medicineDoa medicineDoa();

    public static MedicineDatabase getInstance(Context context) {
        if (Instance == null) {
            Instance = Room.databaseBuilder(context.getApplicationContext(),
                    MedicineDatabase.class
                    , "myMedicine"


            ).allowMainThreadQueries()
                    .build();

        }
        return Instance;
    }

    public void destroyInstance() {
        Instance = null;
    }
}
