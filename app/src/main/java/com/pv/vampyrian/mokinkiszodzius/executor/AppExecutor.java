package com.pv.vampyrian.mokinkiszodzius.executor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutor {

    private static final String LOG_TAG = AppExecutor.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static AppExecutor sInstance;
    private final Executor mDiskIO;
    private final Executor mMainThread;
    private final Executor mNetworkIO;

    private AppExecutor(Executor diskIO, Executor networkIO, Executor mainThread) {
        mDiskIO = diskIO;
        mMainThread = mainThread;
        mNetworkIO = networkIO;
    }

    public static AppExecutor getInstance (){

        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Sukuriam AppExecutor is "  + Thread.currentThread().getName());
                int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
                sInstance = new AppExecutor(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(NUMBER_OF_CORES),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor getDiskIO() {return mDiskIO;}

    public Executor getMainThread() {
        return mMainThread;
    }

    public Executor getNetworkIO() {
        return mNetworkIO;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
