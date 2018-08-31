package com.wuochoang.binarybot.ui.binary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuochoang.binarybot.R;
import com.wuochoang.binarybot.model.LogEntry;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HoangNQ on 13,August,2018
 */
public class ActivityLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LogEntry> logEntries;

    public ActivityLogAdapter(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_log, parent, false);
        return new EntryLogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EntryLogViewHolder) holder).bind(logEntries.get(position) , position);

    }

    @Override
    public int getItemCount() {
        return logEntries.size();
    }

    public class EntryLogViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtLogEntry)
        TextView txtLogEntry;

        public EntryLogViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(LogEntry log, int position) {
            txtLogEntry.setText(String.format("%d) %s", position + 1, log.getText()));
            txtLogEntry.setTextColor(log.isSuccess() ? itemView.getContext().getResources().getColor(R.color.green)
                            : itemView.getContext().getResources().getColor(R.color.red));
        }

    }
}
