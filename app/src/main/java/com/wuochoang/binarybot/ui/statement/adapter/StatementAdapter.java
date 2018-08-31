package com.wuochoang.binarybot.ui.statement.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuochoang.binarybot.R;
import com.wuochoang.binarybot.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HoangNQ on 31,August,2018
 */
public class StatementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Transaction> transactionList;

    public StatementAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public void updateTransactionList(List<Transaction> transactionList) {
        this.transactionList.clear();
        this.transactionList = transactionList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TransactionViewHolder) holder).bind(transactionList.get(position));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTransaction)
        TextView txtTransaction;
        @BindView(R.id.txtPayout)
        TextView txtPayout;

        public TransactionViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Transaction transaction) {
            String time = new SimpleDateFormat("MMMM dd, EEEE", Locale.US)
                    .format(new Date(1000L * transaction.getTransactionTime()));
            String pair = "";
            if (transaction.getShortCode().contains("EURUSD")) {
                pair = "EURUSD";
            }
            if (transaction.getShortCode().contains("EURGBP")) {
                pair = "EURGBP";
            }
            if (transaction.getShortCode().contains("EURJPY")) {
                pair = "EURJPY";
            }
            if (transaction.getShortCode().contains("USDJPY")) {
                pair = "USDJPY";
            }
            if (transaction.getShortCode().contains("USDCAD")) {
                pair = "USDCAD";
            }
            if (transaction.getShortCode().contains("USDCHF")) {
                pair = "USDCHF";
            }
            if (transaction.getShortCode().contains("GBPUSD")) {
                pair = "GBPUSD";
            }
            txtTransaction.setText(String.format("%s %s", time, pair));
            txtPayout.setText(String.format("$%s", transaction.getAmount()));
            if (transaction.getAmount() > 0)
                txtPayout.setTextColor(itemView.getResources().getColor(R.color.green));
            else
                txtPayout.setTextColor(itemView.getResources().getColor(R.color.red));
        }

    }
}
