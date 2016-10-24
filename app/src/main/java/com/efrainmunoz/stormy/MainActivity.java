package com.efrainmunoz.stormy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // api parameters
        String apiKey = "31aba2d82602cd0abe6741c852737177";
        double lat = 37.8267;
        double lng = -122.4233;
        String forecastURL = "https://api.darksky.net/forecast/"
                + apiKey +"/" + lat + "," + lng;

        // HTTP client
        OkHttpClient client = new OkHttpClient();

        // HTTP request
        Request request = new Request.Builder()
                .url(forecastURL)
                .build();

        // Send request
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        Log.v(TAG, response.body().string());
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception caught: ", e);
                }
            }
        });
    }
}
