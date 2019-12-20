package by.slowar.rentchecker.common;

import android.content.Context;

/**
 * Created by SlowAR on 04.12.2019.
 */
public abstract class BaseRouter {

    private Context context;

    public BaseRouter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
