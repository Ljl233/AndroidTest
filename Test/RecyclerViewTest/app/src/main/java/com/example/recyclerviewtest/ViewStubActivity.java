package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

public class ViewStubActivity extends AppCompatActivity implements View.OnClickListener {
    Button show, hide;
    ViewStub viewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);

        viewStub = findViewById(R.id.view_stub);
        show = findViewById(R.id.bt_show);
        hide = findViewById(R.id.bt_hide);
        show.setOnClickListener(this);
        hide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_show:
                try {
                    View view = viewStub.inflate();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    viewStub.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.bt_hide:
                viewStub.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
