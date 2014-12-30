package org.lalf.gerenciamentoponto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import org.lalf.gerenciamentoponto.controller.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lalf on 12/29/2014.
 */
public class CheckDialogFragment extends DialogFragment {

    private TimePicker mTimePicker;
    private Controller mController;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.check_time_dialog, null);
        mTimePicker = (TimePicker) view.findViewById(R.id.timePicker);

        builder.setView(inflater.inflate(R.layout.check_time_dialog, null))
                .setTitle(R.string.put_your_time)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                         mController.insertTimeCheck(mTimePicker.getCurrentHour(), mTimePicker.getCurrentMinute());
                        //String day = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(mTimePicker.getDrawingTime()));
                        //System.out.println("---------------------> " + mTimePicker.getDrawingTime());
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    /*private TimePickerDialog.OnTimeSetListener timePickerListener =  new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            mTimePicker.setCurrentHour(selectedHour);
            mTimePicker.setCurrentMinute(selectedMinute);
        }
    };*/

    public void setController(Controller controller) {
        mController = controller;
    }
}