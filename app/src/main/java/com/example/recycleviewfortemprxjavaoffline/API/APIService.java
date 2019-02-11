package com.example.recycleviewfortemprxjavaoffline.API;


import com.example.recycleviewfortemprxjavaoffline.Database.TempSensorData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface APIService {

    @GET("temp")
    Observable<List<TempSensorData>> getTempData();


//    @GET("profile")
//    Observable<Notification> getNotificationProfile();
}
