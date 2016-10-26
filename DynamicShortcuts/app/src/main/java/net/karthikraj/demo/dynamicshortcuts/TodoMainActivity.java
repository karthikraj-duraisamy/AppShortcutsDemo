package net.karthikraj.demo.dynamicshortcuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.karthikraj.demo.dynamicshortcuts.shortcuts.CompletedTasksShortcutManager;
import net.karthikraj.demo.dynamicshortcuts.shortcuts.InProgressTasksShortcutManager;
import net.karthikraj.demo.dynamicshortcuts.shortcuts.NewTaskShortcutManager;

public class TodoMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main);

        NewTaskShortcutManager.enableInProgressShortcut(this);
        CompletedTasksShortcutManager.enableCompletedShortcut(this);
        InProgressTasksShortcutManager.enableInProgressShortcut(this);
    }
}
