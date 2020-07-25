package com.example.dialogfragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CrimeFragment extends Fragment {

    private Button mButton;
    private static final String TAG = "crime 2 dialog";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_crime, container, false);

        mButton = root.findViewById(R.id.bt_show_dialog_fg);
        mButton.setOnClickListener(v -> {
//            DatePickerFragment dialog = new DatePickerFragment();
//            SuccessDialog dialog = new SuccessDialog();
            SuccessDialog dialog = SuccessDialog.newInstance("您已成功解决问题");
            dialog.show(getFragmentManager(), TAG);
        });
        return root;
    }
}
