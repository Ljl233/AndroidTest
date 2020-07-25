package com.ljl.sharedprenferencestest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Config config = new Config(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);

        Button store = findViewById(R.id.store);
        Button show = findViewById(R.id.show);

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                config.setUsername(name);

                String psw = password.getText().toString();
                config.setPassword(psw);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = config.getUsername();
                String psw = config.getPassword();
                Toast.makeText(MainActivity.this, "name:" + name + ",psw=" + psw, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
