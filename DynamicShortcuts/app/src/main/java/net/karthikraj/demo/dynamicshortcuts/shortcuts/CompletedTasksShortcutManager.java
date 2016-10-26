package net.karthikraj.demo.dynamicshortcuts.shortcuts;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;

import net.karthikraj.demo.dynamicshortcuts.CompletedTasksActivity;
import net.karthikraj.demo.dynamicshortcuts.R;
import net.karthikraj.demo.dynamicshortcuts.TodoMainActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by karthik on 27/10/16.
 */

public class CompletedTasksShortcutManager {

    private static final String COMPLETED_SHORTCUT_ID = "completed_shortcut_id";
    private static final List<String> SHORTCUT_IDS = Arrays.asList(COMPLETED_SHORTCUT_ID);

    private CompletedTasksShortcutManager() { }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    public static void enableCompletedShortcut(@NonNull Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) return;
        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);

        Intent completedTasksIntent = new Intent(context, CompletedTasksActivity.class);
        completedTasksIntent.setAction(Intent.ACTION_VIEW);
        completedTasksIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ShortcutInfo postShortcut
                = new ShortcutInfo.Builder(context, COMPLETED_SHORTCUT_ID)
                .setShortLabel(context.getString(R.string.completed_tasks_shortcut_short_label))
                .setLongLabel(context.getString(R.string.completed_tasks_shortcut_long_label))
                .setDisabledMessage(context.getString(R.string.completed_tasks_shortcut_disabled_message))
                .setIcon(Icon.createWithResource(context, R.drawable.ic_completed))
                .setIntents(new Intent[]{
                        new Intent(Intent.ACTION_MAIN, Uri.EMPTY, context, TodoMainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),
                        completedTasksIntent
                })
                .build();
        shortcutManager.addDynamicShortcuts(Arrays.asList(postShortcut));
    }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    public static void disableCompletedShortcut(@NonNull Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) return;
        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
        shortcutManager.disableShortcuts(SHORTCUT_IDS);
    }
}
