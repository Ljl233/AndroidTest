package com.example.dialogfragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    private Button mButton, mButton2;

    private static final String DIALOG_DATE = "DialogDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mButton = findViewById(R.id.bt_show_dialog);
//        mButton.setOnClickListener(v -> {
//            FragmentManager fm = getSupportFragmentManager();
//            DatePickerFragment dialog = new DatePickerFragment();
//            dialog.show(fm, DIALOG_DATE);
//        });
//
//        mButton2 = findViewById(R.id.bt_show_fragment);
//        mButton2.setOnClickListener(v -> {
//            CrimeFragment f = new CrimeFragment();
//            FragmentManager fm = getSupportFragmentManager();
//            fm.beginTransaction().add(f, "crime fragment").commit();
//            Toast.makeText(this, "crime fragment", Toast.LENGTH_SHORT).show();
//        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        CrimeFragment fragment = new CrimeFragment();
        ft.replace(R.id.content, fragment)
                .commit();
    }
}
