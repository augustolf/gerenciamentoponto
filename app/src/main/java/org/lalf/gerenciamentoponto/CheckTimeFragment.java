package org.lalf.gerenciamentoponto;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;

import org.lalf.gerenciamentoponto.controller.Controller;

import java.util.List;

/**
 * Created by lalf on 12/29/2014.
 */
public class CheckTimeFragment extends Fragment {

    private Activity mActivity;
    private TimePickerFragment mDialog;
    private Controller mController;
    private RecordAdapter mAdapter;
    private List<Record> records;

    private MyChronometer mChronometer;
    private int clickCounter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mActivity = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.records);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        records = mController.getRecordsToDay();

        mAdapter = new RecordAdapter(getActivity());
        mAdapter.updateRecords(records);
        listView.setAdapter(mAdapter);

        if (mDialog == null) {
            mDialog = new TimePickerFragment();
            mDialog.setOnTimeSetListener(new TimePickerFragment.OnTimeSetListener() {
                @Override
                public void OnTimeSetListener(TimePicker view, int hourOfDay, int minute) {
                    mController.insertTimeCheck(hourOfDay, minute);
                    Log.d("Luiz", hourOfDay + ":" + minute);
                    records = mController.getRecordsToDay();
                    mAdapter.updateRecords(records);
                    clickCounter++;
                    mChronometer.update(records);
                    /*mChronometer.setMsElapsed(mController.getSpentTimeToDay());
                    if (clickCounter % 2 > 0) {
                        mChronometer.start();
                    } else {
                        mChronometer.stop();
                    }*/
                }
            });
        }

        mChronometer = (MyChronometer) rootView.findViewById(R.id.chronometer);
        //mChronometer.setFormat("%tH:%tM:%tS");

        int recordCount = mController.getRecordsToDay().size();

        Log.e("Luiz", "Tempo gasto: " + (mController.getSpentTimeToDay()/(60*1000)));

        mChronometer.update(records);
        /*mChronometer.setMsElapsed(mController.getSpentTimeToDay());
        if (recordCount > 0 && (recordCount % 2 > 0)) {
            mChronometer.start();
        }*/

        Button btn = (Button) rootView.findViewById(R.id.btn_check);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show(mActivity.getFragmentManager(), "TimePickerFragment");

            }
        });

        return rootView;
    }

    public void setController(Controller controller) {
        mController = controller;
    }
}
