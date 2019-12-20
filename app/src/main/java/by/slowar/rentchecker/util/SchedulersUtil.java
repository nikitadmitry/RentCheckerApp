package by.slowar.rentchecker.util;


import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SlowAR on 05.12.2019.
 */

public class SchedulersUtil {

    public Scheduler getIoScheduler() {
        return Schedulers.io();
    }

    public Scheduler getComputationScheduler() {
        return Schedulers.computation();
    }

    public Scheduler getNewThreadScheduler() {
        return Schedulers.newThread();
    }

    public Scheduler getTrampolineScheduler() {
        return Schedulers.trampoline();
    }

    public Scheduler getAndroidMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}