package fu.prm391.sample.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHOlder> {
    private List<String> list;
    private LayoutInflater inflater;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_row_layout, parent, false);
        MyViewHOlder viewHOlder = new MyViewHOlder(view);
        return viewHOlder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHOlder holder, int position) {
        holder.getTextView().setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
