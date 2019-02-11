package com.example.recycleviewfortemprxjavaoffline.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "sensor_data_table")
public class TempSensorData {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "row_id")
    private Integer id;


    @ColumnInfo(name = "location_table")
    private String location;

    @ColumnInfo(name = "temperature_table")
    private String tempValue;

    @ColumnInfo(name = "timestamp_table")
    private String timestamp;


    public TempSensorData(Integer id, String location, String tempValue, String timestamp) {
        this.id = id;
        this.location = location;
        this.tempValue = tempValue;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTempValue() {
        return tempValue;
    }

    public void setTempValue(String tempValue) {
        this.tempValue = tempValue;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
