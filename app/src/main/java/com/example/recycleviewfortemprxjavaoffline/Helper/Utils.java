package com.example.recycleviewfortemprxjavaoffline.Helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
    public static boolean isNetworkAvailable(Context context){
        boolean isNetworkAvailable;
        boolean isWiFiConnected;
        boolean isMobileNetConnected;
        ConnectivityManager myConManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeInfo = myConManager.getActiveNetworkInfo();
        if(activeInfo != null && activeInfo.isConnected()){//so connected with either wifi or cellular
            isNetworkAvailable = true;
            isWiFiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            isMobileNetConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;

//            if(isWiFiConnected){
//                Log.e("Connectivity ","WiFi");
//                Toast.makeText(context, "WiFi connected", Toast.LENGTH_SHORT).show();
//
//            }
//            else if(isMobileNetConnected){
//                Log.e("Connectivity ","Cellular");
//                Toast.makeText(context, "Cellular data connected", Toast.LENGTH_SHORT).show();
//            }


        }
        else {//No internet connection
            isNetworkAvailable = false;
//            Log.e("Connectivity ","None!");
//            Toast.makeText(context, "No Internet!", Toast.LENGTH_SHORT).show();
        }

        return isNetworkAvailable;

    }
}
