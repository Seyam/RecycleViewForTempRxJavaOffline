package com.example.recycleviewfortemprxjavaoffline.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.recycleviewfortemprxjavaoffline.API.APIService;
import com.example.recycleviewfortemprxjavaoffline.API.RetrofitInstance;
import com.example.recycleviewfortemprxjavaoffline.Adapter.EmployeeAdapter;
import com.example.recycleviewfortemprxjavaoffline.Database.TempSensorData;
import com.example.recycleviewfortemprxjavaoffline.Database.TempSensorDataDatabase;
import com.example.recycleviewfortemprxjavaoffline.Helper.Utils;
import com.example.recycleviewfortemprxjavaoffline.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    APIService apiService;
    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;
    TempSensorDataDatabase dBService;

    //first we create a CompositeDisposable object which acts as a container for disposables (think Recycle Bin)
    // and add our Disposable to it in the onSubscribe() or onStop() method:
    private CompositeDisposable compositeDisposable = new CompositeDisposable();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*init API. We must first create an instance of the ApiService using the Retrofit object we get as returned from RetrofitInstance class.*/
        apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);
        //Create only a single instance of DB
        dBService = TempSensorDataDatabase.getInstance(MainActivity.this);

        if(Utils.isNetworkAvailable(getApplicationContext())){
            Log.e("Network"," Connected");
            Toast.makeText(this, "Connected to WiFi/Cellular", Toast.LENGTH_SHORT).show();
            makeNetworkRequestForTemp();

//            makeNetworkRequestForProfile();
        }
        else {
            Log.e("Network"," Not Connected");
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
//            fetchDataLocally();

        }

    }



    private void makeNetworkRequestForTemp() {
        Observable<List<TempSensorData>> myObservable= apiService.getTempData()
                .subscribeOn(Schedulers.io())//we told RxJava to do all the work on the background(io) thread
                .observeOn(AndroidSchedulers.mainThread());//When the work is done and our data is ready, observeOn() ensures that onNext() or onSuccess() or onError() or accept() are called on the main thread.
        myObservable.subscribe(new Observer<List<TempSensorData>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("onSubscribe ","called for     TempSensorData");
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<TempSensorData> tempSensorData) {
                Log.e("onNext ","called for     TempSensorData");
//                Toast.makeText(MainActivity.this, "OutledID: "+tempSensorData., Toast.LENGTH_SHORT).show();
                for 
                generateEmployeeList(tempSensorData);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError ","called for     TempSensorData");
            }

            @Override
            public void onComplete() {
                Log.e("onComplete ","called for     TempSensorData");
            }
        });


    }


    /*Method to generate List of employees using RecyclerView with custom adapter*/
    public void generateEmployeeList(List<TempSensorData> empDataList){

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_data_list);


        //create a new constructor of EmployeeAdapter class with required params
        adapter = new EmployeeAdapter(empDataList, this);
        //create a new constructor of LinearLayoutManager class with required params
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }



    @Override
    protected void onStop() {
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
        super.onStop();
    }



//    @Override
//    protected void onStop() {
//        compositeDisposable.clear();
//        super.onStop();
//    }


    //Another way to perform network request using Consumer class
//    private void fetchData() {
//        /*Make our http request by calling the service method to get the data*/
//        compositeDisposable.add(apiService.getTempData()
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new Consumer<TempSensorDataList>() {
//            @Override
//            public void accept(TempSensorDataList tempSensorDataList) throws Exception {
//                Toast.makeText(MainActivity.this, "OutledID: "+tempSensorDataList.getOutletId(), Toast.LENGTH_SHORT).show();
//                generateEmployeeList(tempSensorDataList.getTempSensorDataList());
//            }
//        }));
//    }

}
