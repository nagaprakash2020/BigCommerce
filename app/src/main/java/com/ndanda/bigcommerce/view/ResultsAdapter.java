package com.ndanda.bigcommerce.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ndanda.bigcommerce.R;
import com.ndanda.bigcommerce.data.events;
import com.ndanda.bigcommerce.databinding.ResultsRowBinding;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {

    private List<events> eventsList;
    private Context context;
    private ResultsClickListener resultsClickListener;

    public interface ResultsClickListener{
        void onResultItemClicked(events event);
    }

    public ResultsAdapter(@NonNull Context context,ResultsClickListener resultsClickListener) {
        this.context = context;
        this.resultsClickListener = resultsClickListener;
    }

    /**
     * Call this method to update the data in adapter.
     * @param eventsList list of events to be displayed.
     */
    public void setEventsList(List<events> eventsList){
        this.eventsList = eventsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResultsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.results_row, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {
        ResultsRowBinding resultsRowBinding = holder.resultsRowBinding;

        // Set binding for the row.
        resultsRowBinding.setEvent(eventsList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventsList == null || eventsList.isEmpty() ? 0 : eventsList.size();
    }

    public class ResultsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ResultsRowBinding resultsRowBinding;

        public ResultsViewHolder(ResultsRowBinding resultsRowBinding) {
            super(resultsRowBinding.getRoot());
            this.resultsRowBinding = resultsRowBinding;
            resultsRowBinding.getRoot().setOnClickListener(this);
        }

        public void setEvent(events event){
            resultsRowBinding.setEvent(event);
        }

        @Override
        public void onClick(View v) {
            // On event selected.
            resultsClickListener.onResultItemClicked(eventsList.get(getAdapterPosition()));
        }
    }
}
