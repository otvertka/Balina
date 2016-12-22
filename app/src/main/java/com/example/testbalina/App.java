package com.example.testbalina;

import android.app.Application;
import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class App extends Application {

    private static final OkHttpClient CLIENT = new OkHttpClient();

    private final String URL ="http://ufa.farfor.ru/";

    private static Ufa inf;
    private Retrofit retrofit;

    private static final String TAG = "myLogs";


    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(CLIENT)
                .build();
        inf = retrofit.create(Ufa.class); //Создаем объект, при помощи которого будем выполнять запросы
        Log.d(TAG, "APP ВЫПОЛНИЛОСЬ!!!!!");

    }

    public static Ufa getInf() {
        return inf;
    }
}