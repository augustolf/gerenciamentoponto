package org.lalf.gerenciamentoponto;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
    private RecordAdapter mAdapter;
    private TimeCounter mTimeCounter;
    private TextView mTextTimeCounter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mActivity = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.records);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mAdapter = new RecordAdapter(getActivity());
        mAdapter.updateRecords(mController.getRecordsToDay());
        listView.setAdapter(mAdapter);

        if (mDialog == null) {
            mDialog = new TimePickerFragment();
            mDialog.setOnTimeSetListener(new TimePickerFragment.OnTimeSetListener() {
                @Override
                public void OnTimeSetListener(TimePicker view, int hourOfDay, int minute) {
                    mController.insertTimeCheck(hourOfDay, minute);
                    System.out.println("Lista atualizada!");
                    mAdapter.updateRecords(mController.getRecordsToDay());
                }
            });
        }

        mTextTimeCounter = (TextView) rootView.findViewById(R.id.timeCounter);

        Button btn = (Button) rootView.findViewById(R.id.btn_check);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show(mActivity.getFragmentManager(), "TimePickerFragment");

                if (mTimeCounter == null) {
                    mTimeCounter = new TimeCounter();
                    mTimeCounter.setTimeCounterTickListener(new TimeCounter.TimeCounterTickListener() {
                        @Override
                        public void onTick(String time) {
                            mTextTimeCounter.setText(time);
                        }
                    });
                    mTimeCounter.start();
                }
            }
        });

        return rootView;
    }

    public void setController(Controller controller) {
        mController = controller;
    }
}
