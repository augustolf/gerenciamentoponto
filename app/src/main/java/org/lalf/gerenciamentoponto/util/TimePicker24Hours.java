package org.lalf.gerenciamentoponto.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TimePicker;

/**
 * Created by lalf on 12/30/2014.
 */
public class TimePicker24Hours extends TimePicker implements TimePicker.OnTimeChangedListener {
    @Override
    public void onTimeChanged(TimePicker timePicker, int i, int i2) {
        Log.d("Luiz", "Time picker onTimeChanged");
    }

    public TimePicker24Hours(Context context) {
        super(context);
        init();
    }

    public TimePicker24Hours(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimePicker24Hours(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setIs24HourView(true);
    }

}