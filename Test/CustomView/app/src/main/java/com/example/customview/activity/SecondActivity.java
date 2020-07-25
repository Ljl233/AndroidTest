package com.example.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.customview.R;

import java.lang.reflect.Method;

public class SecondActivity extends Activity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.menu_toolbar);

        mToolbar.setOnMenuItemClickListener(v -> {
            Toast.makeText(this, v.getTitle()
                    , Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }


}
