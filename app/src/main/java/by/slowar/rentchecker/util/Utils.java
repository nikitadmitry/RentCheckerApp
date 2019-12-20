package by.slowar.rentchecker.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by SlowAR on 14.12.2019.
 */

public class Utils {

    private static final String LOG_TAG = "utilslog";

    public static boolean isServiceWorking(Context context, Class<?> serviceClass) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = activityManager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServiceInfos) {
            if (serviceClass.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void log(String message) {
        Log.d(LOG_TAG, message);
    }
}
