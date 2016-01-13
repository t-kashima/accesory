package com.unuuu.android.accessory

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.util.*

public class MyActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    private val lifecycleExecutors = ArrayList<LifecycleExecutor>()

    private var runningCount = 0;

    public fun addLifecycleExecutor(executor : LifecycleExecutor) {
        lifecycleExecutors.add(executor);
    }

    public fun removeLifecycleExecutor(executor: LifecycleExecutor) {
        if (lifecycleExecutors.contains(executor)) {
           lifecycleExecutors.remove(executor)
        }
    }

    override fun onActivityCreated(activity : Activity, bundle : Bundle?) {
    }

    override fun onActivityStarted(activity : Activity) {
        runningCount += 1;
        if (runningCount == 1) {
            for (executor in lifecycleExecutors) {
                executor.onForeground()
            }
        }
    }

    override fun onActivityResumed(activity : Activity) {
    }

    override fun onActivityPaused(activity : Activity) {

    }

    override fun onActivityStopped(activity : Activity) {
        runningCount -= 1;
        if (runningCount == 0) {
            for (executor in lifecycleExecutors) {
                executor.onBackground()
            }
        }
    }

    override fun onActivitySaveInstanceState(activity : Activity, bundle : Bundle?) {
    }

    override fun onActivityDestroyed(activity : Activity) {
    }

    public interface LifecycleExecutor {
        public fun onForeground()
        public fun onBackground()
    }
}


