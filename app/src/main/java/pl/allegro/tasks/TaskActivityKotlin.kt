package pl.allegro.tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TaskActivityKotlin : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val repo = TaskRepository(this)
            var input by remember { mutableStateOf("") }
            var tasks by remember { mutableStateOf(repo.loadTasks()) }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Lista zadań",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it }
                )

                Button(
                    onClick = {
                        val task = Task(input)
                        tasks = tasks + task
                        repo.saveTasks(tasks)
                        input = ""
                    }
                ) {
                    Text(text = "Dodaj")
                }

                LazyColumn() {
                    itemsIndexed(items = tasks) { index, task ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = task.isDone,
                                onCheckedChange = { checked ->
                                    tasks = tasks.toMutableList().also { it[index] = Task(task.text, checked) }
                                    repo.saveTasks(tasks)
                                }
                            )

                            Text(
                                text = task.text,
                                fontSize = 18.sp,
                                textDecoration = if (task.isDone) TextDecoration.LineThrough else null
                            )
                        }
                    }
                }
            }
        }
    }
}