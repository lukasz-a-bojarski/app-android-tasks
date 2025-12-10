package pl.allegro.tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> tasks;
    private OnItemClickListener listener;

    public TaskAdapter(List<Task> tasks, OnItemClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.checkBox.setText(task.getText());
        holder.checkBox.setChecked(task.isDone());

        if (task.isDone()) {
            holder.checkBox.setPaintFlags(holder.checkBox.getPaintFlags() | android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.checkBox.setPaintFlags(holder.checkBox.getPaintFlags() & ~android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.checkBox.setOnClickListener(view -> {
            task.toggle();
            listener.onItemClick(task);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkboxTask);
        }
    }

    interface OnItemClickListener {
        void onItemClick(Task task);
    }
}
