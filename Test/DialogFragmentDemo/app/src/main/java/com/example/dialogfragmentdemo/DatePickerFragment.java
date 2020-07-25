package com.example.dialogfragmentdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

            return new AlertDialog.Builder(getActivity())
                    .setView(v)
                    .setPositiveButton(android.R.string.ok, null)
                    .create();

    }
    /**
     * Android 有三种对话框的按钮： 如果同一个对话框有多个按钮，按钮类型和命名决定这它们在对话框上显示的位置
     * 1. positive  接受
     * 2. negative  取消
     * 3. neutral   中立
     */
}
