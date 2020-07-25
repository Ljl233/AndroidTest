package com.example.asynctaskdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;
    Button start, end;
    MyAsyncTask asyncTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAG", "on create in" + Thread.currentThread().toString());

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progress_horizontal);
        start = findViewById(R.id.bt_start);
        end = findViewById(R.id.bt_stop);
        asyncTask = new MyAsyncTask();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    asyncTask.execute("start");
                } catch (IllegalStateException e) {
                    asyncTask.cancel(true);
                }
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asyncTask.cancel(true);
            }
        });

       // test();
    }

    void preLoading() {
        textView.setText("Loading");
    }

    void postLoading(String s) {
        textView.setText(s);
    }

    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] mParams;
    }


    private void test() {
        WorkerRunnable mWorker = new WorkerRunnable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };

    }


    class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            preLoading();
        }

        //接受参数，执行耗时操作，返回线程执行任务的结果
        @Override
        protected String doInBackground(String... strings) {

            Log.e("TAG", "do in background in" + Thread.currentThread().toString());
            try {
                int count = 0;
                while (count < 99) {
                    count++;
                    publishProgress(count);

                    Thread.sleep(50);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finish do in background";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            postLoading(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            String s = "loading" + values[0] + "%";
            textView.setText(s);
        }
    }
}
