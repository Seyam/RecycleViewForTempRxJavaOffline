package com.example.recycleviewfortemprxjavaoffline.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface TempSensorDataDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TempSensorData tempSensorData);

    @Query("SELECT * FROM sensor_data_table")
    Flowable<List<TempSensorData>> fetchAll();  //could be a list of  NotificationResponse if many rows in the table.


    @Query("SELECT * FROM sensor_data_table WHERE row_id =:taskId")
    Single<TempSensorData> getById(int taskId);


    @Update
    void updateTask(List<TempSensorData> allListData);


    @Delete
    void deleteTask(List<TempSensorData> allListData);
}
