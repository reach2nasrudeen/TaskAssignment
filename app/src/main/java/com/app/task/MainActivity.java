package com.app.task;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ProgressBar mLoader;

    public Model mDataParser;

    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoader = (ProgressBar) findViewById(R.id.loader);
        listView = (ListView) findViewById(R.id.listView);
        setUpDefaults();
    }

    private void setUpDefaults() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        BaseService service = retrofit.create(BaseService.class);
        mLoader.setVisibility(View.VISIBLE);
        service.userDetails().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mLoader.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    try {
                        try {
                            JSONArray jsonArray = new JSONArray(response.body().string());
                            mDataParser = new Gson().fromJson("{\"users\":" + jsonArray.toString() + "}", Model.class);

                            adapter = new CustomAdapter(mDataParser.users, getApplicationContext());
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Model.User dataModel = mDataParser.users.get(position);

                                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                                    intent.putExtra("user", dataModel);
                                    startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mLoader.setVisibility(View.GONE);

            }
        });

    }
}
