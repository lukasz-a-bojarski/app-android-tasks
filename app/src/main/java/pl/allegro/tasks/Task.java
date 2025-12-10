package pl.allegro.tasks;

import java.util.UUID;

public class Task {
    private String id = UUID.randomUUID().toString();
    private String text;
    private boolean done = false;

    public Task(String text) {
        this.text = text;
    }

    public Task(String text, boolean done) {
        this.text = text;
        this.done = done;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void toggle() {
        this.done = !this.done;
    }
}
