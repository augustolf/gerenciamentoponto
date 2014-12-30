package org.lalf.gerenciamentoponto;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import org.lalf.gerenciamentoponto.controller.Controller;

import java.util.Calendar;

/**
 * Created by lalf on 12/29/2014.
 */
public class CheckTimeFragment extends Fragment {

    private Activity mActivity;
    private TimePickerFragment mDialog;
    private Controller mController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mActivity = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button btn = (Button) rootView.findViewById(R.id.btn_check);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = new TimePickerFragment();
                mDialog.setController(mController);
                mDialog.show(mActivity.getFragmentManager(), "TimePickerFragment");
            }
        });

        return rootView;
    }

    public void setController(Controller controller) {
        mController = controller;
    }
}
