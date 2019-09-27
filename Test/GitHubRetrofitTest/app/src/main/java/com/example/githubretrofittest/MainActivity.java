package com.example.githubretrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                GitHubService service = retrofit.create(GitHubService.class);

                Call<List<Repo>> repos = service.listRepos("octocat");

                repos.enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        String s = response.body().get(1).getArchive_url();

                        textView.setText(s);
                        Log.e("MainActivity.Message", "i am coming!");
                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {
                        Log.e("MainActivity.Massage", t.getMessage());
                    }
                });


            }
        });


    }
}