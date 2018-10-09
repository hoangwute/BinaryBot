package com.wuochoang.binarybot.ui.binary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuochoang.binarybot.R;
import com.wuochoang.binarybot.model.LogEntry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        ((EntryLogViewHolder) holder).bind(logEntries.get(position));

    }

    @Override
    public int getItemCount() {
        return logEntries.size();
    }

    public class EntryLogViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtPair)
        TextView txtPair;
        @BindView(R.id.txtTime)
        TextView txtTime;
        @BindView(R.id.txtResult)
        TextView txtResult;
        @BindView(R.id.txtAction)
        TextView txtAction;

        public EntryLogViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(LogEntry log) {
            txtTime.setText(log.getTime());
            if (log.isSuccess()) {
                txtResult.setVisibility(View.VISIBLE);
                txtAction.setVisibility(View.VISIBLE);
                txtPair.setText(log.getPair().substring(3, log.getPair().length()));
                txtResult.setText(log.getResult());
                txtAction.setText(log.getAction());
                if (log.getResult().equals("ITM"))
                    txtResult.setTextColor(itemView.getResources().getColor(R.color.colorGreenApp));
                else
                    txtResult.setTextColor(itemView.getResources().getColor(R.color.colorRed));
            }
            else {
                txtPair.setText(log.getPair());
                txtResult.setVisibility(View.GONE);
                txtAction.setVisibility(View.GONE);
            }
        }

    }
}
