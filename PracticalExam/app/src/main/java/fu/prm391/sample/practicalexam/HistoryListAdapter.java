package fu.prm391.sample.practicalexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> {

    Context context;
    List<History> histories;

    public HistoryListAdapter(Context context, List<History> histories) {
        this.context = context;
        this.histories = histories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.item_history, parent, false);
        HistoryListAdapter.ViewHolder viewHolder = new HistoryListAdapter.ViewHolder(productView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = histories.get(position);
        holder.date.setText(history.getDate());
        holder.number.setText(String.valueOf(history.getNumber()));
        holder.fromUnit.setText(history.getFromUnit());
        holder.toUnit.setText(history.getToUnit());
        holder.result.setText(String.valueOf(history.getResult()));
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, number, fromUnit, toUnit, result;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.txtDate);
            number = itemView.findViewById(R.id.txtNumber);
            fromUnit = itemView.findViewById(R.id.txtFromUnit);
            toUnit = itemView.findViewById(R.id.txtToUnit);
            result = itemView.findViewById(R.id.txtResultHistory);
        }
    }
}
