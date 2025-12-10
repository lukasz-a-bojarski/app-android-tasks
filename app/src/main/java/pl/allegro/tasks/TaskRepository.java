package pl.allegro.tasks;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();

    public TaskRepository(Context context) {
        this.sharedPreferences = context.getSharedPreferences("tasks_prefs", Context.MODE_PRIVATE);
    }

    public void saveTasks(List<Task> tasks) {
        String json = gson.toJson(tasks);
        sharedPreferences.edit().putString("tasks_json", json).apply();
    }

    public List<Task> loadTasks() {
        String json = sharedPreferences.getString("tasks_json", null);
        if (json == null) return new ArrayList<>();

        Type listType = new TypeToken<List<Task>>(){}.getType();
        return gson.fromJson(json, listType);
    }
}
