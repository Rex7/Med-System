package com.example.regis.medsystem.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface medicineDoa {
    @Query("select * from medicine")
    List<Medicine> getAll();

    @Query("select * from medicine where medicine_name like :medicineName")
    Medicine getMed(String medicineName);

    @Insert
    void insertMedicine(Medicine... medicines);

    @Delete
    void deleteMedicine(Medicine medicines);

}
