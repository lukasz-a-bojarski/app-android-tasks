package pl.allegro.tasks;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends ComponentActivity {

    private List<Task> tasks = new ArrayList<>();
    private TaskRepository taskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        taskRepository = new TaskRepository(this);
        tasks = taskRepository.loadTasks();

        EditText editText = findViewById(R.id.taskEditText);
        Button button = findViewById(R.id.addTaskButton);
        RecyclerView recyclerView = findViewById(R.id.tasksRecyclerView);
        TaskAdapter taskAdapter = new TaskAdapter(tasks, task -> {
            taskRepository.saveTasks(tasks);
        });

        button.setOnClickListener(view -> {
            Toast.makeText(this, editText.getText(), Toast.LENGTH_SHORT).show();
            Task task = new Task(editText.getText().toString());

            tasks.add(task);
            taskRepository.saveTasks(tasks);

            taskAdapter.notifyDataSetChanged();
            editText.setText("");
        });

        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}