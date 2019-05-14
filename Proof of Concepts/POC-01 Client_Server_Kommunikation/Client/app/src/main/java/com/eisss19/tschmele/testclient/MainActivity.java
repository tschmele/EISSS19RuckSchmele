package com.eisss19.tschmele.testclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView tv_hello;
    TestService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.TestButton);
        tv_hello  = findViewById(R.id.TestText);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://poc-01.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        service = retrofit.create(TestService.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<String> hello = service.helloWorld();
                hello.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> hello, Response<String> response) {
                        String hi = response.body();

                        tv_hello.setText(hi);
                    }

                    @Override
                    public void onFailure(Call<String> hello, Throwable t) {
                        Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
