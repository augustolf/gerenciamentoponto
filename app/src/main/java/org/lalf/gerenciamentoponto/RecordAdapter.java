package org.lalf.gerenciamentoponto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by lalf on 12/30/2014.
 */
public class RecordAdapter extends BaseAdapter {

    private List<Record> records = Collections.emptyList();

    private final Context context;

    public RecordAdapter(Context context) {
        this.context = context;
    }

    public void updateRecords(List<Record> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int position) {
        return records.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.record, parent, false);
        }

        TextView phoneView = ViewHolder.get(convertView, R.id.record);

        Record record = (Record) getItem (position);
        phoneView.setText(record.getTime());

        return convertView;
    }
}
