package org.lalf.gerenciamentoponto;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;

import org.lalf.gerenciamentoponto.controller.Controller;
import org.lalf.gerenciamentoponto.controller.WorkingTimeController;

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
    private WorkingTimeController mWorkingTimeController;

    private MyChronometer mChronometer;

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
                    records = mController.getRecordsToDay();
                    mAdapter.updateRecords(records);
                    mChronometer.update(records);
                }
            });
        }

        mChronometer = (MyChronometer) rootView.findViewById(R.id.chronometer);

        mChronometer.update(records);

        Button btn = (Button) rootView.findViewById(R.id.btn_check);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show(mActivity.getFragmentManager(), "TimePickerFragment");
            }
        });

        mWorkingTimeController = new WorkingTimeController(getActivity(), records);
        mWorkingTimeController.check();

        return rootView;
    }

    public void setController(Controller controller) {
        mController = controller;
    }
}
