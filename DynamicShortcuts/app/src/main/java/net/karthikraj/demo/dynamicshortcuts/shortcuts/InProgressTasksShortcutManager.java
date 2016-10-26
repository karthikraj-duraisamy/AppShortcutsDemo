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

import net.karthikraj.demo.dynamicshortcuts.InProgressTasksActivity;
import net.karthikraj.demo.dynamicshortcuts.R;
import net.karthikraj.demo.dynamicshortcuts.TodoMainActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by karthik on 27/10/16.
 */

public class InProgressTasksShortcutManager {

    private static final String IN_PROGRESS_SHORTCUT_ID = "inprogress_shortcut_id";
    private static final List<String> SHORTCUT_IDS = Arrays.asList(IN_PROGRESS_SHORTCUT_ID);

    private InProgressTasksShortcutManager() { }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    public static void enableInProgressShortcut(@NonNull Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) return;
        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);

        Intent inProgressIntent = new Intent(context, InProgressTasksActivity.class);
        inProgressIntent.setAction(Intent.ACTION_VIEW);
        inProgressIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ShortcutInfo postShortcut
                = new ShortcutInfo.Builder(context, IN_PROGRESS_SHORTCUT_ID)
                .setShortLabel(context.getString(R.string.inprogress_tasks_shortcut_short_label))
                .setLongLabel(context.getString(R.string.inprogress_tasks_shortcut_long_label))
                .setDisabledMessage(context.getString(R.string.inprogress_tasks_shortcut_disabled_message))
                .setIcon(Icon.createWithResource(context, R.drawable.ic_in_progress))
                .setIntents(new Intent[]{
                        new Intent(Intent.ACTION_MAIN, Uri.EMPTY, context, TodoMainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),
                        inProgressIntent
                })
                .build();
        shortcutManager.addDynamicShortcuts(Arrays.asList(postShortcut));
    }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    public static void disableInProgressShortcut(@NonNull Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) return;
        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
        shortcutManager.disableShortcuts(SHORTCUT_IDS);
    }

}
